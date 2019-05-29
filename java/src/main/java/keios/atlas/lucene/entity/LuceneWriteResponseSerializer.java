package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneWriteResponse;
import keios.common.ChildSerializer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneWriteResponseSerializer implements ChildSerializer<LuceneWriteResponseEntity> {
    @Override
    public int serialize(LuceneWriteResponseEntity obj, FlatBufferBuilder builder) {
        LuceneWriteResponse.startLuceneWriteResponse(builder);
        LuceneWriteResponse.addWriteResult(builder, obj.getWriteResult().getAsByte());
        return LuceneWriteResponse.endLuceneWriteResponse(builder);
    }
}
