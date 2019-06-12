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

package keios.rain.classification.class2.flatbuffers;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class ClassificationClass2Response extends Table {
  public static ClassificationClass2Response getRootAsClassificationClass2Response(ByteBuffer _bb) { return getRootAsClassificationClass2Response(_bb, new ClassificationClass2Response()); }
  public static ClassificationClass2Response getRootAsClassificationClass2Response(ByteBuffer _bb, ClassificationClass2Response obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public ClassificationClass2Response __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Probability response(int j) { return response(new Probability(), j); }
  public Probability response(Probability obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__vector(o) + j * 8, bb) : null; }
  public int responseLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createClassificationClass2Response(FlatBufferBuilder builder,
      int responseOffset) {
    builder.startObject(1);
    ClassificationClass2Response.addResponse(builder, responseOffset);
    return ClassificationClass2Response.endClassificationClass2Response(builder);
  }

  public static void startClassificationClass2Response(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addResponse(FlatBufferBuilder builder, int responseOffset) { builder.addOffset(0, responseOffset, 0); }
  public static void startResponseVector(FlatBufferBuilder builder, int numElems) { builder.startVector(8, numElems, 4); }
  public static int endClassificationClass2Response(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishClassificationClass2ResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedClassificationClass2ResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }
}

