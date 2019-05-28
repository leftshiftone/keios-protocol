package keios.common;

import com.google.flatbuffers.FlatBufferBuilder;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
public interface ChildSerializer<T> {

    /**
     * Serializes a flatbuffer child (tables in vectors) by reusing the given {@link FlatBufferBuilder}
     * and returns the offset.
     */
    int serialize(T obj, FlatBufferBuilder builder);
}
