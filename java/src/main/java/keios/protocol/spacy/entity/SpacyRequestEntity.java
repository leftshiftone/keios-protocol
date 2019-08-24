package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.Message;
import keios.common.MessageType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyRequestEntity implements Message {

    private final String text;
    private final Set<Byte> types;
    private final SpacyRequestSerializer serializer = new SpacyRequestSerializer();

    public SpacyRequestEntity(String text, ESpacyType... types) {
        Objects.requireNonNull(text, "text can not be null");
        Objects.requireNonNull(types, "types can not be null");
        this.text = text;
        this.types = Arrays.stream(types).map(ESpacyType::getByteValue).collect(Collectors.toSet());
    }

    public SpacyRequestEntity(String text, Set<ESpacyType> types) {
        Objects.requireNonNull(text, "text can not be null");
        Objects.requireNonNull(types, "types can not be null");
        this.text = text;
        this.types = types.stream().map(ESpacyType::getByteValue).collect(Collectors.toSet());
    }

    @Override
    public MessageType type() {
        return SpacyMessageEntity.SpacyMessageType.REQUEST;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    public String getText() {
        return text;
    }

    public Set<ESpacyType> getTypes() {
        return Collections.unmodifiableSet(types.stream().map(ESpacyType::fromByte).collect(Collectors.toSet()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpacyRequestEntity that = (SpacyRequestEntity) o;
        return text.equals(that.text) &&
                types.equals(that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, types);
    }
}
