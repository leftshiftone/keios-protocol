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

package keios.protocol.lucene.entity;

import keios.common.BinaryDeserializer;
import keios.protocol.lucene.flatbuffers.*;

import java.nio.ByteBuffer;

/**
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public class LuceneMessageDeserializer implements BinaryDeserializer<LuceneMessageEntity> {
    @Override
    public LuceneMessageEntity<Message> deserialize(ByteBuffer bb) {
        LuceneMessage message = LuceneMessage.getRootAsLuceneMessage(bb);
        int messageType = message.messageType();
        switch (messageType) {
            case LuceneMessageType.LuceneReadRequest:
                return new LuceneMessageEntity<>(new LuceneReadRequestMapper()
                        .from((LuceneReadRequest) message.message(new LuceneReadRequest())));
            case LuceneMessageType.LuceneReadResponse:
                return new LuceneMessageEntity<>(new LuceneReadResponseMapper()
                        .from((LuceneReadResponse) message.message(new LuceneReadResponse())));
            case LuceneMessageType.LuceneWriteRequest:
                return new LuceneMessageEntity<>(new LuceneWriteRequestMapper()
                        .from((LuceneWriteRequest) message.message(new LuceneWriteRequest())));
            case LuceneMessageType.LuceneWriteResponse:
                return new LuceneMessageEntity<>(new LuceneWriteResponseMapper()
                        .from((LuceneWriteResponse) message.message(new LuceneWriteResponse())));
            default:
                throw new IllegalArgumentException("could not deserialize message");
        }
    }
}
