// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.gensim.flatbuffers.fasttext;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class GensimFastTextSimilarityRequest extends Table {
  public static GensimFastTextSimilarityRequest getRootAsGensimFastTextSimilarityRequest(ByteBuffer _bb) { return getRootAsGensimFastTextSimilarityRequest(_bb, new GensimFastTextSimilarityRequest()); }
  public static GensimFastTextSimilarityRequest getRootAsGensimFastTextSimilarityRequest(ByteBuffer _bb, GensimFastTextSimilarityRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public GensimFastTextSimilarityRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String text1() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer text1AsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer text1InByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  public String text2() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer text2AsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer text2InByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }

  public static int createGensimFastTextSimilarityRequest(FlatBufferBuilder builder,
      int text1Offset,
      int text2Offset) {
    builder.startObject(2);
    GensimFastTextSimilarityRequest.addText2(builder, text2Offset);
    GensimFastTextSimilarityRequest.addText1(builder, text1Offset);
    return GensimFastTextSimilarityRequest.endGensimFastTextSimilarityRequest(builder);
  }

  public static void startGensimFastTextSimilarityRequest(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addText1(FlatBufferBuilder builder, int text1Offset) { builder.addOffset(0, text1Offset, 0); }
  public static void addText2(FlatBufferBuilder builder, int text2Offset) { builder.addOffset(1, text2Offset, 0); }
  public static int endGensimFastTextSimilarityRequest(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

