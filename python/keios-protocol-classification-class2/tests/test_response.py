from keios_protocol_classification_class2 import ClassificationClass2ResponseEntity, ClassificationClass2Response
from keios_protocol_classification_class2 import Probability


def test_serializing_and_deserializing_returns_same_result():
    prob1 = Probability(0.25, 0.5)
    prob2 = Probability(0.7, 0.1)

    response_data = ClassificationClass2Response([prob1, prob2])
    response = ClassificationClass2ResponseEntity()
    result = response.serialize(response_data)

    deserialized = response.fbs.ClassificationClass2Response.GetRootAsClassificationClass2Response(result, 0)

    assert deserialized.Response(0).Probability1() == 0.25
    assert deserialized.Response(0).Probability2() == 0.5

    assert deserialized.Response(1).Probability1() == 0.7
    assert deserialized.Response(1).Probability2() == 0.1
