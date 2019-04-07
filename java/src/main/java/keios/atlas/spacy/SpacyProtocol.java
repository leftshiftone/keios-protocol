package keios.atlas.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.spacy.flatbuffers.SpacyRequest;
import keios.atlas.spacy.flatbuffers.SpacyResponse;

import java.nio.ByteBuffer;

public class SpacyProtocol {

    public static byte[] toSpacyRequest(String text, ESpacyType...types) {
        final FlatBufferBuilder builder = new FlatBufferBuilder();
        int textOffset = builder.createString(text);
        int typeOffset = SpacyRequest.createTypeVector(builder, toBytes(types));

        SpacyRequest.startSpacyRequest(builder);
        SpacyRequest.addText(builder, textOffset);
        SpacyRequest.addType(builder, typeOffset);

        SpacyRequest.endSpacyRequest(builder);
        return builder.dataBuffer().array();
    }

    public static SpacyResponse toSpacyResponse(byte[] bytes) {
        return SpacyResponse.getRootAsSpacyResponse(ByteBuffer.wrap(bytes));
    }

    private static byte[] toBytes(ESpacyType...types) {
        byte[] bytes = new byte[types.length];
        for (int i = 0; i < types.length; i++) {
            bytes[i] = (byte) types[i].ordinal();
        }
        return bytes;
    }

}
