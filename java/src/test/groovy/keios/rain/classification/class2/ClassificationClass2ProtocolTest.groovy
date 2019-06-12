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

package keios.rain.classification.class2

import keios.rain.classification.class2.flatbuffers.ClassificationClass2Request
import spock.lang.Specification

import java.nio.ByteBuffer

/**
 * @author Michael Mair
 */
class ClassificationClass2ProtocolTest extends Specification {

    def "byte request is serialized to flatbuffer bytes"() {
        given:
            byte[][] input = ["foo".bytes, "bar".bytes]
        when:
            byte[] result = ClassificationClass2Protocol.toRequest(input)
        then:
            result == [4, 0, 0, 0, -38, -1, -1, -1, 4, 0, 0, 0, 2, 0, 0, 0, 32, 0, 0, 0, 4, 0, 0, 0, -18, -1, -1, -1, 4, 0, 0, 0, 3, 0, 0, 0, 102, 111, 111, 0, 0, 0, 6, 0, 8, 0, 4, 0, 6, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 102, 111, 111, 0] as byte[]
    }

    def "string request is serialized to flatbuffer bytes"() {
        given:
            String[] input = ["foo", "bar"]
        when:
            byte[] result = ClassificationClass2Protocol.toRequest(input)
        then:
            result == [4, 0, 0, 0, -38, -1, -1, -1, 4, 0, 0, 0, 2, 0, 0, 0, 32, 0, 0, 0, 4, 0, 0, 0, -18, -1, -1, -1, 4, 0, 0, 0, 3, 0, 0, 0, 102, 111, 111, 0, 0, 0, 6, 0, 8, 0, 4, 0, 6, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 102, 111, 111, 0] as byte[]
    }

    def "serializing and deserializing of request returns the same result"() {
        given:
            String[] input = ["foo", "bar"]
        when:
            byte[] serialized = ClassificationClass2Protocol.toRequest(input)
        and:
            ClassificationClass2Request deserialized = ClassificationClass2Request.getRootAsClassificationClass2Request(ByteBuffer.wrap(serialized))
        then:
            deserialized.vectorsLength() == 2
            vectorAsString(deserialized.vectors(0)) == "foo"
            vectorAsString(deserialized.vectors(1)) == "bar"
    }

    private static String vectorAsString(keios.rain.classification.class2.flatbuffers.Vector input) {
        byte[] raw = new byte[input.bytesLength()]
        for (int i = 0; i < input.bytesLength(); i++) {
            raw[i] = input.bytes(i)
        }
        return new String(raw)
    }
}
