package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.spacy.flatbuffers.SpacyRequest;

import java.io.ByteArrayOutputStream;
import java.util.Set;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyRequestSerializer implements ChildSerializer<SpacyRequestEntity> {
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
