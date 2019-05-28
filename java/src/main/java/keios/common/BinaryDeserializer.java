package keios.common;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface BinaryDeserializer<T> {
    default T deserialize(byte[] bytes) {
        return deserialize(ByteBuffer.wrap(bytes));
    }

    T deserialize(ByteBuffer bb);
}
