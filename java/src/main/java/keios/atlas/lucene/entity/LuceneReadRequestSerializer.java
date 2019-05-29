package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.common.ChildSerializer;

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
