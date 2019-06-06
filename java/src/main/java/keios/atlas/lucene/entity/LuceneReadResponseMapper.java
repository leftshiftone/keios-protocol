package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneReadResponse;
import keios.atlas.lucene.flatbuffers.SearchResult;
import keios.common.EntityMapper;

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