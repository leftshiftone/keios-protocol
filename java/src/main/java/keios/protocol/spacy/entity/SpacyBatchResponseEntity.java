package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.Message;
import keios.common.MessageType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyBatchResponseEntity implements Message {

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
}
