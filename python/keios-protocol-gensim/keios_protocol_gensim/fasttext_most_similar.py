from dataclasses import dataclass
from typing import List

import flatbuffers

from keios_protocol_gensim.typed_message import TypedMessage
from keios_protocol_gensim.fbs import MostSimilarRequest, MostSimilarity, MostSimilarResponse, \
    GensimFastTextMostSimilarRequest, GensimFastTextMostSimilarResponse
from keios_protocol_gensim.fbs.GensimMessageType import GensimMessageType


@dataclass
class MostSimilarRequestData:
    text: str


class MostSimilarRequestMapper:

    @staticmethod
    def serialize(data: MostSimilarRequestData, builder: flatbuffers.Builder):
        text = builder.CreateString(data.text)
        MostSimilarRequest.MostSimilarRequestStart(builder)
        MostSimilarRequest.MostSimilarRequestAddText(builder, text)

        return MostSimilarRequest.MostSimilarRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: MostSimilarRequest):
        return MostSimilarRequestData(flatbuffer.Text().decode("utf-8"))


@dataclass
class GensimFastTextMostSimilarRequestData(TypedMessage):
    requests: List[MostSimilarRequestData]

    def type(self):
        return GensimMessageType.GensimFastTextMostSimilarRequest


class GensimFastTextMostSimilarRequestMapper:

    @staticmethod
    def serialize(data: GensimFastTextMostSimilarRequestData, builder: flatbuffers.Builder):
        length = len(data.requests)
        request_offsets = list(map(lambda r: MostSimilarRequestMapper.serialize(r, builder), data.requests))
        GensimFastTextMostSimilarRequest.GensimFastTextMostSimilarRequestStartRequestsVector(builder,
                                                                                             length)
        for offset in reversed(request_offsets):
            builder.PrependUOffsetTRelative(offset)
        requests_offsets_vector = builder.EndVector(length)
        GensimFastTextMostSimilarRequest.GensimFastTextMostSimilarRequestStart(builder)
        GensimFastTextMostSimilarRequest.GensimFastTextMostSimilarRequestAddRequests(builder, requests_offsets_vector)

        return GensimFastTextMostSimilarRequest.GensimFastTextMostSimilarRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: GensimFastTextMostSimilarRequest):
        requests = list(map(lambda r: MostSimilarRequestMapper.to_dataclass(r),
                            map(lambda i: flatbuffer.Requests(i), range(0, flatbuffer.RequestsLength()))))
        return GensimFastTextMostSimilarRequestData(requests)


@dataclass
class MostSimilarityData:
    text: str
    probability: float


class MostSimilarityMapper:
    @staticmethod
    def serialize(data: MostSimilarityData, builder: flatbuffers.Builder):
        text = builder.CreateString(data.text)
        MostSimilarity.MostSimilarityStart(builder)
        MostSimilarity.MostSimilarityAddText(builder, text)
        MostSimilarity.MostSimilarityAddProbability(builder, data.probability)

        return MostSimilarity.MostSimilarityEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: MostSimilarity):
        return MostSimilarityData(flatbuffer.Text().decode("utf-8"), flatbuffer.Probability())


@dataclass
class MostSimilarResponseData:
    similarities: List[MostSimilarityData]


class MostSimilarResponseMapper:
    @staticmethod
    def serialize(data: MostSimilarResponseData, builder: flatbuffers.Builder):
        length = len(data.similarities)
        similarities_offsets = list(map(lambda r: MostSimilarityMapper.serialize(r, builder), data.similarities))
        MostSimilarResponse.MostSimilarResponseStartSimilaritiesVector(builder,
                                                                       length)
        for offset in reversed(similarities_offsets):
            builder.PrependUOffsetTRelative(offset)
        similarities_offsets_vector = builder.EndVector(length)
        MostSimilarResponse.MostSimilarResponseStart(builder)
        MostSimilarResponse.MostSimilarResponseAddSimilarities(builder,
                                                               similarities_offsets_vector)

        return MostSimilarResponse.MostSimilarResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: MostSimilarResponse):
        similarities = list(map(lambda r: MostSimilarityMapper.to_dataclass(r),
                                map(lambda i: flatbuffer.Similarities(i), range(0, flatbuffer.SimilaritiesLength()))))
        return MostSimilarResponseData(similarities)


@dataclass
class GensimFastTextMostSimilarResponseData(TypedMessage):
    responses: List[MostSimilarResponseData]

    def type(self):
        return GensimMessageType.GensimFastTextMostSimilarResponse


class GensimFastTextMostSimilarResponseMapper:
    @staticmethod
    def serialize(data: GensimFastTextMostSimilarResponseData, builder: flatbuffers.Builder):
        length = len(data.responses)
        responses_offsets = list(map(lambda r: MostSimilarResponseMapper.serialize(r, builder), data.responses))
        GensimFastTextMostSimilarResponse.GensimFastTextMostSimilarResponseStartResponsesVector(builder,
                                                                                                length)
        for offset in reversed(responses_offsets):
            builder.PrependUOffsetTRelative(offset)
        responses_offsets_vector = builder.EndVector(length)
        GensimFastTextMostSimilarResponse.GensimFastTextMostSimilarResponseStart(builder)
        GensimFastTextMostSimilarResponse.GensimFastTextMostSimilarResponseAddResponses(builder,
                                                                                        responses_offsets_vector)
        return GensimFastTextMostSimilarResponse.GensimFastTextMostSimilarResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer: GensimFastTextMostSimilarResponse):
        requests = list(map(lambda r: MostSimilarResponseMapper.to_dataclass(r),
                            map(lambda i: flatbuffer.Responses(i), range(0, flatbuffer.ResponsesLength()))))
        return GensimFastTextMostSimilarResponseData(requests)
