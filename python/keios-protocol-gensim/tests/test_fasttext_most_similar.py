import math

from keios_protocol_gensim import GensimFastTextMostSimilarRequest
from keios_protocol_gensim.fasttext_most_similar import MostSimilarRequest, GensimFastTextMostSimilarRequestEntity, \
    MostSimilarity, MostSimilarResponse, GensimFastTextMostSimilarResponse, GensimFastTextMostSimilarResponseEntity


def test_request_serialization_and_deserialization_returns_same_result():
    data1 = MostSimilarRequest("foo")
    data2 = MostSimilarRequest("bar")
    data = GensimFastTextMostSimilarRequest([data1, data2])

    request = GensimFastTextMostSimilarRequestEntity()
    result = request.serialize(data)

    deserialized: GensimFastTextMostSimilarRequest = GensimFastTextMostSimilarRequestEntity().deserialize(result)

    assert len(deserialized.requests) == 2
    assert deserialized.requests[0].text == "foo"
    assert deserialized.requests[1].text == "bar"


def test_response_serialization_and_deserialization_returns_same_result():
    data1_sim1 = MostSimilarity("foo", 0.5)
    data1_sim2 = MostSimilarity("bar", 0.1234)
    data1 = MostSimilarResponse([data1_sim1, data1_sim2])

    data2_sim1 = MostSimilarity("123", 3.14)
    data2_sim2 = MostSimilarity("abc", 1.7)
    data2_sim3 = MostSimilarity("xyz", 42.0)
    data2 = MostSimilarResponse([data2_sim1, data2_sim2, data2_sim3])

    data = GensimFastTextMostSimilarResponse([data1, data2])
    response = GensimFastTextMostSimilarResponseEntity()
    result = response.serialize(data)

    deserialized: GensimFastTextMostSimilarResponse = GensimFastTextMostSimilarResponseEntity().deserialize(result)

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
