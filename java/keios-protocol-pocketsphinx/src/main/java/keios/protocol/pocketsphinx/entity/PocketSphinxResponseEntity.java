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

package keios.protocol.pocketsphinx.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.common.EntityMapper;
import keios.protocol.common.TypedMessage;
import keios.protocol.pocketsphinx.entity.GuessEntity.GuessMapper;
import keios.protocol.pocketsphinx.entity.GuessEntity.GuessSerializer;
import keios.protocol.pocketsphinx.entity.PocketSphinxMessageEntity.PocketSphinxMessageType;
import keios.protocol.pocketsphinx.flatbuffers.PocketSphinxResponse;
import keios.protocol.pocketsphinx.flatbuffers.PocketsphinxResponse;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * @author Michael Mair
 */
public class PocketSphinxResponseEntity implements TypedMessage {

    private final List<GuessEntity> guesses;

    public PocketSphinxResponseEntity(List<GuessEntity> guesses) {
        if (guesses == null) {
            guesses = emptyList();
        }
        this.guesses = guesses;
    }

    public List<GuessEntity> getGuesses() {
        return guesses;
    }

    @Override
    public PocketSphinxMessageType type() {
        return PocketSphinxMessageType.RESPONSE;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return new PocketSphinxResponseSerializer().serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PocketSphinxResponseEntity that = (PocketSphinxResponseEntity) o;
        return Objects.equals(guesses, that.guesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guesses);
    }

    @Override
    public String toString() {
        return "PocketSphinxResponseEntity{" +
                "guesses=" + guesses +
                '}';
    }

    public static class PocketSphinxResponseMapper implements EntityMapper<PocketSphinxResponse, PocketSphinxResponseEntity> {

        private static final GuessMapper guessMapper = new GuessMapper();

        @Override
        public PocketSphinxResponseEntity from(PocketSphinxResponse input) {
            return new PocketSphinxResponseEntity(IntStream.range(0, input.guessesLength())
                    .mapToObj(input::guesses)
                    .map(guessMapper::from)
                    .collect(toList()));
        }
    }

    public static class PocketSphinxResponseSerializer implements ChildSerializer<PocketSphinxResponseEntity> {

        private static final GuessSerializer guessSerializer = new GuessSerializer();

        @Override
        public int serialize(PocketSphinxResponseEntity obj, FlatBufferBuilder builder) {
            int[] guessOffsets = obj.getGuesses().stream()
                    .mapToInt(entity -> guessSerializer.serialize(entity, builder))
                    .toArray();
            int guesses = PocketsphinxResponse.createGuessesVector(builder, guessOffsets);

            PocketsphinxResponse.startPocketsphinxResponse(builder);
            PocketsphinxResponse.addGuesses(builder, guesses);
            return PocketsphinxResponse.endPocketsphinxResponse(builder);
        }
    }
}
