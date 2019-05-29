package keios.common;

/**
 * @author benjamin.krenn@leftshift.one - 5/29/19.
 * @since 0.1.0
 */
public interface EntityMapper<I,O> {
    O from(I input);
}
