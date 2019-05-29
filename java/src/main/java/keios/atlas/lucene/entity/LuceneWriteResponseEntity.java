package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;

import java.util.Objects;

import static keios.atlas.lucene.entity.LuceneMessageEntity.MessageType.WRITE_RESPONSE;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteResponseEntity implements Message {
    private final WriteResultEntity writeResult;

    public LuceneWriteResponseEntity(WriteResultEntity writeResult) {
        this.writeResult = writeResult;
    }

    public WriteResultEntity getWriteResult() {
        return writeResult;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        LuceneWriteResponseSerializer serializer = new LuceneWriteResponseSerializer();
        return serializer.serialize(this, builder);
    }

    @Override
    public LuceneMessageEntity.MessageType type() {
        return WRITE_RESPONSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneWriteResponseEntity that = (LuceneWriteResponseEntity) o;
        return writeResult == that.writeResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(writeResult);
    }
}
