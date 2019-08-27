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
public final class GensimFastTextMostSimilarResponse extends Table {
  public static GensimFastTextMostSimilarResponse getRootAsGensimFastTextMostSimilarResponse(ByteBuffer _bb) { return getRootAsGensimFastTextMostSimilarResponse(_bb, new GensimFastTextMostSimilarResponse()); }
  public static GensimFastTextMostSimilarResponse getRootAsGensimFastTextMostSimilarResponse(ByteBuffer _bb, GensimFastTextMostSimilarResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public GensimFastTextMostSimilarResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public MostSimilarResponse responses(int j) { return responses(new MostSimilarResponse(), j); }
  public MostSimilarResponse responses(MostSimilarResponse obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int responsesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createGensimFastTextMostSimilarResponse(FlatBufferBuilder builder,
      int responsesOffset) {
    builder.startObject(1);
    GensimFastTextMostSimilarResponse.addResponses(builder, responsesOffset);
    return GensimFastTextMostSimilarResponse.endGensimFastTextMostSimilarResponse(builder);
  }

  public static void startGensimFastTextMostSimilarResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addResponses(FlatBufferBuilder builder, int responsesOffset) { builder.addOffset(0, responsesOffset, 0); }
  public static int createResponsesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startResponsesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endGensimFastTextMostSimilarResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
