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
import keios.common.Message;

import java.util.Objects;

import static keios.protocol.lucene.entity.LuceneMessageEntity.LuceneMessageType.WRITE_RESPONSE;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteResponseEntity implements Message {
    private final WriteResultEntity writeResult;
    private final ChildSerializer<LuceneWriteResponseEntity> serializer = new LuceneWriteResponseSerializer();

    public LuceneWriteResponseEntity(WriteResultEntity writeResult) {
        this.writeResult = writeResult;
    }

    public WriteResultEntity getWriteResult() {
        return writeResult;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public LuceneMessageEntity.LuceneMessageType type() {
        return WRITE_RESPONSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneWriteResponseEntity that = (LuceneWriteResponseEntity) o;
        return writeResult == that.writeResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(writeResult);
    }
}
