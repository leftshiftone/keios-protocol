import math

from keios_protocol_gensim.fasttext_similarity import SimilarityRequest, GensimFastTextSimilarityRequest, \
    GensimFastTextSimilarityRequestEntity, SimilarityResponse, GensimFastTextSimilarityResponse, \
    GensimFastTextSimilarityResponseEntity


def test_request_serialization_and_deserialization_returns_same_result():
    data1 = SimilarityRequest("foo", "bar")
    data2 = SimilarityRequest("abc", "xyz")
    data = GensimFastTextSimilarityRequest([data1, data2])

    request = GensimFastTextSimilarityRequestEntity()
    result = request.serialize(data)

    deserialized: GensimFastTextSimilarityRequest = GensimFastTextSimilarityRequestEntity().deserialize(result)

    assert len(deserialized.requests)
    assert deserialized.requests[0].text1 == "foo"
    assert deserialized.requests[0].text2 == "bar"
    assert deserialized.requests[1].text1 == "abc"
    assert deserialized.requests[1].text2 == "xyz"


def test_response_serialization_and_deserialization_returns_same_result():
    data1 = SimilarityResponse(0.1234)
    data2 = SimilarityResponse(3.14)
    data = GensimFastTextSimilarityResponse([data1, data2])

    response = GensimFastTextSimilarityResponseEntity()
    result = response.serialize(data)

    deserialized: GensimFastTextSimilarityResponse = GensimFastTextSimilarityResponseEntity().deserialize(result)

    assert len(deserialized.responses)
    assert math.isclose(deserialized.responses[0].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[1].value, 3.14, rel_tol=1e-07)
