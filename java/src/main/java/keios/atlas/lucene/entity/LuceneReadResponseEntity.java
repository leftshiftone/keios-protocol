package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadResponseEntity implements Message {
    private final List<SearchResultEntity> results;

    public LuceneReadResponseEntity(List<SearchResultEntity> results) {
        this.results = Objects.requireNonNull(results, "results can not be null");
    }

    public List<SearchResultEntity> getResults() {
        return Collections.unmodifiableList(this.results);
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        LuceneReadResponseSerializer serializer = new LuceneReadResponseSerializer();
        return serializer.serialize(this, builder);
    }

    @Override
    public LuceneMessageEntity.MessageType type() {
        return LuceneMessageEntity.MessageType.READ_RESPONSE;
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
