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

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.gensim.entity.GensimMessageEntity.GensimMessageType;
import keios.protocol.common.TypedMessage;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.emptyList;

/**
 * @author Michael Mair
 */
public class GensimFastTextMostSimilarRequestEntity implements TypedMessage {

    private final List<MostSimilarRequestEntity> requests;

    public GensimFastTextMostSimilarRequestEntity(List<MostSimilarRequestEntity> requests) {
        if (requests == null) {
            requests = emptyList();
        }
        this.requests = requests;
    }

    public List<MostSimilarRequestEntity> getRequests() {
        return requests;
    }

    @Override
    public GensimMessageType type() {
        return GensimMessageType.FASTTEXT_MOST_SIMILAR_REQUEST;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return new GensimFastTextMostSimilarRequestSerializer().serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GensimFastTextMostSimilarRequestEntity that = (GensimFastTextMostSimilarRequestEntity) o;
        return Objects.equals(requests, that.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requests);
    }

    @Override
    public String toString() {
        return "GensimFastTextMostSimilarRequestEntity{" +
                "requests=" + requests +
                '}';
    }
}
