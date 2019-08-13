package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.spacy.flatbuffers.SpacyBatchResponse;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyBatchResponseSerializer implements ChildSerializer<SpacyBatchResponseEntity> {

    @Override
    public int serialize(SpacyBatchResponseEntity obj, FlatBufferBuilder builder) {
        int[] responseOffsets = obj.getResponses()
                .stream()
                .map(it -> it.serialize(builder))
                .mapToInt(i -> i).toArray();

        int responsesVectorOffset = SpacyBatchResponse.createResponsesVector(builder, responseOffsets);

        SpacyBatchResponse.startSpacyBatchResponse(builder);
        SpacyBatchResponse.addResponses(builder, responsesVectorOffset);

        return SpacyBatchResponse.endSpacyBatchResponse(builder);
    }
}
