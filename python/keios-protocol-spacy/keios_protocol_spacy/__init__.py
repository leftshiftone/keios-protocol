from dataclasses import dataclass
from enum import Enum
from typing import List, Any

from flatbuffers import Builder
from keios_protocol_spacy.flatbuffers import DEPSpacyResponse as DEPSpacyResponseClass
from keios_protocol_spacy.flatbuffers import NERSpacyResponse as NERSpacyResponseClass
from keios_protocol_spacy.flatbuffers import SpacyBatchRequest as SpacyBatchRequestClass
from keios_protocol_spacy.flatbuffers import SpacyBatchResponse as SpacyBatchResponseClass
from keios_protocol_spacy.flatbuffers import SpacyMessage as SpacyMessageClass
from keios_protocol_spacy.flatbuffers import SpacyMessageType
from keios_protocol_spacy.flatbuffers import SpacyRequest as SpacyRequestClass
from keios_protocol_spacy.flatbuffers import SpacyResponse as SpacyResponseClass
from keios_protocol_spacy.flatbuffers.SpacyBatchRequest import SpacyBatchRequest, SpacyBatchRequestStartRequestsVector, \
    SpacyBatchRequestAddRequests, SpacyBatchRequestEnd
from keios_protocol_spacy.flatbuffers.SpacyBatchResponse import SpacyBatchResponse
from keios_protocol_spacy.flatbuffers.SpacyRequest import SpacyRequest, SpacyRequestStartTypeVector, \
    SpacyRequestAddType, SpacyRequestEnd, SpacyRequestStart
from keios_protocol_spacy.flatbuffers.SpacyResponse import SpacyResponse, SpacyResponseStartDepVector, \
    SpacyResponseAddDep, SpacyResponseEnd, SpacyResponseStart, SpacyResponseStartNerVector, SpacyResponseAddNer
from keios_protocol_spacy.flatbuffers.Type import Type


@dataclass
class SpacyMessageData:
    type: SpacyMessageType
    message: object


class SpacyMessageMapper:
    @staticmethod
    def serialize(entity: SpacyMessageData) -> bytearray:
        builder = Builder(128)

        payload_offset = None

        if entity.type == SpacyMessageType.SpacyMessageType().SpacyResponse:
            payload_offset = SpacyResponseMapper.serialize(entity.message, builder)

        if entity.type == SpacyMessageType.SpacyMessageType().SpacyRequest:
            payload_offset = SpacyRequestMapper.serialize(entity.message, builder)

        if entity.type == SpacyMessageType.SpacyMessageType().SpacyBatchRequest:
            payload_offset = SpacyBatchRequestMapper.serialize(entity.message, builder)

        if entity.type == SpacyMessageType.SpacyMessageType().SpacyBatchResponse:
            payload_offset = SpacyBatchResponseMapper.serialize(entity.message, builder)

        SpacyMessageClass.SpacyMessageStart(builder)
        SpacyMessageClass.SpacyMessageAddMessageType(builder, entity.type)
        SpacyMessageClass.SpacyMessageAddMessage(builder, payload_offset)
        message_offset = SpacyMessageClass.SpacyMessageEnd(builder)
        builder.Finish(message_offset)

        return builder.Output()

    @staticmethod
    def deserialize(bb: bytearray) -> SpacyMessageData:
        message = SpacyMessageClass.SpacyMessage.GetRootAsSpacyMessage(bb, 0)
        wrapped = message.Message()
        if message.MessageType() == SpacyMessageType.SpacyMessageType().SpacyResponse:
            fb_obj = SpacyResponse()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return SpacyMessageData(message.MessageType(), SpacyResponseMapper.to_dataclass(fb_obj))

        if message.MessageType() == SpacyMessageType.SpacyMessageType().SpacyRequest:
            fb_obj = SpacyRequest()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return SpacyMessageData(message.MessageType(), SpacyRequestMapper.to_dataclass(fb_obj))

        if message.MessageType() == SpacyMessageType.SpacyMessageType().SpacyBatchRequest:
            fb_obj = SpacyBatchRequest()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return SpacyMessageData(message.MessageType(), SpacyBatchRequestMapper.to_dataclass(fb_obj))

        if message.MessageType() == SpacyMessageType.SpacyMessageType().SpacyBatchResponse:
            fb_obj = SpacyBatchResponse()
            fb_obj.Init(wrapped.Bytes, wrapped.Pos)
            return SpacyMessageData(message.MessageType(), SpacyBatchResponseMapper.to_dataclass(fb_obj))

        raise ValueError("unknown type")


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


