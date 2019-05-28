package keios.atlas.lucene.entity;

import java.util.Map;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class SearchResultEntity {
    private final Float score;
    private final Map<String, String> document;

    public SearchResultEntity(Float score, Map<String, String> document) {
        this.score = score;
        this.document = document;
    }

    public Float getScore() {
        return score;
    }

    public Map<String, String> getDocument() {
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResultEntity entity = (SearchResultEntity) o;
        return score.equals(entity.score) &&
                document.equals(entity.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, document);
    }
}
