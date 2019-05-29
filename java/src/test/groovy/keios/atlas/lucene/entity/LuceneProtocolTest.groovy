package keios.atlas.lucene.entity

import keios.atlas.lucene.LuceneProtocol
import spock.lang.Specification
import spock.lang.Unroll

class LuceneProtocolTest extends Specification {

    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            LuceneMessageEntity<LuceneReadRequestEntity> message = new LuceneMessageEntity<>(entity)
        when:
            def result = LuceneProtocol.toMessage(LuceneProtocol.toWireMessage(message))
        then:
            result.message == entity
        where:
            entity << [
                    new LuceneReadRequestEntity("test", "hello~", 1.0, 5),
                    new LuceneReadResponseEntity([new SearchResultEntity(1.0, ["x": "y"]), new SearchResultEntity(2.0, ["a": "b", "c": "d"])]),
                    new LuceneWriteRequestEntity(["a": "b", "x": "y"]),
                    new LuceneWriteResponseEntity(WriteResultEntity.FAILURE),
                    new LuceneWriteResponseEntity(WriteResultEntity.SUCCESS),
            ]
    }

}
