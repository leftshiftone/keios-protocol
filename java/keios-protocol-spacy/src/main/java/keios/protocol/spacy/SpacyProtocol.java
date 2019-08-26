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

import keios.protocol.common.BinaryDeserializer;
import keios.protocol.common.BinarySerializer;
import keios.protocol.common.Protocol;

public class SpacyProtocol implements Protocol<SpacyMessageEntity> {

    private static final SpacyProtocol INSTANCE = new SpacyProtocol();
    private final BinarySerializer<SpacyMessageEntity> serializer = new SpacyMessageEntity.SpacyMessageSerializer();
    private final BinaryDeserializer<SpacyMessageEntity> deserializer = new SpacyMessageEntity.SpacyMessageDeserializer();

    private SpacyProtocol() {
    }

    public static SpacyProtocol instance() {
        return INSTANCE;
    }

    @Override
    public SpacyMessageEntity toMessage(byte[] bytes) {
        return deserializer.deserialize(bytes);
    }

    @Override
    public byte[] toWireMessage(SpacyMessageEntity message) {
        return serializer.serialize(message);
    }
}
