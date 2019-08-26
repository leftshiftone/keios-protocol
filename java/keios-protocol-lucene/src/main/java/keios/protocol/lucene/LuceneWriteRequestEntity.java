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

package keios.protocol.lucene;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.common.Message;
import keios.protocol.lucene.flatbuffers.LuceneWriteRequest;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static keios.protocol.lucene.LuceneMessageEntity.LuceneMessageType.WRITE_REQUEST;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 0.3.0
 */
public class LuceneWriteRequestEntity implements Message {
    private final Map<String, String> document;
    private final ChildSerializer<LuceneWriteRequestEntity> serializer = new LuceneWriteRequestSerializer();

    public LuceneWriteRequestEntity(Map<String, String> document) {
        this.document = Objects.requireNonNull(document, "document can not be null");
    }

    public Map<String, String> getDocument() {
        return Collections.unmodifiableMap(this.document);
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return this.serializer.serialize(this, builder);
    }

    @Override
    public LuceneMessageEntity.LuceneMessageType type() {
        return WRITE_REQUEST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LuceneWriteRequestEntity that = (LuceneWriteRequestEntity) o;
        return document.equals(that.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document);
    }

    static class LuceneWriteRequestMapper implements EntityMapper<LuceneWriteRequest, LuceneWriteRequestEntity> {
        @Override
        public LuceneWriteRequestEntity from(LuceneWriteRequest input) {
            Map<String, String> document = IntStream.range(0, input.documentLength())
                    .mapToObj(input::document)
                    .collect(Collectors.toMap(
                            t -> Optional.ofNullable(t.key())
                                    .orElseThrow(throwIfNull()),
                            t -> Optional.ofNullable(t.value())
                                    .orElseThrow(throwIfNull())));

            return new LuceneWriteRequestEntity(document);
        }

        private static Supplier<RuntimeException> throwIfNull() {
            return IllegalStateException::new;
        }
    }

    private static class LuceneWriteRequestSerializer implements ChildSerializer<LuceneWriteRequestEntity> {

        private final DocumentSerializer documentSerializer = new DocumentSerializer();

        @Override
        public int serialize(LuceneWriteRequestEntity obj, FlatBufferBuilder builder) {
            int documentOffset = documentSerializer.serialize(obj.getDocument(), builder);

            LuceneWriteRequest.startLuceneWriteRequest(builder);
            LuceneWriteRequest.addDocument(builder, documentOffset);
            return LuceneWriteRequest.endLuceneWriteRequest(builder);
        }
    }

}
