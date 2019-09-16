import math

from keios_protocol_pocketsphinx import PocketSphinxMessage, PocketSphinxMessageMapper, PocketSphinxRequest, \
    PocketSphinxResponse, Guess
from keios_protocol_pocketsphinx.flatbuffers.PocketSphinxMessageType import PocketSphinxMessageType


def test_message_with_request():
    data = PocketSphinxRequest(bytearray("foo", encoding="utf-8"))

    message_data = PocketSphinxMessage(PocketSphinxMessageType().PocketSphinxRequest, data)

    result = PocketSphinxMessageMapper().serialize(message_data)

    deserialized: PocketSphinxRequest = PocketSphinxMessageMapper().deserialize(result).message

    assert deserialized.speech.decode("utf-8") == "foo"


def test_message_with_response():
    data1 = Guess("foo", 0.95)
    data2 = Guess("bar", 0.75)
    data = PocketSphinxResponse([data1, data2])

    message_data = PocketSphinxMessage(PocketSphinxMessageType().PocketSphinxResponse, data)

    result = PocketSphinxMessageMapper().serialize(message_data)

    deserialized: PocketSphinxResponse = PocketSphinxMessageMapper().deserialize(result).message

    assert len(deserialized.guesses) == 2
    assert deserialized.guesses[0].phrase == "foo"
    assert math.isclose(deserialized.guesses[0].confidence, 0.95, rel_tol=1e-07)
    assert deserialized.guesses[1].phrase == "bar"
    assert math.isclose(deserialized.guesses[1].confidence, 0.75, rel_tol=1e-07)
