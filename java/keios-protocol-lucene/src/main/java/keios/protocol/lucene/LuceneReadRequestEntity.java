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

package keios.protocol.lucene;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.common.TypedMessage;
import keios.protocol.lucene.flatbuffers.LuceneReadRequest;

import java.util.Objects;
import java.util.Optional;

import static keios.protocol.lucene.LuceneMessageEntity.LuceneMessageType.READ_REQUEST;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadRequestEntity implements TypedMessage {
    private final String field;
    private final String query;
    private final Float minimumScore;
    private final Integer limit;

    private final ChildSerializer<LuceneReadRequestEntity> serializer = new LuceneReadRequestSerializer();

    public LuceneReadRequestEntity(String field, String query, Float minimumScore, Integer limit) {
        this.field = Objects.requireNonNull(field, "field can not be null");
        this.query = Objects.requireNonNull(query, "query can not be null");
        this.minimumScore = minimumScore;
        this.limit = limit;
    }

    public LuceneReadRequestEntity(String field, String query, Float minimumScore) {
        this.field = field;
        this.query = query;
        this.minimumScore = minimumScore;
        this.limit = null;
    }

    public LuceneReadRequestEntity(String field, String query) {
        this.field = field;
        this.query = query;
        this.minimumScore = null;
        this.limit = null;
    }

    public String getField() {
        return field;
    }

    public String getQuery() {
        return query;
    }

    public Float getMinimumScore() {
        return minimumScore;
    }

    public Integer getLimit() {
        return limit;
    }

    @Override
    public LuceneMessageEntity.LuceneMessageType type() {
        return READ_REQUEST;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneReadRequestEntity that = (LuceneReadRequestEntity) o;
        return field.equals(that.field) &&
                query.equals(that.query) &&
                Objects.equals(minimumScore, that.minimumScore) &&
                Objects.equals(limit, that.limit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, query, minimumScore, limit);
    }

    /**
     * @author benjamin.krenn@leftshift.one
     * @since 0.3.0
     */
    static class LuceneReadRequestMapper implements EntityMapper<LuceneReadRequest, LuceneReadRequestEntity> {
        @Override
        public LuceneReadRequestEntity from(LuceneReadRequest input) {
            return new LuceneReadRequestEntity(input.field(), input.query(), input.minimumScore(), input.limit());
        }
    }

    private static class LuceneReadRequestSerializer implements ChildSerializer<LuceneReadRequestEntity> {

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

}
