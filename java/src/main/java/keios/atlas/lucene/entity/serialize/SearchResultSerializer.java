package keios.atlas.lucene.entity.serialize;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.entity.SearchResultEntity;
import keios.atlas.lucene.flatbuffers.SearchResult;
import keios.atlas.lucene.flatbuffers.Tuple;
import keios.common.BinarySerializer;

import java.util.Map;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SearchResultSerializer implements BinarySerializer<SearchResultEntity> {
    @Override
    public byte[] serialize(SearchResultEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();
        int[] document = serializeDocument(entity.getDocument(), builder);
        int docVector = SearchResult.createDocumentVector(builder, document);
        SearchResult.startSearchResult(builder);
        SearchResult.addScore(builder, entity.getScore());
        SearchResult.addDocument(builder, docVector);
        int request = SearchResult.endSearchResult(builder);
        builder.finish(request);

        return builder.sizedByteArray();
    }

    private int[] serializeDocument(Map<String, String> document, FlatBufferBuilder builder) {
        return document.entrySet()
                .stream()
                .map(entry -> {
                    int keyOffset = builder.createString(entry.getKey());
                    int valueOffset = builder.createString(entry.getValue());

                    return Tuple.createTuple(builder, keyOffset, valueOffset);
                })
                .mapToInt(i -> i).toArray();
    }
}
