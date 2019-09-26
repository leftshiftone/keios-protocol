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

package keios.protocol.pocketsphinx

import keios.protocol.pocketsphinx.entity.GuessEntity
import keios.protocol.pocketsphinx.entity.PocketSphinxMessageEntity
import keios.protocol.pocketsphinx.entity.PocketSphinxRequestEntity
import keios.protocol.pocketsphinx.entity.PocketSphinxResponseEntity
import spock.lang.Specification
import spock.lang.Unroll

/**
 * @author Michael Mair
 */
class PocketSphinxProtocolTest extends Specification {

    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            PocketSphinxMessageEntity message = new PocketSphinxMessageEntity(entity)
        when:
            def result = PocketSphinxProtocol.instance().toMessage(PocketSphinxProtocol.instance().toWireMessage(message))
        then:
            result.message == entity
        where:
            entity << [
                    new PocketSphinxRequestEntity(PocketSphinxProtocolTest.getResourceAsStream("/goforward.raw").bytes),
                    new PocketSphinxResponseEntity([new GuessEntity("ich will ein bier haben", 1.0), new GuessEntity("ich will ein Gösser haben", 0.9)])
            ]
    }
}
