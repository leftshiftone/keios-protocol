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

package keios.protocol.lucene.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class LuceneWriteRequest extends Table {
  public static LuceneWriteRequest getRootAsLuceneWriteRequest(ByteBuffer _bb) { return getRootAsLuceneWriteRequest(_bb, new LuceneWriteRequest()); }
  public static LuceneWriteRequest getRootAsLuceneWriteRequest(ByteBuffer _bb, LuceneWriteRequest obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public LuceneWriteRequest __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Tuple document(int j) { return document(new Tuple(), j); }
  public Tuple document(Tuple obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int documentLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createLuceneWriteRequest(FlatBufferBuilder builder,
      int documentOffset) {
    builder.startObject(1);
    LuceneWriteRequest.addDocument(builder, documentOffset);
    return LuceneWriteRequest.endLuceneWriteRequest(builder);
  }

  public static void startLuceneWriteRequest(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addDocument(FlatBufferBuilder builder, int documentOffset) { builder.addOffset(0, documentOffset, 0); }
  public static int createDocumentVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startDocumentVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endLuceneWriteRequest(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

