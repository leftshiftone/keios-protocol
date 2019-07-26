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

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class SimilarityResponse extends Table {
  public static SimilarityResponse getRootAsSimilarityResponse(ByteBuffer _bb) { return getRootAsSimilarityResponse(_bb, new SimilarityResponse()); }
  public static SimilarityResponse getRootAsSimilarityResponse(ByteBuffer _bb, SimilarityResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public SimilarityResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public float value() { int o = __offset(4); return o != 0 ? bb.getFloat(o + bb_pos) : 0.0f; }

  public static int createSimilarityResponse(FlatBufferBuilder builder,
      float value) {
    builder.startObject(1);
    SimilarityResponse.addValue(builder, value);
    return SimilarityResponse.endSimilarityResponse(builder);
  }

  public static void startSimilarityResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addValue(FlatBufferBuilder builder, float value) { builder.addFloat(0, value, 0.0f); }
  public static int endSimilarityResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

