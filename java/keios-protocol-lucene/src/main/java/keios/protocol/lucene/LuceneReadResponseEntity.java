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
import keios.protocol.common.EntityMapper;
import keios.protocol.common.Message;
import keios.protocol.lucene.flatbuffers.LuceneReadResponse;
import keios.protocol.lucene.flatbuffers.SearchResult;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadResponseEntity implements Message {
    private final List<SearchResultEntity> results;
    private final ChildSerializer<LuceneReadResponseEntity> serializer = new LuceneReadResponseSerializer();

    public LuceneReadResponseEntity(List<SearchResultEntity> results) {
        this.results = Objects.requireNonNull(results, "results can not be null");
    }

    public List<SearchResultEntity> getResults() {
        return Collections.unmodifiableList(this.results);
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public LuceneMessageEntity.LuceneMessageType type() {
        return LuceneMessageEntity.LuceneMessageType.READ_RESPONSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneReadResponseEntity that = (LuceneReadResponseEntity) o;
        return results.equals(that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    static class LuceneReadResponseMapper implements EntityMapper<LuceneReadResponse, LuceneReadResponseEntity> {

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

    private static class LuceneReadResponseSerializer implements ChildSerializer<LuceneReadResponseEntity> {
        @Override
        public int serialize(LuceneReadResponseEntity obj, FlatBufferBuilder builder) {
            int[] searchResultOffsets = obj.getResults()
                    .stream()
                    .map(searchResult -> searchResult.serialize(builder))
                    .mapToInt(i -> i)
                    .toArray();

            int searchResultVectorOffset = LuceneReadResponse.createResultsVector(builder, searchResultOffsets);

            LuceneReadResponse.startLuceneReadResponse(builder);
            LuceneReadResponse.addResults(builder, searchResultVectorOffset);
            return LuceneReadResponse.endLuceneReadResponse(builder);
        }
    }
}
