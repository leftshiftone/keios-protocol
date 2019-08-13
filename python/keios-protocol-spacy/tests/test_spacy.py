from keios_protocol_spacy import DEPSpacyResponseData, SpacyRequestData, TypeData, SpacyBatchRequestData, \
    SpacyBatchResponseData
from keios_protocol_spacy import NERSpacyResponseData
from keios_protocol_spacy import SpacyMessageData, SpacyMessageMapper
from keios_protocol_spacy import SpacyResponseData
from keios_protocol_spacy.fbs.SpacyMessageType import SpacyMessageType


def response_fixture(s: str):
    ner1 = NERSpacyResponseData(text=f"{s}1", start_char=1, end_char=2, label="x")
    ner2 = NERSpacyResponseData(text=f"{s}2", start_char=1, end_char=2, label="y")
    dep1 = DEPSpacyResponseData(
        f"de-{s}-1",
        "relation",
        "source",
        "source_pos",
        0,
        "source_tag",
        "source_base",
        "target",
        "target_pos",
        10,
        "target_tag",
        "target_base"
    )
    dep2 = DEPSpacyResponseData(
        f"de-{s}-2",
        "relation2",
        "abc",
        "abc",
        0,
        "abc",
        "abc",
        "abc",
        "abc",
        10,
        "abc",
        "abc"
    )
    return SpacyResponseData(dep=[dep1, dep2], ner=[ner1, ner2])


def test_serializes_deserializes_wrapped_spacy_response():
    message_data = SpacyMessageData(SpacyMessageType().SpacyResponse, response_fixture("t"))
    serialized_message = SpacyMessageMapper().serialize(message_data)

    message_mapper = SpacyMessageMapper()
    result: SpacyMessageData = message_mapper.deserialize(serialized_message)
    payload: SpacyResponseData = result.message
    assert result.type is SpacyMessageType().SpacyResponse
    assert payload is not None
    assert len(payload.dep) is 2
    assert len(payload.ner) is 2
    assert payload == response_fixture("t")

def test_serializes_deserializes_wrapped_spacy_request():
    request = SpacyRequestData("hello world", [TypeData.NER, TypeData.DEP])
    message = SpacyMessageData(SpacyMessageType().SpacyRequest, request)
    mapper = SpacyMessageMapper()
    serialized = mapper.serialize(message)
    deserialized = mapper.deserialize(serialized)

    payload: SpacyRequestData = deserialized.message

    assert deserialized.type == SpacyMessageType().SpacyRequest
    assert payload.text == "hello world"
    assert payload.types == [TypeData.NER, TypeData.DEP]


def test_serializes_deserializes_wrapped_spacybatchrequest():
    requests = [SpacyRequestData("first", [TypeData.DEP, TypeData.NER]),
                SpacyRequestData("second", [TypeData.NER]),
                SpacyRequestData("third", [TypeData.DEP])]
    batch_request = SpacyBatchRequestData(requests)
    message = SpacyMessageData(SpacyMessageType().SpacyBatchRequest, batch_request)
    mapper = SpacyMessageMapper()
    serialized = mapper.serialize(message)
    deserialized = mapper.deserialize(serialized)

    payload: SpacyBatchRequestData = deserialized.message

    assert deserialized.type == SpacyMessageType.SpacyBatchRequest
    assert len(payload.requests) == 3
    assert payload.requests == requests


def test_serializes_deserializes_wrapped_spacybatchresponse():
    responses = [response_fixture("1"), response_fixture("2"), response_fixture("3")]
    batch_response = SpacyBatchResponseData(responses)
    mapper = SpacyMessageMapper()
    serialized = mapper.serialize(SpacyMessageData(SpacyMessageType().SpacyBatchResponse, batch_response))
    deserialized = mapper.deserialize(serialized)

    payload: SpacyBatchResponseData = deserialized.message
    print("responses", payload.responses)
    assert payload.responses == responses