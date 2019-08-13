package keios.protocol.spacy.entity;

import keios.common.EntityMapper;
import keios.protocol.spacy.flatbuffers.SpacyBatchResponse;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyBatchResponseMapper implements EntityMapper<SpacyBatchResponse, SpacyBatchResponseEntity> {
    @Override
    public SpacyBatchResponseEntity from(SpacyBatchResponse input) {
        SpacyResponseMapper mapper = new SpacyResponseMapper();
        return new SpacyBatchResponseEntity(IntStream.range(0, input.responsesLength())
                .mapToObj(input::responses)
                .map(mapper::from).collect(Collectors.toList()));
    }
}
