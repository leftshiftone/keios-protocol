from keios_protocol_gensim import GensimFastTextSimilarityRequest, GensimMessage, GensimMessageEntity


def test_request_serialization_and_deserialization_returns_same_result():

    request_data = GensimFastTextSimilarityRequest("foo", "bar")
    message_data = GensimMessage(request_data)

    message = GensimMessageEntity()
    result = message.serialize(message_data)

    deserialized = message.fbs.GensimMessage.GetRootAsGensimMessage(result, 0)

    assert deserialized.Text1().decode("utf-8") == "foo"
    assert deserialized.Text2().decode("utf-8") == "bar"
