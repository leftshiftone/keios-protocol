import math

from keios_protocol_gensim import GensimFastTextEmbeddingRequest, GensimFastTextEmbeddingRequestEntity, \
    GensimFastTextEmbeddingResponse, GensimFastTextEmbeddingResponseEntity


def test_request_serialization_and_deserialization_returns_same_result():
    request_data = GensimFastTextEmbeddingRequest("foo")
    request = GensimFastTextEmbeddingRequestEntity()
    result = request.serialize(request_data)

    deserialized = request.fbs.GensimFastTextEmbeddingRequest.GetRootAsGensimFastTextEmbeddingRequest(result, 0)

    assert deserialized.Text().decode("utf-8") == "foo"


def test_response_serialization_and_deserialization_returns_same_result():
    response_data = GensimFastTextEmbeddingResponse([1.0, 0.1234, 0.42])
    response = GensimFastTextEmbeddingResponseEntity()
    result = response.serialize(response_data)

    deserialized = response.fbs.GensimFastTextEmbeddingResponse.GetRootAsGensimFastTextEmbeddingResponse(result, 0)

    assert deserialized.VectorLength() == 3
    assert math.isclose(deserialized.Vector(0), 1.0, rel_tol=1e-07)
    assert math.isclose(deserialized.Vector(0), 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.Vector(0), 0.42, rel_tol=1e-07)
