package keios.protocol.gensim;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.gensim.flatbuffers.factory.GensimFastTextEmbeddingFactory;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingRequest;
import keios.protocol.gensim.flatbuffers.GensimMessage;
import keios.protocol.gensim.flatbuffers.GensimMessageType;

public class GensimProtocol {

    public static byte[] fastTextEmbeddingRequest(String text) {
        return GensimFastTextEmbeddingFactory.createGensimFastTextEmbeddingRequest(text);
    }

    public static byte[] fastTextEmbeddingResponse(float[] vector) {
        return GensimFastTextEmbeddingFactory.createGensimFastTextEmbeddingResponse(vector);
    }

}
