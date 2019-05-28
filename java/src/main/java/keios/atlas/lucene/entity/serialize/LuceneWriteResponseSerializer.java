package keios.atlas.lucene.entity.serialize;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.entity.LuceneWriteResponseEntity;
import keios.atlas.lucene.flatbuffers.LuceneWriteResponse;
import keios.common.BinarySerializer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteResponseSerializer implements BinarySerializer<LuceneWriteResponseEntity> {
    @Override
    public byte[] serialize(LuceneWriteResponseEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        LuceneWriteResponse.startLuceneWriteResponse(builder);
        LuceneWriteResponse.addWriteResult(builder, entity.getWriteResult().getAsByte());
        int request = LuceneWriteResponse.endLuceneWriteResponse(builder);
        builder.finish(request);

        return builder.sizedByteArray();
    }
}
