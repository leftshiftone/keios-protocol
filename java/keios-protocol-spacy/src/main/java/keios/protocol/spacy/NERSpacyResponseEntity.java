package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.FlatbufferSerializable;
import keios.protocol.spacy.flatbuffers.NERSpacyResponse;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class NERSpacyResponseEntity implements FlatbufferSerializable {
    private final ChildSerializer<NERSpacyResponseEntity> serializer = new NERSpacyResponseSerializer();
    private final String text;
    private final Integer startChar;
    private final Integer endChar;
    private final String label;

    public NERSpacyResponseEntity(String text, Integer startChar, Integer endChar, String label) {
        this.text = text;
        this.startChar = startChar;
        this.endChar = endChar;
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public Integer getStartChar() {
        return startChar;
    }

    public Integer getEndChar() {
        return endChar;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NERSpacyResponseEntity that = (NERSpacyResponseEntity) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(startChar, that.startChar) &&
                Objects.equals(endChar, that.endChar) &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, startChar, endChar, label);
    }

    /**
     * @author benjamin.krenn@leftshift.one - 8/9/19.
     * @since 0.1.0
     */
    static class NERSpacyResponseSerializer implements ChildSerializer<NERSpacyResponseEntity> {
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

}
