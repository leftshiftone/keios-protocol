# automatically generated by the FlatBuffers compiler, do not modify

# namespace: pocketsphinx

import flatbuffers

class PocketsphinxRequest(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsPocketsphinxRequest(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = PocketsphinxRequest()
        x.Init(buf, n + offset)
        return x

    # PocketsphinxRequest
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # PocketsphinxRequest
    def Speech(self, j):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            a = self._tab.Vector(o)
            return self._tab.Get(flatbuffers.number_types.Uint8Flags, a + flatbuffers.number_types.UOffsetTFlags.py_type(j * 1))
        return 0

    # PocketsphinxRequest
    def SpeechAsNumpy(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.GetVectorAsNumpy(flatbuffers.number_types.Uint8Flags, o)
        return 0

    # PocketsphinxRequest
    def SpeechLength(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.VectorLen(o)
        return 0

def PocketsphinxRequestStart(builder): builder.StartObject(1)
def PocketsphinxRequestAddSpeech(builder, speech): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(speech), 0)
def PocketsphinxRequestStartSpeechVector(builder, numElems): return builder.StartVector(1, numElems, 1)
def PocketsphinxRequestEnd(builder): return builder.EndObject()
