# automatically generated by the FlatBuffers compiler, do not modify

# namespace: gensim

import flatbuffers

class MostSimilarResponse(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsMostSimilarResponse(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = MostSimilarResponse()
        x.Init(buf, n + offset)
        return x

    # MostSimilarResponse
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # MostSimilarResponse
    def Similarities(self, j):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            x = self._tab.Vector(o)
            x += flatbuffers.number_types.UOffsetTFlags.py_type(j) * 4
            x = self._tab.Indirect(x)
            from .MostSimilarity import MostSimilarity
            obj = MostSimilarity()
            obj.Init(self._tab.Bytes, x)
            return obj
        return None

    # MostSimilarResponse
    def SimilaritiesLength(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.VectorLen(o)
        return 0

def MostSimilarResponseStart(builder): builder.StartObject(1)
def MostSimilarResponseAddSimilarities(builder, similarities): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(similarities), 0)
def MostSimilarResponseStartSimilaritiesVector(builder, numElems): return builder.StartVector(4, numElems, 4)
def MostSimilarResponseEnd(builder): return builder.EndObject()
