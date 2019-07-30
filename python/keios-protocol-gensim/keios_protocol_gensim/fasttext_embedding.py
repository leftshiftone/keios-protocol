from dataclasses import dataclass
from typing import List

from keios_protocol_common import FlatbufferObject

from .flatbuffers import EmbeddingElement as EmbeddingElementClass
from .flatbuffers import EmbeddingRequest as EmbeddingRequestClass
from .flatbuffers import EmbeddingResponse as EmbeddingResponseClass
from .flatbuffers import GensimFastTextEmbeddingRequest as GensimFastTextEmbeddingRequestClass
from .flatbuffers import GensimFastTextEmbeddingResponse as GensimFastTextEmbeddingResponseClass


@dataclass
class EmbeddingRequest:
    text: str


class EmbeddingRequestEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(EmbeddingRequest, EmbeddingRequestClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        text = self.build_string(dataclass.text)
        self.start()
        self['Text'] = text
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return EmbeddingRequest(flatbuffer.Text().decode("utf-8"))


@dataclass
class GensimFastTextEmbeddingRequest:
    requests: List[EmbeddingRequest]


class GensimFastTextEmbeddingRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextEmbeddingRequest, GensimFastTextEmbeddingRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        requests = self.build_vector("Requests", dataclass.requests, EmbeddingRequestEntity)
        self.start()
        self['Requests'] = requests
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        requests = []
        for i in range(0, flatbuffer.RequestsLength()):
            request = flatbuffer.Requests(i)
            request_entity = EmbeddingRequestEntity(self.builder)
            requests.append(request_entity.flatbuffer_to_dataclass(request))
        return GensimFastTextEmbeddingRequest(requests)


@dataclass
class EmbeddingElement:
    value: float


class EmbeddingElementEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(EmbeddingElement, EmbeddingElementClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        value = dataclass.value
        self.start()
        self['Value'] = value
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return EmbeddingElement(flatbuffer.Value())


@dataclass
class EmbeddingResponse:
    vector: List[EmbeddingElement]


class EmbeddingResponseEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(EmbeddingResponse, EmbeddingResponseClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        vector_vector = self.build_vector('Vector', dataclass.vector, EmbeddingElementEntity)
        self.start()
        self['Vector'] = vector_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        elements = []
        for i in range(0, flatbuffer.VectorLength()):
            element = flatbuffer.Vector(i)
            element_entity = EmbeddingElementEntity(self.builder)
            elements.append(element_entity.flatbuffer_to_dataclass(element))
        return EmbeddingResponse(elements)


@dataclass
class GensimFastTextEmbeddingResponse:
    responses: List[EmbeddingResponse]


class GensimFastTextEmbeddingResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextEmbeddingResponse, GensimFastTextEmbeddingResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        response_vector = self.build_vector('Responses', dataclass.responses, EmbeddingResponseEntity)
        self.start()
        self['Responses'] = response_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        responses = []
        for i in range(0, flatbuffer.ResponsesLength()):
            response = flatbuffer.Responses(i)
            response_entity = EmbeddingResponseEntity(self.builder)
            responses.append(response_entity.flatbuffer_to_dataclass(response))
        return GensimFastTextEmbeddingResponse(responses)
