package keios.protocol.spacy.entity;

import keios.common.EntityMapper;
import keios.protocol.spacy.flatbuffers.SpacyResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class SpacyResponseMapper implements EntityMapper<SpacyResponse, SpacyResponseEntity> {
    @Override
    public SpacyResponseEntity from(SpacyResponse input) {
        return new SpacyResponseEntity(mapDeps(input), mapNer(input));
    }

    private List<DEPSpacyResponseEntity> mapDeps(SpacyResponse response) {
        return IntStream.range(0, response.depLength())
                .mapToObj(response::dep)
                .map(dep -> new DEPSpacyResponseEntity(
                        dep.lang(),
                        dep.relation(),
                        dep.source(),
                        dep.sourcePos(),
                        dep.sourceIndex(),
                        dep.sourceTag(),
                        dep.sourceBase(),
                        dep.target(),
                        dep.targetPos(),
                        dep.targetIndex(),
                        dep.targetTag(),
                        dep.targetBase()
                )).collect(Collectors.toList());
    }

    private List<NERSpacyResponseEntity> mapNer(SpacyResponse response) {
        return IntStream.range(0, response.nerLength())
                .mapToObj(response::ner)
                .map(ner -> new NERSpacyResponseEntity(ner.text(),
                        ner.startChar(),
                        ner.endChar(),
                        ner.label()))
                .collect(Collectors.toList());
    }
}
