import math

from keios_protocol_gensim import GensimFastTextEmbeddingRequest, GensimFastTextEmbeddingRequestEntity, \
    GensimFastTextEmbeddingResponse, GensimFastTextEmbeddingResponseEntity, VectorElement


def test_request_serialization_and_deserialization_returns_same_result():
    request_data = GensimFastTextEmbeddingRequest("foo")
    request = GensimFastTextEmbeddingRequestEntity()
    result = request.serialize(request_data)

    deserialized = request.fbs.GensimFastTextEmbeddingRequest.GetRootAsGensimFastTextEmbeddingRequest(result, 0)

    assert deserialized.Text().decode("utf-8") == "foo"


def test_response_serialization_and_deserialization_returns_same_result():
    element1 = VectorElement(1.0)
    element2 = VectorElement(0.1234)
    element3 = VectorElement(0.42)

    response_data = GensimFastTextEmbeddingResponse([element1, element2, element3])
    response = GensimFastTextEmbeddingResponseEntity()
    result = response.serialize(response_data)

    deserialized = response.fbs.GensimFastTextEmbeddingResponse.GetRootAsGensimFastTextEmbeddingResponse(result, 0)

    assert deserialized.VectorLength() == 3
    assert math.isclose(deserialized.Vector(0).Value(), 1.0, rel_tol=1e-07)
    assert math.isclose(deserialized.Vector(1).Value(), 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.Vector(2).Value(), 0.42, rel_tol=1e-07)
