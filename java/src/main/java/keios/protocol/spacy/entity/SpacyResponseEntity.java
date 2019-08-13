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
public class SpacyResponseEntity implements Message {

    private final List<DEPSpacyResponseEntity> depResponses;
    private final List<NERSpacyResponseEntity> nerResponses;

    public SpacyResponseEntity(List<DEPSpacyResponseEntity> depResponses, List<NERSpacyResponseEntity> nerResponses) {
        Objects.requireNonNull(depResponses, "depResponses can not be null");
        Objects.requireNonNull(nerResponses, "nerResponses can not be null");
        this.depResponses = depResponses;
        this.nerResponses = nerResponses;
    }

    @Override
    public MessageType type() {
        return SpacyMessageEntity.SpacyMessageType.RESPONSE;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return new SpacyResponseSerializer().serialize(this, builder);
    }

    public List<DEPSpacyResponseEntity> getDepResponses() {
        return Collections.unmodifiableList(this.depResponses);
    }

    public List<NERSpacyResponseEntity> getNerResponses() {
        return Collections.unmodifiableList(nerResponses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpacyResponseEntity that = (SpacyResponseEntity) o;
        return depResponses.equals(that.depResponses) &&
                nerResponses.equals(that.nerResponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depResponses, nerResponses);
    }
}
