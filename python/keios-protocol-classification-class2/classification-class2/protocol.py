from dataclasses import dataclass
from typing import List

from .flatbuffers import ClassificationClass2Request as ClassificationClass2RequestClass
from .flatbuffers import Vector as VectorClass
from ...FlatbufferObject import FlatbufferObject


@dataclass
class Vector:
    bytes: bytearray


class VectorEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(Vector, VectorClass)

    # serialize
    def dataclass_to_flatbuffer(self, dataclass):
        # todo: implement
        speech_vector = self.build_vector('Speech', dataclass.speech)
        self.start()
        self['Speech'] = speech_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        # todo: implement
        return self.dataclass(speech=self['Speech'].tobytes())


@dataclass
class ClassificationClass2Request:
    vectors: List[Vector]


class ClassificationClass2RequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(ClassificationClass2Request, ClassificationClass2RequestClass)

    # serialize
    def dataclass_to_flatbuffer(self, dataclass):
        # todo: implement
        speech_vector = self.build_vector('Speech', dataclass.speech)
        self.start()
        self['Speech'] = speech_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        # todo: implement
        return self.dataclass(speech=self['Speech'].tobytes())


# todo: implement response