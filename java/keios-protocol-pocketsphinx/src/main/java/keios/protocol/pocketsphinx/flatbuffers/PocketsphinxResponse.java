// automatically generated by the FlatBuffers compiler, do not modify

package keios.protocol.pocketsphinx.flatbuffers;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class PocketsphinxResponse extends Table {
  public static PocketsphinxResponse getRootAsPocketsphinxResponse(ByteBuffer _bb) { return getRootAsPocketsphinxResponse(_bb, new PocketsphinxResponse()); }
  public static PocketsphinxResponse getRootAsPocketsphinxResponse(ByteBuffer _bb, PocketsphinxResponse obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; vtable_start = bb_pos - bb.getInt(bb_pos); vtable_size = bb.getShort(vtable_start); }
  public PocketsphinxResponse __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Guess guesses(int j) { return guesses(new Guess(), j); }
  public Guess guesses(Guess obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int guessesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }

  public static int createPocketsphinxResponse(FlatBufferBuilder builder,
      int guessesOffset) {
    builder.startObject(1);
    PocketsphinxResponse.addGuesses(builder, guessesOffset);
    return PocketsphinxResponse.endPocketsphinxResponse(builder);
  }

  public static void startPocketsphinxResponse(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addGuesses(FlatBufferBuilder builder, int guessesOffset) { builder.addOffset(0, guessesOffset, 0); }
  public static int createGuessesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startGuessesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endPocketsphinxResponse(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishPocketsphinxResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedPocketsphinxResponseBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }
}

