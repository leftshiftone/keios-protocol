# automatically generated by the FlatBuffers compiler, do not modify

# namespace: spacy

import flatbuffers

class SpacyBatchResponse(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsSpacyBatchResponse(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = SpacyBatchResponse()
        x.Init(buf, n + offset)
        return x

    # SpacyBatchResponse
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # SpacyBatchResponse
    def Responses(self, j):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            x = self._tab.Vector(o)
            x += flatbuffers.number_types.UOffsetTFlags.py_type(j) * 4
            x = self._tab.Indirect(x)
            from .SpacyResponse import SpacyResponse
            obj = SpacyResponse()
            obj.Init(self._tab.Bytes, x)
            return obj
        return None

    # SpacyBatchResponse
    def ResponsesLength(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.VectorLen(o)
        return 0

def SpacyBatchResponseStart(builder): builder.StartObject(1)
def SpacyBatchResponseAddResponses(builder, responses): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(responses), 0)
def SpacyBatchResponseStartResponsesVector(builder, numElems): return builder.StartVector(4, numElems, 4)
def SpacyBatchResponseEnd(builder): return builder.EndObject()
