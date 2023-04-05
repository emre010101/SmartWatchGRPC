// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service3.proto

package sw.Monitoring.service3;

/**
 * <pre>
 * Monitoring request and response messages
 * </pre>
 *
 * Protobuf type {@code service3.UserRecords}
 */
public  final class UserRecords extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:service3.UserRecords)
    UserRecordsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UserRecords.newBuilder() to construct.
  private UserRecords(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UserRecords() {
    patientId_ = 0;
    age_ = 0;
    name_ = "";
    weight_ = 0D;
    height_ = 0D;
    closeContacts_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    address_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UserRecords(
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

            patientId_ = input.readInt32();
            break;
          }
          case 16: {

            age_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 33: {

            weight_ = input.readDouble();
            break;
          }
          case 41: {

            height_ = input.readDouble();
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
              closeContacts_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000020;
            }
            closeContacts_.add(s);
            break;
          }
          case 58: {
            java.lang.String s = input.readStringRequireUtf8();

            address_ = s;
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
      if (((mutable_bitField0_ & 0x00000020) == 0x00000020)) {
        closeContacts_ = closeContacts_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return sw.Monitoring.service3.MonitorImpl.internal_static_service3_UserRecords_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return sw.Monitoring.service3.MonitorImpl.internal_static_service3_UserRecords_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            sw.Monitoring.service3.UserRecords.class, sw.Monitoring.service3.UserRecords.Builder.class);
  }

  private int bitField0_;
  public static final int PATIENT_ID_FIELD_NUMBER = 1;
  private int patientId_;
  /**
   * <code>int32 patient_id = 1;</code>
   */
  public int getPatientId() {
    return patientId_;
  }

  public static final int AGE_FIELD_NUMBER = 2;
  private int age_;
  /**
   * <code>int32 age = 2;</code>
   */
  public int getAge() {
    return age_;
  }

  public static final int NAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 3;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 3;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int WEIGHT_FIELD_NUMBER = 4;
  private double weight_;
  /**
   * <code>double weight = 4;</code>
   */
  public double getWeight() {
    return weight_;
  }

  public static final int HEIGHT_FIELD_NUMBER = 5;
  private double height_;
  /**
   * <code>double height = 5;</code>
   */
  public double getHeight() {
    return height_;
  }

  public static final int CLOSE_CONTACTS_FIELD_NUMBER = 6;
  private com.google.protobuf.LazyStringList closeContacts_;
  /**
   * <code>repeated string close_contacts = 6;</code>
   */
  public com.google.protobuf.ProtocolStringList
      getCloseContactsList() {
    return closeContacts_;
  }
  /**
   * <code>repeated string close_contacts = 6;</code>
   */
  public int getCloseContactsCount() {
    return closeContacts_.size();
  }
  /**
   * <code>repeated string close_contacts = 6;</code>
   */
  public java.lang.String getCloseContacts(int index) {
    return closeContacts_.get(index);
  }
  /**
   * <code>repeated string close_contacts = 6;</code>
   */
  public com.google.protobuf.ByteString
      getCloseContactsBytes(int index) {
    return closeContacts_.getByteString(index);
  }

  public static final int ADDRESS_FIELD_NUMBER = 7;
  private volatile java.lang.Object address_;
  /**
   * <code>string address = 7;</code>
   */
  public java.lang.String getAddress() {
    java.lang.Object ref = address_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      address_ = s;
      return s;
    }
  }
  /**
   * <code>string address = 7;</code>
   */
  public com.google.protobuf.ByteString
      getAddressBytes() {
    java.lang.Object ref = address_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      address_ = b;
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
    if (patientId_ != 0) {
      output.writeInt32(1, patientId_);
    }
    if (age_ != 0) {
      output.writeInt32(2, age_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
    }
    if (weight_ != 0D) {
      output.writeDouble(4, weight_);
    }
    if (height_ != 0D) {
      output.writeDouble(5, height_);
    }
    for (int i = 0; i < closeContacts_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, closeContacts_.getRaw(i));
    }
    if (!getAddressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 7, address_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (patientId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, patientId_);
    }
    if (age_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, age_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
    }
    if (weight_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, weight_);
    }
    if (height_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(5, height_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < closeContacts_.size(); i++) {
        dataSize += computeStringSizeNoTag(closeContacts_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getCloseContactsList().size();
    }
    if (!getAddressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(7, address_);
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
    if (!(obj instanceof sw.Monitoring.service3.UserRecords)) {
      return super.equals(obj);
    }
    sw.Monitoring.service3.UserRecords other = (sw.Monitoring.service3.UserRecords) obj;

    boolean result = true;
    result = result && (getPatientId()
        == other.getPatientId());
    result = result && (getAge()
        == other.getAge());
    result = result && getName()
        .equals(other.getName());
    result = result && (
        java.lang.Double.doubleToLongBits(getWeight())
        == java.lang.Double.doubleToLongBits(
            other.getWeight()));
    result = result && (
        java.lang.Double.doubleToLongBits(getHeight())
        == java.lang.Double.doubleToLongBits(
            other.getHeight()));
    result = result && getCloseContactsList()
        .equals(other.getCloseContactsList());
    result = result && getAddress()
        .equals(other.getAddress());
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
    hash = (37 * hash) + PATIENT_ID_FIELD_NUMBER;
    hash = (53 * hash) + getPatientId();
    hash = (37 * hash) + AGE_FIELD_NUMBER;
    hash = (53 * hash) + getAge();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + WEIGHT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getWeight()));
    hash = (37 * hash) + HEIGHT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getHeight()));
    if (getCloseContactsCount() > 0) {
      hash = (37 * hash) + CLOSE_CONTACTS_FIELD_NUMBER;
      hash = (53 * hash) + getCloseContactsList().hashCode();
    }
    hash = (37 * hash) + ADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getAddress().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static sw.Monitoring.service3.UserRecords parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static sw.Monitoring.service3.UserRecords parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static sw.Monitoring.service3.UserRecords parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static sw.Monitoring.service3.UserRecords parseFrom(
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
  public static Builder newBuilder(sw.Monitoring.service3.UserRecords prototype) {
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
   * Monitoring request and response messages
   * </pre>
   *
   * Protobuf type {@code service3.UserRecords}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:service3.UserRecords)
      sw.Monitoring.service3.UserRecordsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return sw.Monitoring.service3.MonitorImpl.internal_static_service3_UserRecords_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return sw.Monitoring.service3.MonitorImpl.internal_static_service3_UserRecords_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              sw.Monitoring.service3.UserRecords.class, sw.Monitoring.service3.UserRecords.Builder.class);
    }

    // Construct using sw.Monitoring.service3.UserRecords.newBuilder()
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
      patientId_ = 0;

      age_ = 0;

      name_ = "";

      weight_ = 0D;

      height_ = 0D;

      closeContacts_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000020);
      address_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return sw.Monitoring.service3.MonitorImpl.internal_static_service3_UserRecords_descriptor;
    }

    @java.lang.Override
    public sw.Monitoring.service3.UserRecords getDefaultInstanceForType() {
      return sw.Monitoring.service3.UserRecords.getDefaultInstance();
    }

    @java.lang.Override
    public sw.Monitoring.service3.UserRecords build() {
      sw.Monitoring.service3.UserRecords result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public sw.Monitoring.service3.UserRecords buildPartial() {
      sw.Monitoring.service3.UserRecords result = new sw.Monitoring.service3.UserRecords(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      result.patientId_ = patientId_;
      result.age_ = age_;
      result.name_ = name_;
      result.weight_ = weight_;
      result.height_ = height_;
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        closeContacts_ = closeContacts_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000020);
      }
      result.closeContacts_ = closeContacts_;
      result.address_ = address_;
      result.bitField0_ = to_bitField0_;
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
      if (other instanceof sw.Monitoring.service3.UserRecords) {
        return mergeFrom((sw.Monitoring.service3.UserRecords)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(sw.Monitoring.service3.UserRecords other) {
      if (other == sw.Monitoring.service3.UserRecords.getDefaultInstance()) return this;
      if (other.getPatientId() != 0) {
        setPatientId(other.getPatientId());
      }
      if (other.getAge() != 0) {
        setAge(other.getAge());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.getWeight() != 0D) {
        setWeight(other.getWeight());
      }
      if (other.getHeight() != 0D) {
        setHeight(other.getHeight());
      }
      if (!other.closeContacts_.isEmpty()) {
        if (closeContacts_.isEmpty()) {
          closeContacts_ = other.closeContacts_;
          bitField0_ = (bitField0_ & ~0x00000020);
        } else {
          ensureCloseContactsIsMutable();
          closeContacts_.addAll(other.closeContacts_);
        }
        onChanged();
      }
      if (!other.getAddress().isEmpty()) {
        address_ = other.address_;
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
      sw.Monitoring.service3.UserRecords parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (sw.Monitoring.service3.UserRecords) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int patientId_ ;
    /**
     * <code>int32 patient_id = 1;</code>
     */
    public int getPatientId() {
      return patientId_;
    }
    /**
     * <code>int32 patient_id = 1;</code>
     */
    public Builder setPatientId(int value) {
      
      patientId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 patient_id = 1;</code>
     */
    public Builder clearPatientId() {
      
      patientId_ = 0;
      onChanged();
      return this;
    }

    private int age_ ;
    /**
     * <code>int32 age = 2;</code>
     */
    public int getAge() {
      return age_;
    }
    /**
     * <code>int32 age = 2;</code>
     */
    public Builder setAge(int value) {
      
      age_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 age = 2;</code>
     */
    public Builder clearAge() {
      
      age_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 3;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private double weight_ ;
    /**
     * <code>double weight = 4;</code>
     */
    public double getWeight() {
      return weight_;
    }
    /**
     * <code>double weight = 4;</code>
     */
    public Builder setWeight(double value) {
      
      weight_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double weight = 4;</code>
     */
    public Builder clearWeight() {
      
      weight_ = 0D;
      onChanged();
      return this;
    }

    private double height_ ;
    /**
     * <code>double height = 5;</code>
     */
    public double getHeight() {
      return height_;
    }
    /**
     * <code>double height = 5;</code>
     */
    public Builder setHeight(double value) {
      
      height_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double height = 5;</code>
     */
    public Builder clearHeight() {
      
      height_ = 0D;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList closeContacts_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureCloseContactsIsMutable() {
      if (!((bitField0_ & 0x00000020) == 0x00000020)) {
        closeContacts_ = new com.google.protobuf.LazyStringArrayList(closeContacts_);
        bitField0_ |= 0x00000020;
       }
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getCloseContactsList() {
      return closeContacts_.getUnmodifiableView();
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public int getCloseContactsCount() {
      return closeContacts_.size();
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public java.lang.String getCloseContacts(int index) {
      return closeContacts_.get(index);
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public com.google.protobuf.ByteString
        getCloseContactsBytes(int index) {
      return closeContacts_.getByteString(index);
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public Builder setCloseContacts(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureCloseContactsIsMutable();
      closeContacts_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public Builder addCloseContacts(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureCloseContactsIsMutable();
      closeContacts_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public Builder addAllCloseContacts(
        java.lang.Iterable<java.lang.String> values) {
      ensureCloseContactsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, closeContacts_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public Builder clearCloseContacts() {
      closeContacts_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000020);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string close_contacts = 6;</code>
     */
    public Builder addCloseContactsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureCloseContactsIsMutable();
      closeContacts_.add(value);
      onChanged();
      return this;
    }

    private java.lang.Object address_ = "";
    /**
     * <code>string address = 7;</code>
     */
    public java.lang.String getAddress() {
      java.lang.Object ref = address_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        address_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string address = 7;</code>
     */
    public com.google.protobuf.ByteString
        getAddressBytes() {
      java.lang.Object ref = address_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        address_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string address = 7;</code>
     */
    public Builder setAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      address_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string address = 7;</code>
     */
    public Builder clearAddress() {
      
      address_ = getDefaultInstance().getAddress();
      onChanged();
      return this;
    }
    /**
     * <code>string address = 7;</code>
     */
    public Builder setAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      address_ = value;
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


    // @@protoc_insertion_point(builder_scope:service3.UserRecords)
  }

  // @@protoc_insertion_point(class_scope:service3.UserRecords)
  private static final sw.Monitoring.service3.UserRecords DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new sw.Monitoring.service3.UserRecords();
  }

  public static sw.Monitoring.service3.UserRecords getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UserRecords>
      PARSER = new com.google.protobuf.AbstractParser<UserRecords>() {
    @java.lang.Override
    public UserRecords parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UserRecords(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UserRecords> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UserRecords> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public sw.Monitoring.service3.UserRecords getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
