package keios.protocol.common;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface Message extends FlatbufferSerializable {
    MessageType type();
}
