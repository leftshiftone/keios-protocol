package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.SearchResult;
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
