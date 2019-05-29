package keios.atlas.lucene.entity;

import com.google.flatbuffers.FlatBufferBuilder;
import keios.atlas.lucene.flatbuffers.Tuple;
import keios.common.ChildSerializer;

import java.util.Map;

/**
 * @author benjamin.krenn@leftshift.one - 5/28/19.
 * @since 0.3.0
 */
class DocumentSerializer implements ChildSerializer<Map<String,String>> {
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
