/*
 * Copyright (c) 2016-2019, Leftshift One
 * __________________
 * [2019] Leftshift One
 * All Rights Reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Leftshift One and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Leftshift One
 * and its suppliers and may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Leftshift One.
 */

package keios.protocol.lucene;

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

    public static WriteResultEntity fromByte(byte b) {
        return Arrays.stream(values())
                .filter(v -> v.asByte == b)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("could not map %d to %s", b, WriteResultEntity.class.getSimpleName())));
    }

    public byte getAsByte() {
        return asByte;
    }
}
