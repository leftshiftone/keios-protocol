/*
 * Copyright (c) 2016-2019, Leftshift One
 * __________________
 * [2019] Leftshift One
 * All Rights Reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Leftshift One and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Leftshift One
 * and its suppliers and may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Leftshift One.
 */

package keios.protocol.lucene.entity;

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
