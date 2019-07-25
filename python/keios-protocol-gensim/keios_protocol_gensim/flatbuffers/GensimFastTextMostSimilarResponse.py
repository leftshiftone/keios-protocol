# automatically generated by the FlatBuffers compiler, do not modify

# namespace: gensim

import flatbuffers

class GensimFastTextMostSimilarResponse(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsGensimFastTextMostSimilarResponse(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = GensimFastTextMostSimilarResponse()
        x.Init(buf, n + offset)
        return x

    # GensimFastTextMostSimilarResponse
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # GensimFastTextMostSimilarResponse
    def Responses(self, j):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            x = self._tab.Vector(o)
            x += flatbuffers.number_types.UOffsetTFlags.py_type(j) * 4
            x = self._tab.Indirect(x)
            from .MostSimilarResponse import MostSimilarResponse
            obj = MostSimilarResponse()
            obj.Init(self._tab.Bytes, x)
            return obj
        return None

    # GensimFastTextMostSimilarResponse
    def ResponsesLength(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.VectorLen(o)
        return 0

def GensimFastTextMostSimilarResponseStart(builder): builder.StartObject(1)
def GensimFastTextMostSimilarResponseAddResponses(builder, responses): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(responses), 0)
def GensimFastTextMostSimilarResponseStartResponsesVector(builder, numElems): return builder.StartVector(4, numElems, 4)
def GensimFastTextMostSimilarResponseEnd(builder): return builder.EndObject()
