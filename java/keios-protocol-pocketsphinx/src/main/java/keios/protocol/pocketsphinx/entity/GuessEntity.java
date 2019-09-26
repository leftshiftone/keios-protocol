package keios.protocol.pocketsphinx.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.pocketsphinx.flatbuffers.Guess;

import java.util.Objects;

/**
 * @author Michael Mair
 */
public class GuessEntity {

    private final String phrase;
    private final float confidence;

    public GuessEntity(String phrase, Float confidence) {
        this.phrase = phrase == null ? "" : phrase;
        this.confidence = confidence == null ? 0 : confidence;
    }

    public String getPhrase() {
        return phrase;
    }

    public float getConfidence() {
        return confidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessEntity that = (GuessEntity) o;
        return Float.compare(that.confidence, confidence) == 0 &&
                Objects.equals(phrase, that.phrase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phrase, confidence);
    }

    @Override
    public String toString() {
        return "GuessEntity{" +
                "phrase='" + phrase + '\'' +
                ", confidence=" + confidence +
                '}';
    }

    public static class GuessMapper implements EntityMapper<Guess, GuessEntity> {

        @Override
        public GuessEntity from(Guess input) {
            return new GuessEntity(input.phrase(), input.confidence());
        }
    }

    public static class GuessSerializer implements ChildSerializer<GuessEntity> {

        @Override
        public int serialize(GuessEntity obj, FlatBufferBuilder builder) {
            int phraseOffset = builder.createString(obj.getPhrase());

            Guess.startGuess(builder);
            Guess.addPhrase(builder, phraseOffset);
            Guess.addConfidence(builder, obj.getConfidence());
            return Guess.endGuess(builder);
        }
    }
}
