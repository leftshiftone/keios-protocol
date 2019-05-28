package keios.atlas.lucene.entity.serialize;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.entity.LuceneReadRequestEntity;
import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.common.BinarySerializer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadRequestSerializer implements BinarySerializer<LuceneReadRequestEntity> {
    @Override
    public byte[] serialize(LuceneReadRequestEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int fieldOffset = builder.createString(entity.getField());
        int queryOffset = builder.createString(entity.getQuery());
        LuceneReadRequest.startLuceneReadRequest(builder);
        LuceneReadRequest.addField(builder, fieldOffset);
        LuceneReadRequest.addQuery(builder, queryOffset);

        if (entity.getLimit() != null) {
            LuceneReadRequest.addLimit(builder, entity.getLimit());
        }

        if (entity.getMinimumScore() != null) {
            LuceneReadRequest.addMinimumScore(builder, entity.getMinimumScore());
        }

        int request = LuceneReadRequest.endLuceneReadRequest(builder);
        builder.finish(request);

        return builder.sizedByteArray();
    }
}
