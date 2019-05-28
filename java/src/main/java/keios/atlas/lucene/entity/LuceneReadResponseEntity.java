package keios.atlas.lucene.entity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadResponseEntity {
    private final List<SearchResultEntity> results;

    public LuceneReadResponseEntity(List<SearchResultEntity> results) {
        this.results = Objects.requireNonNull(results, "results can not be null");
    }

    public static LuceneReadResponseEntity deserialize(byte[] bb) {
        LuceneReadResponseDeserializer deserializer = new LuceneReadResponseDeserializer();
        return deserializer.deserialize(bb);
    }

    public List<SearchResultEntity> getResults() {
        return Collections.unmodifiableList(this.results);
    }

    public byte[] serialize() {
        LuceneReadResponseSerializer serializer = new LuceneReadResponseSerializer();
        return serializer.serialize(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneReadResponseEntity that = (LuceneReadResponseEntity) o;
        return results.equals(that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }
}
