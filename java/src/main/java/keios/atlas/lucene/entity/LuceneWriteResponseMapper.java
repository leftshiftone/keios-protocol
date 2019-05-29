package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneWriteResponse;
import keios.common.EntityMapper;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneWriteResponseMapper implements EntityMapper<LuceneWriteResponse, LuceneWriteResponseEntity> {
    @Override
    public LuceneWriteResponseEntity from(LuceneWriteResponse input) {
        return new LuceneWriteResponseEntity(WriteResultEntity.fromByte(input.writeResult()));
    }
}
