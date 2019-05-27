package keios.atlas.lucene;

import java.util.Map;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
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
}
