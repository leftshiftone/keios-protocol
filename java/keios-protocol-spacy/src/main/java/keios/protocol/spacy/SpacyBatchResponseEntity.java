package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.common.TypedMessage;
import keios.protocol.common.MessageType;
import keios.protocol.spacy.flatbuffers.SpacyBatchResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyBatchResponseEntity implements TypedMessage {

    private final List<SpacyResponseEntity> responses;
    private final SpacyBatchResponseSerializer serializer = new SpacyBatchResponseSerializer();

    public SpacyBatchResponseEntity(List<SpacyResponseEntity> responses) {
        Objects.requireNonNull(responses, "responses can not be null");
        this.responses = responses;
    }

    @Override
    public MessageType type() {
        return SpacyMessageEntity.SpacyMessageType.BATCH_RESPONSE;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    public List<SpacyResponseEntity> getResponses() {
        return Collections.unmodifiableList(this.responses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpacyBatchResponseEntity that = (SpacyBatchResponseEntity) o;
        return responses.equals(that.responses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responses);
    }

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    private static class SpacyBatchResponseSerializer implements ChildSerializer<SpacyBatchResponseEntity> {
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

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    static class SpacyBatchResponseMapper implements EntityMapper<SpacyBatchResponse, SpacyBatchResponseEntity> {
        @Override
        public SpacyBatchResponseEntity from(SpacyBatchResponse input) {
            SpacyResponseEntity.SpacyResponseMapper mapper = new SpacyResponseEntity.SpacyResponseMapper();
            return new SpacyBatchResponseEntity(IntStream.range(0, input.responsesLength())
                    .mapToObj(input::responses)
                    .map(mapper::from).collect(Collectors.toList()));
        }
    }
}
