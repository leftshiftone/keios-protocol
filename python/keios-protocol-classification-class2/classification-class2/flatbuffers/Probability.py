# automatically generated by the FlatBuffers compiler, do not modify

# namespace: class2

import flatbuffers

class Probability(object):
    __slots__ = ['_tab']

    # Probability
    def Init(self, buf, pos):
        self._tab = flatbuffers.table.Table(buf, pos)

    # Probability
    def Probability1(self): return self._tab.Get(flatbuffers.number_types.Float32Flags, self._tab.Pos + flatbuffers.number_types.UOffsetTFlags.py_type(0))
    # Probability
    def Probability2(self): return self._tab.Get(flatbuffers.number_types.Float32Flags, self._tab.Pos + flatbuffers.number_types.UOffsetTFlags.py_type(4))

def CreateProbability(builder, probability1, probability2):
    builder.Prep(4, 8)
    builder.PrependFloat32(probability2)
    builder.PrependFloat32(probability1)
    return builder.Offset()