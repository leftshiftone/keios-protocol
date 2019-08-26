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

import keios.protocol.common.AbstractMessageEntity;
import keios.protocol.common.Message;
import keios.protocol.common.MessageType;

/**
 * @author Michael Mair
 */
public class GensimMessageEntity extends AbstractMessageEntity<Message> {

    public GensimMessageEntity(Message message) {
        super(message);
    }

    public enum GensimMessageType implements MessageType {
        FASTTEXT_EMBEDDING_REQUEST(keios.protocol.gensim.flatbuffers.GensimMessageType.GensimFastTextEmbeddingRequest),
        FASTTEXT_EMBEDDING_RESPONSE(keios.protocol.gensim.flatbuffers.GensimMessageType.GensimFastTextEmbeddingResponse),
        FASTTEXT_MOST_SIMILAR_REQUEST(keios.protocol.gensim.flatbuffers.GensimMessageType.GensimFastTextMostSimilarRequest),
        FASTTEXT_MOST_SIMILAR_RESPONSE(keios.protocol.gensim.flatbuffers.GensimMessageType.GensimFastTextMostSimilarResponse),
        FASTTEXT_SIMILARITY_REQUEST(keios.protocol.gensim.flatbuffers.GensimMessageType.GensimFastTextSimilarityRequest),
        FASTTEXT_SIMILARITY_RESPONSE(keios.protocol.gensim.flatbuffers.GensimMessageType.GensimFastTextSimilarityResponse),
        NONE(keios.protocol.gensim.flatbuffers.GensimMessageType.NONE);

        final byte byteVal;

        GensimMessageType(byte byteVal) {
            this.byteVal = byteVal;
        }

        @Override
        public byte byteVal() {
            return this.byteVal;
        }
    }
}
