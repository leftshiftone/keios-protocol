from dataclasses import dataclass
from typing import List

from keios_protocol_common import FlatbufferObject

from .flatbuffers import GensimFastTextEmbeddingRequest as GensimFastTextEmbeddingRequestClass
from .flatbuffers import GensimFastTextEmbeddingResponse as GensimFastTextEmbeddingResponseClass
from .flatbuffers import GensimFastTextMostSimilarRequest as GensimFastTextMostSimilarRequestClass
from .flatbuffers import GensimFastTextMostSimilarResponse as GensimFastTextMostSimilarResponseClass
from .flatbuffers import GensimFastTextSimilarityRequest as GensimFastTextSimilarityRequestClass
from .flatbuffers import GensimFastTextSimilarityResponse as GensimFastTextSimilarityResponseClass
from .flatbuffers import GensimMessage as GensimMessageClass
from .flatbuffers import GensimMessageType
from .flatbuffers import Similarity as SimilarityClass


@dataclass
class Similarity:
    text: str
    probability: float


class SimilarityEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(Similarity, SimilarityClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        text = self.build_string(dataclass.text)
        probability = dataclass.probability
        self.start()
        self['Text'] = text
        self['Probability'] = probability
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(text=self['Text'], probability=self['Probability'])


@dataclass
class GensimFastTextEmbeddingRequest:
    text: str


class GensimFastTextEmbeddingRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextEmbeddingRequest, GensimFastTextEmbeddingRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        text = self.build_string(dataclass.text)
        self.start()
        self['Text'] = text
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(text=self['Text'])


@dataclass
class GensimFastTextEmbeddingResponse:
    vector: List[float]


class GensimFastTextEmbeddingResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextEmbeddingResponse, GensimFastTextEmbeddingResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        vector_vector = self.build_vector('Vector', dataclass.vector)
        self.start()
        self['Vector'] = vector_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(vector=[v for v in self['Vector']])


@dataclass
class GensimFastTextMostSimilarRequest:
    text: str


class GensimFastTextMostSimilarRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextMostSimilarRequest, GensimFastTextMostSimilarRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        text = self.build_string(dataclass.text)
        self.start()
        self['Text'] = text
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(text=self['Text'])


@dataclass
class GensimFastTextMostSimilarResponse:
    similarities: List[Similarity]


class GensimFastTextMostSimilarResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextMostSimilarResponse, GensimFastTextMostSimilarResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        similarities = self.build_vector('Similarities', dataclass.similarities, SimilarityEntity)
        self.start()
        self['Similarities'] = similarities
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        similarity = SimilarityEntity(self.builder)
        similarities = [similarity.build(s) for s in self['Similarities']]
        return self.dataclass(similarities=similarities)


@dataclass
class GensimFastTextSimilarityRequest:
    text1: str
    text2: str


class GensimFastTextSimilarityRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextSimilarityRequest, GensimFastTextSimilarityRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        text1 = self.build_string(dataclass.text1)
        text2 = self.build_string(dataclass.text2)
        self.start()
        self['Text1'] = text1
        self['Text2'] = text2
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(text1=self['Text1'], text2=self['Text2'])


@dataclass
class GensimFastTextSimilarityResponse:
    probability: float


class GensimFastTextSimilarityResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimFastTextSimilarityResponse, GensimFastTextSimilarityResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        self.start()
        self['Probability'] = dataclass.probability
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(probability=self['Probability'])


@dataclass
class GensimMessage:
    message: object


class GensimMessageEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(GensimMessage, GensimMessageClass)

    def dataclass_to_flatbuffer(self, dataclass):
        message_type = GensimMessageType.GensimMessageType().NONE
        message_entity = None
        if isinstance(dataclass.message, GensimFastTextEmbeddingRequest):
            message_type = GensimMessageType.GensimMessageType().GensimFastTextEmbeddingRequest
            entity_class_name = globals()[f"{GensimFastTextEmbeddingRequest.__name__}Entity"]
            message_entity = entity_class_name()
        elif isinstance(dataclass.message, GensimFastTextEmbeddingResponse):
            message_type = GensimMessageType.GensimMessageType().GensimFastTextEmbeddingResponse
            entity_class_name = globals()[f"{GensimFastTextEmbeddingResponse.__name__}Entity"]
            message_entity = entity_class_name()
        elif isinstance(dataclass.message, GensimFastTextMostSimilarRequest):
            message_type = GensimMessageType.GensimMessageType().GensimFastTextMostSimilarRequest
            entity_class_name = globals()[f"{GensimFastTextMostSimilarRequest.__name__}Entity"]
            message_entity = entity_class_name()
        elif isinstance(dataclass.message, GensimFastTextMostSimilarResponse):
            message_type = GensimMessageType.GensimMessageType().GensimFastTextMostSimilarResponse
            entity_class_name = globals()[f"{GensimFastTextMostSimilarResponse.__name__}Entity"]
            message_entity = entity_class_name()
        elif isinstance(dataclass.message, GensimFastTextSimilarityRequest):
            message_type = GensimMessageType.GensimMessageType().GensimFastTextSimilarityRequest
            entity_class_name = globals()[f"{GensimFastTextSimilarityRequest.__name__}Entity"]
            message_entity = entity_class_name()
        elif isinstance(dataclass.message, GensimFastTextSimilarityResponse):
            message_type = GensimMessageType.GensimMessageType().GensimFastTextSimilarityResponse
            entity_class_name = globals()[f"{GensimFastTextSimilarityResponse.__name__}Entity"]
            message_entity = entity_class_name()
        else:
            raise ValueError(f"Message type {type(dataclass.message)} is unknown")

        self.start()
        self['MessageType'] = message_type
        self['Message'] = message_entity.dataclass_to_flatbuffer(dataclass.message)
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(messageType=self['MessageType'], message=self['Message'])
