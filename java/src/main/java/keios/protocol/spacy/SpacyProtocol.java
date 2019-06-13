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

package keios.protocol.spacy;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.spacy.flatbuffers.SpacyRequest;
import keios.protocol.spacy.flatbuffers.SpacyResponse;

import java.nio.ByteBuffer;

public class SpacyProtocol {

    public static byte[] toSpacyRequest(String text, ESpacyType... types) {
        final FlatBufferBuilder builder = new FlatBufferBuilder();
        int textOffset = builder.createString(text);
        int typeOffset = SpacyRequest.createTypeVector(builder, toBytes(types));

        SpacyRequest.startSpacyRequest(builder);
        SpacyRequest.addText(builder, textOffset);
        SpacyRequest.addType(builder, typeOffset);

        int request = SpacyRequest.endSpacyRequest(builder);
        builder.finish(request);
        return builder.sizedByteArray();
    }

    public static SpacyResponse toSpacyResponse(byte[] bytes) {
        return SpacyResponse.getRootAsSpacyResponse(ByteBuffer.wrap(bytes));
    }

    private static byte[] toBytes(ESpacyType... types) {
        byte[] bytes = new byte[types.length];
        for (int i = 0; i < types.length; i++) {
            bytes[i] = (byte) types[i].ordinal();
        }
        return bytes;
    }

}