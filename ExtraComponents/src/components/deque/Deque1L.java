package components.deque;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@code Deque} represented as a {@code java.util.List} with implementations of
 * primary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Deque} entries
 * @correspondence <pre>
 * this = [value of $this.rep based on List's "proper sequence"]
 * </pre>
 */
public class Deque1L<T> extends DequeSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private List<T> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new LinkedList<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Deque1L() {
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
        assert source instanceof Deque1L<?> : ""
                + "Violation of: source is of dynamic type Queue1L<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Queue1L<?>,
         * and the ? must be T or the call would not have compiled.
         */
        Deque1L<T> localSource = (Deque1L<T>) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final int length() {
        return this.rep.size();
    }

    @Override
    public final void pushFront(T x) {
        assert x != null : "Violation of: x is not null";
        this.rep.add(0, x);
    }

    @Override
    public final void pushBack(T x) {
        assert x != null : "Violation of: x is not null";
        this.rep.add(x);
    }

    @Override
    public final T popFront() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.rep.remove(0);
    }

    @Override
    public final T popBack() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.rep.remove(this.rep.size() - 1);
    }

    @Override
    public final Iterator<T> iterator() {
        return new Deque1LIterator();
    }

    /**
     * Implementation of {@code Iterator} for {@code Deque1L}.
     */
    public class Deque1LIterator implements Iterator<T> {

        /**
         * Representation iterator.
         */
        private final Iterator<T> iterator;

        /**
         * No-argument constructor.
         */
        public Deque1LIterator() {
            this.iterator = Deque1L.this.rep.iterator();
        }

        @Override
        public final boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override
        public final T next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            return this.iterator.next();
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

    /*
     * Other methods (overridden for performance reasons) ---------------------
     */

    @Override
    public final T front() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.rep.get(0);
    }

    @Override
    public final T back() {
        assert this.length() > 0 : "Violation of: this /= <>";
        return this.rep.get(this.rep.size() - 1);
    }

    @Override
    public final T replaceFront(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";
        assert x != null : "Violation of: x is not null";
        return this.rep.set(0, x);
    }

    @Override
    public final T replaceBack(T x) {
        assert this.length() > 0 : "Violation of: this /= <>";
        assert x != null : "Violation of: x is not null";
        return this.rep.set(this.rep.size() - 1, x);
    }

}
