package keios.protocol.pocketsphinx;

import keios.protocol.common.BinaryDeserializer;
import keios.protocol.common.BinarySerializer;
import keios.protocol.common.Protocol;
import keios.protocol.pocketsphinx.entity.PocketSphinxMessageEntity;
import keios.protocol.pocketsphinx.entity.PocketSphinxMessageEntity.PocketSphinxMessageDeserializer;
import keios.protocol.pocketsphinx.entity.PocketSphinxMessageEntity.PocketSphinxMessageSerializer;

/**
 * @author Michael Mair
 */
public class PocketSphinxProtocol implements Protocol<PocketSphinxMessageEntity> {

    private static final PocketSphinxProtocol INSTANCE = new PocketSphinxProtocol();
    private final BinaryDeserializer<PocketSphinxMessageEntity> deserializer = new PocketSphinxMessageDeserializer();
    private final BinarySerializer<PocketSphinxMessageEntity> serializer = new PocketSphinxMessageSerializer();

    private PocketSphinxProtocol() {
    }

    public static Protocol<PocketSphinxMessageEntity> instance() {
        return INSTANCE;
    }

    @Override
    public PocketSphinxMessageEntity toMessage(byte[] bytes) {
        return this.deserializer.deserialize(bytes);
    }

    @Override
    public byte[] toWireMessage(PocketSphinxMessageEntity message) {
        return this.serializer.serialize(message);
    }
}
