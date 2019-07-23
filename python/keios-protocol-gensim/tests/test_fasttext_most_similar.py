import math

from keios_protocol_gensim import GensimFastTextMostSimilarRequest, \
    GensimFastTextMostSimilarRequestEntity, GensimFastTextMostSimilarResponse, GensimFastTextMostSimilarResponseEntity, \
    Similarity


def test_request_serialization_and_deserialization_returns_same_result():
    request_data = GensimFastTextMostSimilarRequest("foo")
    request = GensimFastTextMostSimilarRequestEntity()
    result = request.serialize(request_data)

    deserialized = request.fbs.GensimFastTextMostSimilarRequest.GetRootAsGensimFastTextMostSimilarRequest(result, 0)

    assert deserialized.Text().decode("utf-8") == "foo"


def test_response_serialization_and_deserialization_returns_same_result():
    similarity1 = Similarity("foo", 0.5)
    similarity2 = Similarity("bar", 0.1234)

    response_data = GensimFastTextMostSimilarResponse([similarity1, similarity2])
    response = GensimFastTextMostSimilarResponseEntity()
    result = response.serialize(response_data)

    deserialized = response.fbs.GensimFastTextMostSimilarResponse.GetRootAsGensimFastTextMostSimilarResponse(result, 0)

    assert deserialized.SimilaritiesLength() == 2
    assert deserialized.Similarities(0).Text().decode("utf-8") == "foo"
    assert math.isclose(deserialized.Similarities(0).Probability(), 0.5, rel_tol=1e-07)
    assert deserialized.Similarities(1).Text().decode("utf-8") == "bar"
    assert math.isclose(deserialized.Similarities(1).Probability(), 0.1234, rel_tol=1e-07)
