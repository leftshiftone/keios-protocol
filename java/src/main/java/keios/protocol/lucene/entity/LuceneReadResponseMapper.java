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

import keios.common.EntityMapper;
import keios.protocol.lucene.flatbuffers.LuceneReadResponse;
import keios.protocol.lucene.flatbuffers.SearchResult;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadResponseMapper implements EntityMapper<LuceneReadResponse, LuceneReadResponseEntity> {

    private static SearchResultEntity toEntity(SearchResult searchResult) {
        //@formatter:off
        return new SearchResultEntity(searchResult.score(), IntStream.range(0, searchResult.documentLength())
                .mapToObj(searchResult::document)
                .collect(Collectors.toMap(
                        t -> Optional.ofNullable(t.key())
                                                .orElseThrow(throwIfNull()),
                        t -> Optional.ofNullable(t.value())
                                                .orElseThrow(throwIfNull()))));
        //@formatter:on
    }

    private static Supplier<RuntimeException> throwIfNull() {
        return IllegalStateException::new;
    }

    @Override
    public LuceneReadResponseEntity from(LuceneReadResponse input) {
        List<SearchResultEntity> searchResults = IntStream.range(0, input.resultsLength())
                .mapToObj(input::results)
                .map(LuceneReadResponseMapper::toEntity)
                .collect(Collectors.toList());

        return new LuceneReadResponseEntity(searchResults);
    }
}
