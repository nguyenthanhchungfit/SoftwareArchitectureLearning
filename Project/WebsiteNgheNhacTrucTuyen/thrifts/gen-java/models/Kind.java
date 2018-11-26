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
public class Kind implements org.apache.thrift.TBase<Kind, Kind._Fields>, java.io.Serializable, Cloneable, Comparable<Kind> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Kind");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SONGS_FIELD_DESC = new org.apache.thrift.protocol.TField("songs", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField AMOUNT_SONGS_FIELD_DESC = new org.apache.thrift.protocol.TField("amount_songs", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new KindStandardSchemeFactory());
    schemes.put(TupleScheme.class, new KindTupleSchemeFactory());
  }

  public String id; // required
  public String name; // required
  public List<models.Referencer> songs; // required
  public int amount_songs; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    NAME((short)2, "name"),
    SONGS((short)3, "songs"),
    AMOUNT_SONGS((short)4, "amount_songs");

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
        case 1: // ID
          return ID;
        case 2: // NAME
          return NAME;
        case 3: // SONGS
          return SONGS;
        case 4: // AMOUNT_SONGS
          return AMOUNT_SONGS;
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
  private static final int __AMOUNT_SONGS_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SONGS, new org.apache.thrift.meta_data.FieldMetaData("songs", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, models.Referencer.class))));
    tmpMap.put(_Fields.AMOUNT_SONGS, new org.apache.thrift.meta_data.FieldMetaData("amount_songs", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Kind.class, metaDataMap);
  }

  public Kind() {
  }

  public Kind(
    String id,
    String name,
    List<models.Referencer> songs,
    int amount_songs)
  {
    this();
    this.id = id;
    this.name = name;
    this.songs = songs;
    this.amount_songs = amount_songs;
    setAmount_songsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Kind(Kind other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetSongs()) {
      List<models.Referencer> __this__songs = new ArrayList<models.Referencer>(other.songs.size());
      for (models.Referencer other_element : other.songs) {
        __this__songs.add(new models.Referencer(other_element));
      }
      this.songs = __this__songs;
    }
    this.amount_songs = other.amount_songs;
  }

  public Kind deepCopy() {
    return new Kind(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.name = null;
    this.songs = null;
    setAmount_songsIsSet(false);
    this.amount_songs = 0;
  }

  public String getId() {
    return this.id;
  }

  public Kind setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getName() {
    return this.name;
  }

  public Kind setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public int getSongsSize() {
    return (this.songs == null) ? 0 : this.songs.size();
  }

  public java.util.Iterator<models.Referencer> getSongsIterator() {
    return (this.songs == null) ? null : this.songs.iterator();
  }

  public void addToSongs(models.Referencer elem) {
    if (this.songs == null) {
      this.songs = new ArrayList<models.Referencer>();
    }
    this.songs.add(elem);
  }

  public List<models.Referencer> getSongs() {
    return this.songs;
  }

  public Kind setSongs(List<models.Referencer> songs) {
    this.songs = songs;
    return this;
  }

  public void unsetSongs() {
    this.songs = null;
  }

  /** Returns true if field songs is set (has been assigned a value) and false otherwise */
  public boolean isSetSongs() {
    return this.songs != null;
  }

  public void setSongsIsSet(boolean value) {
    if (!value) {
      this.songs = null;
    }
  }

  public int getAmount_songs() {
    return this.amount_songs;
  }

  public Kind setAmount_songs(int amount_songs) {
    this.amount_songs = amount_songs;
    setAmount_songsIsSet(true);
    return this;
  }

  public void unsetAmount_songs() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __AMOUNT_SONGS_ISSET_ID);
  }

  /** Returns true if field amount_songs is set (has been assigned a value) and false otherwise */
  public boolean isSetAmount_songs() {
    return EncodingUtils.testBit(__isset_bitfield, __AMOUNT_SONGS_ISSET_ID);
  }

  public void setAmount_songsIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __AMOUNT_SONGS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case SONGS:
      if (value == null) {
        unsetSongs();
      } else {
        setSongs((List<models.Referencer>)value);
      }
      break;

    case AMOUNT_SONGS:
      if (value == null) {
        unsetAmount_songs();
      } else {
        setAmount_songs((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case NAME:
      return getName();

    case SONGS:
      return getSongs();

    case AMOUNT_SONGS:
      return getAmount_songs();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case NAME:
      return isSetName();
    case SONGS:
      return isSetSongs();
    case AMOUNT_SONGS:
      return isSetAmount_songs();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Kind)
      return this.equals((Kind)that);
    return false;
  }

  public boolean equals(Kind that) {
    if (that == null)
      return false;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_songs = true && this.isSetSongs();
    boolean that_present_songs = true && that.isSetSongs();
    if (this_present_songs || that_present_songs) {
      if (!(this_present_songs && that_present_songs))
        return false;
      if (!this.songs.equals(that.songs))
        return false;
    }

    boolean this_present_amount_songs = true;
    boolean that_present_amount_songs = true;
    if (this_present_amount_songs || that_present_amount_songs) {
      if (!(this_present_amount_songs && that_present_amount_songs))
        return false;
      if (this.amount_songs != that.amount_songs)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true && (isSetId());
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_songs = true && (isSetSongs());
    list.add(present_songs);
    if (present_songs)
      list.add(songs);

    boolean present_amount_songs = true;
    list.add(present_amount_songs);
    if (present_amount_songs)
      list.add(amount_songs);

    return list.hashCode();
  }

  @Override
  public int compareTo(Kind other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSongs()).compareTo(other.isSetSongs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSongs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.songs, other.songs);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAmount_songs()).compareTo(other.isSetAmount_songs());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAmount_songs()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.amount_songs, other.amount_songs);
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
    StringBuilder sb = new StringBuilder("Kind(");
    boolean first = true;

    sb.append("id:");
    if (this.id == null) {
      sb.append("null");
    } else {
      sb.append(this.id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("songs:");
    if (this.songs == null) {
      sb.append("null");
    } else {
      sb.append(this.songs);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("amount_songs:");
    sb.append(this.amount_songs);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (id == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'id' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
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

  private static class KindStandardSchemeFactory implements SchemeFactory {
    public KindStandardScheme getScheme() {
      return new KindStandardScheme();
    }
  }

  private static class KindStandardScheme extends StandardScheme<Kind> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Kind struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SONGS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.songs = new ArrayList<models.Referencer>(_list0.size);
                models.Referencer _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new models.Referencer();
                  _elem1.read(iprot);
                  struct.songs.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSongsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // AMOUNT_SONGS
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.amount_songs = iprot.readI32();
              struct.setAmount_songsIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Kind struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        oprot.writeFieldBegin(ID_FIELD_DESC);
        oprot.writeString(struct.id);
        oprot.writeFieldEnd();
      }
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.songs != null) {
        oprot.writeFieldBegin(SONGS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.songs.size()));
          for (models.Referencer _iter3 : struct.songs)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(AMOUNT_SONGS_FIELD_DESC);
      oprot.writeI32(struct.amount_songs);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class KindTupleSchemeFactory implements SchemeFactory {
    public KindTupleScheme getScheme() {
      return new KindTupleScheme();
    }
  }

  private static class KindTupleScheme extends TupleScheme<Kind> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Kind struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.id);
      BitSet optionals = new BitSet();
      if (struct.isSetName()) {
        optionals.set(0);
      }
      if (struct.isSetSongs()) {
        optionals.set(1);
      }
      if (struct.isSetAmount_songs()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetSongs()) {
        {
          oprot.writeI32(struct.songs.size());
          for (models.Referencer _iter4 : struct.songs)
          {
            _iter4.write(oprot);
          }
        }
      }
      if (struct.isSetAmount_songs()) {
        oprot.writeI32(struct.amount_songs);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Kind struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.id = iprot.readString();
      struct.setIdIsSet(true);
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.songs = new ArrayList<models.Referencer>(_list5.size);
          models.Referencer _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new models.Referencer();
            _elem6.read(iprot);
            struct.songs.add(_elem6);
          }
        }
        struct.setSongsIsSet(true);
      }
      if (incoming.get(2)) {
        struct.amount_songs = iprot.readI32();
        struct.setAmount_songsIsSet(true);
      }
    }
  }

}
