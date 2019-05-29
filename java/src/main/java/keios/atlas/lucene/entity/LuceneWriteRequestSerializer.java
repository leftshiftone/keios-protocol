package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneWriteRequest;
import keios.common.ChildSerializer;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
class LuceneWriteRequestSerializer implements ChildSerializer<LuceneWriteRequestEntity> {

    private final DocumentSerializer documentSerializer = new DocumentSerializer();

    @Override
    public int serialize(LuceneWriteRequestEntity obj, FlatBufferBuilder builder) {
        int documentOffset = documentSerializer.serialize(obj.getDocument(), builder);

        LuceneWriteRequest.startLuceneWriteRequest(builder);
        LuceneWriteRequest.addDocument(builder, documentOffset);
        return LuceneWriteRequest.endLuceneWriteRequest(builder);
    }
}
