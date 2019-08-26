package keios.protocol.common;

import com.google.flatbuffers.FlatBufferBuilder;

/**
 * Implemented by serializable entities that can be serialized via flatbuffers.
 *
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
@FunctionalInterface
public interface FlatbufferSerializable {
    int serialize(FlatBufferBuilder builder);
}
