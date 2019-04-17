from enum import Enum
from typing import List

import flatbuffers

from keios.atlas.spacy.DEPSpacyResponse import DEPSpacyResponseStart, DEPSpacyResponseAddLang, \
    DEPSpacyResponseAddRelation, DEPSpacyResponseAddSource, DEPSpacyResponseAddSourcePos, \
    DEPSpacyResponseAddSourceIndex, DEPSpacyResponseAddSourceTag, DEPSpacyResponseAddSourceBase, \
    DEPSpacyResponseAddTarget, DEPSpacyResponseAddTargetPos, DEPSpacyResponseAddTargetIndex, \
    DEPSpacyResponseAddTargetTag, DEPSpacyResponseAddTargetBase, DEPSpacyResponseEnd
from keios.atlas.spacy.NERSpacyResponse import NERSpacyResponseStart, NERSpacyResponseAddText, \
    NERSpacyResponseAddStartChar, NERSpacyResponseAddEndChar, NERSpacyResponseAddLabel, NERSpacyResponseEnd
from keios.atlas.spacy.SpacyRequest import SpacyRequest
from keios.atlas.spacy.SpacyResponse import SpacyResponseStartDepVector, SpacyResponseAddDep, \
    SpacyResponseStartNerVector, SpacyResponseAddNer, SpacyResponseEnd, SpacyResponse


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
            self._relation = relation,
            self._source = source,
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
            return self._relation[0]

        @property
        def source(self) -> str:
            return self._source[0]

        @property
        def source_pos(self):
            return self._source_pos

        @property
        def source_index(self):
            return self._source_index

        @property
        def source_tag(self):
            return self._source_tag

        @property
        def source_base(self):
            return self._source_base

        @property
        def target(self):
            return self._target

        @property
        def target_pos(self):
            return self._target_pos

        @property
        def target_index(self):
            return self._target_index

        @property
        def target_tag(self):
            return self._target_tag

        @property
        def target_base(self):
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

    class SpacyRequestEntity:
        class Type(Enum):
            DEP = 0,
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

        def __str__(self) -> str:
            return "SpacyRequestEntity(text={0}, type={1})".format(self.text, self.type)

    @staticmethod
    def to_spacy_response(response_entity: SpacyResponseEntity) -> bytearray:
        builder = flatbuffers.Builder(1024)
        serialized_dep = list(map(lambda dep: dep.serialize(builder), response_entity.dep))
        serialized_ner = list(map(lambda n: n.serialize(builder), response_entity.ner))
        SpacyResponseStartDepVector(builder, len(response_entity.dep))
        for sp in serialized_dep:
            builder.PrependUOffsetTRelative(sp)
        dep_vector = builder.EndVector(len(response_entity.dep))

        SpacyResponseStartNerVector(builder, len(response_entity.ner))
        for sn in serialized_ner:
            builder.PrependUOffsetTRelative(sn)
        ner_vector = builder.EndVector(len(response_entity.ner))

        DEPSpacyResponseStart(builder)
        SpacyResponseAddDep(builder, dep_vector)
        SpacyResponseAddNer(builder, ner_vector)
        response = SpacyResponseEnd(builder)
        builder.Finish(response)

        return builder.Output()

    @staticmethod
    def to_spacy_request(bb: bytearray):
        flat = SpacyRequest.GetRootAsSpacyRequest(bb, 0)
        types = []
        for n in range(0, flat.TypeLength()):
            types.append(SpacyProtocol.SpacyRequestEntity.Type(flat.Type(n)))
        return SpacyProtocol.SpacyRequestEntity(flat.Text().decode("UTF-8"), types)
