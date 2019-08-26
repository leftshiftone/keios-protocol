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

package keios.protocol.gensim.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.BinarySerializer;
import keios.protocol.gensim.flatbuffers.GensimMessage;

/**
 * @author Michael Mair
 */
public class GensimMessageSerializer implements BinarySerializer<GensimMessageEntity> {
    @Override
    public byte[] serialize(GensimMessageEntity entity) {
        FlatBufferBuilder builder = new FlatBufferBuilder();

        int messageOffset = entity.getMessage().serialize(builder);

        GensimMessage.startGensimMessage(builder);
        GensimMessage.addMessageType(builder, entity.getMessage().type().byteVal());
        GensimMessage.addMessage(builder, messageOffset);
        int resultOffset = GensimMessage.endGensimMessage(builder);
        builder.finish(resultOffset);
        return builder.sizedByteArray();
    }
}
