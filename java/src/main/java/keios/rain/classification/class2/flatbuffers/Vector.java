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
public final class Vector extends Table {
  public static Vector getRootAsVector(ByteBuffer _bb) { return getRootAsVector(_bb, new Vector()); }
  public static Vector getRootAsVector(ByteBuffer _bb, Vector obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public Vector __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public byte bytes(int j) { int o = __offset(4); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int bytesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer bytesAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer bytesInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }

  public static int createVector(FlatBufferBuilder builder,
      int bytesOffset) {
    builder.startObject(1);
    Vector.addBytes(builder, bytesOffset);
    return Vector.endVector(builder);
  }

  public static void startVector(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addBytes(FlatBufferBuilder builder, int bytesOffset) { builder.addOffset(0, bytesOffset, 0); }
  public static int createBytesVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startBytesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int endVector(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

