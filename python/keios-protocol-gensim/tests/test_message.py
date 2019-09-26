import math

from keios_protocol_gensim import GensimFastTextSimilarityRequestData, GensimMessage, GensimMessageMapper, \
    GensimFastTextSimilarityResponseData, GensimFastTextMostSimilarRequestData, GensimFastTextMostSimilarResponseData, \
    MostSimilarityData, \
    GensimFastTextEmbeddingRequestData, EmbeddingElementData, GensimFastTextEmbeddingResponseData
from keios_protocol_gensim.fasttext_embedding import EmbeddingRequestData, EmbeddingResponseData
from keios_protocol_gensim.fasttext_most_similar import MostSimilarRequestData, MostSimilarResponseData
from keios_protocol_gensim.fasttext_similarity import SimilarityRequestData, SimilarityResponseData


def test_message_with_fasttext_embedding_request():
    data1 = EmbeddingRequestData("foo")
    data2 = EmbeddingRequestData("bar")
    data = GensimFastTextEmbeddingRequestData([data1, data2])

    message_data = GensimMessage(data)

    result = GensimMessageMapper.serialize(message_data)

    deserialized: GensimFastTextEmbeddingRequestData = GensimMessageMapper.deserialize(result).message

    assert deserialized.requests[0].text == "foo"


def test_message_with_fasttext_embedding_response():
    data1_element1 = EmbeddingElementData(1.0)
    data1_element2 = EmbeddingElementData(0.1234)
    data1_element3 = EmbeddingElementData(0.42)
    data1 = EmbeddingResponseData([data1_element1, data1_element2, data1_element3])

    data2_element1 = EmbeddingElementData(42.0)
    data2_element2 = EmbeddingElementData(0.314)
    data2 = EmbeddingResponseData([data2_element1, data2_element2])

    data = GensimFastTextEmbeddingResponseData([data1, data2])
    message_data = GensimMessage(data)

    result = GensimMessageMapper.serialize(message_data)

    deserialized: GensimFastTextEmbeddingResponseData = GensimMessageMapper.deserialize(result).message

    assert len(deserialized.responses) == 2
    assert len(deserialized.responses[0].vector) == 3
    assert math.isclose(deserialized.responses[0].vector[0].value, 1.0, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[0].vector[1].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[0].vector[2].value, 0.42, rel_tol=1e-07)
    assert len(deserialized.responses[1].vector) == 2
    assert math.isclose(deserialized.responses[1].vector[0].value, 42.0, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[1].vector[1].value, 0.314, rel_tol=1e-07)


def test_message_with_fasttext_most_similar_request():
    data1 = MostSimilarRequestData("foo")
    data2 = MostSimilarRequestData("bar")
    data = GensimFastTextMostSimilarRequestData([data1, data2])
    message_data = GensimMessage(data)

    result = GensimMessageMapper.serialize(message_data)

    deserialized: GensimFastTextMostSimilarRequestData = GensimMessageMapper.deserialize(result).message

    assert len(deserialized.requests) == 2
    assert deserialized.requests[0].text == "foo"
    assert deserialized.requests[1].text == "bar"


def test_message_with_fasttext_most_similar_response():
    data1_sim1 = MostSimilarityData("foo", 0.5)
    data1_sim2 = MostSimilarityData("bar", 0.1234)
    data1 = MostSimilarResponseData([data1_sim1, data1_sim2])

    data2_sim1 = MostSimilarityData("123", 3.14)
    data2_sim2 = MostSimilarityData("abc", 1.7)
    data2_sim3 = MostSimilarityData("xyz", 42.0)
    data2 = MostSimilarResponseData([data2_sim1, data2_sim2, data2_sim3])

    data = GensimFastTextMostSimilarResponseData([data1, data2])
    message_data = GensimMessage(data)

    result = GensimMessageMapper.serialize(message_data)

    deserialized: GensimFastTextMostSimilarResponseData = GensimMessageMapper.deserialize(result).message

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
    data1 = SimilarityRequestData("foo", "bar")
    data2 = SimilarityRequestData("abc", "xyz")
    data = GensimFastTextSimilarityRequestData([data1, data2])
    message_data = GensimMessage(data)

    result = GensimMessageMapper.serialize(message_data)

    deserialized: GensimFastTextSimilarityRequestData = GensimMessageMapper.deserialize(result).message

    assert len(deserialized.requests)
    assert deserialized.requests[0].text1 == "foo"
    assert deserialized.requests[0].text2 == "bar"
    assert deserialized.requests[1].text1 == "abc"
    assert deserialized.requests[1].text2 == "xyz"


def test_message_with_fasttext_similaritity_response():
    data1 = SimilarityResponseData(0.1234)
    data2 = SimilarityResponseData(3.14)
    data = GensimFastTextSimilarityResponseData([data1, data2])
    message_data = GensimMessage(data)

    result = GensimMessageMapper.serialize(message_data)

    deserialized: GensimFastTextSimilarityResponseData = GensimMessageMapper.deserialize(result).message

    assert len(deserialized.responses)
    assert math.isclose(deserialized.responses[0].value, 0.1234, rel_tol=1e-07)
    assert math.isclose(deserialized.responses[1].value, 3.14, rel_tol=1e-07)
