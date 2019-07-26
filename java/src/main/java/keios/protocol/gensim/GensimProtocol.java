package keios.protocol.gensim;

import keios.protocol.gensim.entity.GensimMessageDeserializer;
import keios.protocol.gensim.entity.GensimMessageEntity;
import keios.protocol.gensim.entity.GensimMessageSerializer;
import keios.protocol.gensim.entity.Message;

public class GensimProtocol {

    private static final GensimMessageDeserializer deserializer = new GensimMessageDeserializer();
    private static final GensimMessageSerializer serializer = new GensimMessageSerializer();

    public static GensimMessageEntity toMessage(byte[] bytes) {
        return deserializer.deserialize(bytes);
    }

    public static <T extends Message> byte[] toWireMessage(GensimMessageEntity<T> message) {
        return serializer.serialize(message);
    }
}
