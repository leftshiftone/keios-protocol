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

package keios.protocol.classification.class2;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.classification.class2.flatbuffers.ClassificationClass2Request;
import keios.protocol.classification.class2.flatbuffers.ClassificationClass2Response;
import keios.protocol.classification.class2.flatbuffers.Vector;

import java.nio.ByteBuffer;
import java.util.stream.Stream;


/**
 * @author Michael Mair
 */
public class ClassificationClass2Protocol {

    public static byte[] toRequest(String[] vectors) {
        return toRequest(Stream.of(vectors).map(String::getBytes).toArray(byte[][]::new));
    }

    public static byte[] toRequest(byte[][] input) {
        final FlatBufferBuilder builder = new FlatBufferBuilder();

        int[] vecs = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int vector = builder.createByteVector(input[i]);
            Vector.startVector(builder);
            Vector.addBytes(builder, vector);
            vecs[i] = Vector.endVector(builder);
        }
        int vectors = ClassificationClass2Request.createVectorsVector(builder, vecs);

        ClassificationClass2Request.startClassificationClass2Request(builder);
        ClassificationClass2Request.addVectors(builder, vectors);
        int request = ClassificationClass2Request.endClassificationClass2Request(builder);
        builder.finish(request);

        return builder.sizedByteArray();
    }

    public static ClassificationClass2Response fromResponse(byte[] response) {
        return ClassificationClass2Response.getRootAsClassificationClass2Response(ByteBuffer.wrap(response));
    }
}
