package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.spacy.flatbuffers.SpacyResponse;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyResponseSerializer implements ChildSerializer<SpacyResponseEntity> {

    private final DEPSpacyResponseSerializer depSpacyResponseSerializer = new DEPSpacyResponseSerializer();
    private final NERSpacyResponseSerializer nerSpacyResponseSerializer = new NERSpacyResponseSerializer();

    @Override
    public int serialize(SpacyResponseEntity obj, FlatBufferBuilder builder) {
        int depVectorOffset = SpacyResponse.createDepVector(builder, obj.getDepResponses()
                .stream()
                .map(it -> depSpacyResponseSerializer.serialize(it, builder))
                .mapToInt(i -> i).toArray());

        int nerVectorOffset = SpacyResponse.createNerVector(builder, obj.getNerResponses()
                .stream()
                .map(it -> nerSpacyResponseSerializer.serialize(it, builder))
                .mapToInt(i -> i).toArray()
        );

        SpacyResponse.startSpacyResponse(builder);
        SpacyResponse.addDep(builder, depVectorOffset);
        SpacyResponse.addNer(builder, nerVectorOffset);

        return SpacyResponse.endSpacyResponse(builder);
    }
}
