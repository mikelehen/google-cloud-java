// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/datastore/v1/entity.proto

package com.google.datastore.v1;

public interface ValueOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.datastore.v1.Value)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * A null value.
   * </pre>
   *
   * <code>.google.protobuf.NullValue null_value = 11;</code>
   */
  int getNullValueValue();
  /**
   *
   *
   * <pre>
   * A null value.
   * </pre>
   *
   * <code>.google.protobuf.NullValue null_value = 11;</code>
   */
  com.google.protobuf.NullValue getNullValue();

  /**
   *
   *
   * <pre>
   * A boolean value.
   * </pre>
   *
   * <code>bool boolean_value = 1;</code>
   */
  boolean getBooleanValue();

  /**
   *
   *
   * <pre>
   * An integer value.
   * </pre>
   *
   * <code>int64 integer_value = 2;</code>
   */
  long getIntegerValue();

  /**
   *
   *
   * <pre>
   * A double value.
   * </pre>
   *
   * <code>double double_value = 3;</code>
   */
  double getDoubleValue();

  /**
   *
   *
   * <pre>
   * A timestamp value.
   * When stored in the Datastore, precise only to microseconds;
   * any additional precision is rounded down.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp timestamp_value = 10;</code>
   */
  boolean hasTimestampValue();
  /**
   *
   *
   * <pre>
   * A timestamp value.
   * When stored in the Datastore, precise only to microseconds;
   * any additional precision is rounded down.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp timestamp_value = 10;</code>
   */
  com.google.protobuf.Timestamp getTimestampValue();
  /**
   *
   *
   * <pre>
   * A timestamp value.
   * When stored in the Datastore, precise only to microseconds;
   * any additional precision is rounded down.
   * </pre>
   *
   * <code>.google.protobuf.Timestamp timestamp_value = 10;</code>
   */
  com.google.protobuf.TimestampOrBuilder getTimestampValueOrBuilder();

  /**
   *
   *
   * <pre>
   * A key value.
   * </pre>
   *
   * <code>.google.datastore.v1.Key key_value = 5;</code>
   */
  boolean hasKeyValue();
  /**
   *
   *
   * <pre>
   * A key value.
   * </pre>
   *
   * <code>.google.datastore.v1.Key key_value = 5;</code>
   */
  com.google.datastore.v1.Key getKeyValue();
  /**
   *
   *
   * <pre>
   * A key value.
   * </pre>
   *
   * <code>.google.datastore.v1.Key key_value = 5;</code>
   */
  com.google.datastore.v1.KeyOrBuilder getKeyValueOrBuilder();

  /**
   *
   *
   * <pre>
   * A UTF-8 encoded string value.
   * When `exclude_from_indexes` is false (it is indexed) , may have at most 1500 bytes.
   * Otherwise, may be set to at least 1,000,000 bytes.
   * </pre>
   *
   * <code>string string_value = 17;</code>
   */
  java.lang.String getStringValue();
  /**
   *
   *
   * <pre>
   * A UTF-8 encoded string value.
   * When `exclude_from_indexes` is false (it is indexed) , may have at most 1500 bytes.
   * Otherwise, may be set to at least 1,000,000 bytes.
   * </pre>
   *
   * <code>string string_value = 17;</code>
   */
  com.google.protobuf.ByteString getStringValueBytes();

  /**
   *
   *
   * <pre>
   * A blob value.
   * May have at most 1,000,000 bytes.
   * When `exclude_from_indexes` is false, may have at most 1500 bytes.
   * In JSON requests, must be base64-encoded.
   * </pre>
   *
   * <code>bytes blob_value = 18;</code>
   */
  com.google.protobuf.ByteString getBlobValue();

  /**
   *
   *
   * <pre>
   * A geo point value representing a point on the surface of Earth.
   * </pre>
   *
   * <code>.google.type.LatLng geo_point_value = 8;</code>
   */
  boolean hasGeoPointValue();
  /**
   *
   *
   * <pre>
   * A geo point value representing a point on the surface of Earth.
   * </pre>
   *
   * <code>.google.type.LatLng geo_point_value = 8;</code>
   */
  com.google.type.LatLng getGeoPointValue();
  /**
   *
   *
   * <pre>
   * A geo point value representing a point on the surface of Earth.
   * </pre>
   *
   * <code>.google.type.LatLng geo_point_value = 8;</code>
   */
  com.google.type.LatLngOrBuilder getGeoPointValueOrBuilder();

  /**
   *
   *
   * <pre>
   * An entity value.
   * - May have no key.
   * - May have a key with an incomplete key path.
   * - May have a reserved/read-only key.
   * </pre>
   *
   * <code>.google.datastore.v1.Entity entity_value = 6;</code>
   */
  boolean hasEntityValue();
  /**
   *
   *
   * <pre>
   * An entity value.
   * - May have no key.
   * - May have a key with an incomplete key path.
   * - May have a reserved/read-only key.
   * </pre>
   *
   * <code>.google.datastore.v1.Entity entity_value = 6;</code>
   */
  com.google.datastore.v1.Entity getEntityValue();
  /**
   *
   *
   * <pre>
   * An entity value.
   * - May have no key.
   * - May have a key with an incomplete key path.
   * - May have a reserved/read-only key.
   * </pre>
   *
   * <code>.google.datastore.v1.Entity entity_value = 6;</code>
   */
  com.google.datastore.v1.EntityOrBuilder getEntityValueOrBuilder();

  /**
   *
   *
   * <pre>
   * An array value.
   * Cannot contain another array value.
   * A `Value` instance that sets field `array_value` must not set fields
   * `meaning` or `exclude_from_indexes`.
   * </pre>
   *
   * <code>.google.datastore.v1.ArrayValue array_value = 9;</code>
   */
  boolean hasArrayValue();
  /**
   *
   *
   * <pre>
   * An array value.
   * Cannot contain another array value.
   * A `Value` instance that sets field `array_value` must not set fields
   * `meaning` or `exclude_from_indexes`.
   * </pre>
   *
   * <code>.google.datastore.v1.ArrayValue array_value = 9;</code>
   */
  com.google.datastore.v1.ArrayValue getArrayValue();
  /**
   *
   *
   * <pre>
   * An array value.
   * Cannot contain another array value.
   * A `Value` instance that sets field `array_value` must not set fields
   * `meaning` or `exclude_from_indexes`.
   * </pre>
   *
   * <code>.google.datastore.v1.ArrayValue array_value = 9;</code>
   */
  com.google.datastore.v1.ArrayValueOrBuilder getArrayValueOrBuilder();

  /**
   *
   *
   * <pre>
   * The `meaning` field should only be populated for backwards compatibility.
   * </pre>
   *
   * <code>int32 meaning = 14;</code>
   */
  int getMeaning();

  /**
   *
   *
   * <pre>
   * If the value should be excluded from all indexes including those defined
   * explicitly.
   * </pre>
   *
   * <code>bool exclude_from_indexes = 19;</code>
   */
  boolean getExcludeFromIndexes();

  public com.google.datastore.v1.Value.ValueTypeCase getValueTypeCase();
}
