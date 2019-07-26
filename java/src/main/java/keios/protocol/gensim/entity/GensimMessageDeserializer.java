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

package keios.protocol.gensim.entity;

import keios.common.BinaryDeserializer;
import keios.protocol.gensim.entity.fasttext.*;
import keios.protocol.gensim.flatbuffers.GensimMessage;
import keios.protocol.gensim.flatbuffers.GensimMessageType;
import keios.protocol.gensim.flatbuffers.fasttext.*;

import java.nio.ByteBuffer;


/**
 * @author Michael Mair
 */
public class GensimMessageDeserializer implements BinaryDeserializer<GensimMessageEntity> {

    @Override
    public GensimMessageEntity deserialize(ByteBuffer bb) {
        GensimMessage message = GensimMessage.getRootAsGensimMessage(bb);
        int messageType = message.messageType();

        switch (messageType) {
            case GensimMessageType.GensimFastTextEmbeddingRequest:
                GensimFastTextEmbeddingRequest embeddingRequest = (GensimFastTextEmbeddingRequest) message.message(new GensimFastTextEmbeddingRequest());
                assert embeddingRequest != null;
                return new GensimMessageEntity<>(new GensimFastTextEmbeddingRequestMapper().from(embeddingRequest));
            case GensimMessageType.GensimFastTextEmbeddingResponse:
                GensimFastTextEmbeddingResponse embeddingResponse = (GensimFastTextEmbeddingResponse) message.message(new GensimFastTextEmbeddingResponse());
                assert embeddingResponse != null;
                return new GensimMessageEntity<>(new GensimFastTextEmbeddingResponseMapper().from(embeddingResponse));
            case GensimMessageType.GensimFastTextMostSimilarRequest:
                GensimFastTextMostSimilarRequest mostSimilarRequest = (GensimFastTextMostSimilarRequest) message.message(new GensimFastTextMostSimilarRequest());
                assert mostSimilarRequest != null;
                return new GensimMessageEntity<>(new GensimFastTextMostSimilarRequestMapper().from(mostSimilarRequest));
            case GensimMessageType.GensimFastTextMostSimilarResponse:
                GensimFastTextMostSimilarResponse mostSimilarResponse = (GensimFastTextMostSimilarResponse) message.message(new GensimFastTextMostSimilarResponse());
                assert mostSimilarResponse != null;
                return new GensimMessageEntity<>(new GensimFastTextMostSimilarResponseMapper().from(mostSimilarResponse));
            case GensimMessageType.GensimFastTextSimilarityRequest:
                GensimFastTextSimilarityRequest similarityRequest = (GensimFastTextSimilarityRequest) message.message(new GensimFastTextSimilarityRequest());
                assert similarityRequest != null;
                return new GensimMessageEntity<>(new GensimFastTextSimilarityRequestMapper().from(similarityRequest));
            case GensimMessageType.GensimFastTextSimilarityResponse:
                GensimFastTextSimilarityResponse similarityResponse = (GensimFastTextSimilarityResponse) message.message(new GensimFastTextSimilarityResponse());
                assert similarityResponse != null;
                return new GensimMessageEntity<>(new GensimFastTextSimilarityResponseMapper().from(similarityResponse));
            default:
                throw new IllegalArgumentException("could not deserialize message");
        }
    }
}
