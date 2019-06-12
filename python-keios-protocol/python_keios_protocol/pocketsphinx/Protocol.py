from typing import List, NamedTuple
from dataclasses import dataclass
import numpy as np
import flatbuffers
from ..FlatbufferObject import FlatbufferObject
from .fbs import PocketsphinxRequest as PocketsphinxRequestClass
from .fbs import PocketsphinxResponse as PocketsphinxResponseClass
from .fbs import Guess as GuessClass

@dataclass
class PocketsphinxRequestData:
    speech: bytearray

class PocketsphinxRequestEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(PocketsphinxRequestData, PocketsphinxRequestClass)

    def dataclass_to_flatbuffer(self, dataclass):
        s_len = len(dataclass.speech)
        self.fbs.PocketsphinxRequestStartSpeechVector(self.builder, s_len)
        for b in reversed(dataclass.speech):
            self.builder.PrependByte(b)
        speech_vector = self.builder.EndVector(s_len)
        self.fbs.PocketsphinxRequestStart(self.builder)
        self.fbs.PocketsphinxRequestAddSpeech(self.builder, speech_vector)
        return self.fbs.PocketsphinxRequestEnd(self.builder)
        
    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(speech=flatbuffer.SpeechAsNumpy().tobytes())

class GuessData(NamedTuple):
    phrase: str = ''
    confidence: float = 0

@dataclass
class PocketsphinxResponseData:
    guesses: List[GuessData]

class GuessEntity(FlatbufferObject):
    def __init__(self, builder=None):
        if builder is None:
            super().__init__(GuessData, GuessClass)
        else:
            super().__init__(GuessData, GuessClass, builder)

    def dataclass_to_flatbuffer(self, dataclass):
        phrase = self.builder.CreateString(dataclass[0])
        self.fbs.GuessStart(self.builder)
        self.fbs.GuessAddConfidence(self.builder, dataclass[1])
        self.fbs.GuessAddPhrase(self.builder, phrase)
        return self.fbs.GuessEnd(self.builder)
        
    def flatbuffer_to_dataclass(self, flatbuffer):
        return self.dataclass(
            confidence=flatbuffer.Confidence(),
            phrase=flatbuffer.Phrase().decode("utf-8")
        )

class PocketsphinxResponseEntity(FlatbufferObject):
    def __init__(self):
        super().__init__(PocketsphinxResponseData, PocketsphinxResponseClass)

    def dataclass_to_flatbuffer(self, dataclass):
        g_len = len(dataclass.guesses)
        guess = GuessEntity(self.builder)
        ser_guesses = [guess.dataclass_to_flatbuffer(g) for g in reversed(dataclass.guesses)]
        self.fbs.PocketsphinxResponseStartGuessesVector(self.builder, g_len)
        for b in ser_guesses:
            self.builder.PrependUOffsetTRelative(b)
        guess_vector = self.builder.EndVector(g_len)
        self.fbs.PocketsphinxResponseStart(self.builder)
        self.fbs.PocketsphinxResponseAddGuesses(self.builder, guess_vector)
        return self.fbs.PocketsphinxResponseEnd(self.builder)
        
    def flatbuffer_to_dataclass(self, flatbuffer):
        g_len = flatbuffer.GuessesLength()
        guess = GuessEntity()
        guesses = [guess.flatbuffer_to_dataclass(flatbuffer.Guesses(i)) for i in range(g_len)]
        return self.dataclass(guesses=guesses)