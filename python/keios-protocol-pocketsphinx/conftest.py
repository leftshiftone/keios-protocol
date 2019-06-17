import os
import pytest
import numpy as np
from keios_protocol_pocketsphinx import GuessData

@pytest.fixture
def supply_goforward_file():
        test_data = os.path.join(os.path.dirname(__file__), 'tests/goforward.raw')
        return open(test_data, 'rb')

@pytest.fixture
def supply_nparray_serialized():
    expected_buffer = b'\x0c\x00\x00\x00\x00\x00\x06\x00\x08\x00\x04\x00\x06\x00\x00\x00\x04\x00\x00\x00\x08\x00\x00\x00\xecQ_\x1ew\xf8\xd7?'
    np.random.seed(42)
    rand_arr = np.random.rand(1)
    return [rand_arr, expected_buffer]

@pytest.fixture
def supply_guesses():
        guess_arr = [('go forward ten meters', -28034),
                ('go for word ten meters', -28570),
                ('go forward and majors', -28670),
                ('go forward and meters', -28681),
                ('go forward and readers', -28685),
                ('go forward ten readers', -28688),
                ('go forward ten leaders', -28695),
                ('go forward can meters', -28695),
                ('go forward and leaders', -28706),
                ('go for work ten meters', -28722)]
        return [GuessData(phrase=x[0], confidence=x[1]) for x in guess_arr]