package keios.atlas.lucene;

import keios.atlas.lucene.flatbuffers.LuceneReadRequest;
import keios.atlas.lucene.flatbuffers.LuceneReadResponse;
import keios.atlas.lucene.flatbuffers.LuceneWriteRequest;
import keios.atlas.lucene.flatbuffers.LuceneWriteResponse;

import java.util.List;
import java.util.Map;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneProtocol {

    public static LuceneWriteRequest from(Map<String, String> mapping) {
        return null;
    }

    public static LuceneReadRequest from(String field, String query, Float minimumScore, int limit) {
        return null;
    }

    public static LuceneReadResponse from(List<SearchResultEntity> searchResults) {
        return null;
    }

    public static LuceneWriteResponse from(WriteResultEntity writeResult) {
        return null;
    }

}
