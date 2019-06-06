import unittest
import os

from pocketsphinx import StreamSpeech
import numpy as np

from keios.atlas.pocketsphinx.PocketsphinxProtocol import PReq
from keios.atlas.pocketsphinx.PocketsphinxProtocol import PRes

TESTDATA_FILENAME = os.path.join(os.path.dirname(__file__), 'goforward.raw')

class TestPocketsphinxProtocol(unittest.TestCase):

    def setUp(self):
        self.f = open(TESTDATA_FILENAME, 'rb')

    def tearDown(self):
        self.f.close()    

    def test_pocketsphinx_transfer_request(self):
        def dummy_serializer():
            return PReq.serialize(PReq.Data(speech=self.f.read(2048)))

        def dummy_callback():
            flatbuffer = dummy_serializer()
            return PReq.deserialize(flatbuffer).speech

        result = ''
        for phrase in StreamSpeech(callback=dummy_callback):
            result = phrase.best()[0][0]

        self.assertEqual(result, 'go forward ten meters')

    def test_serialize_request(self):
        elem_count = 10
        
        rand_arr = np.random.rand(elem_count)
        byte_vector = rand_arr.tobytes()

        result = PReq.serialize(PReq.Data(speech=byte_vector))
        deserialized = PReq.PocketsphinxRequest.GetRootAsPocketsphinxRequest(result, 0)
        deserialized_np = np.frombuffer(deserialized.SpeechAsNumpy().tobytes(), count=elem_count)
        
        self.assertTrue(
                np.allclose(
                    rand_arr,
                    deserialized_np
                )
            )

    def test_deserialize_request(self):
        expected_buffer = b'\x0c\x00\x00\x00\x00\x00\x06\x00\x08\x00\x04\x00\x06\x00\x00\x00\x04\x00\x00\x00\x08\x00\x00\x00\xecQ_\x1ew\xf8\xd7?'
        
        elem_count = 1
        np.random.seed(42)
        rand_arr = np.random.rand(elem_count)

        byte_vector = rand_arr.tobytes()
        self.assertEqual(byte_vector, PReq.deserialize(expected_buffer).speech)