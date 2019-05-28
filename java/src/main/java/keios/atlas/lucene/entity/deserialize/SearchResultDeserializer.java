package keios.atlas.lucene.entity.deserialize;

import keios.atlas.lucene.entity.SearchResultEntity;
import keios.atlas.lucene.flatbuffers.SearchResult;
import keios.atlas.lucene.flatbuffers.Tuple;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SearchResultDeserializer implements BinaryDeserializer<SearchResultEntity> {
    @Override
    public SearchResultEntity deserialize(ByteBuffer bb) {
        SearchResult searchResult = SearchResult.getRootAsSearchResult(bb);

        Map<String, String> document = IntStream.range(0, searchResult.documentLength())
                .mapToObj(searchResult::document)
                .collect(Collectors.toMap(Tuple::key, Tuple::value));
        return new SearchResultEntity(searchResult.score(), document);
    }
}
