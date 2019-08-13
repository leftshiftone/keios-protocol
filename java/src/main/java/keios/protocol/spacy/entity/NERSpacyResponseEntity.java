package keios.protocol.spacy.entity;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one - 8/9/19.
 * @since 0.1.0
 */
public class NERSpacyResponseEntity {
    private final String text;
    private final Integer startChar;
    private final Integer endChar;
    private final String label;

    public NERSpacyResponseEntity(String text, Integer startChar, Integer endChar, String label) {
        this.text = text;
        this.startChar = startChar;
        this.endChar = endChar;
        this.label = label;
    }

    public String getText() {
        return text;
    }

    public Integer getStartChar() {
        return startChar;
    }

    public Integer getEndChar() {
        return endChar;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NERSpacyResponseEntity that = (NERSpacyResponseEntity) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(startChar, that.startChar) &&
                Objects.equals(endChar, that.endChar) &&
                Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, startChar, endChar, label);
    }
}
