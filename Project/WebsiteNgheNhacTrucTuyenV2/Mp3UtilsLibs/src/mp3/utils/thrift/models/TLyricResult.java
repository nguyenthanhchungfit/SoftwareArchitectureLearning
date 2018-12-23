/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package mp3.utils.thrift.models;

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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2018-12-23")
public class TLyricResult implements org.apache.thrift.TBase<TLyricResult, TLyricResult._Fields>, java.io.Serializable, Cloneable, Comparable<TLyricResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TLyricResult");

  private static final org.apache.thrift.protocol.TField ERROR_FIELD_DESC = new org.apache.thrift.protocol.TField("error", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField LYRIC_FIELD_DESC = new org.apache.thrift.protocol.TField("lyric", org.apache.thrift.protocol.TType.STRUCT, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TLyricResultStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TLyricResultTupleSchemeFactory());
  }

  public short error; // required
  public TLyric lyric; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ERROR((short)1, "error"),
    LYRIC((short)2, "lyric");

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
        case 1: // ERROR
          return ERROR;
        case 2: // LYRIC
          return LYRIC;
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
  private static final int __ERROR_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.LYRIC};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ERROR, new org.apache.thrift.meta_data.FieldMetaData("error", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.LYRIC, new org.apache.thrift.meta_data.FieldMetaData("lyric", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TLyric.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TLyricResult.class, metaDataMap);
  }

  public TLyricResult() {
    this.error = (short)0;

  }

  public TLyricResult(
    short error)
  {
    this();
    this.error = error;
    setErrorIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TLyricResult(TLyricResult other) {
    __isset_bitfield = other.__isset_bitfield;
    this.error = other.error;
    if (other.isSetLyric()) {
      this.lyric = new TLyric(other.lyric);
    }
  }

  public TLyricResult deepCopy() {
    return new TLyricResult(this);
  }

  @Override
  public void clear() {
    this.error = (short)0;

    this.lyric = null;
  }

  public short getError() {
    return this.error;
  }

  public TLyricResult setError(short error) {
    this.error = error;
    setErrorIsSet(true);
    return this;
  }

  public void unsetError() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ERROR_ISSET_ID);
  }

  /** Returns true if field error is set (has been assigned a value) and false otherwise */
  public boolean isSetError() {
    return EncodingUtils.testBit(__isset_bitfield, __ERROR_ISSET_ID);
  }

  public void setErrorIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ERROR_ISSET_ID, value);
  }

  public TLyric getLyric() {
    return this.lyric;
  }

  public TLyricResult setLyric(TLyric lyric) {
    this.lyric = lyric;
    return this;
  }

  public void unsetLyric() {
    this.lyric = null;
  }

  /** Returns true if field lyric is set (has been assigned a value) and false otherwise */
  public boolean isSetLyric() {
    return this.lyric != null;
  }

  public void setLyricIsSet(boolean value) {
    if (!value) {
      this.lyric = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ERROR:
      if (value == null) {
        unsetError();
      } else {
        setError((Short)value);
      }
      break;

    case LYRIC:
      if (value == null) {
        unsetLyric();
      } else {
        setLyric((TLyric)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ERROR:
      return getError();

    case LYRIC:
      return getLyric();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ERROR:
      return isSetError();
    case LYRIC:
      return isSetLyric();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TLyricResult)
      return this.equals((TLyricResult)that);
    return false;
  }

  public boolean equals(TLyricResult that) {
    if (that == null)
      return false;

    boolean this_present_error = true;
    boolean that_present_error = true;
    if (this_present_error || that_present_error) {
      if (!(this_present_error && that_present_error))
        return false;
      if (this.error != that.error)
        return false;
    }

    boolean this_present_lyric = true && this.isSetLyric();
    boolean that_present_lyric = true && that.isSetLyric();
    if (this_present_lyric || that_present_lyric) {
      if (!(this_present_lyric && that_present_lyric))
        return false;
      if (!this.lyric.equals(that.lyric))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_error = true;
    list.add(present_error);
    if (present_error)
      list.add(error);

    boolean present_lyric = true && (isSetLyric());
    list.add(present_lyric);
    if (present_lyric)
      list.add(lyric);

    return list.hashCode();
  }

  @Override
  public int compareTo(TLyricResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetError()).compareTo(other.isSetError());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetError()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.error, other.error);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLyric()).compareTo(other.isSetLyric());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLyric()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lyric, other.lyric);
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
    StringBuilder sb = new StringBuilder("TLyricResult(");
    boolean first = true;

    sb.append("error:");
    sb.append(this.error);
    first = false;
    if (isSetLyric()) {
      if (!first) sb.append(", ");
      sb.append("lyric:");
      if (this.lyric == null) {
        sb.append("null");
      } else {
        sb.append(this.lyric);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'error' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
    if (lyric != null) {
      lyric.validate();
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

  private static class TLyricResultStandardSchemeFactory implements SchemeFactory {
    public TLyricResultStandardScheme getScheme() {
      return new TLyricResultStandardScheme();
    }
  }

  private static class TLyricResultStandardScheme extends StandardScheme<TLyricResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TLyricResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ERROR
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.error = iprot.readI16();
              struct.setErrorIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LYRIC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.lyric = new TLyric();
              struct.lyric.read(iprot);
              struct.setLyricIsSet(true);
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
      if (!struct.isSetError()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'error' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TLyricResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ERROR_FIELD_DESC);
      oprot.writeI16(struct.error);
      oprot.writeFieldEnd();
      if (struct.lyric != null) {
        if (struct.isSetLyric()) {
          oprot.writeFieldBegin(LYRIC_FIELD_DESC);
          struct.lyric.write(oprot);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TLyricResultTupleSchemeFactory implements SchemeFactory {
    public TLyricResultTupleScheme getScheme() {
      return new TLyricResultTupleScheme();
    }
  }

  private static class TLyricResultTupleScheme extends TupleScheme<TLyricResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TLyricResult struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI16(struct.error);
      BitSet optionals = new BitSet();
      if (struct.isSetLyric()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetLyric()) {
        struct.lyric.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TLyricResult struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.error = iprot.readI16();
      struct.setErrorIsSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.lyric = new TLyric();
        struct.lyric.read(iprot);
        struct.setLyricIsSet(true);
      }
    }
  }

}

