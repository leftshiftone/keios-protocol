package keios.common;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
@FunctionalInterface
public interface BinarySerializer<T> {
    byte[] serialize(T entity);
}
