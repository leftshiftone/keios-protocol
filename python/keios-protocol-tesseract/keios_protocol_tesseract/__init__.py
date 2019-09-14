from abc import ABC
from dataclasses import dataclass, field

from flatbuffers import Builder
from keios_protocol_tesseract.flatbuffers.TesseractMessage import TesseractMessage, TesseractMessageStart, TesseractMessageEnd, \
    TesseractMessageAddMessage, TesseractMessageAddMessageType
from keios_protocol_tesseract.flatbuffers.TesseractMessageType import TesseractMessageType
from keios_protocol_tesseract.flatbuffers.TesseractOcrRequest import TesseractOcrRequest, TesseractOcrRequestStart, \
    TesseractOcrRequestAddImage, TesseractOcrRequestEnd
from keios_protocol_tesseract.flatbuffers.TesseractOcrResponse import TesseractOcrResponse, TesseractOcrResponseStart, \
    TesseractOcrResponseAddText, TesseractOcrResponseAddConfidence, TesseractOcrResponseEnd


class SerializableMessageEntity(ABC):
    def type(self) -> TesseractMessageType:
        pass

    def serialize(self, builder: Builder) -> bytearray:
        pass


@dataclass()
class TesseractMessageEntity:
    type: TesseractMessageType = field(init=False)
    message: SerializableMessageEntity

    def __post_init__(self):
        self.type = self.message.type()


@dataclass(frozen=True)
class TesseractOcrRequestEntity(SerializableMessageEntity):
    image: bytearray

    @staticmethod
    def new(fb_obj: TesseractOcrRequest):
        image = list(map(lambda i: fb_obj.Image(i), range(0, fb_obj.ImageLength())))
        return TesseractOcrRequestEntity(bytearray(image))

    def serialize(self, builder: Builder):
        image_offset = builder.CreateByteVector(self.image)

        TesseractOcrRequestStart(builder)
        TesseractOcrRequestAddImage(builder, image_offset)

        return TesseractOcrRequestEnd(builder)

    def type(self) -> TesseractMessageType:
        return TesseractMessageType.TesseractOcrRequest


@dataclass(frozen=True)
class TesseractOcrResponseEntity(SerializableMessageEntity):
    text: str
    confidence: int

    @staticmethod
    def new(fb_obj: TesseractOcrResponse):
        return TesseractOcrResponseEntity(fb_obj.Text().decode("utf-8"), fb_obj.Confidence())

    def serialize(self, builder: Builder):
        text_offset = builder.CreateString(self.text)
        TesseractOcrResponseStart(builder)
        TesseractOcrResponseAddText(builder, text_offset)
        TesseractOcrResponseAddConfidence(builder, self.confidence)

        return TesseractOcrResponseEnd(builder)

    def type(self) -> TesseractMessageType:
        return TesseractMessageType.TesseractOcrResponse


class TesseractProtocol:
    @staticmethod
    def serialize(entity: TesseractMessageEntity) -> bytearray:
        builder = Builder(1024)
        payload_offset = entity.message.serialize(builder)
        TesseractMessageStart(builder)
        TesseractMessageAddMessageType(builder, entity.type)
        TesseractMessageAddMessage(builder, payload_offset)

        message_offset = TesseractMessageEnd(builder)
        builder.Finish(message_offset)
        return builder.Output()

    @staticmethod
    def deserialize(bb: bytearray) -> TesseractMessageEntity:
        message = TesseractMessage.GetRootAsTesseractMessage(bb, 0)

        if message.MessageType() == TesseractMessageType.TesseractOcrRequest:
            fb_obj = TesseractOcrRequest()
            fb_obj.Init(message.Message().Bytes, message.Message().Pos)

            return TesseractMessageEntity(TesseractOcrRequestEntity.new(fb_obj))
        if message.MessageType() == TesseractMessageType.TesseractOcrResponse:
            fb_obj = TesseractOcrResponse()
            fb_obj.Init(message.Message().Bytes, message.Message().Pos)

            return TesseractMessageEntity(TesseractOcrResponseEntity.new(fb_obj))
