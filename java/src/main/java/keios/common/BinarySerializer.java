package keios.common;

/**
 * @author benjamin.krenn@leftshift.one
 * @since 1.0.0
 */
public interface BinarySerializer<T> {
    byte[] serialize(T entity);
}
