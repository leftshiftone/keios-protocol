import math
import unittest

from keios_protocol_gensim import GensimFastTextMostSimilarRequestData, GensimMessageMapper, GensimMessage
from keios_protocol_gensim.fasttext_most_similar import MostSimilarRequestData, MostSimilarityData, \
    MostSimilarResponseData, GensimFastTextMostSimilarResponseData


class TestFastTextMostSimilar(unittest.TestCase):
    def test_request_serialization_and_deserialization_returns_same_result(self):
        data1 = MostSimilarRequestData("foo")
        data2 = MostSimilarRequestData("bar")
        data = GensimFastTextMostSimilarRequestData([data1, data2])

        result = GensimMessageMapper.serialize(GensimMessage(data))

        deserialized: GensimFastTextMostSimilarRequestData = GensimMessageMapper.deserialize(result).message

        assert len(deserialized.requests) == 2
        assert deserialized.requests[0].text == "foo"
        assert deserialized.requests[1].text == "bar"

    def test_response_serialization_and_deserialization_returns_same_result(self):
        data1_sim1 = MostSimilarityData("foo", 0.5)
        data1_sim2 = MostSimilarityData("bar", 0.1234)
        data1 = MostSimilarResponseData([data1_sim1, data1_sim2])

        data2_sim1 = MostSimilarityData("123", 3.14)
        data2_sim2 = MostSimilarityData("abc", 1.7)
        data2_sim3 = MostSimilarityData("xyz", 42.0)
        data2 = MostSimilarResponseData([data2_sim1, data2_sim2, data2_sim3])

        data = GensimFastTextMostSimilarResponseData([data1, data2])
        result = GensimMessageMapper.serialize(GensimMessage(data))

        deserialized: GensimFastTextMostSimilarResponseData = GensimMessageMapper.deserialize(result).message

        assert len(deserialized.responses) == 2
        assert len(deserialized.responses[0].similarities) == 2
        assert deserialized.responses[0].similarities[0].text == "foo"
        assert math.isclose(deserialized.responses[0].similarities[0].probability, 0.5, rel_tol=1e-07)
        assert deserialized.responses[0].similarities[1].text == "bar"
        assert math.isclose(deserialized.responses[0].similarities[1].probability, 0.1234, rel_tol=1e-07)
        assert len(deserialized.responses[1].similarities) == 3
        assert deserialized.responses[1].similarities[0].text == "123"
        assert math.isclose(deserialized.responses[1].similarities[0].probability, 3.14, rel_tol=1e-07)
        assert deserialized.responses[1].similarities[1].text == "abc"
        assert math.isclose(deserialized.responses[1].similarities[1].probability, 1.7, rel_tol=1e-07)
        assert deserialized.responses[1].similarities[2].text == "xyz"
        assert math.isclose(deserialized.responses[1].similarities[2].probability, 42.0, rel_tol=1e-07)
