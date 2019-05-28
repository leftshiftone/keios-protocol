package keios.atlas.lucene.entity.serialize

import keios.atlas.lucene.entity.LuceneWriteResponseEntity
import keios.atlas.lucene.entity.WriteResultEntity
import keios.atlas.lucene.entity.deserialize.LuceneWriteResponseDeserializer
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class LuceneWriteResponseSerializationTest extends Specification {
    
    @Subject
    LuceneWriteResponseSerializer serializer = new LuceneWriteResponseSerializer()
    
    @Subject
    LuceneWriteResponseDeserializer deserializer = new LuceneWriteResponseDeserializer()
    
    @Unroll
    void "serializes / deserializes"() {
        given:
            LuceneWriteResponseEntity entity = new LuceneWriteResponseEntity(writeResult)
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            result == entity
        where:
            writeResult               || _
            WriteResultEntity.SUCCESS || _
            WriteResultEntity.FAILURE || _
    }
}