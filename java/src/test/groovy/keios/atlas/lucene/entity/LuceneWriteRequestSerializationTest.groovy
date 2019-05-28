package keios.atlas.lucene.entity

import keios.atlas.lucene.entity.LuceneWriteRequestEntity
import keios.atlas.lucene.entity.LuceneWriteRequestSerializer
import spock.lang.Specification
import spock.lang.Subject

class LuceneWriteRequestSerializationTest extends Specification {

    @Subject
    LuceneWriteRequestDeserializer deserializer = new LuceneWriteRequestDeserializer()

    @Subject
    LuceneWriteRequestSerializer serializer = new LuceneWriteRequestSerializer()

    void "serializes / deserializes"() {
        given:
            LuceneWriteRequestEntity entity = new LuceneWriteRequestEntity(["a": "1", "b": "2"])
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            entity == result

    }

}
