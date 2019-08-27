import os
import unittest

from keios_protocol_tesseract import TesseractMessageEntity, TesseractOcrRequestEntity, TesseractProtocol, \
    TesseractOcrResponseEntity


class TesseractProtocolTest(unittest.TestCase):

    def test_serializes_and_deserializes(self):

        def parameters():
            base_dir = os.path.dirname(os.path.realpath(__file__))
            with open(f"{base_dir}/hello_world.jpg", "rb") as f:
                yield TesseractOcrRequestEntity(bytearray(f.read()))
            yield TesseractOcrResponseEntity("some test", 99)

        for p in parameters():
            message = TesseractMessageEntity(p)
            serialized = TesseractProtocol.serialize(message)
            deserialized = TesseractProtocol.deserialize(serialized)

            self.assertEqual(message, deserialized)
