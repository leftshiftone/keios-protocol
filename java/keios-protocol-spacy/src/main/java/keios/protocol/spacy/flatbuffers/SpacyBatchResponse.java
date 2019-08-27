// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.spacy.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class SpacyBatchResponse extends Table {
  public static SpacyBatchResponse getRootAsSpacyBatchResponse(ByteBuffer _bb) { return getRootAsSpacyBatchResponse(_bb, new SpacyBatchResponse()); }
  public static SpacyBatchResponse getRootAsSpacyBatchResponse(ByteBuffer _bb, SpacyBatchResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public SpacyBatchResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public SpacyResponse responses(int j) { return responses(new SpacyResponse(), j); }
  public SpacyResponse responses(SpacyResponse obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int responsesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createSpacyBatchResponse(FlatBufferBuilder builder,
      int responsesOffset) {
    builder.startObject(1);
    SpacyBatchResponse.addResponses(builder, responsesOffset);
    return SpacyBatchResponse.endSpacyBatchResponse(builder);
  }

  public static void startSpacyBatchResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addResponses(FlatBufferBuilder builder, int responsesOffset) { builder.addOffset(0, responsesOffset, 0); }
  public static int createResponsesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startResponsesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endSpacyBatchResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    builder.required(o, 4);  // responses
    return o;
  }
  public static void finishSpacyBatchResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedSpacyBatchResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }
}
