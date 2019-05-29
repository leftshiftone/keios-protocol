package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static keios.atlas.lucene.entity.LuceneMessageEntity.MessageType.WRITE_REQUEST;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteRequestEntity implements Message {
    private final Map<String, String> document;

    public LuceneWriteRequestEntity(Map<String, String> document) {
        this.document = Objects.requireNonNull(document, "document can not be null");
    }

    public Map<String, String> getDocument() {
        return Collections.unmodifiableMap(this.document);
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        LuceneWriteRequestSerializer serializer = new LuceneWriteRequestSerializer();
        return serializer.serialize(this, builder);
    }

    @Override
    public LuceneMessageEntity.MessageType type() {
        return WRITE_REQUEST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneWriteRequestEntity that = (LuceneWriteRequestEntity) o;
        return document.equals(that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document);
    }
}
