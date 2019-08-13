package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.spacy.flatbuffers.NERSpacyResponse;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class NERSpacyResponseSerializer implements ChildSerializer<NERSpacyResponseEntity> {
    @Override
    public int serialize(NERSpacyResponseEntity obj, FlatBufferBuilder builder) {
        int textOffset = builder.createString(obj.getText());
        int labelOffset = builder.createString(obj.getLabel());

        NERSpacyResponse.startNERSpacyResponse(builder);
        NERSpacyResponse.addText(builder, textOffset);
        NERSpacyResponse.addStartChar(builder, obj.getStartChar());
        NERSpacyResponse.addEndChar(builder, obj.getEndChar());
        NERSpacyResponse.addLabel(builder, labelOffset);

        return NERSpacyResponse.endNERSpacyResponse(builder);
    }
}
