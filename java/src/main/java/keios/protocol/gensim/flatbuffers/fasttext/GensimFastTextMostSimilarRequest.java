// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.gensim.flatbuffers.fasttext;

import java.nio.*;
import java.lang.*;

import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class GensimFastTextMostSimilarRequest extends Table {
  public static GensimFastTextMostSimilarRequest getRootAsGensimFastTextMostSimilarRequest(ByteBuffer _bb) { return getRootAsGensimFastTextMostSimilarRequest(_bb, new GensimFastTextMostSimilarRequest()); }
  public static GensimFastTextMostSimilarRequest getRootAsGensimFastTextMostSimilarRequest(ByteBuffer _bb, GensimFastTextMostSimilarRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public GensimFastTextMostSimilarRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String text() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer textAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer textInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }

  public static int createGensimFastTextMostSimilarRequest(FlatBufferBuilder builder,
      int textOffset) {
    builder.startObject(1);
    GensimFastTextMostSimilarRequest.addText(builder, textOffset);
    return GensimFastTextMostSimilarRequest.endGensimFastTextMostSimilarRequest(builder);
  }

  public static void startGensimFastTextMostSimilarRequest(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addText(FlatBufferBuilder builder, int textOffset) { builder.addOffset(0, textOffset, 0); }
  public static int endGensimFastTextMostSimilarRequest(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

