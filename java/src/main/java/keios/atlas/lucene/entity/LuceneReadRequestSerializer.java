package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.common.BinarySerializer;

import java.util.Optional;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadRequestSerializer implements BinarySerializer<LuceneReadRequestEntity> {
    @Override
    public byte[] serialize(LuceneReadRequestEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int fieldOffset = builder.createString(entity.getField());
        int queryOffset = builder.createString(entity.getQuery());
        LuceneReadRequest.startLuceneReadRequest(builder);
        LuceneReadRequest.addField(builder, fieldOffset);
        LuceneReadRequest.addQuery(builder, queryOffset);

        Optional.ofNullable(entity.getLimit())
                .ifPresent(limit -> LuceneReadRequest.addLimit(builder, entity.getLimit()));
        Optional.ofNullable(entity.getMinimumScore())
                .ifPresent(minimumScore -> LuceneReadRequest.addMinimumScore(builder, entity.getMinimumScore()));

        int request = LuceneReadRequest.endLuceneReadRequest(builder);
        builder.finish(request);

        return builder.sizedByteArray();
    }
}
