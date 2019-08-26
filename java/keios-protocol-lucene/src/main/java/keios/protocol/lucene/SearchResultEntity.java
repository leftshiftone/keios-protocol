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

package keios.protocol.lucene;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.FlatbufferSerializable;
import keios.protocol.lucene.flatbuffers.SearchResult;

import java.util.Map;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class SearchResultEntity implements FlatbufferSerializable {

    private final ChildSerializer<SearchResultEntity> serializer = new SearchResultSerializer();
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
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, document);
    }

    private static class SearchResultSerializer implements ChildSerializer<SearchResultEntity> {

        private final DocumentSerializer documentSerializer = new DocumentSerializer();

        @Override
        public int serialize(SearchResultEntity obj, FlatBufferBuilder builder) {
            int documentOffset = documentSerializer.serialize(obj.getDocument(), builder);
            SearchResult.startSearchResult(builder);
            SearchResult.addScore(builder, obj.getScore());
            SearchResult.addDocument(builder, documentOffset);
            return SearchResult.endSearchResult(builder);
        }
    }

}
