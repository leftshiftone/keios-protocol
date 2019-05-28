package keios.atlas.lucene.entity.deserialize;

import keios.atlas.lucene.entity.LuceneReadRequestEntity;
import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadRequestDeserializer implements BinaryDeserializer<LuceneReadRequestEntity> {

    @Override
    public LuceneReadRequestEntity deserialize(ByteBuffer bb) {
        LuceneReadRequest readRequest = LuceneReadRequest.getRootAsLuceneReadRequest(bb);
        return new LuceneReadRequestEntity(readRequest.field(), readRequest.query(), readRequest.minimumScore(), readRequest.limit());
    }
}
