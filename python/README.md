# python_keios_protocol

This contains python objects to deal with flatbuffers serialization/deserialization accross different keios instances.

All of these classes extend ``FlatbufferObject`` which makes dealing with flatbuffer slightly easier.

## Getting Started

````
cd python
poetry env use 3.7
poetry install
poetry run pytest
````

## Using a Protocol

````python
from python_keios_protocol.some_protocol.some_entity import SomeProtocolEntity

entity = SomeProtocolEntity()

# create objects to hold data in python
data = entity.dataclass(some_attribute=...)

# serialize data (result is a bytearray)
entity.serialize(data)

# deserialize data (result is an object of type entity.dataclass)
date_from_elsewhere = entity.deserialize(bytearray_received_from_somewhere_else)

````

## Creating a new Protocol
This section will tell you how to create a protocol for a new keios instance.
When being done with creating a new protocol, you should arrive at the following project structure.
````
python_keios_protocol/
├── ... other protocols ...
├── my_new_protocol/
│   ├── fbs/
|   |   └── ... generated flatbuffer objects ...
│   ├── some_datatype.py
│   └── some_other_datatype.py
└── FlatbufferObject.py
````

### Installing Flatbuffers

#### Ubuntu
````bash
sudo add-apt-repository ppa:dluxen/flatbuffers
sudo apt-get update
sudo apt-get install flatbuffers
````
See package on [launchpad.net](https://launchpad.net/~dluxen/+archive/ubuntu/flatbuffers)

#### Pacman
````bash
sudo pacman -S flatbuffers
````

### Generating Flatbuffer Objects
Refer to the flatbuffer documention on [writing a schema](https://google.github.io/flatbuffers/flatbuffers_guide_writing_schema.html).
When a ``.fbs`` schema is done, it can be compiled to python files using ``flac your_flatbuffer.fbs --python``.
Move the generated files to ``python_keios_protocol/your_protocol/fbs``.

### Extending FlatbufferObject
Now it is time to use the ``FlatbufferObject`` helper to create a class that lets users easily serialize and deserialize flatbuffer objects.

#### Imports
We only need to import our previously generated flattbuffer class
````python
from .fbs import SomeGeneratedFlatbuffer as MyFlatbuffer
````

#### Dataclass
We create a python dataclass to hold our data in an easily accessible format.
````python
from typing import List

@dataclass
class MyDataclass:
    A: str
    B: List[int]
````

#### Entity
The heart of your protocol is your implementation of ``FlatbufferObject`` with its methods
``serialize(item)`` that takes an item of your dataclass and returns a bytearray, and ``deserialize(buffer)`` which will take a bytearray and return an item of your dataclass.

For these two methods to work, we need to implement ``dataclass_to_flatbuffer(dataclass)`` and ``flatbuffer_to_dataclass(flatbuffer)``.

Both of the following two examples are valid. The first one uses ``FlatbufferObject`` helper functions while the other one accesses ``self.fbs`` and ``self.builder`` directly.

##### With Helpers

````python
import numpy as np

class MyEntity(FlatbufferObject):

    def __init__(self):
        super().__init__(MyDataclass, MyFlatbuffer)

    def dataclass_to_flatbuffer(self, dataclass):
        a = self.build_string(dataclass.A)
        b_vector = self.build_vector(dataclass.B)
        self.start()
        self['A'] = a
        self['B'] = b_vector
        return self.end()
        
    def flatbuffer_to_dataclass(self, flatbuffer): # the flatbuffer argument is supplied for legacy use (see "Without Helpers" example below)
        self['A']
        return MyDataclass(
            some_attribute=self['A']
            some_list_attribute=self['B'].tolist() # returns a numpy array
        )
````

##### Without Helpers (native flatbuffer methods)

````python
import numpy as np

class MyEntity(FlatbufferObject):

    def __init__(self):
        super().__init__(MyDataclass, MyFlatbuffer)

    def dataclass_to_flatbuffer(self, dataclass):
        """
        Take the attributes of the dataclass and use 
        self.fbs and self.builder to add them onto the flatbuffer.
        Return the result of SomeGeneratedFlatbufferEnd(self.builder)
        and FlatbufferObject will take care of the rest.
        """
        a = self.builder.CreateString(dataclass.A)
        s_len = len(dataclass.B)
        """
        Refer to https://google.github.io/flatbuffers/flatbuffers_guide_tutorial.html
        (select python) for the following code.
        """
        self.fbs.SomeGeneratedFlatbufferStartBVector(self.builder, s_len)
        for b in reversed(dataclass.B):
            self.builder.PrependByte(b)
        b_vector = self.builder.EndVector(s_len)
        self.fbs.SomeGeneratedFlatbufferStart(self.builder)
        self.fbs.SomeGeneratedFlatbufferAddA(self.builder, a)
        self.fbs.SomeGeneratedFlatbufferAddB(self.builder, b_vector)
        """
        You can omit builder.Finish and builder.Output
        """
        return self.fbs.SomeGeneratedFlatbufferEnd(self.builder)
        
    def flatbuffer_to_dataclass(self, flatbuffer):
        """
        This is a lot more straightforward than serializing.
        """

        return MyDataclass(
            some_attribute=flatbuffer.SomeAttribute().tolist()
            some_list_attribute=flatbuffer.SomeListAttributeAsNumpy().tolist()
        )

        """
        Again, you can omit GetRootAs... because the flatbuffer passed 
        is already parsed into a SomeGeneratedFlatbuffer object by FlatbufferObject
        """
````