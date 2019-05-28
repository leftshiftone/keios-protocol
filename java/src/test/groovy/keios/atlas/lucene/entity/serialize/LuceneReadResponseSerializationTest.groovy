package keios.atlas.lucene.entity.serialize


import keios.atlas.lucene.entity.LuceneReadResponseEntity
import keios.atlas.lucene.entity.SearchResultEntity
import keios.atlas.lucene.entity.deserialize.LuceneReadResponseDeserializer
import spock.lang.Specification
import spock.lang.Subject

class LuceneReadResponseSerializationTest extends Specification {
    
    @Subject
    LuceneReadResponseDeserializer deserializer = new LuceneReadResponseDeserializer()
    
    @Subject
    LuceneReadResponseSerializer serializer = new LuceneReadResponseSerializer()
    
    
    void "serializes / deserializes"() {
        given:
            LuceneReadResponseEntity entity = new LuceneReadResponseEntity(
                    [
//                            new SearchResultEntity(1.0f, ["a": "1", "b": "2"]),
//                            new SearchResultEntity(2.0f, ["c": "3", "d": "4"]),
                            new SearchResultEntity(3.0f, ["field1": "xyz", "field2": "abc"])
                    ])
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            result != null
    }
    
}
