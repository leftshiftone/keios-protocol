package keios.atlas.lucene.entity

import keios.atlas.lucene.entity.LuceneReadResponseEntity
import keios.atlas.lucene.entity.SearchResultEntity
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
                            new SearchResultEntity(1.0f, ["a": "1", "b": "2"]),
                            new SearchResultEntity(2.0f, ["c": "3", "d": "4"]),
                    ])
        when:
            def result = deserializer.deserialize(serializer.serialize(entity))
        then:
            result == entity
    }

}
