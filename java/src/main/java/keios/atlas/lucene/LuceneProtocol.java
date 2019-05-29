package keios.atlas.lucene;

import keios.atlas.lucene.entity.*;

/**
 * Facade for easy serial-/deserialization
 *
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public class LuceneProtocol {
    private static final LuceneMessageDeserializer deserializer = new LuceneMessageDeserializer();
    private static final LuceneMessageSerializer serializer = new LuceneMessageSerializer();

    public static LuceneMessageEntity toMessage(byte[] bytes) {
        return deserializer.deserialize(bytes);
    }

    public static <T extends Message> byte[] toWireMessage(LuceneMessageEntity<T> message) {
        return serializer.serialize(message);
    }
}
