from enum import Enum
from typing import List, NamedTuple
from dataclasses import dataclass
import numpy as np
import flatbuffers
from ..FlatbufferObject import FlatbufferObject
from .fbs import DEPSpacyResponse as DEPSpacyResponseClass
from .fbs import NERSpacyResponse as NERSpacyResponseClass
from .fbs import SpacyRequest as SpacyRequestClass
from .fbs import SpacyResponse as SpacyResponseClass
from .fbs import Type as TypeClass

@dataclass
class DEPSpacyResponseData:
    lang: str
    relation: str
    source: str
    source_pos: str
    source_index: int
    source_tag: str
    source_base: str
    target: str
    target_pos: str
    target_index: int
    target_tag: str
    target_base: str

class DEPSpacyResponseEntity(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(DEPSpacyResponseData, DEPSpacyResponseClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        attr_dict = {}
        for str_attr in [
            'lang',
            'relation',
            'source',
            'source_pos',
            'source_tag',
            'source_base',
            'target',
            'target_pos',
            'target_tag',
            'target_base'
        ]:
            attr_dict[str_attr] = self.builder.CreateString(getattr(dataclass, str_attr))
        self.fbs.DEPSpacyResponseStart(self.builder)
        self.fbs.DEPSpacyResponseAddLang(self.builder, attr_dict.lang)
        self.fbs.DEPSpacyResponseAddRelation(self.builder, attr_dict.relation)
        self.fbs.DEPSpacyResponseAddSource(self.builder, attr_dict.source)
        self.fbs.DEPSpacyResponseAddSourcePos(self.builder, attr_dict.source_pos)
        self.fbs.DEPSpacyResponseAddSourceIndex(self.builder, self.source_index)
        self.fbs.DEPSpacyResponseAddSourceTag(self.builder, attr_dict.source_tag)
        self.fbs.DEPSpacyResponseAddSourceBase(self.builder, attr_dict.source_base)
        self.fbs.DEPSpacyResponseAddTarget(self.builder, attr_dict.target)
        self.fbs.DEPSpacyResponseAddTargetPos(self.builder, attr_dict.target_pos)
        self.fbs.DEPSpacyResponseAddTargetIndex(self.builder, self.target_index)
        self.fbs.DEPSpacyResponseAddTargetTag(self.builder, attr_dict.target_tag)
        self.fbs.DEPSpacyResponseAddTargetBase(self.builder, attr_dict.target_base)
        return self.fbs.DEPSpacyResponseEnd(self.builder)
        
    def flatbuffer_to_dataclass(self, flatbuffer):
        pass

class SpacyProtocol:
    class NERSpacyResponseEntity:
        def __init__(self, text: str, start_char: int, end_char: int, label: str):
            self._text = text
            self._start_char = start_char
            self._end_char = end_char
            self._label = label

        @property
        def text(self):
            return self._text

        @property
        def start_char(self):
            return self._start_char

        @property
        def end_char(self):
            return self._end_char

        @property
        def label(self):
            return self._label

        def serialize(self, builder):
            text = builder.CreateString(self.text)
            label = builder.CreateString(self.label)
            NERSpacyResponseStart(builder)
            NERSpacyResponseAddText(builder, text)
            NERSpacyResponseAddStartChar(builder, self.start_char)
            NERSpacyResponseAddEndChar(builder, self.end_char)
            NERSpacyResponseAddLabel(builder, label)
            return NERSpacyResponseEnd(builder)

    class DEPSpacyResponseEntity:
        def __init__(self,
                     lang: str,
                     relation: str,
                     source: str,
                     source_pos: str,
                     source_index: int,
                     source_tag: str,
                     source_base: str,
                     target: str,
                     target_pos: str,
                     target_index: int,
                     target_tag: str,
                     target_base: str):
            self._lang = lang
            self._relation = relation
            self._source = source
            self._source_pos = source_pos
            self._source_index = source_index
            self._source_tag = source_tag
            self._source_base = source_base
            self._target: str = target
            self._target_pos = target_pos
            self._target_index = target_index
            self._target_tag = target_tag
            self._target_base = target_base

        @property
        def lang(self) -> str:
            return self._lang

        @property
        def relation(self) -> str:
            return self._relation

        @property
        def source(self) -> str:
            return self._source

        @property
        def source_pos(self) -> str:
            return self._source_pos

        @property
        def source_index(self) -> int:
            return self._source_index

        @property
        def source_tag(self) -> str:
            return self._source_tag

        @property
        def source_base(self) -> str:
            return self._source_base

        @property
        def target(self) -> str:
            return self._target

        @property
        def target_pos(self) -> str:
            return self._target_pos

        @property
        def target_index(self) -> int:
            return self._target_index

        @property
        def target_tag(self) -> str:
            return self._target_tag

        @property
        def target_base(self) -> str:
            return self._target_base

        def serialize(self, builder):
            lang = builder.CreateString(self.lang)
            relation = builder.CreateString(self.relation)
            source = builder.CreateString(self.source)
            source_pos = builder.CreateString(self.source_pos)
            source_tag = builder.CreateString(self.source_tag)
            source_base = builder.CreateString(self.source_base)
            target = builder.CreateString(self.target)
            target_pos = builder.CreateString(self.target_pos)
            target_tag = builder.CreateString(self.target_tag)
            target_base = builder.CreateString(self.target_base)

            DEPSpacyResponseStart(builder)
            DEPSpacyResponseAddLang(builder, lang)
            DEPSpacyResponseAddRelation(builder, relation)
            DEPSpacyResponseAddSource(builder, source)
            DEPSpacyResponseAddSourcePos(builder, source_pos)
            DEPSpacyResponseAddSourceIndex(builder, self.source_index)
            DEPSpacyResponseAddSourceTag(builder, source_tag)
            DEPSpacyResponseAddSourceBase(builder, source_base)
            DEPSpacyResponseAddTarget(builder, target)
            DEPSpacyResponseAddTargetPos(builder, target_pos)
            DEPSpacyResponseAddTargetIndex(builder, self.target_index)
            DEPSpacyResponseAddTargetTag(builder, target_tag)
            DEPSpacyResponseAddTargetBase(builder, target_base)
            return DEPSpacyResponseEnd(builder)

    class SpacyResponseEntity:
        def __init__(self, dep: list,
                     ner: list):
            self._dep = dep
            self._ner = ner

        @property
        def ner(self) -> List:
            return self._ner

        @property
        def dep(self) -> List:
            return self._dep

        def serialize(self) -> bytearray:
            return SpacyProtocol.serialize(self)

    class SpacyRequestEntity:
        class Type(Enum):
            DEP = 0
            NER = 1

        def __init__(self, text: str, type: List[Type]):
            self._text = text
            self._type = type

        @property
        def text(self) -> str:
            return self._text

        @property
        def type(self) -> List[Type]:
            return self._type

        @classmethod
        def deserialize(cls, bb: bytearray) -> 'SpacyRequestEntity':
            return SpacyProtocol.deserialize(bb)


        def __str__(self) -> str:
            return "SpacyRequestEntity(text={0}, type={1})".format(self.text, self.type)

    @staticmethod
    def serialize(response_entity: SpacyResponseEntity) -> bytearray:
        builder = flatbuffers.Builder(1024)
        serialized_dep = list(map(lambda dep: dep.serialize(builder), response_entity.dep))
        serialized_ner = list(map(lambda n: n.serialize(builder), response_entity.ner))
        SpacyResponseStartDepVector(builder, len(response_entity.dep))
        for sp in reversed(serialized_dep):
            builder.PrependUOffsetTRelative(sp)
        dep_vector = builder.EndVector(len(response_entity.dep))

        SpacyResponseStartNerVector(builder, len(response_entity.ner))
        for sn in reversed(serialized_ner):
            builder.PrependUOffsetTRelative(sn)
        ner_vector = builder.EndVector(len(response_entity.ner))

        DEPSpacyResponseStart(builder)
        SpacyResponseAddDep(builder, dep_vector)
        SpacyResponseAddNer(builder, ner_vector)
        response = SpacyResponseEnd(builder)
        builder.Finish(response)

        return builder.Output()

    @staticmethod
    def deserialize(bb: bytearray) -> SpacyRequestEntity:
        flat = SpacyRequest.GetRootAsSpacyRequest(bb, 0)
        types = []
        for n in range(0, flat.TypeLength()):
            types.append(SpacyProtocol.SpacyRequestEntity.Type(flat.Type(n)))
        return SpacyProtocol.SpacyRequestEntity(flat.Text().decode("UTF-8"), types)
