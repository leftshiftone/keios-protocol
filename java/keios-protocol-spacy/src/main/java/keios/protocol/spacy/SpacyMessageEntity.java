package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.*;
import keios.protocol.spacy.flatbuffers.*;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyMessageEntity extends AbstractMessageEntity<TypedMessage> {

    public SpacyMessageEntity(TypedMessage message) {
        super(message);
    }

    public enum SpacyMessageType implements MessageType {
        REQUEST(keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyRequest),
        BATCH_REQUEST(keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyBatchRequest),
        RESPONSE(keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyResponse),
        BATCH_RESPONSE(keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyBatchResponse);

        private final byte byteVal;

        SpacyMessageType(byte byteVal) {
            this.byteVal = byteVal;
        }

        @Override
        public byte byteVal() {
            return byteVal;
        }
    }

    static class SpacyMessageSerializer implements BinarySerializer<SpacyMessageEntity> {
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

    static class SpacyMessageDeserializer implements BinaryDeserializer<SpacyMessageEntity> {
        @Override
        public SpacyMessageEntity deserialize(ByteBuffer bb) {
            SpacyMessage message = SpacyMessage.getRootAsSpacyMessage(bb);
            switch (message.messageType()) {
                case keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyRequest:
                    return new SpacyMessageEntity(new SpacyRequestEntity.SpacyRequestMapper()
                            .from((SpacyRequest) Objects.requireNonNull(message.message(new SpacyRequest()))));
                case keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyBatchRequest:
                    return new SpacyMessageEntity(new SpacyBatchRequestEntity.SpacyBatchRequestMapper()
                            .from((SpacyBatchRequest) Objects.requireNonNull(message.message(new SpacyBatchRequest()))));
                case keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyResponse:
                    return new SpacyMessageEntity(new SpacyResponseEntity.SpacyResponseMapper()
                            .from((SpacyResponse) Objects.requireNonNull(message.message(new SpacyResponse()))));
                case keios.protocol.spacy.flatbuffers.SpacyMessageType.SpacyBatchResponse:
                    return new SpacyMessageEntity(new SpacyBatchResponseEntity.SpacyBatchResponseMapper()
                            .from((SpacyBatchResponse) Objects.requireNonNull(message.message(new SpacyBatchResponse()))));
                default:
                    throw new IllegalArgumentException("Could not deserialize message");
            }
        }
    }
}
