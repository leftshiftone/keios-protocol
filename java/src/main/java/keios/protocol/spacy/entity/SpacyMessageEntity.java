package keios.protocol.spacy.entity;

import keios.common.AbstractMessageEntity;
import keios.common.Message;
import keios.common.MessageType;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyMessageEntity<T extends Message> extends AbstractMessageEntity<T> {
    public SpacyMessageEntity(T message) {
        super(message);
    }

    enum SpacyMessageType implements MessageType {
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
}
