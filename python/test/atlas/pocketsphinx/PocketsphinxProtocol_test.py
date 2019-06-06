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
        guesses = [
            ('go forward ten meters', -28034),
            ('go for word ten meters', -28570),
            ('go forward and majors', -28670),
            ('go forward and meters', -28681),
            ('go forward and readers', -28685),
            ('go forward ten readers', -28688),
            ('go forward ten leaders', -28695),
            ('go forward can meters', -28695),
            ('go forward and leaders', -28706),
            ('go for work ten meters', -28722)
        ]
        self.dummy_response = PRes.Data(
            guesses=[PRes.Guess(phrase=guess[0], confidence=guess[1]) for guess in guesses]
        )
            

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

    def test_serialize_response(self):
        result = PRes.serialize(self.dummy_response)
        deserialized = PRes.PocketsphinxResponse.GetRootAsPocketsphinxResponse(result, 0)

        self.assertEqual(self.dummy_response.guesses[0].confidence, deserialized.Guesses(0).Confidence())
        self.assertEqual(self.dummy_response.guesses[0].phrase, deserialized.Guesses(0).Phrase().decode("utf-8") )
        self.assertEqual(self.dummy_response.guesses[9].confidence, deserialized.Guesses(9).Confidence())
        self.assertEqual(self.dummy_response.guesses[9].phrase, deserialized.Guesses(9).Phrase().decode("utf-8") )

    def test_deserialize_response(self):
        short_dummy = self.dummy_response
        short_dummy.guesses = self.dummy_response.guesses[:1]
        expected_buffer = b'\x0c\x00\x00\x00\x00\x00\x06\x00\x08\x00\x04\x00\x06\x00\x00\x00\x04\x00\x00\x00\x01\x00\x00\x00\x0c\x00\x00\x00\x08\x00\x0c\x00\x08\x00\x04\x00\x08\x00\x00\x00\x08\x00\x00\x00\x00\x04\xdb\xc6\x15\x00\x00\x00go forward ten meters\x00\x00\x00'
        self.assertEqual(short_dummy.guesses[0], PRes.deserialize(expected_buffer).guesses[0])