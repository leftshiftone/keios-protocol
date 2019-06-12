import pytest

from python_keios_protocol.pocketsphinx.Protocol import PocketsphinxResponseEntity

def test_serialize_response(supply_guesses):
    response = PocketsphinxResponseEntity()
    result = response.serialize(response.dataclass(guesses=supply_guesses))
    deserialized = response.fbs.PocketsphinxResponse.GetRootAsPocketsphinxResponse(result, 0)

    assert supply_guesses[0][1] == deserialized.Guesses(0).Confidence()
    assert supply_guesses[0][0] == deserialized.Guesses(0).Phrase().decode("utf-8")
    assert supply_guesses[9][1] == deserialized.Guesses(9).Confidence()
    assert supply_guesses[9][0] == deserialized.Guesses(9).Phrase().decode("utf-8")

def test_deserialize_response(supply_guesses):
    response = PocketsphinxResponseEntity()
    data=response.dataclass(guesses=supply_guesses[:1])
    expected_buffer = b'\x0c\x00\x00\x00\x00\x00\x06\x00\x08\x00\x04\x00\x06\x00\x00\x00\x04\x00\x00\x00\x01\x00\x00\x00\x0c\x00\x00\x00\x08\x00\x0c\x00\x08\x00\x04\x00\x08\x00\x00\x00\x08\x00\x00\x00\x00\x04\xdb\xc6\x15\x00\x00\x00go forward ten meters\x00\x00\x00'
    assert data.guesses[0] == response.deserialize(expected_buffer).guesses[0]