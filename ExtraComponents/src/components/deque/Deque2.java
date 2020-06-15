package components.deque;

import java.util.Iterator;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Deque} represented as a {@code Sequence} with implementations of
 * primary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Deque} entries
 * @correspondence <pre>
 * this = $this.entries
 * </pre>
 */
public class Deque2<T> extends DequeSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Entries of {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Deque2() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Deque<T> newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void transferFrom(Deque<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Deque2<?> : ""
                + "Violation of: source is of dynamic type Queue1L<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Queue1L<?>,
         * and the ? must be T or the call would not have compiled.
         */
        Deque2<T> localSource = (Deque2<T>) source;
        this.entries = localSource.entries;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final int length() {
        return this.entries.length();
    }

    @Override
    public final void pushFront(T x) {
        assert x != null : "Violation of: x is not null";
        this.entries.add(0, x);
    }

    @Override
    public final void pushBack(T x) {
        assert x != null : "Violation of: x is not null";
        this.entries.add(this.entries.length(), x);
    }

    @Override
    public final T popFront() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.entries.remove(0);
    }

    @Override
    public final T popBack() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.entries.remove(this.entries.length() - 1);
    }

    @Override
    public final Iterator<T> iterator() {
        return this.entries.iterator();
    }

    /*
     * Other methods (overridden for performance reasons) ---------------------
     */

    @Override
    public final T front() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.entries.entry(0);
    }

    @Override
    public final T back() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.entries.entry(this.entries.length() - 1);
    }

    @Override
    public final T replaceFront(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";
        assert x != null : "Violation of: x is not null";
        return this.entries.replaceEntry(0, x);
    }

    @Override
    public final T replaceBack(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";
        assert x != null : "Violation of: x is not null";
        return this.entries.replaceEntry(this.entries.length() - 1, x);
    }

}
