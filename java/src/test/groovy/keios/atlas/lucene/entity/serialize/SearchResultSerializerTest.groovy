package keios.atlas.lucene.entity.serialize

import keios.atlas.lucene.entity.SearchResultEntity
import keios.atlas.lucene.entity.deserialize.SearchResultDeserializer
import spock.lang.Specification
import spock.lang.Subject

class SearchResultSerializerTest extends Specification {
    
    @Subject
    SearchResultSerializer serializer = new SearchResultSerializer()
    
    @Subject
    SearchResultDeserializer deserializer = new SearchResultDeserializer()
    
    
    void "serializes / deserializes"() {
        given:
            SearchResultEntity entity = new SearchResultEntity(3.0f, ["field1": "xyz", "field2": "abc"])
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            result == entity
    }
    
}
