// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service1.proto

package sw.stepCounter.service1;

public interface HourlyStepRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:service1.HourlyStepRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 hour = 1;</code>
   */
  int getHour();

  /**
   * <code>.service1.TimePeriod time_period = 2;</code>
   */
  int getTimePeriodValue();
  /**
   * <code>.service1.TimePeriod time_period = 2;</code>
   */
  sw.stepCounter.service1.TimePeriod getTimePeriod();
}
