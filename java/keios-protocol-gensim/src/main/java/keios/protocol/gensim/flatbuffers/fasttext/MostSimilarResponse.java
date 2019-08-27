/*
 * Copyright (c) 2016-2019, Leftshift One
 * __________________
 * [2019] Leftshift One
 * All Rights Reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Leftshift One and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Leftshift One
 * and its suppliers and may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Leftshift One.
 */

// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.gensim.flatbuffers.fasttext;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class MostSimilarResponse extends Table {
  public static MostSimilarResponse getRootAsMostSimilarResponse(ByteBuffer _bb) { return getRootAsMostSimilarResponse(_bb, new MostSimilarResponse()); }
  public static MostSimilarResponse getRootAsMostSimilarResponse(ByteBuffer _bb, MostSimilarResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public MostSimilarResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public MostSimilarity similarities(int j) { return similarities(new MostSimilarity(), j); }
  public MostSimilarity similarities(MostSimilarity obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int similaritiesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createMostSimilarResponse(FlatBufferBuilder builder,
      int similaritiesOffset) {
    builder.startObject(1);
    MostSimilarResponse.addSimilarities(builder, similaritiesOffset);
    return MostSimilarResponse.endMostSimilarResponse(builder);
  }

  public static void startMostSimilarResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addSimilarities(FlatBufferBuilder builder, int similaritiesOffset) { builder.addOffset(0, similaritiesOffset, 0); }
  public static int createSimilaritiesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startSimilaritiesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endMostSimilarResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
