import math

from keios_protocol_gensim import GensimFastTextSimilarityRequest, GensimMessage, GensimMessageEntity, \
    GensimFastTextSimilarityResponse, GensimFastTextMostSimilarRequest, GensimFastTextMostSimilarResponse, \
    MostSimilarity, \
    GensimFastTextEmbeddingRequest, EmbeddingElement, GensimFastTextEmbeddingResponse
from keios_protocol_gensim.fasttext_embedding import EmbeddingRequest, EmbeddingResponse
from keios_protocol_gensim.fasttext_most_similar import MostSimilarRequest, MostSimilarResponse
from keios_protocol_gensim.fasttext_similarity import SimilarityRequest, SimilarityResponse
from keios_protocol_gensim.flatbuffers.GensimMessageType import GensimMessageType


def test_message_with_fasttext_embedding_request():
    data1 = EmbeddingRequest("foo")
    data2 = EmbeddingRequest("bar")
    data = GensimFastTextEmbeddingRequest([data1, data2])

    message_data = GensimMessage(GensimMessageType().GensimFastTextEmbeddingRequest, data)

    result = GensimMessageEntity().serialize(message_data)

    deserialized: GensimFastTextEmbeddingRequest = GensimMessageEntity().deserialize(result).message

    assert deserialized.requests[0].text == "foo"


def test_message_with_fasttext_embedding_response():
    data1_element1 = EmbeddingElement(1.0)
    data1_element2 = EmbeddingElement(0.1234)
    data1_element3 = EmbeddingElement(0.42)
    data1 = EmbeddingResponse([data1_element1, data1_element2, data1_element3])

    data2_element1 = EmbeddingElement(42.0)
    data2_element2 = EmbeddingElement(0.314)
    data2 = EmbeddingResponse([data2_element1, data2_element2])

    data = GensimFastTextEmbeddingResponse([data1, data2])
    message_data = GensimMessage(GensimMessageType().GensimFastTextSimilarityResponse, data)

    result = GensimMessageEntity().serialize(message_data)

    deserialized: GensimFastTextEmbeddingResponse = GensimMessageEntity().deserialize(result).message

    assert len(deserialized.responses) == 2
    assert len(deserialized.responses[0].vector) == 3
    assert math.isclose(deserialized.responses[0].vector[0].value, 1.0, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[0].vector[1].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[0].vector[2].value, 0.42, rel_tol=1e-07)
    assert len(deserialized.responses[1].vector) == 2
    assert math.isclose(deserialized.responses[1].vector[0].value, 42.0, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[1].vector[1].value, 0.314, rel_tol=1e-07)


def test_message_with_fasttext_most_similar_request():
    data1 = MostSimilarRequest("foo")
    data2 = MostSimilarRequest("bar")
    data = GensimFastTextMostSimilarRequest([data1, data2])
    message_data = GensimMessage(GensimMessageType().GensimFastTextMostSimilarRequest, data)

    result = GensimMessageEntity().serialize(message_data)

    deserialized: GensimFastTextMostSimilarRequest = GensimMessageEntity().deserialize(result).message

    assert len(deserialized.requests) == 2
    assert deserialized.requests[0].text == "foo"
    assert deserialized.requests[1].text == "bar"


def test_message_with_fasttext_most_similar_response():
    data1_sim1 = MostSimilarity("foo", 0.5)
    data1_sim2 = MostSimilarity("bar", 0.1234)
    data1 = MostSimilarResponse([data1_sim1, data1_sim2])

    data2_sim1 = MostSimilarity("123", 3.14)
    data2_sim2 = MostSimilarity("abc", 1.7)
    data2_sim3 = MostSimilarity("xyz", 42.0)
    data2 = MostSimilarResponse([data2_sim1, data2_sim2, data2_sim3])

    data = GensimFastTextMostSimilarResponse([data1, data2])
    message_data = GensimMessage(GensimMessageType().GensimFastTextSimilarityResponse, data)

    result = GensimMessageEntity().serialize(message_data)

    deserialized: GensimFastTextMostSimilarResponse = GensimMessageEntity().deserialize(result).message

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


def test_message_with_fasttext_similaritity_request():
    data1 = SimilarityRequest("foo", "bar")
    data2 = SimilarityRequest("abc", "xyz")
    data = GensimFastTextSimilarityRequest([data1, data2])
    message_data = GensimMessage(GensimMessageType().GensimFastTextSimilarityRequest, data)

    result = GensimMessageEntity().serialize(message_data)

    deserialized: GensimFastTextSimilarityRequest = GensimMessageEntity().deserialize(result).message

    assert len(deserialized.requests)
    assert deserialized.requests[0].text1 == "foo"
    assert deserialized.requests[0].text2 == "bar"
    assert deserialized.requests[1].text1 == "abc"
    assert deserialized.requests[1].text2 == "xyz"


def test_message_with_fasttext_similaritity_response():
    data1 = SimilarityResponse(0.1234)
    data2 = SimilarityResponse(3.14)
    data = GensimFastTextSimilarityResponse([data1, data2])
    message_data = GensimMessage(GensimMessageType().GensimFastTextSimilarityResponse, data)

    result = GensimMessageEntity().serialize(message_data)

    deserialized: GensimFastTextSimilarityResponse = GensimMessageEntity().deserialize(result).message

    assert len(deserialized.responses)
    assert math.isclose(deserialized.responses[0].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[1].value, 3.14, rel_tol=1e-07)
