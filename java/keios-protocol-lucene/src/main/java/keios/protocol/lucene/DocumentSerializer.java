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

package keios.protocol.lucene;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.protocol.common.ChildSerializer;
import keios.protocol.lucene.flatbuffers.Tuple;

import java.util.Map;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
class DocumentSerializer implements ChildSerializer<Map<String, String>> {
    @Override
    public int serialize(Map<String, String> obj, FlatBufferBuilder builder) {
        return builder.createVectorOfTables(
                obj.entrySet()
                        .stream()
                        .map(entry -> {
                            int keyOffset = builder.createString(entry.getKey());
                            int valueOffset = builder.createString(entry.getValue());

                            return Tuple.createTuple(builder, keyOffset, valueOffset);
                        })
                        .mapToInt(i -> i).toArray()
        );
    }
}
