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

package keios.protocol.gensim.entity.fasttext;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingResponse;

/**
 * @author Michael Mair
 */
public class GensimFastTextEmbeddingResponseSerializer implements ChildSerializer<GensimFastTextEmbeddingResponseEntity> {

    private static final EmbeddingResponseSerializer embeddingResponseSerializer = new EmbeddingResponseSerializer();

    @Override
    public int serialize(GensimFastTextEmbeddingResponseEntity obj, FlatBufferBuilder builder) {

        int[] responseOffsets = obj.getResponses().stream()
                .mapToInt(entity -> embeddingResponseSerializer.serialize(entity, builder))
                .toArray();
        int responses = GensimFastTextEmbeddingResponse.createResponsesVector(builder, responseOffsets);

        GensimFastTextEmbeddingResponse.startGensimFastTextEmbeddingResponse(builder);
        GensimFastTextEmbeddingResponse.addResponses(builder, responses);
        return GensimFastTextEmbeddingResponse.endGensimFastTextEmbeddingResponse(builder);
    }
}
