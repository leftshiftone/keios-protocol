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
public final class MostSimilarRequest extends Table {
  public static MostSimilarRequest getRootAsMostSimilarRequest(ByteBuffer _bb) { return getRootAsMostSimilarRequest(_bb, new MostSimilarRequest()); }
  public static MostSimilarRequest getRootAsMostSimilarRequest(ByteBuffer _bb, MostSimilarRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public MostSimilarRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String text() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer textAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer textInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }

  public static int createMostSimilarRequest(FlatBufferBuilder builder,
      int textOffset) {
    builder.startObject(1);
    MostSimilarRequest.addText(builder, textOffset);
    return MostSimilarRequest.endMostSimilarRequest(builder);
  }

  public static void startMostSimilarRequest(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addText(FlatBufferBuilder builder, int textOffset) { builder.addOffset(0, textOffset, 0); }
  public static int endMostSimilarRequest(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
