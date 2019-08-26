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

package keios.protocol.spacy;

import java.util.Arrays;

public enum ESpacyType {
    DEP((byte) 0), NER((byte) 1);

    private final byte byteValue;

    ESpacyType(byte byteValue) {
        this.byteValue = byteValue;
    }

    public static ESpacyType fromByte(byte b) {
        return Arrays.stream(values())
                .filter(v -> v.byteValue == b)
                .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("could not map %d to %s", b, ESpacyType.class.getSimpleName())));
    }

    public static byte[] toBytes(ESpacyType... types) {
        byte[] bytes = new byte[types.length];
        for (int i = 0; i < types.length; i++) {
            bytes[i] = (byte) types[i].ordinal();
        }
        return bytes;
    }

    public byte getByteValue() {
        return byteValue;
    }
}
