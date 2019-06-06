// automatically generated by the FlatBuffers compiler, do not modify

package keios.atlas.lucene.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class LuceneMessage extends Table {
  public static LuceneMessage getRootAsLuceneMessage(ByteBuffer _bb) { return getRootAsLuceneMessage(_bb, new LuceneMessage()); }
  public static LuceneMessage getRootAsLuceneMessage(ByteBuffer _bb, LuceneMessage obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public LuceneMessage __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public byte messageType() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public Table message(Table obj) { int o = __offset(6); return o != 0 ? __union(obj, o) : null; }

  public static int createLuceneMessage(FlatBufferBuilder builder,
      byte message_type,
      int messageOffset) {
    builder.startObject(2);
    LuceneMessage.addMessage(builder, messageOffset);
    LuceneMessage.addMessageType(builder, message_type);
    return LuceneMessage.endLuceneMessage(builder);
  }

  public static void startLuceneMessage(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addMessageType(FlatBufferBuilder builder, byte messageType) { builder.addByte(0, messageType, 0); }
  public static void addMessage(FlatBufferBuilder builder, int messageOffset) { builder.addOffset(1, messageOffset, 0); }
  public static int endLuceneMessage(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}
