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
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextMostSimilarRequest;

/**
 * @author Michael Mair
 */
public class GensimFastTextMostSimilarRequestSerializer implements ChildSerializer<GensimFastTextMostSimilarRequestEntity> {

    private static final MostSimilarRequestSerializer mostSimilarRequestSerializer = new MostSimilarRequestSerializer();

    @Override
    public int serialize(GensimFastTextMostSimilarRequestEntity obj, FlatBufferBuilder builder) {

        int[] requestOffsets = obj.getRequests().stream()
                .mapToInt(entity -> mostSimilarRequestSerializer.serialize(entity, builder))
                .toArray();
        int requests = GensimFastTextMostSimilarRequest.createRequestsVector(builder, requestOffsets);

        GensimFastTextMostSimilarRequest.startGensimFastTextMostSimilarRequest(builder);
        GensimFastTextMostSimilarRequest.addRequests(builder, requests);
        return GensimFastTextMostSimilarRequest.endGensimFastTextMostSimilarRequest(builder);
    }
}
