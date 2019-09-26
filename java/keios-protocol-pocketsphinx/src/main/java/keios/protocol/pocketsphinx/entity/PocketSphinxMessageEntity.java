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

package keios.protocol.pocketsphinx.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.*;
import keios.protocol.pocketsphinx.entity.PocketSphinxRequestEntity.PocketSphinxRequestMapper;
import keios.protocol.pocketsphinx.entity.PocketSphinxResponseEntity.PocketSphinxResponseMapper;
import keios.protocol.pocketsphinx.flatbuffers.PocketSphinxMessage;
import keios.protocol.pocketsphinx.flatbuffers.PocketSphinxRequest;
import keios.protocol.pocketsphinx.flatbuffers.PocketSphinxResponse;

import java.nio.ByteBuffer;

/**
 * @author Michael Mair
 */
public class PocketSphinxMessageEntity extends AbstractMessageEntity<TypedMessage> {

    public PocketSphinxMessageEntity(TypedMessage message) {
        super(message);
    }

    public enum PocketSphinxMessageType implements MessageType {
        REQUEST(keios.protocol.pocketsphinx.flatbuffers.PocketSphinxMessageType.PocketSphinxRequest),
        RESPONSE(keios.protocol.pocketsphinx.flatbuffers.PocketSphinxMessageType.PocketSphinxResponse),
        NONE(keios.protocol.pocketsphinx.flatbuffers.PocketSphinxMessageType.NONE);

        final byte byteVal;

        PocketSphinxMessageType(byte byteVal) {
            this.byteVal = byteVal;
        }

        @Override
        public byte byteVal() {
            return this.byteVal;
        }
    }

    public static class PocketSphinxMessageSerializer implements BinarySerializer<PocketSphinxMessageEntity> {
        @Override
        public byte[] serialize(PocketSphinxMessageEntity entity) {
            FlatBufferBuilder builder = new FlatBufferBuilder();

            int messageOffset = entity.getMessage().serialize(builder);

            PocketSphinxMessage.startPocketSphinxMessage(builder);
            PocketSphinxMessage.addMessageType(builder, entity.getMessage().type().byteVal());
            PocketSphinxMessage.addMessage(builder, messageOffset);
            int resultOffset = PocketSphinxMessage.endPocketSphinxMessage(builder);
            builder.finish(resultOffset);
            return builder.sizedByteArray();
        }
    }

    public static class PocketSphinxMessageDeserializer implements BinaryDeserializer<PocketSphinxMessageEntity> {

        @Override
        public PocketSphinxMessageEntity deserialize(ByteBuffer bb) {
            PocketSphinxMessage message = PocketSphinxMessage.getRootAsPocketSphinxMessage(bb);
            int messageType = message.messageType();

            switch (messageType) {
                case keios.protocol.pocketsphinx.flatbuffers.PocketSphinxMessageType.PocketSphinxRequest:
                    PocketSphinxRequest embeddingRequest = (PocketSphinxRequest) message.message(new PocketSphinxRequest());
                    assert embeddingRequest != null;
                    return new PocketSphinxMessageEntity(new PocketSphinxRequestMapper().from(embeddingRequest));
                case keios.protocol.pocketsphinx.flatbuffers.PocketSphinxMessageType.PocketSphinxResponse:
                    PocketSphinxResponse embeddingResponse = (PocketSphinxResponse) message.message(new PocketSphinxResponse());
                    assert embeddingResponse != null;
                    return new PocketSphinxMessageEntity(new PocketSphinxResponseMapper().from(embeddingResponse));
                default:
                    throw new IllegalArgumentException("could not deserialize message");
            }
        }
    }
}
