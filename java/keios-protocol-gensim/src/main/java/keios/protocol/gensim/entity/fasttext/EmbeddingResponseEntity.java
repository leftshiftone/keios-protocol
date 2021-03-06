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

package keios.protocol.gensim.entity.fasttext;

import java.util.List;
import java.util.Objects;

/**
 * @author Michael Mair
 */
public class EmbeddingResponseEntity {

    private final List<EmbeddingElementEntity> vector;

    public EmbeddingResponseEntity(List<EmbeddingElementEntity> vector) {
        this.vector = vector;
    }

    public List<EmbeddingElementEntity> getVector() {
        return vector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmbeddingResponseEntity that = (EmbeddingResponseEntity) o;
        return Objects.equals(vector, that.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vector);
    }

    @Override
    public String toString() {
        return "EmbeddingResponseEntity{" +
                "vector=" + vector +
                '}';
    }
}
