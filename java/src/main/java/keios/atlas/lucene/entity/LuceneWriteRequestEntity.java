package keios.atlas.lucene.entity;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteRequestEntity {
    private final Map<String, String> document;

    public LuceneWriteRequestEntity(Map<String, String> document) {
        this.document = Objects.requireNonNull(document, "document can not be null");
    }

    public static LuceneWriteRequestEntity deserialize(byte[] bb) {
        LuceneWriteRequestDeserializer deserializer = new LuceneWriteRequestDeserializer();
        return deserializer.deserialize(bb);
    }

    public Map<String, String> getDocument() {
        return Collections.unmodifiableMap(this.document);
    }

    public byte[] serialize() {
        LuceneWriteRequestSerializer serializer = new LuceneWriteRequestSerializer();
        return serializer.serialize(this);
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
