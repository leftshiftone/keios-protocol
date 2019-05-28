package keios.atlas.lucene.entity.deserialize;

import keios.atlas.lucene.entity.LuceneReadResponseEntity;
import keios.atlas.lucene.entity.SearchResultEntity;
import keios.atlas.lucene.flatbuffers.LuceneReadResponse;
import keios.common.BinaryDeserializer;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneReadResponseDeserializer implements BinaryDeserializer<LuceneReadResponseEntity> {

    private final SearchResultDeserializer searchResultDeserializer = new SearchResultDeserializer();

    @Override
    public LuceneReadResponseEntity deserialize(ByteBuffer bb) {
        LuceneReadResponse readResponse = LuceneReadResponse.getRootAsLuceneReadResponse(bb);

        List<SearchResultEntity> searchResults = IntStream.range(0, readResponse.resultsLength())
                .mapToObj(readResponse::results)
                .map(s -> searchResultDeserializer.deserialize(s.getByteBuffer()))
                .collect(Collectors.toList());

        return new LuceneReadResponseEntity(searchResults);
    }
}
