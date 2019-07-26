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

package keios.protocol.gensim

import keios.protocol.gensim.entity.GensimMessageEntity
import keios.protocol.gensim.entity.fasttext.*
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Michael Mair
 */
class GensimProtocolTest extends Specification {

    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            GensimMessageEntity message = new GensimMessageEntity<>(entity)
        when:
            def result = GensimProtocol.toMessage(GensimProtocol.toWireMessage(message))
        then:
            result.message == entity
        where:
            entity << [
                    new GensimFastTextEmbeddingRequestEntity([new EmbeddingRequestEntity("foo"), new EmbeddingRequestEntity("bar")]),
                    new GensimFastTextEmbeddingResponseEntity([new EmbeddingResponseEntity([new EmbeddingElementEntity(0.1234), new EmbeddingElementEntity(42)])]),
                    new GensimFastTextMostSimilarRequestEntity([new MostSimilarRequestEntity("foo")]),
                    new GensimFastTextMostSimilarResponseEntity([new MostSimilarResponseEntity([new MostSimilarityEntity("foo", 0.1234)])]),
                    new GensimFastTextSimilarityRequestEntity([new SimilarityRequestEntity("foo", "bar"), new SimilarityRequestEntity("foo", "bar")]),
                    new GensimFastTextSimilarityResponseEntity([new SimilarityResponseEntity(0.1234), new SimilarityResponseEntity(42)])
            ]
    }
}
