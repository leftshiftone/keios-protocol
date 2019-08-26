package keios.protocol.common;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface TypedMessage extends FlatbufferSerializable {
    MessageType type();
}
