package keios.atlas.lucene.entity.serialize;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.entity.LuceneReadResponseEntity;
import keios.atlas.lucene.flatbuffers.LuceneReadResponse;
import keios.common.BinarySerializer;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class LuceneReadResponseSerializer implements BinarySerializer<LuceneReadResponseEntity> {

    private final SearchResultSerializer searchResultSerializer = new SearchResultSerializer();

    @Override
    public byte[] serialize(LuceneReadResponseEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        LuceneReadResponse.startResultsVector(builder, entity.getResults().size());
        int vec = builder.endVector();

        LuceneReadResponse.startLuceneReadResponse(builder);
        LuceneReadResponse.addResults(builder, vec);
        int result = LuceneReadResponse.endLuceneReadResponse(builder);
        builder.finish(result);

        return builder.sizedByteArray();
    }
}
