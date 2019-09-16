from dataclasses import dataclass
from typing import List

from keios_protocol_common import FlatbufferObject

from .flatbuffers import Guess as GuessClass
from .flatbuffers import PocketSphinxMessage as PocketSphinxMessageClass
from .flatbuffers import PocketSphinxRequest as PocketSphinxRequestClass
from .flatbuffers import PocketSphinxResponse as PocketSphinxResponseClass
from .flatbuffers.PocketSphinxMessageType import PocketSphinxMessageType


@dataclass
class PocketSphinxMessage:
    message_type: PocketSphinxMessageType
    message: object  # is a dataclass


class PocketSphinxMessageMapper(FlatbufferObject):
    def __init__(self):
        super().__init__(PocketSphinxMessage, PocketSphinxMessageClass)

    def dataclass_to_flatbuffer(self, dataclass):
        if isinstance(dataclass.message, PocketSphinxRequest):
            type_ = PocketSphinxMessageType().PocketSphinxRequest
            mapper = PocketSphinxRequestMapper()
        elif isinstance(dataclass.message, PocketSphinxResponse):
            type_ = PocketSphinxMessageType().PocketSphinxResponse
            mapper = PocketSphinxResponseMapper()
        else:
            raise ValueError(f"Message type {type(dataclass.message)} is unknown")

        message = mapper.dataclass_to_flatbuffer(dataclass.message)

        self.start()
        self['MessageType'] = type_
        self['Message'] = message
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        type_ = flatbuffer.MessageType()
        wrapped = flatbuffer.Message()
        if type_ == PocketSphinxMessageType().PocketSphinxRequest:
            wrapped_flatbuffer = PocketSphinxRequestClass.PocketSphinxRequest()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = PocketSphinxRequestMapper().flatbuffer_to_dataclass(wrapped_flatbuffer)
        elif type_ == PocketSphinxMessageType().PocketSphinxResponse:
            wrapped_flatbuffer = PocketSphinxResponseClass.PocketSphinxResponse()
            wrapped_flatbuffer.Init(wrapped.Bytes, wrapped.Pos)
            message = PocketSphinxResponseMapper().flatbuffer_to_dataclass(wrapped_flatbuffer)
        else:
            raise ValueError(f"Message type {type_} is unknown")

        return self.dataclass(message_type=type_, message=message)


@dataclass
class PocketSphinxRequest:
    speech: bytearray


class PocketSphinxRequestMapper(FlatbufferObject):
    def __init__(self):
        super().__init__(PocketSphinxRequest, PocketSphinxRequestClass)

    # serialize
    def dataclass_to_flatbuffer(self, dataclass):
        speech_vector = self.build_vector('Speech', dataclass.speech)
        self.start()
        self['Speech'] = speech_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        bytes = bytearray()
        for i in range(flatbuffer.SpeechLength()):
            bytes.append(flatbuffer.Speech(i))
        return PocketSphinxRequest(bytes)


@dataclass
class Guess:
    phrase: str = ''
    confidence: float = 0


class GuessMapper(FlatbufferObject):
    def __init__(self, builder):
        super().__init__(Guess, GuessClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        phrase = self.build_string(dataclass.phrase)
        self.start()
        self['Confidence'] = dataclass.confidence
        self['Phrase'] = phrase
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        return Guess(flatbuffer.Phrase().decode("utf-8"), flatbuffer.Confidence())


@dataclass
class PocketSphinxResponse:
    guesses: List[Guess]


class PocketSphinxResponseMapper(FlatbufferObject):
    def __init__(self):
        super().__init__(PocketSphinxResponse, PocketSphinxResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        guess_vector = self.build_vector('Guesses', dataclass.guesses, GuessMapper)
        self.start()
        self['Guesses'] = guess_vector
        return self.end()

    def flatbuffer_to_dataclass(self, flatbuffer):
        guesses = []
        for i in range(0, flatbuffer.GuessesLength()):
            guess = flatbuffer.Guesses(i)
            guess_mapper = GuessMapper(self.builder)
            guesses.append(guess_mapper.flatbuffer_to_dataclass(guess))
        return PocketSphinxResponse(guesses)
