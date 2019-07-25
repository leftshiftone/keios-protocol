package keios.protocol.gensim.flatbuffers.factory;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.gensim.flatbuffers.GensimMessage;
import keios.protocol.gensim.flatbuffers.GensimMessageType;
import keios.protocol.gensim.flatbuffers.fasttext.EmbeddingRequest;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingRequest;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingResponse;

import java.nio.ByteBuffer;

public class GensimFastTextEmbeddingFactory {

    public static byte[] createGensimFastTextEmbeddingRequest(String text) {
        final FlatBufferBuilder builder = new FlatBufferBuilder();

        final int textOffset = builder.createString(text);
        EmbeddingRequest.startEmbeddingRequest(builder);
        EmbeddingRequest.addText(builder, textOffset);
        final int offset = EmbeddingRequest.endEmbeddingRequest(builder);

        GensimFastTextEmbeddingRequest.startGensimFastTextEmbeddingRequest(builder);
        GensimFastTextEmbeddingRequest.addRequests(builder, offset);
        int message = GensimFastTextEmbeddingRequest.endGensimFastTextEmbeddingRequest(builder);

        GensimMessage.startGensimMessage(builder);
        GensimMessage.addMessageType(builder, GensimMessageType.GensimFastTextEmbeddingRequest);
        GensimMessage.addMessage(builder, message);

        final int msgOffset = GensimMessage.endGensimMessage(builder);
        builder.finish(msgOffset);

        return builder.sizedByteArray();
    }

    public static GensimFastTextEmbeddingResponse createGensimFastTextEmbeddingResponse(byte[] bytes) {
        return GensimFastTextEmbeddingResponse.getRootAsGensimFastTextEmbeddingResponse(ByteBuffer.wrap(bytes));
    }

}
