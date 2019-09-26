from dataclasses import dataclass
from typing import List

import flatbuffers

from keios_protocol_gensim.typed_message import TypedMessage
from keios_protocol_gensim.fbs import EmbeddingRequest, GensimFastTextEmbeddingRequest, EmbeddingElement, \
    EmbeddingResponse, GensimFastTextEmbeddingResponse
from keios_protocol_gensim.fbs.GensimMessageType import GensimMessageType


@dataclass
class EmbeddingRequestData:
    text: str


class EmbeddingRequestMapper:
    @staticmethod
    def serialize(data: EmbeddingRequestData, builder: flatbuffers.Builder):
        text = builder.CreateString(data.text)
        EmbeddingRequest.EmbeddingRequestStart(builder)
        EmbeddingRequest.EmbeddingRequestAddText(builder, text)

        return EmbeddingRequest.EmbeddingRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: EmbeddingRequest):
        return EmbeddingRequestData(flatbuffer.Text().decode("utf-8"))


@dataclass
class GensimFastTextEmbeddingRequestData(TypedMessage):
    requests: List[EmbeddingRequestData]

    def type(self):
        return GensimMessageType.GensimFastTextEmbeddingRequest


class GensimFastTextEmbeddingRequestMapper:
    @staticmethod
    def serialize(data: GensimFastTextEmbeddingRequestData, builder: flatbuffers.Builder):
        length = len(data.requests)
        request_offsets = list(map(lambda r: EmbeddingRequestMapper.serialize(r, builder), data.requests))
        GensimFastTextEmbeddingRequest.GensimFastTextEmbeddingRequestStartRequestsVector(builder,
                                                                                         length)
        for offset in reversed(request_offsets):
            builder.PrependUOffsetTRelative(offset)
        request_offsets_vector = builder.EndVector(length)
        GensimFastTextEmbeddingRequest.GensimFastTextEmbeddingRequestStart(builder)
        GensimFastTextEmbeddingRequest.GensimFastTextEmbeddingRequestAddRequests(builder, request_offsets_vector)

        return GensimFastTextEmbeddingRequest.GensimFastTextEmbeddingRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: GensimFastTextEmbeddingRequest):
        requests = list(map(lambda r: EmbeddingRequestMapper.to_dataclass(r),
                            map(lambda i: flatbuffer.Requests(i), range(0, flatbuffer.RequestsLength()))))
        return GensimFastTextEmbeddingRequestData(requests)


@dataclass
class EmbeddingElementData:
    value: float


class EmbeddingElementMapper:
    @staticmethod
    def serialize(data: EmbeddingElementData, builder: flatbuffers.Builder):
        EmbeddingElement.EmbeddingElementStart(builder)
        EmbeddingElement.EmbeddingElementAddValue(builder, data.value)

        return EmbeddingElement.EmbeddingElementEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: EmbeddingElement):
        return EmbeddingElementData(flatbuffer.Value())


@dataclass
class EmbeddingResponseData:
    vector: List[EmbeddingElementData]


class EmbeddingResponseMapper:
    @staticmethod
    def serialize(data: EmbeddingResponseData, builder: flatbuffers.Builder):
        length = len(data.vector)
        vector_offsets = list(map(lambda r: EmbeddingElementMapper.serialize(r, builder), data.vector))
        EmbeddingResponse.EmbeddingResponseStartVectorVector(builder,
                                                             length)
        for offset in reversed(vector_offsets):
            builder.PrependUOffsetTRelative(offset)
        vector_offsets_vector = builder.EndVector(length)
        EmbeddingResponse.EmbeddingResponseStart(builder)
        EmbeddingResponse.EmbeddingResponseAddVector(builder, vector_offsets_vector)

        return EmbeddingResponse.EmbeddingResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: EmbeddingResponse):
        vector = list(map(lambda r: EmbeddingElementMapper.to_dataclass(r),
                          map(lambda i: flatbuffer.Vector(i), range(0, flatbuffer.VectorLength()))))
        return EmbeddingResponseData(vector)


@dataclass
class GensimFastTextEmbeddingResponseData(TypedMessage):
    responses: List[EmbeddingResponseData]

    def type(self):
        return GensimMessageType.GensimFastTextEmbeddingResponse


class GensimFastTextEmbeddingResponseMapper:
    @staticmethod
    def serialize(data: GensimFastTextEmbeddingResponseData, builder: flatbuffers.Builder):
        length = len(data.responses)
        responses_offsets = list(map(lambda r: EmbeddingResponseMapper.serialize(r, builder), data.responses))
        GensimFastTextEmbeddingResponse.GensimFastTextEmbeddingResponseStartResponsesVector(builder,
                                                                                            length)
        for offset in reversed(responses_offsets):
            builder.PrependUOffsetTRelative(offset)
        responses_offsets_vector = builder.EndVector(length)
        GensimFastTextEmbeddingResponse.GensimFastTextEmbeddingResponseStart(builder)
        GensimFastTextEmbeddingResponse.GensimFastTextEmbeddingResponseAddResponses(builder, responses_offsets_vector)

        return GensimFastTextEmbeddingResponse.GensimFastTextEmbeddingResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: GensimFastTextEmbeddingResponse):
        requests = list(map(lambda r: EmbeddingResponseMapper.to_dataclass(r),
                            map(lambda i: flatbuffer.Responses(i), range(0, flatbuffer.ResponsesLength()))))
        return GensimFastTextEmbeddingResponseData(requests)
