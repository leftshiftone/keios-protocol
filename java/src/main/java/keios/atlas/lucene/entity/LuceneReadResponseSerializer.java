package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.LuceneReadResponse;
import keios.common.BinarySerializer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneReadResponseSerializer implements BinarySerializer<LuceneReadResponseEntity> {

    private final SearchResultChildSerializer searchResultSerializer = new SearchResultChildSerializer();

    @Override
    public byte[] serialize(LuceneReadResponseEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        int[] searchResultOffsets = entity.getResults()
                .stream()
                .map(searchResult -> searchResultSerializer.serialize(searchResult, builder))
                .mapToInt(i -> i)
                .toArray();

        int searchResultVectorOffset = LuceneReadResponse.createResultsVector(builder, searchResultOffsets);

        LuceneReadResponse.startLuceneReadResponse(builder);
        LuceneReadResponse.addResults(builder, searchResultVectorOffset);
        int result = LuceneReadResponse.endLuceneReadResponse(builder);
        builder.finish(result);

        return builder.sizedByteArray();
    }
}
