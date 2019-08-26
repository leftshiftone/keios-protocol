package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.common.TypedMessage;
import keios.protocol.common.MessageType;
import keios.protocol.spacy.flatbuffers.SpacyBatchRequest;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyBatchRequestEntity implements TypedMessage {

    private final List<SpacyRequestEntity> requests;
    private final SpacyBatchRequestSerializer serializer = new SpacyBatchRequestSerializer();

    public SpacyBatchRequestEntity(List<SpacyRequestEntity> requests) {
        Objects.requireNonNull(requests, "requests can not be null");
        this.requests = requests;
    }

    @Override
    public MessageType type() {
        return SpacyMessageEntity.SpacyMessageType.BATCH_REQUEST;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    public List<SpacyRequestEntity> getRequests() {
        return Collections.unmodifiableList(requests);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpacyBatchRequestEntity that = (SpacyBatchRequestEntity) o;
        return requests.equals(that.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requests);
    }

    private static class SpacyBatchRequestSerializer implements ChildSerializer<SpacyBatchRequestEntity> {
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

    static class SpacyBatchRequestMapper implements EntityMapper<SpacyBatchRequest, SpacyBatchRequestEntity> {
        @Override
        public SpacyBatchRequestEntity from(SpacyBatchRequest input) {
            SpacyRequestEntity.SpacyRequestMapper requestMapper = new SpacyRequestEntity.SpacyRequestMapper();
            return new SpacyBatchRequestEntity(IntStream.range(0, input.requestsLength())
                    .mapToObj(input::requests)
                    .map(requestMapper::from).collect(Collectors.toList()));
        }
    }
}
