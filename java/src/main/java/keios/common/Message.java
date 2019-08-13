package keios.common;

import com.google.flatbuffers.FlatBufferBuilder;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface Message {
    MessageType type();

    default int serialize(FlatBufferBuilder builder) {
        return 0;
    }
}
