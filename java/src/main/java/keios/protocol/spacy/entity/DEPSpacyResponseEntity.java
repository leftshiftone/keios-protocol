package keios.protocol.spacy.entity;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class DEPSpacyResponseEntity {
    private final String lang;
    private final String relation;
    private final String source;
    private final String sourcePos;
    private final Integer sourceIndex;
    private final String sourceTag;
    private final String sourceBase;
    private final String target;
    private final String targetPos;
    private final Integer targetIndex;
    private final String targetTag;
    private final String targetBase;

    public DEPSpacyResponseEntity(String lang, String relation, String source, String sourcePos, Integer sourceIndex, String sourceTag, String sourceBase, String target, String targetPos, Integer targetIndex, String targetTag, String targetBase) {
        this.lang = lang;
        this.relation = relation;
        this.source = source;
        this.sourcePos = sourcePos;
        this.sourceIndex = sourceIndex;
        this.sourceTag = sourceTag;
        this.sourceBase = sourceBase;
        this.target = target;
        this.targetPos = targetPos;
        this.targetIndex = targetIndex;
        this.targetTag = targetTag;
        this.targetBase = targetBase;
    }

    public String getLang() {
        return lang;
    }

    public String getRelation() {
        return relation;
    }

    public String getSource() {
        return source;
    }

    public String getSourcePos() {
        return sourcePos;
    }

    public Integer getSourceIndex() {
        return sourceIndex;
    }

    public String getSourceTag() {
        return sourceTag;
    }

    public String getSourceBase() {
        return sourceBase;
    }

    public String getTarget() {
        return target;
    }

    public String getTargetPos() {
        return targetPos;
    }

    public Integer getTargetIndex() {
        return targetIndex;
    }

    public String getTargetTag() {
        return targetTag;
    }

    public String getTargetBase() {
        return targetBase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DEPSpacyResponseEntity that = (DEPSpacyResponseEntity) o;
        return Objects.equals(lang, that.lang) &&
                Objects.equals(relation, that.relation) &&
                Objects.equals(source, that.source) &&
                Objects.equals(sourcePos, that.sourcePos) &&
                Objects.equals(sourceIndex, that.sourceIndex) &&
                Objects.equals(sourceTag, that.sourceTag) &&
                Objects.equals(sourceBase, that.sourceBase) &&
                Objects.equals(target, that.target) &&
                Objects.equals(targetPos, that.targetPos) &&
                Objects.equals(targetIndex, that.targetIndex) &&
                Objects.equals(targetTag, that.targetTag) &&
                Objects.equals(targetBase, that.targetBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lang, relation, source, sourcePos, sourceIndex, sourceTag, sourceBase, target, targetPos, targetIndex, targetTag, targetBase);
    }
}
