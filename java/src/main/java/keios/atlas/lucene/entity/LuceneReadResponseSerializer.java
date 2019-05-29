package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneReadResponse;
import keios.common.ChildSerializer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadResponseSerializer implements ChildSerializer<LuceneReadResponseEntity> {

    private final SearchResultSerializer searchResultSerializer = new SearchResultSerializer();

    @Override
    public int serialize(LuceneReadResponseEntity obj, FlatBufferBuilder builder) {
        int[] searchResultOffsets = obj.getResults()
                .stream()
                .map(searchResult -> searchResultSerializer.serialize(searchResult, builder))
                .mapToInt(i -> i)
                .toArray();

        int searchResultVectorOffset = LuceneReadResponse.createResultsVector(builder, searchResultOffsets);

        LuceneReadResponse.startLuceneReadResponse(builder);
        LuceneReadResponse.addResults(builder, searchResultVectorOffset);
        return LuceneReadResponse.endLuceneReadResponse(builder);
    }
}
