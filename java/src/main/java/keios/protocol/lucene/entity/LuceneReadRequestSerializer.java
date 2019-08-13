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

package keios.protocol.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.lucene.flatbuffers.LuceneReadRequest;

import java.util.Optional;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadRequestSerializer implements ChildSerializer<LuceneReadRequestEntity> {

    @Override
    public int serialize(LuceneReadRequestEntity obj, FlatBufferBuilder builder) {
        int fieldOffset = builder.createString(obj.getField());
        int queryOffset = builder.createString(obj.getQuery());
        LuceneReadRequest.startLuceneReadRequest(builder);
        LuceneReadRequest.addField(builder, fieldOffset);
        LuceneReadRequest.addQuery(builder, queryOffset);

        Optional.ofNullable(obj.getLimit())
                .ifPresent(limit -> LuceneReadRequest.addLimit(builder, obj.getLimit()));
        Optional.ofNullable(obj.getMinimumScore())
                .ifPresent(minimumScore -> LuceneReadRequest.addMinimumScore(builder, obj.getMinimumScore()));

        return LuceneReadRequest.endLuceneReadRequest(builder);
    }
}
