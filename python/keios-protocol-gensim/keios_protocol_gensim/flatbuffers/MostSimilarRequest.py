# automatically generated by the FlatBuffers compiler, do not modify

# namespace: gensim

import flatbuffers

class MostSimilarRequest(object):
    __slots__ = ['_tab']

    @classmethod
    def GetRootAsMostSimilarRequest(cls, buf, offset):
        n = flatbuffers.encode.Get(flatbuffers.packer.uoffset, buf, offset)
        x = MostSimilarRequest()
        x.Init(buf, n + offset)
        return x

    # MostSimilarRequest
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # MostSimilarRequest
    def Text(self):
        o = flatbuffers.number_types.UOffsetTFlags.py_type(self._tab.Offset(4))
        if o != 0:
            return self._tab.String(o + self._tab.Pos)
        return None

def MostSimilarRequestStart(builder): builder.StartObject(1)
def MostSimilarRequestAddText(builder, text): builder.PrependUOffsetTRelativeSlot(0, flatbuffers.number_types.UOffsetTFlags.py_type(text), 0)
def MostSimilarRequestEnd(builder): return builder.EndObject()
