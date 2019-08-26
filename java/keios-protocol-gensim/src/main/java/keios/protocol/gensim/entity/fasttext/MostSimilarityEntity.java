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

import java.util.Objects;

/**
 * @author Michael Mair
 */
public class MostSimilarityEntity {

    private final String text;
    private final float probability;

    public MostSimilarityEntity(String text, float probability) {
        this.text = text;
        this.probability = probability;
    }

    public String getText() {
        return text;
    }

    public float getProbability() {
        return probability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MostSimilarityEntity that = (MostSimilarityEntity) o;
        return Float.compare(that.probability, probability) == 0 &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, probability);
    }

    @Override
    public String toString() {
        return "MostSimilarityEntity{" +
                "text='" + text + '\'' +
                ", probability=" + probability +
                '}';
    }
}
