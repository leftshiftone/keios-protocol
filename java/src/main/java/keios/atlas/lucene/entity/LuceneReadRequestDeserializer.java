package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadRequestDeserializer implements BinaryDeserializer<LuceneReadRequestEntity> {

    @Override
    public LuceneReadRequestEntity deserialize(ByteBuffer bb) {
        LuceneReadRequest readRequest = LuceneReadRequest.getRootAsLuceneReadRequest(bb);
        return new LuceneReadRequestEntity(readRequest.field(), readRequest.query(), readRequest.minimumScore(), readRequest.limit());
    }
}
