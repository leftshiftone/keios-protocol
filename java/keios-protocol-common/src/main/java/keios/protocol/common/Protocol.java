package keios.protocol.common;

/**
 * A {@link Protocol} is the main serialization/deserialization client facing API for keios protocols.
 *
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 1.0.0
 */
public interface Protocol<T extends AbstractMessageEntity<TypedMessage>> {

    /**
     * Converts a serialized flatbuffers message back to an entity class. Usually an {@link AbstractMessageEntity} containing
     * the actual message.
     *
     * @param bytes
     * @return an entity class
     */
    T toMessage(byte[] bytes);

    /**
     * Serializes an entity object tree via flatbuffers.
     *
     * @param message
     * @return a message ready to be send over the wire
     */
    byte[] toWireMessage(T message);
}
