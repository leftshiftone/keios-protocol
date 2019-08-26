package keios.protocol.tesseract;

import keios.common.BinaryDeserializer;
import keios.common.BinarySerializer;
import keios.common.Message;
import keios.common.Protocol;

/**
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 0.1.0
 */
public class TesseractProtocol implements Protocol<TesseractMessageEntity<Message>> {

    private static final TesseractProtocol INSTANCE = new TesseractProtocol();
    private final BinaryDeserializer<TesseractMessageEntity<Message>> deserializer = new TesseractMessageEntity.TesseractMessageDeserializer();
    private final BinarySerializer<TesseractMessageEntity<Message>> serializer = new TesseractMessageEntity.TesseractMessageSerializer();

    private TesseractProtocol() {
    }

    public static TesseractProtocol instance() {
        return INSTANCE;
    }

    @Override
    public TesseractMessageEntity<Message> toMessage(byte[] bytes) {
        return this.deserializer.deserialize(bytes);
    }

    @Override
    public byte[] toWireMessage(TesseractMessageEntity<Message> message) {
        return this.serializer.serialize(message);
    }
}
