from dataclasses import dataclass

from keios_protocol_common import FlatbufferObject
from keios_protocol_gensim.fasttext_embedding import GensimFastTextEmbeddingRequest, GensimFastTextEmbeddingResponse, \
    EmbeddingElement, GensimFastTextEmbeddingRequestEntity, GensimFastTextEmbeddingResponseEntity, \
    EmbeddingRequestEntity
from keios_protocol_gensim.fasttext_most_similar import GensimFastTextMostSimilarRequest, \
    GensimFastTextMostSimilarResponse, GensimFastTextMostSimilarRequestEntity, GensimFastTextMostSimilarResponseEntity, \
    MostSimilarity
from keios_protocol_gensim.fasttext_similarity import GensimFastTextSimilarityRequest, GensimFastTextSimilarityResponse, \
    GensimFastTextSimilarityRequestEntity, GensimFastTextSimilarityResponseEntity
from keios_protocol_gensim.flatbuffers.GensimMessageType import GensimMessageType

from .flatbuffers import EmbeddingElement as EmbeddingElementClass
from .flatbuffers import EmbeddingRequest as EmbeddingRequestClass
from .flatbuffers import EmbeddingResponse as EmbeddingResponseClass
from .flatbuffers import GensimFastTextEmbeddingRequest as GensimFastTextEmbeddingRequestClass
from .flatbuffers import GensimFastTextEmbeddingResponse as GensimFastTextEmbeddingResponseClass
from .flatbuffers import GensimFastTextMostSimilarRequest as GensimFastTextMostSimilarRequestClass
from .flatbuffers import GensimFastTextMostSimilarResponse as GensimFastTextMostSimilarResponseClass
from .flatbuffers import GensimFastTextSimilarityRequest as GensimFastTextSimilarityRequestClass
from .flatbuffers import GensimFastTextSimilarityResponse as GensimFastTextSimilarityResponseClass
from .flatbuffers import GensimMessage as GensimMessageClass
from .flatbuffers import MostSimilarRequest as MostSimilarRequestClass
from .flatbuffers import MostSimilarResponse as MostSimilarResponseClass
from .flatbuffers import MostSimilarity as MostSimilarityClass
from .flatbuffers import SimilarityRequest as SimilarityRequestClass
from .flatbuffers import SimilarityResponse as SimilarityResponseClass


@dataclass
class GensimMessage:
    message_type: GensimMessageType
    message: object  # is a dataclass


class GensimMessageEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimMessage, GensimMessageClass)

    def dataclass_to_flatbuffer(self, dataclass):
        if isinstance(dataclass.message, GensimFastTextEmbeddingRequest):
            type_ = GensimMessageType().GensimFastTextEmbeddingRequest
            entity = GensimFastTextEmbeddingRequestEntity()
        elif isinstance(dataclass.message, GensimFastTextEmbeddingResponse):
            type_ = GensimMessageType().GensimFastTextEmbeddingResponse
            entity = GensimFastTextEmbeddingResponseEntity()
        elif isinstance(dataclass.message, GensimFastTextMostSimilarRequest):
            type_ = GensimMessageType().GensimFastTextMostSimilarRequest
            entity = GensimFastTextMostSimilarRequestEntity()
        elif isinstance(dataclass.message, GensimFastTextMostSimilarResponse):
            type_ = GensimMessageType().GensimFastTextMostSimilarResponse
            entity = GensimFastTextMostSimilarResponseEntity()
        elif isinstance(dataclass.message, GensimFastTextSimilarityRequest):
            type_ = GensimMessageType().GensimFastTextSimilarityRequest
            entity = GensimFastTextSimilarityRequestEntity()
        elif isinstance(dataclass.message, GensimFastTextSimilarityResponse):
            type_ = GensimMessageType().GensimFastTextSimilarityResponse
            entity = GensimFastTextSimilarityResponseEntity()
        else:
            raise ValueError(f"Message type {type(dataclass.message)} is unknown")

        message = entity.dataclass_to_flatbuffer(dataclass.message)

        self.start()
        self['MessageType'] = type_
        self['Message'] = message
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        type_ = flatbuffer.MessageType()
        wrapped = flatbuffer.Message()
        if type_ == GensimMessageType().GensimFastTextEmbeddingRequest:
            wrapped_flatbuffer = GensimFastTextEmbeddingRequestClass.GensimFastTextEmbeddingRequest()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = GensimFastTextEmbeddingRequestEntity().flatbuffer_to_dataclass(wrapped_flatbuffer)
        elif type_ == GensimMessageType().GensimFastTextEmbeddingResponse:
            wrapped_flatbuffer = GensimFastTextEmbeddingResponseClass.GensimFastTextEmbeddingResponse()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = GensimFastTextEmbeddingResponseEntity().flatbuffer_to_dataclass(wrapped_flatbuffer)
        elif type_ == GensimMessageType().GensimFastTextMostSimilarRequest:
            wrapped_flatbuffer = GensimFastTextMostSimilarRequestClass.GensimFastTextMostSimilarRequest()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = GensimFastTextMostSimilarRequestEntity().flatbuffer_to_dataclass(wrapped_flatbuffer)
        elif type_ == GensimMessageType().GensimFastTextMostSimilarResponse:
            wrapped_flatbuffer = GensimFastTextMostSimilarResponseClass.GensimFastTextMostSimilarResponse()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = GensimFastTextMostSimilarResponseEntity().flatbuffer_to_dataclass(wrapped_flatbuffer)
        elif type_ == GensimMessageType().GensimFastTextSimilarityRequest:
            wrapped_flatbuffer = GensimFastTextSimilarityRequestClass.GensimFastTextSimilarityRequest()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = GensimFastTextSimilarityRequestEntity().flatbuffer_to_dataclass(wrapped_flatbuffer)
        elif type_ == GensimMessageType().GensimFastTextSimilarityResponse:
            wrapped_flatbuffer = GensimFastTextSimilarityResponseClass.GensimFastTextSimilarityResponse()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = GensimFastTextSimilarityResponseEntity().flatbuffer_to_dataclass(wrapped_flatbuffer)
        else:
            raise ValueError(f"Message type {type_} is unknown")

        return self.dataclass(message_type=type_, message=message)
