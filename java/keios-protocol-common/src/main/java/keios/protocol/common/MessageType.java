package keios.protocol.common;

/**
 * A message type (usually a union type containing all possible messages) in flatbuffers is determined by a byte value.
 *
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface MessageType {
    /**
     * @return the payload message type as byte value
     */
    byte byteVal();
}
