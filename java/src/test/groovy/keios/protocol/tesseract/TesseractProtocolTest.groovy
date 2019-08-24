package keios.protocol.tesseract

import keios.common.Message
import spock.lang.Specification
import spock.lang.Unroll

class TesseractProtocolTest extends Specification {

    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            TesseractMessageEntity<Message> messageEntity = new TesseractMessageEntity<>(entity)
        when:
            def result = TesseractProtocol.instance().toMessage(TesseractProtocol.instance().toWireMessage(messageEntity))
        then:
            result.message == entity
        where:
            entity << [
                    new TesseractOcrRequestEntity("not an image".bytes),
                    new TesseractOcrResponseEntity("some text", 99L)
            ]
    }
}
