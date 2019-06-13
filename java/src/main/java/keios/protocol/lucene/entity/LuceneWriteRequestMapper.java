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

import keios.protocol.lucene.flatbuffers.LuceneWriteRequest;
import keios.common.EntityMapper;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
class LuceneWriteRequestMapper implements EntityMapper<LuceneWriteRequest, LuceneWriteRequestEntity> {
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
