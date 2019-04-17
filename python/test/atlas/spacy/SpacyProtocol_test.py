import unittest

from keios.atlas.spacy.SpacyProtocol import SpacyProtocol
from keios.atlas.spacy.SpacyResponse import SpacyResponse


class TestSpacyProtocol(unittest.TestCase):

    def test_serializes_spacy_response(self):
        ner = SpacyProtocol.NERSpacyResponseEntity("hallo", 1, 2, "hansi")
        dep1 = SpacyProtocol.DEPSpacyResponseEntity("de", "abc", "abc", "abc", 0, "abc", "abc", "abc", "abc", 10, "abc",
                                                    "abc")

        dep2 = SpacyProtocol.DEPSpacyResponseEntity("de", "abc", "abc", "abc", 0, "abc", "abc", "abc", "abc", 10, "abc",
                                                    "abc")

        entity = SpacyProtocol.SpacyResponseEntity([dep1, dep2], [ner])
        result = entity.serialize()
        deserialized = SpacyResponse.GetRootAsSpacyResponse(result, 0)
        self.assertEqual(deserialized.Ner(0).Text().decode("UTF-8"), 'hallo')
        self.assertEqual(deserialized.Ner(0).StartChar(), 1)
        self.assertEqual(deserialized.Ner(0).EndChar(), 2)
        self.assertEqual(deserialized.Ner(0).Label().decode("UTF-8"), "hansi")
        self.assertEqual(deserialized.Dep(0).Lang().decode("UTF-8"), "de")
        self.assertEqual(deserialized.Dep(1).Lang().decode("UTF-8"), "de")


    def test_deserializes_spacy_request(self):
        hex = "0C00000008000C000800040008000000080000000C00000001000000010000001800000068616C6C6F206963682062696E732064657220706574657200000000"
        bb = bytearray.fromhex(hex)
        request = SpacyProtocol.deserialize(bb)
        self.assertEqual(request.text, "hallo ich bins der peter")
        self.assertEqual(request.type, [SpacyProtocol.SpacyRequestEntity.Type.NER])