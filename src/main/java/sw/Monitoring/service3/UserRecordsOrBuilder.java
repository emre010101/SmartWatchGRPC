// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service3.proto

package sw.Monitoring.service3;

public interface UserRecordsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:service3.UserRecords)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 patient_id = 1;</code>
   */
  int getPatientId();

  /**
   * <code>int32 age = 2;</code>
   */
  int getAge();

  /**
   * <code>string name = 3;</code>
   */
  java.lang.String getName();
  /**
   * <code>string name = 3;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>double weight = 4;</code>
   */
  double getWeight();

  /**
   * <code>double height = 5;</code>
   */
  double getHeight();

  /**
   * <code>string address = 6;</code>
   */
  java.lang.String getAddress();
  /**
   * <code>string address = 6;</code>
   */
  com.google.protobuf.ByteString
      getAddressBytes();

  /**
   * <pre>
   *could be more than one emergency contact
   * </pre>
   *
   * <code>repeated .service3.EmergencyContact Contacts = 7;</code>
   */
  java.util.List<sw.Monitoring.service3.EmergencyContact> 
      getContactsList();
  /**
   * <pre>
   *could be more than one emergency contact
   * </pre>
   *
   * <code>repeated .service3.EmergencyContact Contacts = 7;</code>
   */
  sw.Monitoring.service3.EmergencyContact getContacts(int index);
  /**
   * <pre>
   *could be more than one emergency contact
   * </pre>
   *
   * <code>repeated .service3.EmergencyContact Contacts = 7;</code>
   */
  int getContactsCount();
  /**
   * <pre>
   *could be more than one emergency contact
   * </pre>
   *
   * <code>repeated .service3.EmergencyContact Contacts = 7;</code>
   */
  java.util.List<? extends sw.Monitoring.service3.EmergencyContactOrBuilder> 
      getContactsOrBuilderList();
  /**
   * <pre>
   *could be more than one emergency contact
   * </pre>
   *
   * <code>repeated .service3.EmergencyContact Contacts = 7;</code>
   */
  sw.Monitoring.service3.EmergencyContactOrBuilder getContactsOrBuilder(
      int index);
}
