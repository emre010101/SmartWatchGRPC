// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service1.proto

package sw.stepCounter.service1;

public interface HourlyStepCountOrBuilder extends
    // @@protoc_insertion_point(interface_extends:service1.HourlyStepCount)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 average_steps = 1;</code>
   */
  int getAverageSteps();

  /**
   * <code>.service1.WeekDays week_days = 2;</code>
   */
  int getWeekDaysValue();
  /**
   * <code>.service1.WeekDays week_days = 2;</code>
   */
  sw.stepCounter.service1.WeekDays getWeekDays();

  /**
   * <code>string message = 3;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 3;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
