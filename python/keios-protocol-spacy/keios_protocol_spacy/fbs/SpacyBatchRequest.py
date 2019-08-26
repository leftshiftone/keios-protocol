# automatically generated by the FlatBuffers compiler, do not modify

# namespace: spacy

import flatbuffers

class SpacyBatchRequest(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsSpacyBatchRequest(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = SpacyBatchRequest()
        x.Init(buf, n + offset)
        return x

    # SpacyBatchRequest
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # SpacyBatchRequest
    def Requests(self, j):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            x = self._tab.Vector(o)
            x += flatbuffers.number_types.UOffsetTFlags.py_type(j) * 4
            x = self._tab.Indirect(x)
            from .SpacyRequest import SpacyRequest
            obj = SpacyRequest()
            obj.Init(self._tab.Bytes, x)
            return obj
        return None

    # SpacyBatchRequest
    def RequestsLength(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.VectorLen(o)
        return 0

def SpacyBatchRequestStart(builder): builder.StartObject(1)
def SpacyBatchRequestAddRequests(builder, requests): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(requests), 0)
def SpacyBatchRequestStartRequestsVector(builder, numElems): return builder.StartVector(4, numElems, 4)
def SpacyBatchRequestEnd(builder): return builder.EndObject()