// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service1.proto

package sw.stepCounter.service1;

/**
 * <pre>
 * HourlyStepCount message also references the WeekDays enumeration
 * </pre>
 *
 * Protobuf type {@code service1.HourlyStepCount}
 */
public  final class HourlyStepCount extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:service1.HourlyStepCount)
    HourlyStepCountOrBuilder {
private static final long serialVersionUID = 0L;
  // Use HourlyStepCount.newBuilder() to construct.
  private HourlyStepCount(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HourlyStepCount() {
    averageSteps_ = 0;
    weekDays_ = 0;
    message_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HourlyStepCount(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            averageSteps_ = input.readInt32();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            weekDays_ = rawValue;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            message_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepCount_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepCount_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sw.stepCounter.service1.HourlyStepCount.class, sw.stepCounter.service1.HourlyStepCount.Builder.class);
  }

  public static final int AVERAGE_STEPS_FIELD_NUMBER = 1;
  private int averageSteps_;
  /**
   * <code>int32 average_steps = 1;</code>
   */
  public int getAverageSteps() {
    return averageSteps_;
  }

  public static final int WEEK_DAYS_FIELD_NUMBER = 2;
  private int weekDays_;
  /**
   * <code>.service1.WeekDays week_days = 2;</code>
   */
  public int getWeekDaysValue() {
    return weekDays_;
  }
  /**
   * <code>.service1.WeekDays week_days = 2;</code>
   */
  public sw.stepCounter.service1.WeekDays getWeekDays() {
    @SuppressWarnings("deprecation")
    sw.stepCounter.service1.WeekDays result = sw.stepCounter.service1.WeekDays.valueOf(weekDays_);
    return result == null ? sw.stepCounter.service1.WeekDays.UNRECOGNIZED : result;
  }

  public static final int MESSAGE_FIELD_NUMBER = 3;
  private volatile java.lang.Object message_;
  /**
   * <code>string message = 3;</code>
   */
  public java.lang.String getMessage() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      message_ = s;
      return s;
    }
  }
  /**
   * <code>string message = 3;</code>
   */
  public com.google.protobuf.ByteString
      getMessageBytes() {
    java.lang.Object ref = message_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      message_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (averageSteps_ != 0) {
      output.writeInt32(1, averageSteps_);
    }
    if (weekDays_ != sw.stepCounter.service1.WeekDays.LAST_DAY.getNumber()) {
      output.writeEnum(2, weekDays_);
    }
    if (!getMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, message_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (averageSteps_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, averageSteps_);
    }
    if (weekDays_ != sw.stepCounter.service1.WeekDays.LAST_DAY.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, weekDays_);
    }
    if (!getMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, message_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof sw.stepCounter.service1.HourlyStepCount)) {
      return super.equals(obj);
    }
    sw.stepCounter.service1.HourlyStepCount other = (sw.stepCounter.service1.HourlyStepCount) obj;

    boolean result = true;
    result = result && (getAverageSteps()
        == other.getAverageSteps());
    result = result && weekDays_ == other.weekDays_;
    result = result && getMessage()
        .equals(other.getMessage());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + AVERAGE_STEPS_FIELD_NUMBER;
    hash = (53 * hash) + getAverageSteps();
    hash = (37 * hash) + WEEK_DAYS_FIELD_NUMBER;
    hash = (53 * hash) + weekDays_;
    hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getMessage().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sw.stepCounter.service1.HourlyStepCount parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(sw.stepCounter.service1.HourlyStepCount prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * HourlyStepCount message also references the WeekDays enumeration
   * </pre>
   *
   * Protobuf type {@code service1.HourlyStepCount}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:service1.HourlyStepCount)
      sw.stepCounter.service1.HourlyStepCountOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepCount_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepCount_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sw.stepCounter.service1.HourlyStepCount.class, sw.stepCounter.service1.HourlyStepCount.Builder.class);
    }

    // Construct using sw.stepCounter.service1.HourlyStepCount.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      averageSteps_ = 0;

      weekDays_ = 0;

      message_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepCount_descriptor;
    }

    @java.lang.Override
    public sw.stepCounter.service1.HourlyStepCount getDefaultInstanceForType() {
      return sw.stepCounter.service1.HourlyStepCount.getDefaultInstance();
    }

    @java.lang.Override
    public sw.stepCounter.service1.HourlyStepCount build() {
      sw.stepCounter.service1.HourlyStepCount result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sw.stepCounter.service1.HourlyStepCount buildPartial() {
      sw.stepCounter.service1.HourlyStepCount result = new sw.stepCounter.service1.HourlyStepCount(this);
      result.averageSteps_ = averageSteps_;
      result.weekDays_ = weekDays_;
      result.message_ = message_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof sw.stepCounter.service1.HourlyStepCount) {
        return mergeFrom((sw.stepCounter.service1.HourlyStepCount)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sw.stepCounter.service1.HourlyStepCount other) {
      if (other == sw.stepCounter.service1.HourlyStepCount.getDefaultInstance()) return this;
      if (other.getAverageSteps() != 0) {
        setAverageSteps(other.getAverageSteps());
      }
      if (other.weekDays_ != 0) {
        setWeekDaysValue(other.getWeekDaysValue());
      }
      if (!other.getMessage().isEmpty()) {
        message_ = other.message_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      sw.stepCounter.service1.HourlyStepCount parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sw.stepCounter.service1.HourlyStepCount) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int averageSteps_ ;
    /**
     * <code>int32 average_steps = 1;</code>
     */
    public int getAverageSteps() {
      return averageSteps_;
    }
    /**
     * <code>int32 average_steps = 1;</code>
     */
    public Builder setAverageSteps(int value) {
      
      averageSteps_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 average_steps = 1;</code>
     */
    public Builder clearAverageSteps() {
      
      averageSteps_ = 0;
      onChanged();
      return this;
    }

    private int weekDays_ = 0;
    /**
     * <code>.service1.WeekDays week_days = 2;</code>
     */
    public int getWeekDaysValue() {
      return weekDays_;
    }
    /**
     * <code>.service1.WeekDays week_days = 2;</code>
     */
    public Builder setWeekDaysValue(int value) {
      weekDays_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.service1.WeekDays week_days = 2;</code>
     */
    public sw.stepCounter.service1.WeekDays getWeekDays() {
      @SuppressWarnings("deprecation")
      sw.stepCounter.service1.WeekDays result = sw.stepCounter.service1.WeekDays.valueOf(weekDays_);
      return result == null ? sw.stepCounter.service1.WeekDays.UNRECOGNIZED : result;
    }
    /**
     * <code>.service1.WeekDays week_days = 2;</code>
     */
    public Builder setWeekDays(sw.stepCounter.service1.WeekDays value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      weekDays_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.service1.WeekDays week_days = 2;</code>
     */
    public Builder clearWeekDays() {
      
      weekDays_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object message_ = "";
    /**
     * <code>string message = 3;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        message_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string message = 3;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string message = 3;</code>
     */
    public Builder setMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      message_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string message = 3;</code>
     */
    public Builder clearMessage() {
      
      message_ = getDefaultInstance().getMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string message = 3;</code>
     */
    public Builder setMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      message_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:service1.HourlyStepCount)
  }

  // @@protoc_insertion_point(class_scope:service1.HourlyStepCount)
  private static final sw.stepCounter.service1.HourlyStepCount DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sw.stepCounter.service1.HourlyStepCount();
  }

  public static sw.stepCounter.service1.HourlyStepCount getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<HourlyStepCount>
      PARSER = new com.google.protobuf.AbstractParser<HourlyStepCount>() {
    @java.lang.Override
    public HourlyStepCount parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new HourlyStepCount(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HourlyStepCount> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HourlyStepCount> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sw.stepCounter.service1.HourlyStepCount getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

