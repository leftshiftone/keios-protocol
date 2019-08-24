package keios.protocol.tesseract;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.common.EntityMapper;
import keios.common.Message;
import keios.common.MessageType;
import keios.protocol.tesseract.flatbuffers.TesseractOcrRequest;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one - 8/24/19.
 * @since 0.1.0
 */
public class TesseractOcrRequestEntity implements Message {

    private final byte[] image;
    private final ChildSerializer<TesseractOcrRequestEntity> serializer = new TesseractOcrRequestSerializer();

    public TesseractOcrRequestEntity(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public MessageType type() {
        return TesseractMessageEntity.TesseractMessageType.OCR_REQUEST;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesseractOcrRequestEntity that = (TesseractOcrRequestEntity) o;
        return Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }

    @Override
    public String toString() {
        return "TesseractOcrRequestEntity{" +
                "image=" + Arrays.toString(image) +
                '}';
    }

    private static class TesseractOcrRequestSerializer implements ChildSerializer<TesseractOcrRequestEntity> {
        @Override
        public int serialize(TesseractOcrRequestEntity obj, FlatBufferBuilder builder) {
            int bytesOffset = TesseractOcrRequest.createImageVector(builder, obj.getImage());

            TesseractOcrRequest.startTesseractOcrRequest(builder);
            TesseractOcrRequest.addImage(builder, bytesOffset);

            return TesseractOcrRequest.endTesseractOcrRequest(builder);
        }
    }

    static class TesseractOcrRequestMapper implements EntityMapper<TesseractOcrRequest, TesseractOcrRequestEntity> {
        @Override
        public TesseractOcrRequestEntity from(TesseractOcrRequest input) {
            byte[] bb = new byte[input.imageLength()];
            IntStream.range(0, input.imageLength())
                    .forEach(i -> bb[i] = input.image(i));
            return new TesseractOcrRequestEntity(bb);
        }
    }
}
