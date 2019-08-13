package keios.protocol.spacy.entity;

import keios.common.EntityMapper;
import keios.protocol.spacy.flatbuffers.SpacyBatchRequest;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyBatchRequestMapper implements EntityMapper<SpacyBatchRequest, SpacyBatchRequestEntity> {
    @Override
    public SpacyBatchRequestEntity from(SpacyBatchRequest input) {
        SpacyRequestMapper requestMapper = new SpacyRequestMapper();
        return new SpacyBatchRequestEntity(IntStream.range(0, input.requestsLength())
                .mapToObj(input::requests)
                .map(requestMapper::from).collect(Collectors.toList()));
    }
}
