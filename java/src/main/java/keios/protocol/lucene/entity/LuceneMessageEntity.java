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

import keios.protocol.lucene.flatbuffers.LuceneMessageType;
import keios.protocol.lucene.flatbuffers.LuceneMessage;

/**
 * Message wrapper that holds a reference to the actual message. Every {@link Message} must be wrapped
 * by a {@link LuceneMessage} before sending it over the wire.
 *
 * This eases the deserialization on the receiver side by being able to call {@link Message#type()}
 *
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public class LuceneMessageEntity<T extends Message> {

    private final T message;

    public LuceneMessageEntity(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    enum MessageType {
        READ_REQUEST(LuceneMessageType.LuceneReadRequest),
        READ_RESPONSE(LuceneMessageType.LuceneReadResponse),
        WRITE_REQUEST(LuceneMessageType.LuceneWriteRequest),
        WRITE_RESPONSE(LuceneMessageType.LuceneWriteResponse),
        NONE(LuceneMessageType.NONE);

        final byte byteVal;

        MessageType(byte byteVal) {
            this.byteVal = byteVal;
        }
    }
}
