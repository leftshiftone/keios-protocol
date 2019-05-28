package keios.atlas.lucene.entity;

import java.util.Collections;
import java.util.Map;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class LuceneWriteRequestEntity {
    private final Map<String, String> mapping;

    public LuceneWriteRequestEntity(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    public Map<String, String> getMapping() {
        return Collections.unmodifiableMap(this.mapping);
    }
}
