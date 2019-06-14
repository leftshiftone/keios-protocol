import os
import pytest
import numpy as np
from pocketsphinx import StreamSpeech
from ..keios_protocol_pocketsphinx import PocketsphinxRequestEntity

def test_serialize_request():
        elem_count = 10
        rand_arr = np.random.rand(elem_count)
        byte_vector = rand_arr.tobytes()
        request = PocketsphinxRequestEntity()
        data = request.dataclass(speech=byte_vector)
        serialized_data = request.serialize(data)
        deserialized = request.fbs.PocketsphinxRequest.GetRootAsPocketsphinxRequest(serialized_data, 0)
        deserialized_np = np.frombuffer(deserialized.SpeechAsNumpy().tobytes(), count=elem_count)
        assert np.allclose(rand_arr, deserialized_np)

def test_deserialize_request(supply_nparray_serialized):
    request = PocketsphinxRequestEntity()    
    byte_vector = supply_nparray_serialized[0].tobytes()
    assert byte_vector == request.deserialize(supply_nparray_serialized[1]).speech

def test_pocketsphinx_transfer_request(supply_goforward_file):
    request = PocketsphinxRequestEntity() 
    goforward = supply_goforward_file
    def dummy_serializer():
        return request.serialize(request.dataclass(speech=goforward.read(2048)))
    def dummy_callback():
        flatbuffer = dummy_serializer()
        return request.deserialize(flatbuffer).speech
    for phrase in StreamSpeech(callback=dummy_callback):
        result = phrase.best()[0][0]
    assert result == 'go forward ten meters'