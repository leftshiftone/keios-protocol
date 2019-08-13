package keios.protocol.spacy.entity;

import keios.common.EntityMapper;
import keios.protocol.spacy.flatbuffers.SpacyRequest;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyRequestMapper implements EntityMapper<SpacyRequest, SpacyRequestEntity> {
    @Override
    public SpacyRequestEntity from(SpacyRequest input) {
        return new SpacyRequestEntity(input.text(),
                IntStream.range(0, input.typeLength())
                        .mapToObj(input::type)
                        .map(ESpacyType::fromByte)
                .collect(Collectors.toSet())
        );
    }
}
