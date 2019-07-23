import math

from keios_protocol_gensim import GensimFastTextSimilarityRequest, \
    GensimFastTextSimilarityRequestEntity, GensimFastTextSimilarityResponse, GensimFastTextSimilarityResponseEntity


def test_request_serialization_and_deserialization_returns_same_result():
    request_data = GensimFastTextSimilarityRequest("foo", "bar")
    request = GensimFastTextSimilarityRequestEntity()
    result = request.serialize(request_data)

    deserialized = request.fbs.GensimFastTextSimilarityRequest.GetRootAsGensimFastTextSimilarityRequest(result, 0)

    assert deserialized.Text1().decode("utf-8") == "foo"
    assert deserialized.Text2().decode("utf-8") == "bar"


def test_response_serialization_and_deserialization_returns_same_result():
    response_data = GensimFastTextSimilarityResponse(0.1234)
    response = GensimFastTextSimilarityResponseEntity()
    result = response.serialize(response_data)

    deserialized = response.fbs.GensimFastTextSimilarityResponse.GetRootAsGensimFastTextSimilarityResponse(result, 0)

    assert math.isclose(deserialized.Probability(), 0.1234, rel_tol=1e-07)
