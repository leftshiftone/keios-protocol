from dataclasses import dataclass
from typing import List

from keios_protocol_common import FlatbufferObject

from .flatbuffers import ClassificationClass2Request as ClassificationClass2RequestClass
from .flatbuffers import Vector as VectorClass


@dataclass
class Vector:
    bytes: bytearray


class VectorEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(Vector, VectorClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        bytes_vector = self.build_vector('Bytes', dataclass.speech)
        self.start()
        self['Bytes'] = bytes_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(bytes=self['Bytes'].tobytes())


@dataclass
class ClassificationClass2Request:
    vectors: List[Vector]


class ClassificationClass2RequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(ClassificationClass2Request, ClassificationClass2RequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        vector_vector = self.build_vector('Vectors', dataclass.vectors, VectorEntity)
        self.start()
        self['Vectors'] = vector_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        vector = VectorEntity(self.builder)
        vectors = [vector.build(v) for v in self['Vectors']]
        return self.dataclass(vectors=vectors)
