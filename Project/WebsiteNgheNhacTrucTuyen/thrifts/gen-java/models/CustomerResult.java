/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package models;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2018-08-07")
public class CustomerResult implements org.apache.thrift.TBase<CustomerResult, CustomerResult._Fields>, java.io.Serializable, Cloneable, Comparable<CustomerResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("CustomerResult");

  private static final org.apache.thrift.protocol.TField RESULT_FIELD_DESC = new org.apache.thrift.protocol.TField("result", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField CUSTOMER_FIELD_DESC = new org.apache.thrift.protocol.TField("customer", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new CustomerResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new CustomerResultTupleSchemeFactory());
  }

  public short result; // required
  public Customer customer; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESULT((short)1, "result"),
    CUSTOMER((short)2, "customer");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // RESULT
          return RESULT;
        case 2: // CUSTOMER
          return CUSTOMER;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __RESULT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.CUSTOMER};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESULT, new org.apache.thrift.meta_data.FieldMetaData("result", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.CUSTOMER, new org.apache.thrift.meta_data.FieldMetaData("customer", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Customer.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(CustomerResult.class, metaDataMap);
  }

  public CustomerResult() {
    this.result = (short)0;

  }

  public CustomerResult(
    short result)
  {
    this();
    this.result = result;
    setResultIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public CustomerResult(CustomerResult other) {
    __isset_bitfield = other.__isset_bitfield;
    this.result = other.result;
    if (other.isSetCustomer()) {
      this.customer = new Customer(other.customer);
    }
  }

  public CustomerResult deepCopy() {
    return new CustomerResult(this);
  }

  @Override
  public void clear() {
    this.result = (short)0;

    this.customer = null;
  }

  public short getResult() {
    return this.result;
  }

  public CustomerResult setResult(short result) {
    this.result = result;
    setResultIsSet(true);
    return this;
  }

  public void unsetResult() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RESULT_ISSET_ID);
  }

  /** Returns true if field result is set (has been assigned a value) and false otherwise */
  public boolean isSetResult() {
    return EncodingUtils.testBit(__isset_bitfield, __RESULT_ISSET_ID);
  }

  public void setResultIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RESULT_ISSET_ID, value);
  }

  public Customer getCustomer() {
    return this.customer;
  }

  public CustomerResult setCustomer(Customer customer) {
    this.customer = customer;
    return this;
  }

  public void unsetCustomer() {
    this.customer = null;
  }

  /** Returns true if field customer is set (has been assigned a value) and false otherwise */
  public boolean isSetCustomer() {
    return this.customer != null;
  }

  public void setCustomerIsSet(boolean value) {
    if (!value) {
      this.customer = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RESULT:
      if (value == null) {
        unsetResult();
      } else {
        setResult((Short)value);
      }
      break;

    case CUSTOMER:
      if (value == null) {
        unsetCustomer();
      } else {
        setCustomer((Customer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RESULT:
      return getResult();

    case CUSTOMER:
      return getCustomer();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RESULT:
      return isSetResult();
    case CUSTOMER:
      return isSetCustomer();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof CustomerResult)
      return this.equals((CustomerResult)that);
    return false;
  }

  public boolean equals(CustomerResult that) {
    if (that == null)
      return false;

    boolean this_present_result = true;
    boolean that_present_result = true;
    if (this_present_result || that_present_result) {
      if (!(this_present_result && that_present_result))
        return false;
      if (this.result != that.result)
        return false;
    }

    boolean this_present_customer = true && this.isSetCustomer();
    boolean that_present_customer = true && that.isSetCustomer();
    if (this_present_customer || that_present_customer) {
      if (!(this_present_customer && that_present_customer))
        return false;
      if (!this.customer.equals(that.customer))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_result = true;
    list.add(present_result);
    if (present_result)
      list.add(result);

    boolean present_customer = true && (isSetCustomer());
    list.add(present_customer);
    if (present_customer)
      list.add(customer);

    return list.hashCode();
  }

  @Override
  public int compareTo(CustomerResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetResult()).compareTo(other.isSetResult());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResult()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.result, other.result);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCustomer()).compareTo(other.isSetCustomer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCustomer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.customer, other.customer);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("CustomerResult(");
    boolean first = true;

    sb.append("result:");
    sb.append(this.result);
    first = false;
    if (isSetCustomer()) {
      if (!first) sb.append(", ");
      sb.append("customer:");
      if (this.customer == null) {
        sb.append("null");
      } else {
        sb.append(this.customer);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'result' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
    if (customer != null) {
      customer.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class CustomerResultStandardSchemeFactory implements SchemeFactory {
    public CustomerResultStandardScheme getScheme() {
      return new CustomerResultStandardScheme();
    }
  }

  private static class CustomerResultStandardScheme extends StandardScheme<CustomerResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, CustomerResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESULT
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.result = iprot.readI16();
              struct.setResultIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CUSTOMER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.customer = new Customer();
              struct.customer.read(iprot);
              struct.setCustomerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetResult()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'result' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, CustomerResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(RESULT_FIELD_DESC);
      oprot.writeI16(struct.result);
      oprot.writeFieldEnd();
      if (struct.customer != null) {
        if (struct.isSetCustomer()) {
          oprot.writeFieldBegin(CUSTOMER_FIELD_DESC);
          struct.customer.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class CustomerResultTupleSchemeFactory implements SchemeFactory {
    public CustomerResultTupleScheme getScheme() {
      return new CustomerResultTupleScheme();
    }
  }

  private static class CustomerResultTupleScheme extends TupleScheme<CustomerResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, CustomerResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI16(struct.result);
      BitSet optionals = new BitSet();
      if (struct.isSetCustomer()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetCustomer()) {
        struct.customer.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, CustomerResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.result = iprot.readI16();
      struct.setResultIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.customer = new Customer();
        struct.customer.read(iprot);
        struct.setCustomerIsSet(true);
      }
    }
  }

}
