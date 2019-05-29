package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.*;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public class LuceneMessageDeserializer implements BinaryDeserializer<LuceneMessageEntity> {
    @Override
    public LuceneMessageEntity<Message> deserialize(ByteBuffer bb) {
        LuceneMessage message = LuceneMessage.getRootAsLuceneMessage(bb);
        int messageType = message.messageType();
        switch (messageType) {
            case LuceneMessageType.LuceneReadRequest:
                return new LuceneMessageEntity<>(new LuceneReadRequestMapper()
                        .from((LuceneReadRequest) message.message(new LuceneReadRequest())));
            case LuceneMessageType.LuceneReadResponse:
                return new LuceneMessageEntity<>(new LuceneReadResponseMapper()
                        .from((LuceneReadResponse) message.message(new LuceneReadResponse())));
            case LuceneMessageType.LuceneWriteRequest:
                return new LuceneMessageEntity<>(new LuceneWriteRequestMapper()
                        .from((LuceneWriteRequest) message.message(new LuceneWriteRequest())));
            case LuceneMessageType.LuceneWriteResponse:
                return new LuceneMessageEntity<>(new LuceneWriteResponseMapper()
                        .from((LuceneWriteResponse) message.message(new LuceneWriteResponse())));
            default:
                throw new IllegalArgumentException("could not deserialize message");
        }
    }
}
