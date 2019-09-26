import math
import unittest

from keios_protocol_gensim import GensimMessageMapper, GensimMessage
from keios_protocol_gensim.fasttext_similarity import SimilarityRequestData, GensimFastTextSimilarityRequestData, \
    GensimFastTextSimilarityResponseData, SimilarityResponseData


class TestFastTextSimilarity(unittest.TestCase):
    def test_request_serialization_and_deserialization_returns_same_result(self):
        data1 = SimilarityRequestData("foo", "bar")
        data2 = SimilarityRequestData("abc", "xyz")
        data = GensimFastTextSimilarityRequestData([data1, data2])

        result = GensimMessageMapper.serialize(GensimMessage(data))

        deserialized: GensimFastTextSimilarityRequestData = GensimMessageMapper.deserialize(result).message

        assert len(deserialized.requests)
        assert deserialized.requests[0].text1 == "foo"
        assert deserialized.requests[0].text2 == "bar"
        assert deserialized.requests[1].text1 == "abc"
        assert deserialized.requests[1].text2 == "xyz"

    def test_response_serialization_and_deserialization_returns_same_result(self):
        data1 = SimilarityResponseData(0.1234)
        data2 = SimilarityResponseData(3.14)
        data = GensimFastTextSimilarityResponseData([data1, data2])

        result = GensimMessageMapper.serialize(GensimMessage(data))

        deserialized: GensimFastTextSimilarityResponseData = GensimMessageMapper.deserialize(result).message

        assert len(deserialized.responses)
        assert math.isclose(deserialized.responses[0].value, 0.1234, rel_tol=1e-07)
        assert math.isclose(deserialized.responses[1].value, 3.14, rel_tol=1e-07)
