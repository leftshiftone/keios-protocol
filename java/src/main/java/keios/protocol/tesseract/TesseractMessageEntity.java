package keios.protocol.tesseract;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.*;
import keios.protocol.tesseract.flatbuffers.TesseractMessage;
import keios.protocol.tesseract.flatbuffers.TesseractOcrRequest;
import keios.protocol.tesseract.flatbuffers.TesseractOcrResponse;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 0.1.0
 */
public class TesseractMessageEntity<T extends Message> extends AbstractMessageEntity<T> {

    public TesseractMessageEntity(T message) {
        super(message);
    }

    public enum TesseractMessageType implements MessageType {
        OCR_REQUEST(keios.protocol.tesseract.flatbuffers.TesseractMessageType.TesseractOcrRequest),
        OCR_RESPONSE(keios.protocol.tesseract.flatbuffers.TesseractMessageType.TesseractOcrResponse);


        private final byte byteVal;

        TesseractMessageType(byte byteVal) {
            this.byteVal = byteVal;
        }

        @Override
        public byte byteVal() {
            return byteVal;
        }
    }

    static class TesseractMessageSerializer implements BinarySerializer<TesseractMessageEntity<Message>> {
        @Override
        public byte[] serialize(TesseractMessageEntity<Message> entity) {
            FlatBufferBuilder builder = new FlatBufferBuilder();
            int messageOffset = entity.getMessage().serialize(builder);

            TesseractMessage.startTesseractMessage(builder);
            TesseractMessage.addMessageType(builder, entity.getMessage().type().byteVal());
            TesseractMessage.addMessage(builder, messageOffset);

            int resultOffset = TesseractMessage.endTesseractMessage(builder);

            builder.finish(resultOffset);

            return builder.sizedByteArray();
        }
    }

    static class TesseractMessageDeserializer implements BinaryDeserializer<TesseractMessageEntity<Message>> {
        @Override
        public TesseractMessageEntity<Message> deserialize(ByteBuffer bb) {
            TesseractMessage message = TesseractMessage.getRootAsTesseractMessage(bb);
            switch (message.messageType()) {
                case keios.protocol.tesseract.flatbuffers.TesseractMessageType.TesseractOcrRequest:
                    return new TesseractMessageEntity<>(new TesseractOcrRequestEntity.TesseractOcrRequestMapper().from((TesseractOcrRequest) message.message(new TesseractOcrRequest())));
                case keios.protocol.tesseract.flatbuffers.TesseractMessageType.TesseractOcrResponse:
                    return new TesseractMessageEntity<>(new TesseractOcrResponseEntity.TesseractOcrResponseMapper().from((TesseractOcrResponse) message.message(new TesseractOcrResponse())));
                default:
                    throw new IllegalArgumentException("Could not deserialize message");
            }
        }
    }
}
