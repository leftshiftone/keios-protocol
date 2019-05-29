package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;

import static keios.atlas.lucene.entity.LuceneMessageEntity.MessageType.NONE;

/**
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public interface Message {
    /**
     * Indicates the {@link keios.atlas.lucene.flatbuffers.LuceneMessageType}
     */
    default LuceneMessageEntity.MessageType type() {
        return NONE;
    }

    default int serialize(FlatBufferBuilder builder) {
        return 0;
    }
}
