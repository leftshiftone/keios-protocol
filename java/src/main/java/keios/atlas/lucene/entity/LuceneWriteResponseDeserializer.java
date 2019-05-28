package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneWriteResponse;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneWriteResponseDeserializer implements BinaryDeserializer<LuceneWriteResponseEntity> {
    @Override
    public LuceneWriteResponseEntity deserialize(ByteBuffer bb) {
        LuceneWriteResponse response = LuceneWriteResponse.getRootAsLuceneWriteResponse(bb);
        return new LuceneWriteResponseEntity(WriteResultEntity.fromByte(response.writeResult()));
    }
}
