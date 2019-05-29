package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneMessageType;

/**
 * Message wrapper that holds a reference to the actual message. Every {@link Message} must be wrapped
 * by a {@link keios.atlas.lucene.flatbuffers.LuceneMessage} before sending it over the wire.
 *
 * This eases the deserialization on the receiver side by being able to call {@link Message#type()}
 *
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public class LuceneMessageEntity<T extends Message> {

    private final T message;

    public LuceneMessageEntity(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    enum MessageType {
        READ_REQUEST(LuceneMessageType.LuceneReadRequest),
        READ_RESPONSE(LuceneMessageType.LuceneReadResponse),
        WRITE_REQUEST(LuceneMessageType.LuceneWriteRequest),
        WRITE_RESPONSE(LuceneMessageType.LuceneWriteResponse),
        NONE(LuceneMessageType.NONE);

        final byte byteVal;

        MessageType(byte byteVal) {
            this.byteVal = byteVal;
        }
    }
}
