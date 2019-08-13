package keios.common;

import java.util.Objects;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public class AbstractMessageEntity<T extends Message> {
    private final T message;

    public AbstractMessageEntity(T message) {
        Objects.requireNonNull(message, "message can not be null");
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMessageEntity<?> that = (AbstractMessageEntity<?>) o;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
