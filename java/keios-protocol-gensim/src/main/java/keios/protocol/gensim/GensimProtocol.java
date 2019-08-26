package keios.protocol.gensim;

import keios.protocol.common.BinaryDeserializer;
import keios.protocol.common.BinarySerializer;
import keios.protocol.common.Protocol;
import keios.protocol.gensim.entity.GensimMessageDeserializer;
import keios.protocol.gensim.entity.GensimMessageEntity;
import keios.protocol.gensim.entity.GensimMessageSerializer;
import keios.protocol.gensim.entity.Message;

public class GensimProtocol implements Protocol<GensimMessageEntity> {

    private static final GensimProtocol INSTANCE = new GensimProtocol();
    private final BinaryDeserializer<GensimMessageEntity> deserializer = new GensimMessageDeserializer();
    private final BinarySerializer<GensimMessageEntity> serializer = new GensimMessageSerializer();

    private GensimProtocol() {}

    @Override
    public GensimMessageEntity toMessage(byte[] bytes) {
        return this.deserializer.deserialize(bytes);
    }

    @Override
    public byte[] toWireMessage(GensimMessageEntity message) {
        return this.serializer.serialize(message);
    }
}
