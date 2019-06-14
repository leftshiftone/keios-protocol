import pytest

from keios_protocol_spacy import SpacyRequestEntity
from keios_protocol_spacy import SpacyResponseEntity
from keios_protocol_spacy import NERSpacyResponseEntity
from keios_protocol_spacy import DEPSpacyResponseEntity
from keios_protocol_spacy import SpacyResponseData
from keios_protocol_spacy import NERSpacyResponseData
from keios_protocol_spacy import DEPSpacyResponseData
from keios_protocol_spacy import TypeData

def test_serializes_spacy_response():
    ner1 = NERSpacyResponseData(text="test1", start_char=1, end_char=2, label="x")
    ner2 = NERSpacyResponseData(text="test2", start_char=1, end_char=2, label="y")
    dep1 = DEPSpacyResponseData(
        "de",
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
        "de",
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
    response_data = SpacyResponseData(dep=[dep1, dep2], ner=[ner1, ner2])
    response = SpacyResponseEntity()
    result = response.serialize(response_data)
    deserialized = response.fbs.SpacyResponse.GetRootAsSpacyResponse(result, 0)
    # ner
    assert deserialized.Ner(0).Text().decode("UTF-8") == 'test1'
    assert deserialized.Ner(0).StartChar() == 1
    assert deserialized.Ner(0).EndChar() == 2
    assert deserialized.Ner(0).Label().decode("UTF-8") == "x"
    assert deserialized.Ner(1).Text().decode("UTF-8") == "test2"
    # dep
    assert deserialized.Dep(0).Lang().decode("UTF-8") == "de"
    assert deserialized.Dep(0).Relation().decode("UTF-8") == "relation"
    assert deserialized.Dep(1).Relation().decode("UTF-8") == "relation2"
    assert deserialized.Dep(1).Lang().decode("UTF-8") == "de"

def test_deserializes_spacy_request():
    hex = "0C00000008000C000800040008000000080000000C00000001000000010000001800000068616C6C6F206963682062696E732064657220706574657200000000"
    bb = bytearray.fromhex(hex)
    request = SpacyRequestEntity()
    request_obj = request.deserialize(bb)
    assert request_obj.text == "hallo ich bins der peter"
    assert request_obj.type == [TypeData.NER]