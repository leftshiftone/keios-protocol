import abc
import flatbuffers
from typing import TypeVar, Type

T = TypeVar('T')

class FlatbufferObject():
    __metaclass__  = abc.ABCMeta
    
    def __init__(self, dataclass, flatbuffer, builder=flatbuffers.Builder(0)):
        self.dataclass = dataclass
        self.fbs = flatbuffer
        self.builder = builder

    @abc.abstractmethod
    def dataclass_to_flatbuffer(self, dataclass):
        """ convert given dataclass to the right fbs object """

    @abc.abstractmethod
    def flatbuffer_to_dataclass(self, flatbuffer):
        """ convert given fbs object to the right dataclass """

    def serialize(self, item: Type[T]) -> bytearray:
        self.builder.Finish(self.dataclass_to_flatbuffer(item))
        return self.builder.Output()

    def deserialize(self, item: bytearray) -> T:
        classname = self.fbs.__name__.split('.')[-1]
        flatbuffer = getattr(getattr(self.fbs, classname), f'GetRootAs{classname}')(item, 0)
        return self.flatbuffer_to_dataclass(flatbuffer)
