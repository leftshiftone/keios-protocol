from dataclasses import dataclass
from typing import List

import flatbuffers

from keios_protocol_gensim.fbs import SimilarityRequest, GensimFastTextSimilarityRequest, SimilarityResponse, \
    GensimFastTextSimilarityResponse, GensimMessageType
from keios_protocol_gensim.fbs.SimilarityRequest import SimilarityRequestStart, SimilarityRequestAddText1, \
    SimilarityRequestAddText2, SimilarityRequestEnd
from keios_protocol_gensim.typed_message import TypedMessage


@dataclass
class SimilarityRequestData:
    text1: str
    text2: str


class SimilarityRequestMapper:
    @staticmethod
    def serialize(data: SimilarityRequestData, builder: flatbuffers.Builder):
        text1 = builder.CreateString(data.text1)
        text2 = builder.CreateString(data.text2)

        SimilarityRequestStart(builder)
        SimilarityRequestAddText1(builder, text1)
        SimilarityRequestAddText2(builder, text2)

        return SimilarityRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: SimilarityRequest):
        return SimilarityRequestData(flatbuffer.Text1().decode("utf-8"), flatbuffer.Text2().decode("utf-8"))


@dataclass
class GensimFastTextSimilarityRequestData(TypedMessage):
    requests: List[SimilarityRequestData]

    def type(self):
        return GensimMessageType.GensimMessageType.GensimFastTextSimilarityRequest


class GensimFastTextSimilarityRequestMapper:

    @staticmethod
    def serialize(data: GensimFastTextSimilarityRequestData, builder: flatbuffers.Builder):
        length = len(data.requests)
        request_offsets = list(map(lambda r: SimilarityRequestMapper.serialize(r, builder), data.requests))
        GensimFastTextSimilarityRequest.GensimFastTextSimilarityRequestStartRequestsVector(builder,
                                                                                           length)
        for offset in reversed(request_offsets):
            builder.PrependUOffsetTRelative(offset)
        request_offsets_vector = builder.EndVector(length)
        GensimFastTextSimilarityRequest.GensimFastTextSimilarityRequestStart(builder)
        GensimFastTextSimilarityRequest.GensimFastTextSimilarityRequestAddRequests(builder, request_offsets_vector)

        return GensimFastTextSimilarityRequest.GensimFastTextSimilarityRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: GensimFastTextSimilarityRequest):
        requests = list(map(lambda r: SimilarityRequestMapper.to_dataclass(r),
                            map(lambda i: flatbuffer.Requests(i), range(0, flatbuffer.RequestsLength()))))
        return GensimFastTextSimilarityRequestData(requests)


@dataclass
class SimilarityResponseData:
    value: float


class SimilarityResponseMapper:
    @staticmethod
    def serialize(data: SimilarityResponseData, builder: flatbuffers.Builder):
        SimilarityResponse.SimilarityResponseStart(builder)
        SimilarityResponse.SimilarityResponseAddValue(builder, data.value)

        return SimilarityResponse.SimilarityResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: SimilarityResponse):
        return SimilarityResponseData(flatbuffer.Value())


@dataclass
class GensimFastTextSimilarityResponseData(TypedMessage):
    responses: List[SimilarityResponseData]

    def type(self):
        return GensimMessageType.GensimMessageType.GensimFastTextSimilarityResponse


class GensimFastTextSimilarityResponseMapper:

    @staticmethod
    def serialize(data: GensimFastTextSimilarityResponseData, builder: flatbuffers.Builder) -> bytearray:
        length = len(data.responses)
        responses_offsets = list(map(lambda r: SimilarityResponseMapper.serialize(r, builder), data.responses))
        GensimFastTextSimilarityResponse.GensimFastTextSimilarityResponseStartResponsesVector(builder,
                                                                                              length)
        for offset in reversed(responses_offsets):
            builder.PrependUOffsetTRelative(offset)
        responses_offsets_vector = builder.EndVector(length)
        GensimFastTextSimilarityResponse.GensimFastTextSimilarityResponseStart(builder)
        GensimFastTextSimilarityResponse.GensimFastTextSimilarityResponseAddResponses(builder, responses_offsets_vector)

        return GensimFastTextSimilarityResponse.GensimFastTextSimilarityResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: GensimFastTextSimilarityResponse) -> GensimFastTextSimilarityResponseData:
        requests = list(map(lambda r: SimilarityResponseMapper.to_dataclass(r),
                            map(lambda i: flatbuffer.Responses(i), range(0, flatbuffer.ResponsesLength()))))
        return GensimFastTextSimilarityResponseData(requests)
