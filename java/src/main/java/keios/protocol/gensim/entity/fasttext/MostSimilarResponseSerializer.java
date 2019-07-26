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
import keios.protocol.gensim.flatbuffers.fasttext.MostSimilarResponse;

/**
 * @author Michael Mair
 */
public class MostSimilarResponseSerializer implements ChildSerializer<MostSimilarResponseEntity> {

    private static final MostSimilaritySerializer mostSimilaritySerializer = new MostSimilaritySerializer();

    @Override
    public int serialize(MostSimilarResponseEntity obj, FlatBufferBuilder builder) {

        int[] similarityOffsets = obj.getSimilarities().stream()
                .mapToInt(entity -> mostSimilaritySerializer.serialize(entity, builder))
                .toArray();

        int similarities = MostSimilarResponse.createSimilaritiesVector(builder, similarityOffsets);

        MostSimilarResponse.startMostSimilarResponse(builder);
        MostSimilarResponse.addSimilarities(builder, similarities);
        return MostSimilarResponse.endMostSimilarResponse(builder);
    }
}
