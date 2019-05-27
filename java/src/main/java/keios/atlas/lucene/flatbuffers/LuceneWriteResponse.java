// automatically generated by the FlatBuffers compiler, do not modify

package keios.atlas.lucene.flatbuffers;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class LuceneWriteResponse extends Table {
  public static LuceneWriteResponse getRootAsLuceneWriteResponse(ByteBuffer _bb) { return getRootAsLuceneWriteResponse(_bb, new LuceneWriteResponse()); }
  public static LuceneWriteResponse getRootAsLuceneWriteResponse(ByteBuffer _bb, LuceneWriteResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public LuceneWriteResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public byte writeResult() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createLuceneWriteResponse(FlatBufferBuilder builder,
      byte writeResult) {
    builder.startObject(1);
    LuceneWriteResponse.addWriteResult(builder, writeResult);
    return LuceneWriteResponse.endLuceneWriteResponse(builder);
  }

  public static void startLuceneWriteResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addWriteResult(FlatBufferBuilder builder, byte writeResult) { builder.addByte(0, writeResult, 0); }
  public static int endLuceneWriteResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

