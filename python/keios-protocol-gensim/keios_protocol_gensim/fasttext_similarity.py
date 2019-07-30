from dataclasses import dataclass
from typing import List

from keios_protocol_common import FlatbufferObject

from .flatbuffers import GensimFastTextSimilarityRequest as GensimFastTextSimilarityRequestClass
from .flatbuffers import GensimFastTextSimilarityResponse as GensimFastTextSimilarityResponseClass
from .flatbuffers import SimilarityRequest as SimilarityRequestClass
from .flatbuffers import SimilarityResponse as SimilarityResponseClass


@dataclass
class SimilarityRequest:
    text1: str
    text2: str


class SimilarityRequestEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(SimilarityRequest, SimilarityRequestClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        text1 = self.build_string(dataclass.text1)
        text2 = self.build_string(dataclass.text2)
        self.start()
        self['Text1'] = text1
        self['Text2'] = text2
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return SimilarityRequest(flatbuffer.Text1().decode("utf-8"), flatbuffer.Text2().decode("utf-8"))


@dataclass
class GensimFastTextSimilarityRequest:
    requests: List[SimilarityRequest]


class GensimFastTextSimilarityRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextSimilarityRequest, GensimFastTextSimilarityRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        request_vector = self.build_vector('Requests', dataclass.requests, SimilarityRequestEntity)
        self.start()
        self['Requests'] = request_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        requests = []
        for i in range(0, flatbuffer.RequestsLength()):
            request = flatbuffer.Requests(i)
            request_entity = SimilarityRequestEntity(self.builder)
            requests.append(request_entity.flatbuffer_to_dataclass(request))
        return GensimFastTextSimilarityRequest(requests)


@dataclass
class SimilarityResponse:
    value: float


class SimilarityResponseEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(SimilarityResponse, SimilarityResponseClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        self.start()
        self['Value'] = dataclass.value
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return SimilarityResponse(flatbuffer.Value())


@dataclass
class GensimFastTextSimilarityResponse:
    responses: List[SimilarityResponse]


class GensimFastTextSimilarityResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextSimilarityResponse, GensimFastTextSimilarityResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        response_vector = self.build_vector('Responses', dataclass.responses, SimilarityResponseEntity)
        self.start()
        self['Responses'] = response_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        responses = []
        for i in range(0, flatbuffer.ResponsesLength()):
            response = flatbuffer.Responses(i)
            response_entity = SimilarityResponseEntity(self.builder)
            responses.append(response_entity.flatbuffer_to_dataclass(response))
        return GensimFastTextSimilarityResponse(responses)
