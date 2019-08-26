package keios.protocol.tesseract;

import keios.protocol.common.BinaryDeserializer;
import keios.protocol.common.BinarySerializer;
import keios.protocol.common.Protocol;

/**
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 0.1.0
 */
public class TesseractProtocol implements Protocol<TesseractMessageEntity> {

    private static final TesseractProtocol INSTANCE = new TesseractProtocol();
    private final BinaryDeserializer<TesseractMessageEntity> deserializer = new TesseractMessageEntity.TesseractMessageDeserializer();
    private final BinarySerializer<TesseractMessageEntity> serializer = new TesseractMessageEntity.TesseractMessageSerializer();

    private TesseractProtocol() {
    }

    public static TesseractProtocol instance() {
        return INSTANCE;
    }

    @Override
    public TesseractMessageEntity toMessage(byte[] bytes) {
        return this.deserializer.deserialize(bytes);
    }

    @Override
    public byte[] toWireMessage(TesseractMessageEntity message) {
        return this.serializer.serialize(message);
    }
}
