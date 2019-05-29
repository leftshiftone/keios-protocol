package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneMessage;
import keios.common.BinarySerializer;

/**
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.1.0
 */
public class LuceneMessageSerializer implements BinarySerializer<LuceneMessageEntity> {
    @Override
    public byte[] serialize(LuceneMessageEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        int messageOffset = entity.getMessage().serialize(builder);

        LuceneMessage.startLuceneMessage(builder);
        LuceneMessage.addMessageType(builder, entity.getMessage().type().byteVal);
        LuceneMessage.addMessage(builder, messageOffset);
        int resultOffset = LuceneMessage.endLuceneMessage(builder);
        builder.finish(resultOffset);
        return builder.sizedByteArray();
    }
}
