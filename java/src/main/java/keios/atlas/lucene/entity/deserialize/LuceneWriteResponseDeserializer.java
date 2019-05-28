package keios.atlas.lucene.entity.deserialize;

import keios.atlas.lucene.entity.LuceneWriteResponseEntity;
import keios.atlas.lucene.entity.WriteResultEntity;
import keios.atlas.lucene.flatbuffers.LuceneWriteResponse;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteResponseDeserializer implements BinaryDeserializer<LuceneWriteResponseEntity> {
    @Override
    public LuceneWriteResponseEntity deserialize(ByteBuffer bb) {
        LuceneWriteResponse response = LuceneWriteResponse.getRootAsLuceneWriteResponse(bb);
        return new LuceneWriteResponseEntity(WriteResultEntity.fromByte(response.writeResult()));
    }
}
