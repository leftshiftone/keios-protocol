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
import keios.protocol.common.ChildSerializer;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextMostSimilarResponse;

/**
 * @author Michael Mair
 */
public class GensimFastTextMostSimilarResponseSerializer implements ChildSerializer<GensimFastTextMostSimilarResponseEntity> {

    private static final MostSimilarResponseSerializer mostSimilarResponseSerializer = new MostSimilarResponseSerializer();

    @Override
    public int serialize(GensimFastTextMostSimilarResponseEntity obj, FlatBufferBuilder builder) {
        int[] responseOffsets = obj.getResponses().stream()
                .mapToInt(entity -> mostSimilarResponseSerializer.serialize(entity, builder))
                .toArray();
        int responses = GensimFastTextMostSimilarResponse.createResponsesVector(builder, responseOffsets);

        GensimFastTextMostSimilarResponse.startGensimFastTextMostSimilarResponse(builder);
        GensimFastTextMostSimilarResponse.addResponses(builder, responses);
        return GensimFastTextMostSimilarResponse.endGensimFastTextMostSimilarResponse(builder);
    }
}
