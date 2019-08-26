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
public class SimilarityRequestEntity {

    private final String text1;
    private final String text2;

    public SimilarityRequestEntity(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimilarityRequestEntity that = (SimilarityRequestEntity) o;
        return Objects.equals(text1, that.text1) &&
                Objects.equals(text2, that.text2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text1, text2);
    }

    @Override
    public String toString() {
        return "SimilarityRequestEntity{" +
                "text1='" + text1 + '\'' +
                ", text2='" + text2 + '\'' +
                '}';
    }
}
