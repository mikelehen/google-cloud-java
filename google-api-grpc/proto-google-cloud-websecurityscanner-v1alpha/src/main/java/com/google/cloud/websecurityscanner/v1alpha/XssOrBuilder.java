// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/websecurityscanner/v1alpha/finding_addon.proto

package com.google.cloud.websecurityscanner.v1alpha;

public interface XssOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.websecurityscanner.v1alpha.Xss)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Stack traces leading to the point where the XSS occurred.
   * </pre>
   *
   * <code>repeated string stack_traces = 1;</code>
   */
  java.util.List<java.lang.String>
      getStackTracesList();
  /**
   * <pre>
   * Stack traces leading to the point where the XSS occurred.
   * </pre>
   *
   * <code>repeated string stack_traces = 1;</code>
   */
  int getStackTracesCount();
  /**
   * <pre>
   * Stack traces leading to the point where the XSS occurred.
   * </pre>
   *
   * <code>repeated string stack_traces = 1;</code>
   */
  java.lang.String getStackTraces(int index);
  /**
   * <pre>
   * Stack traces leading to the point where the XSS occurred.
   * </pre>
   *
   * <code>repeated string stack_traces = 1;</code>
   */
  com.google.protobuf.ByteString
      getStackTracesBytes(int index);

  /**
   * <pre>
   * An error message generated by a javascript breakage.
   * </pre>
   *
   * <code>string error_message = 2;</code>
   */
  java.lang.String getErrorMessage();
  /**
   * <pre>
   * An error message generated by a javascript breakage.
   * </pre>
   *
   * <code>string error_message = 2;</code>
   */
  com.google.protobuf.ByteString
      getErrorMessageBytes();
}
