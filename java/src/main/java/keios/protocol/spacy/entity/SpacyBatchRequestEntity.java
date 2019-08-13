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
public class SpacyBatchRequestEntity implements Message {

    private final List<SpacyRequestEntity> requests;

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
        return new SpacyBatchRequestSerializer().serialize(this, builder);
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
}
