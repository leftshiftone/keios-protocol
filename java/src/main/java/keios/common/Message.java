package keios.common;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface Message extends FlatbuffersSerializable {
    MessageType type();
}
