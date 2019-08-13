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

import keios.common.Message;
import keios.protocol.spacy.entity.SpacyMessageDeserializer;
import keios.protocol.spacy.entity.SpacyMessageEntity;
import keios.protocol.spacy.entity.SpacyMessageSerializer;

public class SpacyProtocol {

    private static final SpacyMessageSerializer serializer = new SpacyMessageSerializer();
    private static final SpacyMessageDeserializer deserializer = new SpacyMessageDeserializer();

    private SpacyProtocol() {
        throw new UnsupportedOperationException();
    }

    public static SpacyMessageEntity toMessage(byte[] bytes) {
        return deserializer.deserialize(bytes);
    }

    public static <T extends Message> byte[] toWireMessage(SpacyMessageEntity<T> entity) {
        return serializer.serialize(entity);
    }
}
