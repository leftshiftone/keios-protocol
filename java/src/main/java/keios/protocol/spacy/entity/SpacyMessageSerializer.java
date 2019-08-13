package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.BinarySerializer;
import keios.protocol.spacy.flatbuffers.SpacyMessage;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyMessageSerializer implements BinarySerializer<SpacyMessageEntity> {
    @Override
    public byte[] serialize(SpacyMessageEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int messageOffset = entity.getMessage().serialize(builder);

        SpacyMessage.startSpacyMessage(builder);
        SpacyMessage.addMessageType(builder, entity.getMessage().type().byteVal());
        SpacyMessage.addMessage(builder, messageOffset);

        int resultOffset = SpacyMessage.endSpacyMessage(builder);
        builder.finish(resultOffset);
        return builder.sizedByteArray();
    }
}
