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

import keios.protocol.common.EntityMapper;
import keios.protocol.gensim.flatbuffers.fasttext.EmbeddingRequest;

/**
 * @author Michael Mair
 */
public class EmbeddingRequestMapper implements EntityMapper<EmbeddingRequest, EmbeddingRequestEntity> {

    @Override
    public EmbeddingRequestEntity from(EmbeddingRequest input) {
        return new EmbeddingRequestEntity(input.text());
    }
}
