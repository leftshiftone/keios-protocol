from dataclasses import dataclass
from typing import List

from keios_protocol_common import FlatbufferObject

from .flatbuffers import GensimFastTextMostSimilarRequest as GensimFastTextMostSimilarRequestClass
from .flatbuffers import GensimFastTextMostSimilarResponse as GensimFastTextMostSimilarResponseClass
from .flatbuffers import MostSimilarRequest as MostSimilarRequestClass
from .flatbuffers import MostSimilarResponse as MostSimilarResponseClass
from .flatbuffers import MostSimilarity as MostSimilarityClass


@dataclass
class MostSimilarRequest:
    text: str


class MostSimilarRequestEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(MostSimilarRequest, MostSimilarRequestClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        text = self.build_string(dataclass.text)
        self.start()
        self['Text'] = text
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return MostSimilarRequest(flatbuffer.Text().decode("utf-8"))


@dataclass
class GensimFastTextMostSimilarRequest:
    requests: List[MostSimilarRequest]


class GensimFastTextMostSimilarRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextMostSimilarRequest, GensimFastTextMostSimilarRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        request_vector = self.build_vector('Requests', dataclass.requests, MostSimilarRequestEntity)
        self.start()
        self['Requests'] = request_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        requests = []
        for i in range(0, flatbuffer.RequestsLength()):
            request = flatbuffer.Requests(i)
            request_entity = MostSimilarRequestEntity(self.builder)
            requests.append(request_entity.flatbuffer_to_dataclass(request))
        return GensimFastTextMostSimilarRequest(requests)


@dataclass
class MostSimilarity:
    text: str
    probability: float


class MostSimilarityEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(MostSimilarity, MostSimilarityClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        text = self.build_string(dataclass.text)
        probability = dataclass.probability
        self.start()
        self['Text'] = text
        self['Probability'] = probability
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return MostSimilarity(flatbuffer.Text().decode("utf-8"), flatbuffer.Probability())


@dataclass
class MostSimilarResponse:
    similarities: List[MostSimilarity]


class MostSimilarResponseEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(MostSimilarResponse, MostSimilarResponseClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        similarities = self.build_vector('Similarities', dataclass.similarities, MostSimilarityEntity)
        self.start()
        self['Similarities'] = similarities
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        similarities = []
        for i in range(0, flatbuffer.SimilaritiesLength()):
            similarity = flatbuffer.Similarities(i)
            similarity_entity = MostSimilarityEntity(self.builder)
            similarities.append(similarity_entity.flatbuffer_to_dataclass(similarity))
        return MostSimilarResponse(similarities)


@dataclass
class GensimFastTextMostSimilarResponse:
    responses: List[MostSimilarResponse]


class GensimFastTextMostSimilarResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextMostSimilarResponse, GensimFastTextMostSimilarResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        responses = self.build_vector('Responses', dataclass.responses, MostSimilarResponseEntity)
        self.start()
        self['Responses'] = responses
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        responses = []
        for i in range(0, flatbuffer.ResponsesLength()):
            response = flatbuffer.Responses(i)
            response_entity = MostSimilarResponseEntity(self.builder)
            responses.append(response_entity.flatbuffer_to_dataclass(response))
        return GensimFastTextMostSimilarResponse(responses)
