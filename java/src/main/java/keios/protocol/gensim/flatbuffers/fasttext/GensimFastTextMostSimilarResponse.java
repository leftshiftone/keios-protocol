// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.gensim.flatbuffers.fasttext;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class GensimFastTextMostSimilarResponse extends Table {
  public static GensimFastTextMostSimilarResponse getRootAsGensimFastTextMostSimilarResponse(ByteBuffer _bb) { return getRootAsGensimFastTextMostSimilarResponse(_bb, new GensimFastTextMostSimilarResponse()); }
  public static GensimFastTextMostSimilarResponse getRootAsGensimFastTextMostSimilarResponse(ByteBuffer _bb, GensimFastTextMostSimilarResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public GensimFastTextMostSimilarResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Similarity similarities(int j) { return similarities(new Similarity(), j); }
  public Similarity similarities(Similarity obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int similaritiesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createGensimFastTextMostSimilarResponse(FlatBufferBuilder builder,
      int similaritiesOffset) {
    builder.startObject(1);
    GensimFastTextMostSimilarResponse.addSimilarities(builder, similaritiesOffset);
    return GensimFastTextMostSimilarResponse.endGensimFastTextMostSimilarResponse(builder);
  }

  public static void startGensimFastTextMostSimilarResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addSimilarities(FlatBufferBuilder builder, int similaritiesOffset) { builder.addOffset(0, similaritiesOffset, 0); }
  public static int createSimilaritiesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startSimilaritiesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endGensimFastTextMostSimilarResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

