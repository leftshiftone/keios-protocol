package keios.atlas.lucene.entity;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class LuceneWriteResponseEntity {
    private final WriteResultEntity writeResult;

    public LuceneWriteResponseEntity(WriteResultEntity writeResult) {
        this.writeResult = writeResult;
    }

    public WriteResultEntity getWriteResult() {
        return writeResult;
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
