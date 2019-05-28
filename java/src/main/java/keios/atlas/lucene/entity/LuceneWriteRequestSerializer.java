package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneWriteRequest;
import keios.common.BinarySerializer;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
class LuceneWriteRequestSerializer implements BinarySerializer<LuceneWriteRequestEntity> {

    private final DocumentChildSerializer documentChildSerializer = new DocumentChildSerializer();

    @Override
    public byte[] serialize(LuceneWriteRequestEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        int documentOffset = documentChildSerializer.serialize(entity.getDocument(), builder);

        LuceneWriteRequest.startLuceneWriteRequest(builder);
        LuceneWriteRequest.addDocument(builder, documentOffset);
        int tableOffset = LuceneWriteRequest.endLuceneWriteRequest(builder);
        builder.finish(tableOffset);

        return builder.sizedByteArray();
    }
}
