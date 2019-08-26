package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.common.EntityMapper;
import keios.common.Message;
import keios.common.MessageType;
import keios.protocol.spacy.flatbuffers.SpacyRequest;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    private static class SpacyRequestSerializer implements ChildSerializer<SpacyRequestEntity> {
        @Override
        public int serialize(SpacyRequestEntity obj, FlatBufferBuilder builder) {
            int textOffset = builder.createString(obj.getText());
            int typesVector = SpacyRequest.createTypeVector(builder, toBytes(obj.getTypes()));
            SpacyRequest.startSpacyRequest(builder);
            SpacyRequest.addText(builder, textOffset);
            SpacyRequest.addType(builder, typesVector);
            return SpacyRequest.endSpacyRequest(builder);
        }

        private byte[] toBytes(Set<ESpacyType> types) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            types.forEach(t -> bos.write(t.getByteValue()));
            return bos.toByteArray();
        }


    }

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    static class SpacyRequestMapper implements EntityMapper<SpacyRequest, SpacyRequestEntity> {
        @Override
        public SpacyRequestEntity from(SpacyRequest input) {
            return new SpacyRequestEntity(input.text(),
                    IntStream.range(0, input.typeLength())
                            .mapToObj(input::type)
                            .map(ESpacyType::fromByte)
                            .collect(Collectors.toSet())
            );
        }
    }

}
