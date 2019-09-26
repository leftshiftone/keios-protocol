import flatbuffers

from keios_protocol_gensim.fasttext_embedding import GensimFastTextEmbeddingRequestData, \
    GensimFastTextEmbeddingResponseData, \
    EmbeddingElementData, GensimFastTextEmbeddingRequestMapper, GensimFastTextEmbeddingResponseMapper, \
    EmbeddingRequestMapper
from keios_protocol_gensim.fasttext_most_similar import GensimFastTextMostSimilarRequestData, \
    GensimFastTextMostSimilarResponseData, GensimFastTextMostSimilarRequestMapper, \
    GensimFastTextMostSimilarResponseMapper, \
    MostSimilarityData
from keios_protocol_gensim.fasttext_similarity import GensimFastTextSimilarityRequestData, \
    GensimFastTextSimilarityResponseData, \
    GensimFastTextSimilarityRequestMapper, GensimFastTextSimilarityResponseMapper
from keios_protocol_gensim.fbs.GensimFastTextEmbeddingRequest import GensimFastTextEmbeddingRequest
from keios_protocol_gensim.fbs.GensimFastTextEmbeddingResponse import GensimFastTextEmbeddingResponse
from keios_protocol_gensim.fbs.GensimFastTextMostSimilarRequest import GensimFastTextMostSimilarRequest
from keios_protocol_gensim.fbs.GensimFastTextMostSimilarResponse import GensimFastTextMostSimilarResponse
from keios_protocol_gensim.fbs.GensimFastTextSimilarityRequest import GensimFastTextSimilarityRequest
from keios_protocol_gensim.fbs.GensimFastTextSimilarityResponse import GensimFastTextSimilarityResponse
from keios_protocol_gensim.fbs.GensimMessageType import GensimMessageType
from keios_protocol_gensim.typed_message import TypedMessage
from .fbs import EmbeddingElement as EmbeddingElementClass
from .fbs import EmbeddingRequest as EmbeddingRequestClass
from .fbs import EmbeddingResponse as EmbeddingResponseClass
from .fbs import GensimFastTextEmbeddingRequest as GensimFastTextEmbeddingRequestClass
from .fbs import GensimFastTextEmbeddingResponse as GensimFastTextEmbeddingResponseClass
from .fbs import GensimFastTextMostSimilarRequest as GensimFastTextMostSimilarRequestClass
from .fbs import GensimFastTextMostSimilarResponse as GensimFastTextMostSimilarResponseClass
from .fbs import GensimFastTextSimilarityRequest as GensimFastTextSimilarityRequestClass
from .fbs import GensimMessage as GensimMessageClass
from .fbs import MostSimilarRequest as MostSimilarRequestClass
from .fbs import MostSimilarResponse as MostSimilarResponseClass
from .fbs import MostSimilarity as MostSimilarityClass
from .fbs import SimilarityRequest as SimilarityRequestClass
from .fbs import SimilarityResponse as SimilarityResponseClass


class GensimMessage:
    def __init__(self, message: TypedMessage):
        self._message: TypedMessage = message
        self._message_type = message.type()

    @property
    def message(self):
        return self._message

    @property
    def message_type(self):
        return self._message_type


class GensimMessageMapper:
    @staticmethod
    def serialize(entity: GensimMessage) -> bytearray:

        builder = flatbuffers.Builder(128)
        payload_offset = None
        if entity.message_type == GensimMessageType.GensimFastTextSimilarityRequest:
            payload_offset = GensimFastTextSimilarityRequestMapper.serialize(entity.message, builder)
        elif entity.message_type == GensimMessageType.GensimFastTextSimilarityResponse:
            payload_offset = GensimFastTextSimilarityResponseMapper.serialize(entity.message, builder)
        elif entity.message_type == GensimMessageType.GensimFastTextEmbeddingRequest:
            payload_offset = GensimFastTextEmbeddingRequestMapper.serialize(entity.message, builder)
        elif entity.message_type == GensimMessageType.GensimFastTextEmbeddingResponse:
            payload_offset = GensimFastTextEmbeddingResponseMapper.serialize(entity.message, builder)
        elif entity.message_type == GensimMessageType.GensimFastTextMostSimilarRequest:
            payload_offset = GensimFastTextMostSimilarRequestMapper.serialize(entity.message, builder)
        elif entity.message_type == GensimMessageType.GensimFastTextMostSimilarResponse:
            payload_offset = GensimFastTextMostSimilarResponseMapper.serialize(entity.message, builder)
        else:
            raise ValueError(f"Message type {type(entity)} is unknown")

        GensimMessageClass.GensimMessageStart(builder)
        GensimMessageClass.GensimMessageAddMessageType(builder, entity.message_type)
        GensimMessageClass.GensimMessageAddMessage(builder, payload_offset)

        message_offset = GensimMessageClass.GensimMessageEnd(builder)
        builder.Finish(message_offset)

        return builder.Output()

    @staticmethod
    def deserialize(bb: bytearray):
        message = GensimMessageClass.GensimMessage.GetRootAsGensimMessage(bb, 0)
        wrapped = message.Message()
        if message.MessageType() == GensimMessageType().GensimFastTextSimilarityRequest:
            fb_obj = GensimFastTextSimilarityRequest()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return GensimMessage(GensimFastTextSimilarityRequestMapper.to_dataclass(fb_obj))
        elif message.MessageType() == GensimMessageType().GensimFastTextSimilarityResponse:
            fb_obj = GensimFastTextSimilarityResponse()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return GensimMessage(GensimFastTextSimilarityResponseMapper.to_dataclass(fb_obj))
        elif message.MessageType() == GensimMessageType().GensimFastTextEmbeddingRequest:
            fb_obj = GensimFastTextEmbeddingRequest()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return GensimMessage(GensimFastTextEmbeddingRequestMapper.to_dataclass(fb_obj))
        elif message.MessageType() == GensimMessageType().GensimFastTextEmbeddingResponse:
            fb_obj = GensimFastTextEmbeddingResponse()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return GensimMessage(GensimFastTextEmbeddingResponseMapper.to_dataclass(fb_obj))
        elif message.MessageType() == GensimMessageType().GensimFastTextMostSimilarRequest:
            fb_obj = GensimFastTextMostSimilarRequest()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return GensimMessage(GensimFastTextMostSimilarRequestMapper.to_dataclass(fb_obj))
        elif message.MessageType() == GensimMessageType().GensimFastTextMostSimilarResponse:
            fb_obj = GensimFastTextMostSimilarResponse()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return GensimMessage(GensimFastTextMostSimilarResponseMapper.to_dataclass(fb_obj))
        else:
            raise ValueError(f"Message type {message.MessageType()} is unknown")
