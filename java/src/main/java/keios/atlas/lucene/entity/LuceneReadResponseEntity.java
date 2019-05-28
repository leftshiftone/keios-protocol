package keios.atlas.lucene.entity;

import java.util.Collections;
import java.util.List;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class LuceneReadResponseEntity {
    private final List<SearchResultEntity> results;

    public LuceneReadResponseEntity(List<SearchResultEntity> results) {
        this.results = results;
    }

    public List<SearchResultEntity> getResults() {
        return Collections.unmodifiableList(this.results);
    }
}
