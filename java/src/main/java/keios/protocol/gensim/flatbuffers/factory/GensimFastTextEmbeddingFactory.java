package keios.protocol.gensim.flatbuffers.factory;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.gensim.flatbuffers.GensimMessage;
import keios.protocol.gensim.flatbuffers.GensimMessageType;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingRequest;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingResponse;

public class GensimFastTextEmbeddingFactory {

    public static byte[] createGensimFastTextEmbeddingRequest(String text) {
        final FlatBufferBuilder builder = new FlatBufferBuilder();

        final int textOffset = builder.createString(text);

        GensimFastTextEmbeddingRequest.startGensimFastTextEmbeddingRequest(builder);
        GensimFastTextEmbeddingRequest.addText(builder, textOffset);
        int message = GensimFastTextEmbeddingRequest.endGensimFastTextEmbeddingRequest(builder);

        GensimMessage.startGensimMessage(builder);
        GensimMessage.addMessageType(builder, GensimMessageType.GensimFastTextEmbeddingRequest);
        GensimMessage.addMessage(builder, message);

        final int msgOffset = GensimMessage.endGensimMessage(builder);
        builder.finish(msgOffset);

        return builder.sizedByteArray();
    }

    public static byte[] createGensimFastTextEmbeddingResponse(float[] embedding) {
        final FlatBufferBuilder builder = new FlatBufferBuilder();

        final int offset = GensimFastTextEmbeddingResponse.createVectorVector(builder, embedding);

        GensimFastTextEmbeddingResponse.startGensimFastTextEmbeddingResponse(builder);
        GensimFastTextEmbeddingResponse.addVector(builder, offset);
        int message = GensimFastTextEmbeddingResponse.endGensimFastTextEmbeddingResponse(builder);

        GensimMessage.startGensimMessage(builder);
        GensimMessage.addMessageType(builder, GensimMessageType.GensimFastTextEmbeddingResponse);
        GensimMessage.addMessage(builder, message);

        final int msgOffset = GensimMessage.endGensimMessage(builder);
        builder.finish(msgOffset);

        return builder.sizedByteArray();
    }

}
