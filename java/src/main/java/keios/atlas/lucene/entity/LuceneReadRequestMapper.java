package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.common.EntityMapper;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadRequestMapper implements EntityMapper<LuceneReadRequest, LuceneReadRequestEntity> {
    @Override
    public LuceneReadRequestEntity from(LuceneReadRequest input) {
        return new LuceneReadRequestEntity(input.field(), input.query(), input.minimumScore(), input.limit());
    }
}
