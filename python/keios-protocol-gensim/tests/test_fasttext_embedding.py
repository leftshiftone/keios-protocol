import math

from keios_protocol_gensim import GensimFastTextEmbeddingRequest, EmbeddingElement
from keios_protocol_gensim.fasttext_embedding import EmbeddingRequest, GensimFastTextEmbeddingRequestEntity, \
    EmbeddingResponse, GensimFastTextEmbeddingResponse, GensimFastTextEmbeddingResponseEntity


def test_request_serialization_and_deserialization_returns_same_result():
    data1 = EmbeddingRequest("foo")
    data2 = EmbeddingRequest("bar")
    data = GensimFastTextEmbeddingRequest([data1, data2])

    request = GensimFastTextEmbeddingRequestEntity()
    result = request.serialize(data)

    deserialized: GensimFastTextEmbeddingRequest = GensimFastTextEmbeddingRequestEntity().deserialize(result)

    assert len(deserialized.requests) == 2
    assert deserialized.requests[0].text == "foo"
    assert deserialized.requests[1].text == "bar"


def test_response_serialization_and_deserialization_returns_same_result():
    data1_element1 = EmbeddingElement(1.0)
    data1_element2 = EmbeddingElement(0.1234)
    data1_element3 = EmbeddingElement(0.42)
    data1 = EmbeddingResponse([data1_element1, data1_element2, data1_element3])

    data2_element1 = EmbeddingElement(42.0)
    data2_element2 = EmbeddingElement(0.314)
    data2 = EmbeddingResponse([data2_element1, data2_element2])

    data = GensimFastTextEmbeddingResponse([data1, data2])
    response = GensimFastTextEmbeddingResponseEntity()
    result = response.serialize(data)

    deserialized: GensimFastTextEmbeddingResponse = GensimFastTextEmbeddingResponseEntity().deserialize(result)

    assert len(deserialized.responses) == 2
    assert len(deserialized.responses[0].vector) == 3
    assert math.isclose(deserialized.responses[0].vector[0].value, 1.0, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[0].vector[1].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[0].vector[2].value, 0.42, rel_tol=1e-07)
    assert len(deserialized.responses[1].vector) == 2
    assert math.isclose(deserialized.responses[1].vector[0].value, 42.0, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[1].vector[1].value, 0.314, rel_tol=1e-07)
