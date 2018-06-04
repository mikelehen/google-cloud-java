// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/cloud/websecurityscanner/v1alpha/web_security_scanner.proto

package com.google.cloud.websecurityscanner.v1alpha;

public interface ListFindingTypeStatsResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.cloud.websecurityscanner.v1alpha.ListFindingTypeStatsResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The list of FindingTypeStats returned.
   * </pre>
   *
   * <code>repeated .google.cloud.websecurityscanner.v1alpha.FindingTypeStats finding_type_stats = 1;</code>
   */
  java.util.List<com.google.cloud.websecurityscanner.v1alpha.FindingTypeStats> 
      getFindingTypeStatsList();
  /**
   * <pre>
   * The list of FindingTypeStats returned.
   * </pre>
   *
   * <code>repeated .google.cloud.websecurityscanner.v1alpha.FindingTypeStats finding_type_stats = 1;</code>
   */
  com.google.cloud.websecurityscanner.v1alpha.FindingTypeStats getFindingTypeStats(int index);
  /**
   * <pre>
   * The list of FindingTypeStats returned.
   * </pre>
   *
   * <code>repeated .google.cloud.websecurityscanner.v1alpha.FindingTypeStats finding_type_stats = 1;</code>
   */
  int getFindingTypeStatsCount();
  /**
   * <pre>
   * The list of FindingTypeStats returned.
   * </pre>
   *
   * <code>repeated .google.cloud.websecurityscanner.v1alpha.FindingTypeStats finding_type_stats = 1;</code>
   */
  java.util.List<? extends com.google.cloud.websecurityscanner.v1alpha.FindingTypeStatsOrBuilder> 
      getFindingTypeStatsOrBuilderList();
  /**
   * <pre>
   * The list of FindingTypeStats returned.
   * </pre>
   *
   * <code>repeated .google.cloud.websecurityscanner.v1alpha.FindingTypeStats finding_type_stats = 1;</code>
   */
  com.google.cloud.websecurityscanner.v1alpha.FindingTypeStatsOrBuilder getFindingTypeStatsOrBuilder(
      int index);
}
