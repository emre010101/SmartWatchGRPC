// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service2.proto

package sw.Reminder.service2;

/**
 * Protobuf enum {@code service2.Type}
 */
public enum Type
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>STANDART = 0;</code>
   */
  STANDART(0),
  /**
   * <code>DOCTOR = 1;</code>
   */
  DOCTOR(1),
  /**
   * <code>PILL = 2;</code>
   */
  PILL(2),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>STANDART = 0;</code>
   */
  public static final int STANDART_VALUE = 0;
  /**
   * <code>DOCTOR = 1;</code>
   */
  public static final int DOCTOR_VALUE = 1;
  /**
   * <code>PILL = 2;</code>
   */
  public static final int PILL_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Type valueOf(int value) {
    return forNumber(value);
  }

  public static Type forNumber(int value) {
    switch (value) {
      case 0: return STANDART;
      case 1: return DOCTOR;
      case 2: return PILL;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Type>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Type> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Type>() {
          public Type findValueByNumber(int number) {
            return Type.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return sw.Reminder.service2.taskReminderImpl.getDescriptor().getEnumTypes().get(0);
  }

  private static final Type[] VALUES = values();

  public static Type valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Type(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:service2.Type)
}
