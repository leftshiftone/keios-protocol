from dataclasses import dataclass
from types import MethodType
from typing import List

import numpy as np
import flatbuffers

#   _____                            _   
#  |  __ \                          | |  
#  | |__) |___  __ _ _   _  ___  ___| |_ 
#  |  _  // _ \/ _` | | | |/ _ \/ __| __|
#  | | \ \  __/ (_| | |_| |  __/\__ \ |_ 
#  |_|  \_\___|\__, |\__,_|\___||___/\__|
#                 | |                    
#                 |_|                    

import keios.atlas.pocketsphinx.PocketsphinxRequest as PReq

@dataclass
class PocketsphinxRequestData:
    speech: bytearray

PReq.Data = PocketsphinxRequestData

def req_serialize(request: PReq.Data) -> bytearray:
    builder = flatbuffers.Builder(0)

    s_len = len(request.speech)

    # speech vector
    PReq.PocketsphinxRequestStartSpeechVector(builder, s_len)
    for b in reversed(request.speech):
        builder.PrependByte(b)
    speech_vector = builder.EndVector(s_len)
    
    PReq.PocketsphinxRequestStart(builder)
    PReq.PocketsphinxRequestAddSpeech(builder, speech_vector)
    result = PReq.PocketsphinxRequestEnd(builder)
    builder.Finish(result)
    
    return builder.Output()
    
def req_deserialize(bb: bytearray) -> PReq.Data:
    request = PReq.PocketsphinxRequest.GetRootAsPocketsphinxRequest(bb, 0)
    return PocketsphinxRequestData(speech=request.SpeechAsNumpy().tobytes())

PReq.serialize = req_serialize
PReq.deserialize = req_deserialize

#   _____                                      
#  |  __ \                                     
#  | |__) |___  ___ _ __   ___  _ __  ___  ___ 
#  |  _  // _ \/ __| '_ \ / _ \| '_ \/ __|/ _ \
#  | | \ \  __/\__ \ |_) | (_) | | | \__ \  __/
#  |_|  \_\___||___/ .__/ \___/|_| |_|___/\___|
#                  | |                         
#                  |_|                         

import keios.atlas.pocketsphinx.PocketsphinxResponse as PRes
import keios.atlas.pocketsphinx.Guess as PGuess

@dataclass
class Guess:
    confidence: float
    phrase: str

@dataclass
class PocketsphinxResponseData:
    guesses: List[Guess]

PRes.Data = PocketsphinxResponseData

def serialize_guess(guess: Guess):
    builder = flatbuffers.Builder(0)

    PGuess.GuessStart(builder)

    PGuess.GuessAddConfidence(builder, guess.confidence)
    PGuess.GuessAddPhrase(builder, builder.CreateString(guess.phrase))

    result = PGuess.GuessEnd(builder)

    builder.Finish(result)
    return builder.Output()

def deserialize_guess(bb: bytearray) -> Guess:
    guess = PGuess.Guess.GetRootAsGuess(bb, 0)
    return Guess(
            confidence=guess.Confidence(),
            phrase=guess.Phrase()
        )


def res_serialize(response: PRes.Data) -> bytearray:
    builder = flatbuffers.Builder(0)
    g_len = len(response.guesses)

    PRes.PocketsphinxResponseStartGuessesVector(builder, g_len) 
    ser_guesses = [serialize_guess(g) for g in reversed(request.guesses)]
    for b in reversed(ser_guesses):
        builder.PrependByte(b)
    guess_vector = builder.EndVector(g_len)
    
    PRes.PocketsphinxResponseStart(builder)
    PRes.PocketsphinxResponseAddGuesses(builder, guess_vector)
    result = PRes.PocketsphinxResponseEnd(builder)

    builder.Finish(result)
    return builder.Output()
    
def res_deserialize(bb: bytearray) -> PRes.Data:
    response = PRes.PocketsphinxResponse.GetRootAsPocketsphinxResponse(bb, 0)
    g_len = response.GuessesLength()

    def get_guess(i):
        deserialize_guess(response.Guesses(i))

    guesses = [get_guess(i) for i in range(g_len)]
    
    return PocketsphinxResponseData(guesses=guesses)

PRes.serialize = res_serialize
PRes.deserialize = res_deserialize