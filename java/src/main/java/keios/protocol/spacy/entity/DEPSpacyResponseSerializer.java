package keios.protocol.spacy.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.common.ChildSerializer;
import keios.protocol.spacy.flatbuffers.DEPSpacyResponse;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class DEPSpacyResponseSerializer implements ChildSerializer<DEPSpacyResponseEntity> {
    @Override
    public int serialize(DEPSpacyResponseEntity obj, FlatBufferBuilder builder) {
        int langOffset = builder.createString(obj.getLang());
        int relationOffset = builder.createString(obj.getRelation());
        int sourceOffset = builder.createString(obj.getSource());
        int sourcePosOffset = builder.createString(obj.getSourcePos());
        int sourceTagOffset = builder.createString(obj.getSourceTag());
        int sourceBaseOffset = builder.createString(obj.getSourceBase());
        int targetOffset = builder.createString(obj.getTarget());
        int targetPosOffset = builder.createString(obj.getTargetPos());
        int targetTagOffset = builder.createString(obj.getTargetTag());
        int targetBaseOffset = builder.createString(obj.getTargetBase());

        DEPSpacyResponse.startDEPSpacyResponse(builder);
        DEPSpacyResponse.addLang(builder, langOffset);
        DEPSpacyResponse.addRelation(builder, relationOffset);
        DEPSpacyResponse.addSource(builder, sourceOffset);
        DEPSpacyResponse.addSourcePos(builder, sourcePosOffset);
        DEPSpacyResponse.addSourceIndex(builder, obj.getSourceIndex());
        DEPSpacyResponse.addSourceTag(builder, sourceTagOffset);
        DEPSpacyResponse.addSourceBase(builder, sourceBaseOffset);
        DEPSpacyResponse.addTarget(builder, targetOffset);
        DEPSpacyResponse.addTargetPos(builder, targetPosOffset);
        DEPSpacyResponse.addTargetIndex(builder, obj.getTargetIndex());
        DEPSpacyResponse.addTargetTag(builder, targetTagOffset);
        DEPSpacyResponse.addTargetBase(builder, targetBaseOffset);

        return DEPSpacyResponse.endDEPSpacyResponse(builder);
    }
}
