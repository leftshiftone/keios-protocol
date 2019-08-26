package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.common.TypedMessage;
import keios.protocol.common.MessageType;
import keios.protocol.spacy.flatbuffers.SpacyResponse;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class SpacyResponseEntity implements TypedMessage {

    private final List<DEPSpacyResponseEntity> depResponses;
    private final List<NERSpacyResponseEntity> nerResponses;
    private final ChildSerializer<SpacyResponseEntity> serializer = new SpacyResponseSerializer();

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
        return this.serializer.serialize(this, builder);
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

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    private static class SpacyResponseSerializer implements ChildSerializer<SpacyResponseEntity> {
        @Override
        public int serialize(SpacyResponseEntity obj, FlatBufferBuilder builder) {
            int depVectorOffset = SpacyResponse.createDepVector(builder, obj.getDepResponses()
                    .stream()
                    .map(it -> it.serialize(builder))
                    .mapToInt(i -> i).toArray());

            int nerVectorOffset = SpacyResponse.createNerVector(builder, obj.getNerResponses()
                    .stream()
                    .map(it -> it.serialize(builder))
                    .mapToInt(i -> i).toArray()
            );

            SpacyResponse.startSpacyResponse(builder);
            SpacyResponse.addDep(builder, depVectorOffset);
            SpacyResponse.addNer(builder, nerVectorOffset);

            return SpacyResponse.endSpacyResponse(builder);
        }
    }

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    static class SpacyResponseMapper implements EntityMapper<SpacyResponse, SpacyResponseEntity> {
        @Override
        public SpacyResponseEntity from(SpacyResponse input) {
            return new SpacyResponseEntity(mapDeps(input), mapNer(input));
        }

        private List<DEPSpacyResponseEntity> mapDeps(SpacyResponse response) {
            return IntStream.range(0, response.depLength())
                    .mapToObj(response::dep)
                    .map(dep -> new DEPSpacyResponseEntity(
                            dep.lang(),
                            dep.relation(),
                            dep.source(),
                            dep.sourcePos(),
                            dep.sourceIndex(),
                            dep.sourceTag(),
                            dep.sourceBase(),
                            dep.target(),
                            dep.targetPos(),
                            dep.targetIndex(),
                            dep.targetTag(),
                            dep.targetBase()
                    )).collect(Collectors.toList());
        }

        private List<NERSpacyResponseEntity> mapNer(SpacyResponse response) {
            return IntStream.range(0, response.nerLength())
                    .mapToObj(response::ner)
                    .map(ner -> new NERSpacyResponseEntity(ner.text(),
                            ner.startChar(),
                            ner.endChar(),
                            ner.label()))
                    .collect(Collectors.toList());
        }
    }

}
