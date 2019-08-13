package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.spacy.flatbuffers.SpacyBatchRequest;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyBatchRequestSerializer implements ChildSerializer<SpacyBatchRequestEntity> {

    @Override
    public int serialize(SpacyBatchRequestEntity obj, FlatBufferBuilder builder) {
        int[] requestsOffsets = obj.getRequests()
                .stream()
                .map(it -> it.serialize(builder))
                .mapToInt(i -> i).toArray();

        int requestVectorOffset = SpacyBatchRequest.createRequestsVector(builder, requestsOffsets);
        SpacyBatchRequest.startSpacyBatchRequest(builder);
        SpacyBatchRequest.addRequests(builder, requestVectorOffset);

        return SpacyBatchRequest.endSpacyBatchRequest(builder);
    }
}
