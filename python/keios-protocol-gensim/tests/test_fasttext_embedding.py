import math
import unittest

from keios_protocol_gensim import GensimFastTextEmbeddingRequestData, EmbeddingElementData, GensimMessageMapper, \
    GensimMessage
from keios_protocol_gensim.fasttext_embedding import EmbeddingRequestData, EmbeddingResponseData, \
    GensimFastTextEmbeddingResponseData


class TestFastTextEmbedding(unittest.TestCase):
    def test_request_serialization_and_deserialization_returns_same_result(self):
        data1 = EmbeddingRequestData("foo")
        data2 = EmbeddingRequestData("bar")
        data = GensimFastTextEmbeddingRequestData([data1, data2])

        result = GensimMessageMapper.serialize(GensimMessage(data))

        deserialized: GensimFastTextEmbeddingRequestData = GensimMessageMapper.deserialize(result).message

        assert len(deserialized.requests) == 2
        assert deserialized.requests[0].text == "foo"
        assert deserialized.requests[1].text == "bar"

    def test_response_serialization_and_deserialization_returns_same_result(self):
        data1_element1 = EmbeddingElementData(1.0)
        data1_element2 = EmbeddingElementData(0.1234)
        data1_element3 = EmbeddingElementData(0.42)
        data1 = EmbeddingResponseData([data1_element1, data1_element2, data1_element3])

        data2_element1 = EmbeddingElementData(42.0)
        data2_element2 = EmbeddingElementData(0.314)
        data2 = EmbeddingResponseData([data2_element1, data2_element2])

        data = GensimFastTextEmbeddingResponseData([data1, data2])
        result = GensimMessageMapper.serialize(GensimMessage(data))

        deserialized: GensimFastTextEmbeddingResponseData = GensimMessageMapper.deserialize(result).message

        assert len(deserialized.responses) == 2
        assert len(deserialized.responses[0].vector) == 3
        assert math.isclose(deserialized.responses[0].vector[0].value, 1.0, rel_tol=1e-07)
        assert math.isclose(deserialized.responses[0].vector[1].value, 0.1234, rel_tol=1e-07)
        assert math.isclose(deserialized.responses[0].vector[2].value, 0.42, rel_tol=1e-07)
        assert len(deserialized.responses[1].vector) == 2
        assert math.isclose(deserialized.responses[1].vector[0].value, 42.0, rel_tol=1e-07)
        assert math.isclose(deserialized.responses[1].vector[1].value, 0.314, rel_tol=1e-07)
