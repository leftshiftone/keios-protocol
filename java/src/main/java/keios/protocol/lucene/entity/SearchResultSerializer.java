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

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.lucene.flatbuffers.SearchResult;
import keios.common.ChildSerializer;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
class SearchResultSerializer implements ChildSerializer<SearchResultEntity> {

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
