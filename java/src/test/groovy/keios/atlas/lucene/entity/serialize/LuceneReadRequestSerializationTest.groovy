package keios.atlas.lucene.entity.serialize

import keios.atlas.lucene.entity.LuceneReadRequestEntity
import keios.atlas.lucene.entity.LuceneWriteResponseEntity
import keios.atlas.lucene.entity.deserialize.LuceneReadRequestDeserializer
import spock.lang.Specification
import spock.lang.Subject

class LuceneReadRequestSerializationTest extends Specification {
    
    @Subject
    LuceneReadRequestSerializer serializer = new LuceneReadRequestSerializer()
    
    @Subject
    LuceneReadRequestDeserializer deserializer = new LuceneReadRequestDeserializer()
    
    void "serializes / deserializes"() {
        given:
            LuceneReadRequestEntity entity = new LuceneReadRequestEntity(
                    "test",
                    "test:xyz~",
                    3.0f,
                    100
                    
    
            )
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            result == entity
    }
    
    void "respects defaults"() {
        given:
            LuceneReadRequestEntity entity = new LuceneReadRequestEntity("someField", "abc~ AND xyz~")
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            result.minimumScore == 0.0
            result.limit == 10
    }
    
}
