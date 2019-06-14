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

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.lucene.flatbuffers.LuceneMessageType;

/**
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.4.0
 */
public interface Message {
    /**
     * Indicates the {@link LuceneMessageType}
     */
    default LuceneMessageEntity.MessageType type() {
        return LuceneMessageEntity.MessageType.NONE;
    }

    default int serialize(FlatBufferBuilder builder) {
        return 0;
    }
}