/*
 * Copyright (c) 2016-2019, Leftshift One
 * __________________
 * [2019] Leftshift One
 * All Rights Reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Leftshift One and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Leftshift One
 * and its suppliers and may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Leftshift One.
 */

package keios.protocol.lucene;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.*;
import keios.protocol.lucene.flatbuffers.*;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Message wrapper that holds a reference to the actual message. Every {@link TypedMessage} must be wrapped
 * by a {@link LuceneMessage} before sending it over the wire.
 * <p>
 * This eases the deserialization on the receiver side by being able to call {@link TypedMessage#type()}
 *
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public class LuceneMessageEntity extends AbstractMessageEntity<TypedMessage> {

    public LuceneMessageEntity(TypedMessage message) {
        super(message);
    }

    public enum LuceneMessageType implements MessageType {
        READ_REQUEST(keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneReadRequest),
        READ_RESPONSE(keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneReadResponse),
        WRITE_REQUEST(keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneWriteRequest),
        WRITE_RESPONSE(keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneWriteResponse),
        NONE(keios.protocol.lucene.flatbuffers.LuceneMessageType.NONE);

        private final byte byteVal;

        LuceneMessageType(byte byteVal) {
            this.byteVal = byteVal;
        }

        @Override
        public byte byteVal() {
            return byteVal;
        }
    }

    static class LuceneMessageDeserializer implements BinaryDeserializer<LuceneMessageEntity> {
        @Override
        public LuceneMessageEntity deserialize(ByteBuffer bb) {
            LuceneMessage message = LuceneMessage.getRootAsLuceneMessage(bb);
            int messageType = message.messageType();
            switch (messageType) {
                case keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneReadRequest:
                    return new LuceneMessageEntity(new LuceneReadRequestEntity.LuceneReadRequestMapper()
                            .from((LuceneReadRequest) Objects.requireNonNull(message.message(new LuceneReadRequest()))));
                case keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneReadResponse:
                    return new LuceneMessageEntity(new LuceneReadResponseEntity.LuceneReadResponseMapper()
                            .from((LuceneReadResponse) Objects.requireNonNull(message.message(new LuceneReadResponse()))));
                case keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneWriteRequest:
                    return new LuceneMessageEntity(new LuceneWriteRequestEntity.LuceneWriteRequestMapper()
                            .from((LuceneWriteRequest) Objects.requireNonNull(message.message(new LuceneWriteRequest()))));
                case keios.protocol.lucene.flatbuffers.LuceneMessageType.LuceneWriteResponse:
                    return new LuceneMessageEntity(new LuceneWriteResponseEntity.LuceneWriteResponseMapper()
                            .from((LuceneWriteResponse) Objects.requireNonNull(message.message(new LuceneWriteResponse()))));
                default:
                    throw new IllegalArgumentException("could not deserialize message");
            }
        }
    }

    static class LuceneMessageSerializer implements BinarySerializer<LuceneMessageEntity> {
        @Override
        public byte[] serialize(LuceneMessageEntity entity) {
            FlatBufferBuilder builder = new FlatBufferBuilder();

            int messageOffset = entity.getMessage().serialize(builder);

            LuceneMessage.startLuceneMessage(builder);
            LuceneMessage.addMessageType(builder, entity.getMessage().type().byteVal());
            LuceneMessage.addMessage(builder, messageOffset);
            int resultOffset = LuceneMessage.endLuceneMessage(builder);
            builder.finish(resultOffset);
            return builder.sizedByteArray();
        }
    }

}
