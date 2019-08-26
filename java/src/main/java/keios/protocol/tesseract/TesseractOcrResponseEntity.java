package keios.protocol.tesseract;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.common.EntityMapper;
import keios.common.Message;
import keios.common.MessageType;
import keios.protocol.tesseract.flatbuffers.TesseractOcrResponse;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 0.1.0
 */
public class TesseractOcrResponseEntity implements Message {

    private final String text;
    private final long confidence;
    private final ChildSerializer<TesseractOcrResponseEntity> serializer = new TesseractOcrResponseSerializer();

    public TesseractOcrResponseEntity(String text, long confidence) {
        this.text = Objects.requireNonNull(text, "text can not be null");
        this.confidence = confidence;
    }

    @Override
    public MessageType type() {
        return TesseractMessageEntity.TesseractMessageType.OCR_RESPONSE;
    }

    public String getText() {
        return text;
    }

    public long getConfidence() {
        return confidence;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesseractOcrResponseEntity that = (TesseractOcrResponseEntity) o;
        return confidence == that.confidence &&
                text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, confidence);
    }

    @Override
    public String toString() {
        return "TesseractOcrResponseEntity{" +
                "text='" + text + '\'' +
                ", confidence=" + confidence +
                '}';
    }

    private static class TesseractOcrResponseSerializer implements ChildSerializer<TesseractOcrResponseEntity> {
        @Override
        public int serialize(TesseractOcrResponseEntity obj, FlatBufferBuilder builder) {
            int textOffset = builder.createString(obj.getText());
            TesseractOcrResponse.startTesseractOcrResponse(builder);
            TesseractOcrResponse.addText(builder, textOffset);
            TesseractOcrResponse.addConfidence(builder, obj.getConfidence());

            return TesseractOcrResponse.endTesseractOcrResponse(builder);
        }
    }

    static class TesseractOcrResponseMapper implements EntityMapper<TesseractOcrResponse, TesseractOcrResponseEntity> {
        @Override
        public TesseractOcrResponseEntity from(TesseractOcrResponse input) {
            return new TesseractOcrResponseEntity(input.text(), input.confidence());
        }
    }
}
