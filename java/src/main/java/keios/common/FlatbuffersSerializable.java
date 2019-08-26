package keios.common;

import com.google.flatbuffers.FlatBufferBuilder;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
@FunctionalInterface
public interface FlatbuffersSerializable {
    int serialize(FlatBufferBuilder builder);
}
