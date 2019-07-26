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

import keios.common.EntityMapper;
import keios.protocol.gensim.flatbuffers.fasttext.GensimFastTextEmbeddingRequest;

import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Michael Mair
 */
public class GensimFastTextEmbeddingRequestMapper implements EntityMapper<GensimFastTextEmbeddingRequest, GensimFastTextEmbeddingRequestEntity> {

    private static final EmbeddingRequestMapper embeddingRequestMapper = new EmbeddingRequestMapper();

    @Override
    public GensimFastTextEmbeddingRequestEntity from(GensimFastTextEmbeddingRequest input) {
        return new GensimFastTextEmbeddingRequestEntity(IntStream.range(0, input.requestsLength())
                .mapToObj(input::requests)
                .map(embeddingRequestMapper::from)
                .collect(toList()));
    }
}
