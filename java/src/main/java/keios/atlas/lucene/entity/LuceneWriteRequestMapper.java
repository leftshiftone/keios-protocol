package keios.atlas.lucene.entity;

import keios.atlas.lucene.flatbuffers.LuceneWriteRequest;
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