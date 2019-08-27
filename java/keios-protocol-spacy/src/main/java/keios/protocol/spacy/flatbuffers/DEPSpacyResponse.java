// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.spacy.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class DEPSpacyResponse extends Table {
  public static DEPSpacyResponse getRootAsDEPSpacyResponse(ByteBuffer _bb) { return getRootAsDEPSpacyResponse(_bb, new DEPSpacyResponse()); }
  public static DEPSpacyResponse getRootAsDEPSpacyResponse(ByteBuffer _bb, DEPSpacyResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public DEPSpacyResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String lang() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer langAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer langInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  public String relation() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer relationAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer relationInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  public String source() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer sourceAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public ByteBuffer sourceInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 1); }
  public String sourcePos() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer sourcePosAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public ByteBuffer sourcePosInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 10, 1); }
  public int sourceIndex() { int o = __offset(12); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String sourceTag() { int o = __offset(14); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer sourceTagAsByteBuffer() { return __vector_as_bytebuffer(14, 1); }
  public ByteBuffer sourceTagInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 14, 1); }
  public String sourceBase() { int o = __offset(16); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer sourceBaseAsByteBuffer() { return __vector_as_bytebuffer(16, 1); }
  public ByteBuffer sourceBaseInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 16, 1); }
  public String target() { int o = __offset(18); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer targetAsByteBuffer() { return __vector_as_bytebuffer(18, 1); }
  public ByteBuffer targetInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 18, 1); }
  public String targetPos() { int o = __offset(20); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer targetPosAsByteBuffer() { return __vector_as_bytebuffer(20, 1); }
  public ByteBuffer targetPosInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 20, 1); }
  public int targetIndex() { int o = __offset(22); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String targetTag() { int o = __offset(24); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer targetTagAsByteBuffer() { return __vector_as_bytebuffer(24, 1); }
  public ByteBuffer targetTagInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 24, 1); }
  public String targetBase() { int o = __offset(26); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer targetBaseAsByteBuffer() { return __vector_as_bytebuffer(26, 1); }
  public ByteBuffer targetBaseInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 26, 1); }

  public static int createDEPSpacyResponse(FlatBufferBuilder builder,
      int langOffset,
      int relationOffset,
      int sourceOffset,
      int sourcePosOffset,
      int sourceIndex,
      int sourceTagOffset,
      int sourceBaseOffset,
      int targetOffset,
      int targetPosOffset,
      int targetIndex,
      int targetTagOffset,
      int targetBaseOffset) {
    builder.startObject(12);
    DEPSpacyResponse.addTargetBase(builder, targetBaseOffset);
    DEPSpacyResponse.addTargetTag(builder, targetTagOffset);
    DEPSpacyResponse.addTargetIndex(builder, targetIndex);
    DEPSpacyResponse.addTargetPos(builder, targetPosOffset);
    DEPSpacyResponse.addTarget(builder, targetOffset);
    DEPSpacyResponse.addSourceBase(builder, sourceBaseOffset);
    DEPSpacyResponse.addSourceTag(builder, sourceTagOffset);
    DEPSpacyResponse.addSourceIndex(builder, sourceIndex);
    DEPSpacyResponse.addSourcePos(builder, sourcePosOffset);
    DEPSpacyResponse.addSource(builder, sourceOffset);
    DEPSpacyResponse.addRelation(builder, relationOffset);
    DEPSpacyResponse.addLang(builder, langOffset);
    return DEPSpacyResponse.endDEPSpacyResponse(builder);
  }

  public static void startDEPSpacyResponse(FlatBufferBuilder builder) { builder.startObject(12); }
  public static void addLang(FlatBufferBuilder builder, int langOffset) { builder.addOffset(0, langOffset, 0); }
  public static void addRelation(FlatBufferBuilder builder, int relationOffset) { builder.addOffset(1, relationOffset, 0); }
  public static void addSource(FlatBufferBuilder builder, int sourceOffset) { builder.addOffset(2, sourceOffset, 0); }
  public static void addSourcePos(FlatBufferBuilder builder, int sourcePosOffset) { builder.addOffset(3, sourcePosOffset, 0); }
  public static void addSourceIndex(FlatBufferBuilder builder, int sourceIndex) { builder.addInt(4, sourceIndex, 0); }
  public static void addSourceTag(FlatBufferBuilder builder, int sourceTagOffset) { builder.addOffset(5, sourceTagOffset, 0); }
  public static void addSourceBase(FlatBufferBuilder builder, int sourceBaseOffset) { builder.addOffset(6, sourceBaseOffset, 0); }
  public static void addTarget(FlatBufferBuilder builder, int targetOffset) { builder.addOffset(7, targetOffset, 0); }
  public static void addTargetPos(FlatBufferBuilder builder, int targetPosOffset) { builder.addOffset(8, targetPosOffset, 0); }
  public static void addTargetIndex(FlatBufferBuilder builder, int targetIndex) { builder.addInt(9, targetIndex, 0); }
  public static void addTargetTag(FlatBufferBuilder builder, int targetTagOffset) { builder.addOffset(10, targetTagOffset, 0); }
  public static void addTargetBase(FlatBufferBuilder builder, int targetBaseOffset) { builder.addOffset(11, targetBaseOffset, 0); }
  public static int endDEPSpacyResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
