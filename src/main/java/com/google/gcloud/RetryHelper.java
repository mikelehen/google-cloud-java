package com.google.gcloud;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Stopwatch;

import java.io.InterruptedIOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

/**
 * Utility class for retrying operations. For more details about the parameters, see
 * {@link RetryParams}. If the request is never successful, a {@link RetriesExhaustedException} will
 * be thrown.
 *
 * @param <V> return value of the closure that is being run with retries
 */
public class RetryHelper<V> {

  private static final Logger log = Logger.getLogger(RetryHelper.class.getName());

  private final Stopwatch stopwatch;
  private final Callable<V> callable;
  private final RetryParams params;
  private final ExceptionHandler exceptionHandler;
  private int attemptNumber;


  private static final ThreadLocal<Context> context = new ThreadLocal<>();

  public static class RetryHelperException extends RuntimeException {

    private static final long serialVersionUID = -2907061015610448235L;

    RetryHelperException() {}

    RetryHelperException(String message) {
      super(message);
    }

    RetryHelperException(Throwable cause) {
      super(cause);
    }

    RetryHelperException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  /**
   * Thrown when a RetryHelper failed to complete its work due to interruption. Throwing this
   * exception also sets the thread interrupt flag.
   */
  public static final class RetryInterruptedException extends RetryHelperException {

    private static final long serialVersionUID = 1678966737697204885L;

    RetryInterruptedException() {}

    /**
     * Sets the caller thread interrupt flag and throws {@code RetryInteruptedException}.
     */
    public static void propagate() throws RetryInterruptedException {
      Thread.currentThread().interrupt();
      throw new RetryInterruptedException();
    }
  }

  /**
   * Thrown when a RetryHelper has attempted the maximum number of attempts allowed by RetryParams
   * and was not successful.
   */
  public static final class RetriesExhaustedException extends RetryHelperException {

    private static final long serialVersionUID = 780199686075408083L;

    RetriesExhaustedException(String message) {
      super(message);
    }

    RetriesExhaustedException(String message, Throwable cause) {
      super(message, cause);
    }
  }

  /**
   * Thrown when RetryHelper callable has indicate it should not be retried.
   */
  public static final class NonRetriableException extends RetryHelperException {

    private static final long serialVersionUID = -2331878521983499652L;

    NonRetriableException(Throwable ex) {
      super(ex);
    }
  }

  static class Context {

    private final RetryHelper<?> helper;

    Context(RetryHelper<?> helper) {
      this.helper = helper;
    }

    public RetryParams getRetryParams() {
      return helper.params;
    }

    public int getAttemptNumber() {
      return helper.attemptNumber;
    }
  }

  @VisibleForTesting
  static final void setContext(Context ctx) {
    if (ctx == null) {
      context.remove();
    } else {
      context.set(ctx);
    }
  }

  static final Context getContext() {
    return context.get();
  }

  @VisibleForTesting
  RetryHelper(Callable<V> callable, RetryParams params, ExceptionHandler exceptionHandler,
      Stopwatch stopwatch) {
    this.callable = checkNotNull(callable);
    this.params = checkNotNull(params);
    this.stopwatch = checkNotNull(stopwatch);
    this.exceptionHandler = checkNotNull(exceptionHandler);
    exceptionHandler.verifyCaller(callable);
  }

  @Override
  public String toString() {
    ToStringHelper toStringHelper = MoreObjects.toStringHelper(this);
    toStringHelper.add("stopwatch", stopwatch);
    toStringHelper.add("attempNumber", attemptNumber);
    toStringHelper.add("callable", callable);
    return toStringHelper.toString();
  }

  private V doRetry() throws RetryHelperException {
    stopwatch.start();
    while (true) {
      attemptNumber++;
      Exception exception;
      try {
        V value = callable.call();
        if (attemptNumber > 1) {
          log.fine(this + ": attempt #" + attemptNumber + " succeeded");
        }
        return value;
      } catch (Exception e) {
        if (!exceptionHandler.shouldRetry(e)) {
          if (e instanceof InterruptedException || e instanceof InterruptedIOException
              || e instanceof ClosedByInterruptException) {
            RetryInterruptedException.propagate();
          }
          throw new NonRetriableException(e);
        }
        exception = e;
      }
      if (attemptNumber >= params.getRetryMaxAttempts()
          || attemptNumber >= params.getRetryMinAttempts()
          && stopwatch.elapsed(MILLISECONDS) >= params.getTotalRetryPeriodMillis()) {
        throw new RetriesExhaustedException(this + ": Too many failures, giving up", exception);
      }
      long sleepDurationMillis = getSleepDuration(params, attemptNumber);
      log.fine(this + ": Attempt #" + attemptNumber + " failed [" + exception + "], sleeping for "
          + sleepDurationMillis + " ms");
      try {
        Thread.sleep(sleepDurationMillis);
      } catch (InterruptedException e) {
        RetryInterruptedException.propagate();
      }
    }
  }

  @VisibleForTesting
  static long getSleepDuration(RetryParams retryParams, int attemptsSoFar) {
    long initialDelay = retryParams.getInitialRetryDelayMillis();
    double backoffFactor = retryParams.getRetryDelayBackoffFactor();
    long maxDelay = retryParams.getMaxRetryDelayMillis();
    long retryDelay = getExponentialValue(initialDelay, backoffFactor, maxDelay, attemptsSoFar);
    return (long) ((random() / 2.0 + .75) * retryDelay);
  }

  private static long getExponentialValue(long initialDelay, double backoffFactor, long maxDelay,
      int attemptsSoFar) {
    return (long) min(maxDelay, pow(backoffFactor, max(1, attemptsSoFar) - 1) * initialDelay);
  }

  public static <V> V runWithRetries(Callable<V> callable) throws RetryHelperException {
    return runWithRetries(callable, RetryParams.getDefaultInstance(),
        ExceptionHandler.getDefaultInstance());
  }

  public static <V> V runWithRetries(Callable<V> callable, RetryParams params,
      ExceptionHandler exceptionHandler) throws RetryHelperException {
    return runWithRetries(callable, params, exceptionHandler, Stopwatch.createUnstarted());
  }

  @VisibleForTesting
  static <V> V runWithRetries(Callable<V> callable, RetryParams params,
      ExceptionHandler exceptionHandler, Stopwatch stopwatch) throws RetryHelperException {
    RetryHelper<V> retryHelper = new RetryHelper<>(callable, params, exceptionHandler, stopwatch);
    Context previousContext = getContext();
    setContext(new Context(retryHelper));
    try {
      return retryHelper.doRetry();
    } finally {
      setContext(previousContext);
    }
  }
}
