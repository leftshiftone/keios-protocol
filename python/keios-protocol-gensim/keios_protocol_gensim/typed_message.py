import abc

class TypedMessage(abc.ABC):
    @abc.abstractmethod
    def type(self):
        pass
