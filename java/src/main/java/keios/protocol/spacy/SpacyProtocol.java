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

import keios.common.BinaryDeserializer;
import keios.common.BinarySerializer;
import keios.common.Message;
import keios.common.Protocol;

public class SpacyProtocol implements Protocol<SpacyMessageEntity<Message>> {

    private static final SpacyProtocol INSTANCE = new SpacyProtocol();
    private static final BinarySerializer<SpacyMessageEntity<Message>> serializer = new SpacyMessageEntity.SpacyMessageSerializer();
    private static final BinaryDeserializer<SpacyMessageEntity<Message>> deserializer = new SpacyMessageEntity.SpacyMessageDeserializer();

    private SpacyProtocol() {
    }

    public static SpacyProtocol instance() {
        return INSTANCE;
    }

    @Override
    public SpacyMessageEntity<Message> toMessage(byte[] bytes) {
        return deserializer.deserialize(bytes);
    }

    @Override
    public byte[] toWireMessage(SpacyMessageEntity<Message> message) {
        return serializer.serialize(message);
    }
}
