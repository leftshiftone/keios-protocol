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

package keios.protocol.lucene.entity

import keios.protocol.lucene.LuceneProtocol
import spock.lang.Specification
import spock.lang.Unroll

class LuceneProtocolTest extends Specification {

    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            LuceneMessageEntity message = new LuceneMessageEntity<>(entity)
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
