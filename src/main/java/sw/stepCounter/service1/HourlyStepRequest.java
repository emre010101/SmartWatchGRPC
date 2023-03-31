// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service1.proto

package sw.stepCounter.service1;

/**
 * <pre>
 * HourlyStepRequest message references the TimePeriod enumeration
 * </pre>
 *
 * Protobuf type {@code service1.HourlyStepRequest}
 */
public  final class HourlyStepRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:service1.HourlyStepRequest)
    HourlyStepRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use HourlyStepRequest.newBuilder() to construct.
  private HourlyStepRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private HourlyStepRequest() {
    hour_ = 0;
    timePeriod_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private HourlyStepRequest(
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

            hour_ = input.readInt32();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            timePeriod_ = rawValue;
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
    return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sw.stepCounter.service1.HourlyStepRequest.class, sw.stepCounter.service1.HourlyStepRequest.Builder.class);
  }

  public static final int HOUR_FIELD_NUMBER = 1;
  private int hour_;
  /**
   * <code>int32 hour = 1;</code>
   */
  public int getHour() {
    return hour_;
  }

  public static final int TIME_PERIOD_FIELD_NUMBER = 2;
  private int timePeriod_;
  /**
   * <code>.service1.TimePeriod time_period = 2;</code>
   */
  public int getTimePeriodValue() {
    return timePeriod_;
  }
  /**
   * <code>.service1.TimePeriod time_period = 2;</code>
   */
  public sw.stepCounter.service1.TimePeriod getTimePeriod() {
    @SuppressWarnings("deprecation")
    sw.stepCounter.service1.TimePeriod result = sw.stepCounter.service1.TimePeriod.valueOf(timePeriod_);
    return result == null ? sw.stepCounter.service1.TimePeriod.UNRECOGNIZED : result;
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
    if (hour_ != 0) {
      output.writeInt32(1, hour_);
    }
    if (timePeriod_ != sw.stepCounter.service1.TimePeriod.LAST_WEEK.getNumber()) {
      output.writeEnum(2, timePeriod_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (hour_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, hour_);
    }
    if (timePeriod_ != sw.stepCounter.service1.TimePeriod.LAST_WEEK.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, timePeriod_);
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
    if (!(obj instanceof sw.stepCounter.service1.HourlyStepRequest)) {
      return super.equals(obj);
    }
    sw.stepCounter.service1.HourlyStepRequest other = (sw.stepCounter.service1.HourlyStepRequest) obj;

    boolean result = true;
    result = result && (getHour()
        == other.getHour());
    result = result && timePeriod_ == other.timePeriod_;
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
    hash = (37 * hash) + HOUR_FIELD_NUMBER;
    hash = (53 * hash) + getHour();
    hash = (37 * hash) + TIME_PERIOD_FIELD_NUMBER;
    hash = (53 * hash) + timePeriod_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sw.stepCounter.service1.HourlyStepRequest parseFrom(
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
  public static Builder newBuilder(sw.stepCounter.service1.HourlyStepRequest prototype) {
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
   * HourlyStepRequest message references the TimePeriod enumeration
   * </pre>
   *
   * Protobuf type {@code service1.HourlyStepRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:service1.HourlyStepRequest)
      sw.stepCounter.service1.HourlyStepRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sw.stepCounter.service1.HourlyStepRequest.class, sw.stepCounter.service1.HourlyStepRequest.Builder.class);
    }

    // Construct using sw.stepCounter.service1.HourlyStepRequest.newBuilder()
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
      hour_ = 0;

      timePeriod_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sw.stepCounter.service1.stepCounterImpl.internal_static_service1_HourlyStepRequest_descriptor;
    }

    @java.lang.Override
    public sw.stepCounter.service1.HourlyStepRequest getDefaultInstanceForType() {
      return sw.stepCounter.service1.HourlyStepRequest.getDefaultInstance();
    }

    @java.lang.Override
    public sw.stepCounter.service1.HourlyStepRequest build() {
      sw.stepCounter.service1.HourlyStepRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sw.stepCounter.service1.HourlyStepRequest buildPartial() {
      sw.stepCounter.service1.HourlyStepRequest result = new sw.stepCounter.service1.HourlyStepRequest(this);
      result.hour_ = hour_;
      result.timePeriod_ = timePeriod_;
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
      if (other instanceof sw.stepCounter.service1.HourlyStepRequest) {
        return mergeFrom((sw.stepCounter.service1.HourlyStepRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sw.stepCounter.service1.HourlyStepRequest other) {
      if (other == sw.stepCounter.service1.HourlyStepRequest.getDefaultInstance()) return this;
      if (other.getHour() != 0) {
        setHour(other.getHour());
      }
      if (other.timePeriod_ != 0) {
        setTimePeriodValue(other.getTimePeriodValue());
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
      sw.stepCounter.service1.HourlyStepRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sw.stepCounter.service1.HourlyStepRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int hour_ ;
    /**
     * <code>int32 hour = 1;</code>
     */
    public int getHour() {
      return hour_;
    }
    /**
     * <code>int32 hour = 1;</code>
     */
    public Builder setHour(int value) {
      
      hour_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 hour = 1;</code>
     */
    public Builder clearHour() {
      
      hour_ = 0;
      onChanged();
      return this;
    }

    private int timePeriod_ = 0;
    /**
     * <code>.service1.TimePeriod time_period = 2;</code>
     */
    public int getTimePeriodValue() {
      return timePeriod_;
    }
    /**
     * <code>.service1.TimePeriod time_period = 2;</code>
     */
    public Builder setTimePeriodValue(int value) {
      timePeriod_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.service1.TimePeriod time_period = 2;</code>
     */
    public sw.stepCounter.service1.TimePeriod getTimePeriod() {
      @SuppressWarnings("deprecation")
      sw.stepCounter.service1.TimePeriod result = sw.stepCounter.service1.TimePeriod.valueOf(timePeriod_);
      return result == null ? sw.stepCounter.service1.TimePeriod.UNRECOGNIZED : result;
    }
    /**
     * <code>.service1.TimePeriod time_period = 2;</code>
     */
    public Builder setTimePeriod(sw.stepCounter.service1.TimePeriod value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      timePeriod_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.service1.TimePeriod time_period = 2;</code>
     */
    public Builder clearTimePeriod() {
      
      timePeriod_ = 0;
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


    // @@protoc_insertion_point(builder_scope:service1.HourlyStepRequest)
  }

  // @@protoc_insertion_point(class_scope:service1.HourlyStepRequest)
  private static final sw.stepCounter.service1.HourlyStepRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sw.stepCounter.service1.HourlyStepRequest();
  }

  public static sw.stepCounter.service1.HourlyStepRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<HourlyStepRequest>
      PARSER = new com.google.protobuf.AbstractParser<HourlyStepRequest>() {
    @java.lang.Override
    public HourlyStepRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new HourlyStepRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<HourlyStepRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<HourlyStepRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sw.stepCounter.service1.HourlyStepRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

