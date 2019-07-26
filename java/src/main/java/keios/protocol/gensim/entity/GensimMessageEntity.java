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

import keios.protocol.gensim.flatbuffers.GensimMessageType;

/**
 * @author Michael Mair
 */
public class GensimMessageEntity<T extends Message> {

    private final T message;

    public GensimMessageEntity(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    public enum MessageType {
        FASTTEXT_EMBEDDING_REQUEST(GensimMessageType.GensimFastTextEmbeddingRequest),
        FASTTEXT_EMBEDDING_RESPONSE(GensimMessageType.GensimFastTextEmbeddingResponse),
        FASTTEXT_MOST_SIMILAR_REQUEST(GensimMessageType.GensimFastTextMostSimilarRequest),
        FASTTEXT_MOST_SIMILAR_RESPONSE(GensimMessageType.GensimFastTextMostSimilarResponse),
        FASTTEXT_SIMILARITY_REQUEST(GensimMessageType.GensimFastTextSimilarityRequest),
        FASTTEXT_SIMILARITY_RESPONSE(GensimMessageType.GensimFastTextSimilarityResponse),
        NONE(GensimMessageType.NONE);

        final byte byteVal;

        MessageType(byte byteVal) {
            this.byteVal = byteVal;
        }
    }
}
