# automatically generated by the FlatBuffers compiler, do not modify

# namespace: gensim

import flatbuffers

class EmbeddingRequest(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsEmbeddingRequest(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = EmbeddingRequest()
        x.Init(buf, n + offset)
        return x

    # EmbeddingRequest
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # EmbeddingRequest
    def Text(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.String(o + self._tab.Pos)
        return None

def EmbeddingRequestStart(builder): builder.StartObject(1)
def EmbeddingRequestAddText(builder, text): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(text), 0)
def EmbeddingRequestEnd(builder): return builder.EndObject()