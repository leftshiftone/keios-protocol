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
import keios.common.ChildSerializer;
import keios.protocol.lucene.flatbuffers.LuceneWriteResponse;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
class LuceneWriteResponseSerializer implements ChildSerializer<LuceneWriteResponseEntity> {
    @Override
    public int serialize(LuceneWriteResponseEntity obj, FlatBufferBuilder builder) {
        LuceneWriteResponse.startLuceneWriteResponse(builder);
        LuceneWriteResponse.addWriteResult(builder, obj.getWriteResult().getAsByte());
        return LuceneWriteResponse.endLuceneWriteResponse(builder);
    }
}
