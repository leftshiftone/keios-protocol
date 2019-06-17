from keios_protocol_classification_class2 import ClassificationClass2RequestEntity, ClassificationClass2Request
from keios_protocol_classification_class2 import Vector
from keios_protocol_classification_class2.flatbuffers import Vector as VectorFlatbuffer


def test_serializing_and_deserializing_returns_same_result():
    vec1 = Vector(bytearray("foo", encoding="utf-8"))
    vec2 = Vector(bytearray("bar", encoding="utf-8"))

    request_data = ClassificationClass2Request([vec1, vec2])
    request = ClassificationClass2RequestEntity()
    result = request.serialize(request_data)

    deserialized = request.fbs.ClassificationClass2Request.GetRootAsClassificationClass2Request(result, 0)

    assert to_bytearray(deserialized.Vectors(0)) == bytearray("foo", encoding="utf-8")
    assert to_bytearray(deserialized.Vectors(1)) == bytearray("bar", encoding="utf-8")


def to_bytearray(vector: VectorFlatbuffer):
    bytes = bytearray()
    for i in range(vector.BytesLength()):
        bytes.append(vector.Bytes(i))
    return bytes
