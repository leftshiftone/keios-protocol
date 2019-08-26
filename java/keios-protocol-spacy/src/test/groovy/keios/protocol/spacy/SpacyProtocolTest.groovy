package keios.protocol.spacy


import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

class SpacyProtocolTest extends Specification {

    @Unroll
    void "serializes / deserializes #entity"() {
        given:
            SpacyMessageEntity message = new SpacyMessageEntity(entity)
        when:
            def result = SpacyProtocol.instance().toMessage(SpacyProtocol.instance().toWireMessage(message))
        then:
            result.message == entity
        where:
            entity << [
                    new SpacyRequestEntity("test", [ESpacyType.DEP, ESpacyType.NER] as Set<ESpacyType>),
                    new SpacyBatchRequestEntity([new SpacyRequestEntity("test", [ESpacyType.DEP, ESpacyType.NER] as Set<ESpacyType>),
                                                 new SpacyRequestEntity("xyz", [ESpacyType.DEP] as Set<ESpacyType>)]),
                    new SpacyResponseEntity(
                            [
                                    new DEPSpacyResponseEntity("a", "b", "c", "d", 0, "e", "f", "g", "h", 1, "i", "j"),
                                    new DEPSpacyResponseEntity("a1", "b1", "c1", "d1", 10, "e1", "f1", "g1", "h1", 11, "i1", "j1"),
                            ],
                            [
                                    new NERSpacyResponseEntity("test", 0, 1, "xyz"),
                                    new NERSpacyResponseEntity("test1", 1, 0, "xyz"),
                            ]),
                    new SpacyBatchResponseEntity([
                            new SpacyResponseEntity(
                                    [
                                            new DEPSpacyResponseEntity("sadsad", "basdsad", "fsdfc", "fsdfd", 0, "e", "f", "g", "h", 1, "i", "j"),
                                            new DEPSpacyResponseEntity("cxccv", "sdfsdf", "xcvxcv", "hgfh", 2, "rt3", "f1", "g1", "h1", 11, "asd", "g"),
                                    ],
                                    [
                                            new NERSpacyResponseEntity("test", 0, 1, "xyz"),
                                            new NERSpacyResponseEntity("test1", 1, 0, "xyz"),
                                    ]),
                            new SpacyResponseEntity(
                                    [
                                            new DEPSpacyResponseEntity("a", "b", "c", "d", 0, "e", "f", "g", "h", 1, "i", "j"),
                                            new DEPSpacyResponseEntity("a1", "b1", "c1", "d1", 10, "e1", "f1", "g1", "h1", 11, "i1", "j1"),
                                    ],
                                    [
                                            new NERSpacyResponseEntity("x", 0, 1, "xyz"),
                                            new NERSpacyResponseEntity("y", 1, 0, "xyz"),
                                    ])
                    ])
            ]
    }

    void "wrapped serial/deserialization"() {
        given:
            def messages = Messages()
        when:
            def serialized = messages
                    .parallelStream()
                    .map({ m -> SpacyProtocol.instance().toWireMessage(m) }).collect(Collectors.toList())
            def deserialized = serialized
                    .parallelStream()
                    .map({ it -> SpacyProtocol.instance().toMessage(it) })
                    .collect(Collectors.toList())
        then:
            deserialized == messages
    }

    private List<SpacyMessageEntity> Messages() {
        return [
                new SpacyRequestEntity("test", [ESpacyType.DEP, ESpacyType.NER] as Set<ESpacyType>),
                new SpacyBatchRequestEntity([new SpacyRequestEntity("test", [ESpacyType.DEP, ESpacyType.NER] as Set<ESpacyType>),
                                             new SpacyRequestEntity("xyz", [ESpacyType.DEP] as Set<ESpacyType>)]),
                new SpacyResponseEntity(
                        [
                                new DEPSpacyResponseEntity("a", "b", "c", "d", 0, "e", "f", "g", "h", 1, "i", "j"),
                                new DEPSpacyResponseEntity("a1", "b1", "c1", "d1", 10, "e1", "f1", "g1", "h1", 11, "i1", "j1"),
                        ],
                        [
                                new NERSpacyResponseEntity("test", 0, 1, "xyz"),
                                new NERSpacyResponseEntity("test1", 1, 0, "xyz"),
                        ]),
                new SpacyBatchResponseEntity([
                        new SpacyResponseEntity(
                                [
                                        new DEPSpacyResponseEntity("sadsad", "basdsad", "fsdfc", "fsdfd", 0, "e", "f", "g", "h", 1, "i", "j"),
                                        new DEPSpacyResponseEntity("cxccv", "sdfsdf", "xcvxcv", "hgfh", 2, "rt3", "f1", "g1", "h1", 11, "asd", "g"),
                                ],
                                [
                                        new NERSpacyResponseEntity("test", 0, 1, "xyz"),
                                        new NERSpacyResponseEntity("test1", 1, 0, "xyz"),
                                ]),
                        new SpacyResponseEntity(
                                [
                                        new DEPSpacyResponseEntity("a", "b", "c", "d", 0, "e", "f", "g", "h", 1, "i", "j"),
                                        new DEPSpacyResponseEntity("a1", "b1", "c1", "d1", 10, "e1", "f1", "g1", "h1", 11, "i1", "j1"),
                                ],
                                [
                                        new NERSpacyResponseEntity("x", 0, 1, "xyz"),
                                        new NERSpacyResponseEntity("y", 1, 0, "xyz"),
                                ])
                ])
        ].collect { new SpacyMessageEntity(it) }
    }
}
