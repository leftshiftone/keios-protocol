package keios.common;

/**
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 0.1.0
 */
public interface Protocol<T extends AbstractMessageEntity<Message>> {
    T toMessage(byte[] bytes);

    byte[] toWireMessage(T message);
}
