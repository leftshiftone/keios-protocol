package keios.atlas.lucene.entity;

import java.util.Arrays;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public enum WriteResultEntity {
    SUCCESS((byte) 0),
    FAILURE((byte) 1);

    private final byte asByte;

    WriteResultEntity(byte asByte) {
        this.asByte = asByte;
    }

    public byte getAsByte() {
        return asByte;
    }

    public static WriteResultEntity fromByte(byte b) {
        return Arrays.stream(values())
                .filter(v -> v.asByte == b)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("could not map %d to %s", b, WriteResultEntity.class.getSimpleName())));
    }
}