class DEPSpacyResponseMapper:
    @staticmethod
    def serialize(entity: DEPSpacyResponseData, builder: Builder) -> Any:
        lang_offset_offset = builder.CreateString(entity.lang)
        relation_offset = builder.CreateString(entity.relation)
        source_offset = builder.CreateString(entity.source)
        source_pos_offset = builder.CreateString(entity.source_pos)
        source_tag_offset = builder.CreateString(entity.source_tag)
        source_base_offset = builder.CreateString(entity.source_base)
        target_offset = builder.CreateString(entity.target)
        target_pos_offset = builder.CreateString(entity.target_pos)
        target_tag_offset = builder.CreateString(entity.target_tag)
        target_base_offset = builder.CreateString(entity.target_base)

        DEPSpacyResponseClass.DEPSpacyResponseStart(builder)
        DEPSpacyResponseClass.DEPSpacyResponseAddLang(builder, lang_offset_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddRelation(builder, relation_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddSource(builder, source_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddSourcePos(builder, source_pos_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddSourceIndex(builder, entity.source_index)
        DEPSpacyResponseClass.DEPSpacyResponseAddSourceTag(builder, source_tag_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddSourceBase(builder, source_base_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddTarget(builder, target_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddTargetPos(builder, target_pos_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddTargetIndex(builder, entity.target_index)
        DEPSpacyResponseClass.DEPSpacyResponseAddTargetTag(builder, target_tag_offset)
        DEPSpacyResponseClass.DEPSpacyResponseAddTargetBase(builder, target_base_offset)

        return DEPSpacyResponseClass.DEPSpacyResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer_object: DEPSpacyResponseClass.DEPSpacyResponse) -> DEPSpacyResponseData:
        return DEPSpacyResponseData(
            flatbuffer_object.Lang().decode("UTF-8"),
            flatbuffer_object.Relation().decode("UTF-8"),
            flatbuffer_object.Source().decode("UTF-8"),
            flatbuffer_object.SourcePos().decode("UTF-8"),
            flatbuffer_object.SourceIndex(),
            flatbuffer_object.SourceTag().decode("UTF-8"),
            flatbuffer_object.SourceBase().decode("UTF-8"),
            flatbuffer_object.Target().decode("UTF-8"),
            flatbuffer_object.TargetPos().decode("UTF-8"),
            flatbuffer_object.TargetIndex(),
            flatbuffer_object.TargetTag().decode("UTF-8"),
            flatbuffer_object.TargetBase().decode("UTF-8")
        )


@dataclass
class NERSpacyResponseData:
    text: str
    start_char: int
    end_char: int
    label: str


class NERSpacyResponseMapper:
    @staticmethod
    def serialize(entity: NERSpacyResponseData, builder: Builder) -> Any:
        text_offset = builder.CreateString(entity.text)
        label_offset = builder.CreateString(entity.label)

        NERSpacyResponseClass.NERSpacyResponseStart(builder)
        NERSpacyResponseClass.NERSpacyResponseAddText(builder, text_offset)
        NERSpacyResponseClass.NERSpacyResponseAddStartChar(builder, entity.start_char)
        NERSpacyResponseClass.NERSpacyResponseAddEndChar(builder, entity.end_char)
        NERSpacyResponseClass.NERSpacyResponseAddLabel(builder, label_offset)

        return NERSpacyResponseClass.NERSpacyResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer_object: NERSpacyResponseClass.NERSpacyResponse) -> NERSpacyResponseData:
        return NERSpacyResponseData(flatbuffer_object.Text().decode("UTF-8"), flatbuffer_object.StartChar(),
                                    flatbuffer_object.EndChar(), flatbuffer_object.Label().decode("UTF-8"))


class TypeData(Enum):
    DEP = 0
    NER = 1

    @staticmethod
    def from_value(value: int):
        for t in TypeData:
            if t.value == value:
                return t
        raise ValueError(f"unknown value {value}")


@dataclass
class SpacyRequestData:
    text: str
    types: List[TypeData]


class SpacyRequestMapper:
    @staticmethod
    def serialize(entity: SpacyRequestData, builder: Builder) -> Any:
        text_offset = builder.CreateString(entity.text)
        SpacyRequestClass.SpacyRequestStartTypeVector(builder, len(entity.types))
        for x in reversed(entity.types):
            builder.PrependByte(x.value)
        types_offset = builder.EndVector(len(entity.types))

        SpacyRequestClass.SpacyRequestStart(builder)
        SpacyRequestClass.SpacyRequestAddText(builder, text_offset)
        SpacyRequestClass.SpacyRequestAddType(builder, types_offset)

        return SpacyRequestClass.SpacyRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer_object: SpacyRequestClass.SpacyRequest) -> SpacyRequestData:
        types = list(map(lambda x: TypeData.from_value(x),
                         map(lambda i: flatbuffer_object.Type(i), range(0, flatbuffer_object.TypeLength()))))
        return SpacyRequestData(flatbuffer_object.Text().decode("UTF-8"), types)


@dataclass
class SpacyBatchRequestData:
    requests: List[SpacyRequestData]


class SpacyBatchRequestMapper:
    @staticmethod
    def serialize(entity: SpacyBatchRequestData, builder: Builder) -> Any:
        requests_offsets = list(map(lambda r: SpacyRequestMapper.serialize(r, builder), entity.requests))
        SpacyBatchRequestClass.SpacyBatchRequestStartRequestsVector(builder, len(entity.requests))
        for offset in reversed(requests_offsets):
            builder.PrependUOffsetTRelative(offset)
        requests_vector_offset = builder.EndVector(len(entity.requests))
        SpacyBatchRequestClass.SpacyBatchRequestStart(builder)
        SpacyBatchRequestClass.SpacyBatchRequestAddRequests(builder, requests_vector_offset)

        return SpacyBatchRequestClass.SpacyBatchRequestEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer_object: SpacyBatchRequestClass.SpacyBatchRequest) -> SpacyBatchRequestData:
        requests = list(map(lambda r: SpacyRequestMapper.to_dataclass(r),
                            map(lambda i: flatbuffer_object.Requests(i), range(0, flatbuffer_object.RequestsLength()))))
        return SpacyBatchRequestData(requests)


@dataclass
class SpacyResponseData:
    ner: List[NERSpacyResponseData]
    dep: List[DEPSpacyResponseData]


class SpacyResponseMapper:
    @staticmethod
    def serialize(entity: SpacyResponseData, builder: Builder) -> Any:
        ner_offsets = list(map(lambda n: NERSpacyResponseMapper.serialize(n, builder), entity.ner))
        dep_offsets = list(map(lambda d: DEPSpacyResponseMapper.serialize(d, builder), entity.dep))
        SpacyResponseClass.SpacyResponseStartNerVector(builder, len(entity.ner))
        for x in reversed(ner_offsets):
            builder.PrependUOffsetTRelative(x)
        ner_vector_offset = builder.EndVector(len(entity.ner))

        SpacyResponseClass.SpacyResponseStartDepVector(builder, len(entity.dep))
        for x in reversed(dep_offsets):
            builder.PrependUOffsetTRelative(x)
        dep_vector_offset = builder.EndVector(len(entity.dep))

        SpacyResponseClass.SpacyResponseStart(builder)
        SpacyResponseClass.SpacyResponseAddNer(builder, ner_vector_offset)
        SpacyResponseClass.SpacyResponseAddDep(builder, dep_vector_offset)
        return SpacyResponseClass.SpacyResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer_object: SpacyResponseClass.SpacyResponse) -> SpacyResponseData:
        ner = list(map(lambda n: NERSpacyResponseMapper.to_dataclass(n),
                       map(lambda i: flatbuffer_object.Ner(i), range(0, flatbuffer_object.NerLength()))))

        dep = list(map(lambda d: DEPSpacyResponseMapper.to_dataclass(d),
                       map(lambda i: flatbuffer_object.Dep(i), range(0, flatbuffer_object.DepLength()))))
        return SpacyResponseData(ner, dep)


@dataclass
class SpacyBatchResponseData:
    responses: List[SpacyResponseData]


class SpacyBatchResponseMapper:
    @staticmethod
    def serialize(entity: SpacyBatchResponseData, builder: Builder) -> Any:
        responses_offsets = list(map(lambda r: SpacyResponseMapper.serialize(r, builder), entity.responses))
        SpacyBatchResponseClass.SpacyBatchResponseStartResponsesVector(builder, len(entity.responses))
        for offset in reversed(responses_offsets):
            builder.PrependUOffsetTRelative(offset)
        responses_vector_offset = builder.EndVector(len(entity.responses))

        SpacyBatchResponseClass.SpacyBatchResponseStart(builder)
        SpacyBatchResponseClass.SpacyBatchResponseAddResponses(builder, responses_vector_offset)
        return SpacyBatchResponseClass.SpacyBatchResponseEnd(builder)

    @staticmethod
    def to_dataclass(flatbuffer_object: SpacyBatchResponseClass.SpacyBatchResponse) -> SpacyBatchResponseData:
        responses = list(map(lambda x: SpacyResponseMapper.to_dataclass(x),
                             map(lambda i: flatbuffer_object.Responses(i),
                                 range(0, flatbuffer_object.ResponsesLength()))))
        return SpacyBatchResponseData(responses)
