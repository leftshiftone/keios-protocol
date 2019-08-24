import unittest

from keios_protocol_tesseract import TesseractMessageEntity, TesseractOcrRequestEntity, TesseractProtocol, \
    TesseractOcrResponseEntity


class TesseractProtocolTest(unittest.TestCase):

    def test_serializes_and_deserializes(self):

        def parameters():
            yield TesseractOcrRequestEntity(bytearray([0, 1, 2, 3, 4, 5]))
            yield TesseractOcrResponseEntity("some test", 99)

        for p in parameters():
            message = TesseractMessageEntity(p)
            serialized = TesseractProtocol.serialize(message)
            deserialized = TesseractProtocol.deserialize(serialized)

            self.assertEqual(message, deserialized)
