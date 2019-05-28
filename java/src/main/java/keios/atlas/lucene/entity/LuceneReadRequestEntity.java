package keios.atlas.lucene.entity;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class LuceneReadRequestEntity {
    private final String field;
    private final String query;
    private final Float minimumScore;
    private final Integer limit;

    public LuceneReadRequestEntity(String field, String query, Float minimumScore, Integer limit) {
        this.field = field;
        this.query = query;
        this.minimumScore = minimumScore;
        this.limit = limit;
    }

    public LuceneReadRequestEntity(String field, String query, Float minimumScore) {
        this.field = field;
        this.query = query;
        this.minimumScore = minimumScore;
        this.limit = null;
    }

    public LuceneReadRequestEntity(String field, String query) {
        this.field = field;
        this.query = query;
        this.minimumScore = null;
        this.limit = null;
    }

    public String getField() {
        return field;
    }

    public String getQuery() {
        return query;
    }

    public Float getMinimumScore() {
        return minimumScore;
    }

    public Integer getLimit() {
        return limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneReadRequestEntity that = (LuceneReadRequestEntity) o;
        return field.equals(that.field) &&
                query.equals(that.query) &&
                Objects.equals(minimumScore, that.minimumScore) &&
                Objects.equals(limit, that.limit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, query, minimumScore, limit);
    }
}
