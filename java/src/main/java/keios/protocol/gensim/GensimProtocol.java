package keios.protocol.gensim;

import keios.protocol.gensim.flatbuffers.factory.GensimFastTextEmbeddingFactory;
import keios.protocol.gensim.flatbuffers.factory.GensimFastTextSimilarityFactory;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingResponse;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextSimilarityResponse;

public class GensimProtocol {

    public static byte[] fastTextEmbeddingRequest(String text) {
        return GensimFastTextEmbeddingFactory.createGensimFastTextEmbeddingRequest(text);
    }

    public static GensimFastTextEmbeddingResponse fastTextEmbeddingResponse(byte[] bytes) {
        return GensimFastTextEmbeddingFactory.createGensimFastTextEmbeddingResponse(bytes);
    }

    public static byte[] fastTextSimilarityRequest(String text1, String text2) {
        return GensimFastTextSimilarityFactory.createGensimFastTextSimilarityRequest(text1, text2);
    }

    public static GensimFastTextSimilarityResponse fastTextSimilarityResponse(byte[] bytes) {
        return GensimFastTextSimilarityFactory.createGensimFastTextSimilarityResponse(bytes);
    }

}
