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
import keios.protocol.pocketsphinx.entity.PocketSphinxMessageEntity.PocketSphinxMessageType;
import keios.protocol.pocketsphinx.flatbuffers.PocketSphinxRequest;
import keios.protocol.pocketsphinx.flatbuffers.PocketsphinxRequest;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Michael Mair
 */
public class PocketSphinxRequestEntity implements TypedMessage {

    private final byte[] speech;

    public PocketSphinxRequestEntity(byte[] speech) {
        if (speech == null || speech.length == 0) {
            speech = new byte[0];
        }
        this.speech = speech;
    }

    public byte[] getSpeech() {
        return speech;
    }

    @Override
    public PocketSphinxMessageType type() {
        return PocketSphinxMessageType.REQUEST;
    }

    @Override
    public int serialize(FlatBufferBuilder builder) {
        return new PocketSphinxRequestSerializer().serialize(this, builder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PocketSphinxRequestEntity that = (PocketSphinxRequestEntity) o;
        return Arrays.equals(speech, that.speech);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(speech);
    }

    @Override
    public String toString() {
        return "PocketSphinxRequestEntity{" +
                "speech=" + Arrays.toString(speech) +
                '}';
    }

    public static class PocketSphinxRequestMapper implements EntityMapper<PocketSphinxRequest, PocketSphinxRequestEntity> {

        @Override
        public PocketSphinxRequestEntity from(PocketSphinxRequest input) {
            byte[] speech = new byte[input.speechLength()];
            IntStream.range(0, input.speechLength())
                    .forEach(i -> speech[i] = (byte) input.speech(i));
            return new PocketSphinxRequestEntity(speech);
        }
    }

    public static class PocketSphinxRequestSerializer implements ChildSerializer<PocketSphinxRequestEntity> {

        @Override
        public int serialize(PocketSphinxRequestEntity obj, FlatBufferBuilder builder) {
            int bytesOffset = PocketsphinxRequest.createSpeechVector(builder, obj.getSpeech());

            PocketsphinxRequest.startPocketsphinxRequest(builder);
            PocketsphinxRequest.addSpeech(builder, bytesOffset);

            return PocketsphinxRequest.endPocketsphinxRequest(builder);
        }
    }
}
