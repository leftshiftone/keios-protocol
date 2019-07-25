import math

from keios_protocol_gensim import GensimFastTextSimilarityRequest, GensimMessage, GensimMessageEntity, \
    GensimFastTextSimilarityResponse, GensimFastTextMostSimilarRequest, GensimFastTextMostSimilarResponse, Similarity, \
    GensimFastTextEmbeddingRequest, VectorElement, GensimFastTextEmbeddingResponse
from keios_protocol_gensim.flatbuffers import GensimMessageType


def test_message_with_fasttext_embedding_request():
    data = GensimFastTextEmbeddingRequest("foo")
    message_data = GensimMessage(GensimMessageType.GensimMessageType().GensimFastTextEmbeddingRequest, data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.deserialize(result)

    assert deserialized.message.text == "foo"


def test_message_with_fasttext_embedding_response():
    element1 = VectorElement(1.0)
    element2 = VectorElement(0.1234)
    element3 = VectorElement(0.42)

    data = GensimFastTextEmbeddingResponse([element1, element2, element3])
    message_data = GensimMessage(GensimMessageType.GensimMessageType().GensimFastTextSimilarityResponse, data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.deserialize(result)

    assert len(deserialized.message.vector) == 3
    assert math.isclose(deserialized.message.vector[0].value, 1.0, rel_tol=1e-07)
    assert math.isclose(deserialized.message.vector[1].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.message.vector[2].value, 0.42, rel_tol=1e-07)


def test_message_with_fasttext_most_similar_request():
    data = GensimFastTextMostSimilarRequest("foo")
    message_data = GensimMessage(GensimMessageType.GensimMessageType().GensimFastTextMostSimilarRequest, data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.deserialize(result)

    assert deserialized.message.text == "foo"


def test_message_with_fasttext_most_similar_response():
    similarity1 = Similarity("foo", 0.5)
    similarity2 = Similarity("bar", 0.1234)

    data = GensimFastTextMostSimilarResponse([similarity1, similarity2])
    message_data = GensimMessage(GensimMessageType.GensimMessageType().GensimFastTextSimilarityResponse, data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.deserialize(result)

    assert len(deserialized.message.similarities) == 2
    assert deserialized.message.similarities[0].text == "foo"
    assert math.isclose(deserialized.message.similarities[0].probability, 0.5, rel_tol=1e-07)
    assert deserialized.message.similarities[1].text == "bar"
    assert math.isclose(deserialized.message.similarities[1].probability, 0.1234, rel_tol=1e-07)


def test_message_with_fasttext_similaritity_request():
    data = GensimFastTextSimilarityRequest("foo", "bar")
    message_data = GensimMessage(GensimMessageType.GensimMessageType().GensimFastTextSimilarityRequest, data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.deserialize(result)

    assert deserialized.message.text1 == "foo"
    assert deserialized.message.text2 == "bar"


def test_message_with_fasttext_similaritity_response():
    data = GensimFastTextSimilarityResponse(0.1234)
    message_data = GensimMessage(GensimMessageType.GensimMessageType().GensimFastTextSimilarityResponse, data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.deserialize(result)

    assert math.isclose(deserialized.message.probability, 0.1234, rel_tol=1e-07)
