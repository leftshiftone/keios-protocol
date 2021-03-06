package keios.protocol.tesseract


import spock.lang.Specification
import spock.lang.Unroll

class TesseractProtocolTest extends Specification {
    
    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            TesseractMessageEntity messageEntity = new TesseractMessageEntity(entity)
        when:
            def result = TesseractProtocol.instance().toMessage(TesseractProtocol.instance().toWireMessage(messageEntity))
        then:
            result.message == entity
        where:
            entity << [
                    new TesseractOcrRequestEntity(TesseractProtocolTest.getResourceAsStream("/hello_world.jpg").bytes),
                    new TesseractOcrResponseEntity("some text", 99L)
            ]
    }
}
