package keios.protocol.spacy.entity;

import keios.common.BinaryDeserializer;
import keios.protocol.spacy.flatbuffers.*;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyMessageDeserializer implements BinaryDeserializer<SpacyMessageEntity> {
    @Override
    public SpacyMessageEntity deserialize(ByteBuffer bb) {
        SpacyMessage message = SpacyMessage.getRootAsSpacyMessage(bb);
        switch (message.messageType()) {
            case SpacyMessageType.SpacyRequest:
                return new SpacyMessageEntity<>(new SpacyRequestMapper()
                        .from((SpacyRequest) message.message(new SpacyRequest())));
            case SpacyMessageType.SpacyBatchRequest:
                return new SpacyMessageEntity<>(new SpacyBatchRequestMapper()
                        .from((SpacyBatchRequest) message.message(new SpacyBatchRequest())));
            case SpacyMessageType.SpacyResponse:
                return new SpacyMessageEntity<>(new SpacyResponseMapper()
                        .from((SpacyResponse) message.message(new SpacyResponse())));
            case SpacyMessageType.SpacyBatchResponse:
                return new SpacyMessageEntity<>(new SpacyBatchResponseMapper()
                        .from((SpacyBatchResponse) message.message(new SpacyBatchResponse())));
            default:
                throw new IllegalArgumentException("Could not deserialize message");
        }
    }
}
