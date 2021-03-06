// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.nlpaug.text.augmenter;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SuppressWarnings("unused")
public final class BertAugmenter extends Table {
  public static BertAugmenter getRootAsBertAugmenter(ByteBuffer _bb) { return getRootAsBertAugmenter(_bb, new BertAugmenter()); }
  public static BertAugmenter getRootAsBertAugmenter(ByteBuffer _bb, BertAugmenter obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public BertAugmenter __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public boolean insert() { int o = __offset(4); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public boolean substitute() { int o = __offset(6); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createBertAugmenter(FlatBufferBuilder builder,
      boolean insert,
      boolean substitute) {
    builder.startObject(2);
    BertAugmenter.addSubstitute(builder, substitute);
    BertAugmenter.addInsert(builder, insert);
    return BertAugmenter.endBertAugmenter(builder);
  }

  public static void startBertAugmenter(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addInsert(FlatBufferBuilder builder, boolean insert) { builder.addBoolean(0, insert, false); }
  public static void addSubstitute(FlatBufferBuilder builder, boolean substitute) { builder.addBoolean(1, substitute, false); }
  public static int endBertAugmenter(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

