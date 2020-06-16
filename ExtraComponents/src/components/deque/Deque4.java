package components.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@code Deque} represented as a doubly linked list, done "bare-handed", with
 * implementations of primary methods.
 *
 * <p>
 * Execution-time performance of all methods implemented in this class is O(1).
 * </p>
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            Argument type of {@code Deque} entries
 *
 * @convention <pre>
 * $this.length >= 0  and
 * [$this.preFront is not null]  and
 * [$this.postBack is not null]  and
 * [$this.preFront points to the first node of a singly linked list
 * containing $this.length + 2 nodes]  and
 * [$this.postBack points to the last node in that singly linked list]  and
 * [$this.postBack.next is null]
 * </pre>
 *
 * @correspondence <pre>
 * this = [data in nodes starting at $this.preFront.next and
 *  running through $this.postBack.previous]
 * </pre>
 */
public class Deque4<T> extends DequeSecondary<T> {

    /*
     * Private members ----------------------------------------
     */

    /**
     * Node class for singly linked list nodes.
     */
    private final class Node {

        /**
         * Data in node.
         */
        private T data;

        /**
         * Next node in doubly linked list, or null.
         */
        private Node next;

        /**
         * Previous node in doubly linked list, or null.
         */
        private Node previous;

    }

    /**
     * "Smart node" before front node of doubly linked list.
     */
    private Node preFront;

    /**
     * "Smart node" after back node of doubly linked list.
     */
    private Node postBack;

    /**
     * Two less than number of nodes in double linked list, i.e., length =
     * |this|.
     */
    private int length;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.preFront = new Node();
        this.postBack = new Node();
        this.preFront.next = this.postBack;
        this.postBack.previous = this.preFront;
        this.length = 0;
    }

    /*
     * Constructors ------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Deque4() {
        this.createNewRep();
    }

    /*
     * Standard methods --------------------------------------------------
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
        assert source instanceof Deque4<?> : ""
                + "Violation of: source is of dynamic type Deque4<?>";

        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Deque4<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Deque4<T> localsource = (Deque4<T>) source;
        this.preFront = localsource.preFront;
        this.postBack = localsource.postBack;
        this.length = localsource.length;
        localsource.createNewRep();
    }

    /*
     * Kernel methods --------------------------------------------------------
     */

    @Override
    public final int length() {
        return this.length;
    }

    @Override
    public final void pushFront(T x) {
        assert x != null : "Violation of: x is not null";

        //Make new preFront, modify current preFront to be new front
        Node newPreFront = new Node();
        this.preFront.data = x;

        //Change pointers
        newPreFront.next = this.preFront;
        this.preFront.previous = newPreFront;
        this.preFront = newPreFront;

        this.length++;
    }

    @Override
    public final void pushBack(T x) {
        assert x != null : "Violation of: x is not null";

        //Make new postBack, modify current postBack to be new back
        Node newPostBack = new Node();
        this.postBack.data = x;

        //Change pointers
        newPostBack.previous = this.postBack;
        this.postBack.next = newPostBack;
        this.postBack = newPostBack;

        this.length++;
    }

    @Override
    public final T popFront() {
        assert this.length > 0 : "Violation of: this /= <>";

        //Get data of next node and make that preFront
        Node newPreFront = this.preFront.next;
        T popped = newPreFront.data;
        this.preFront = newPreFront;

        this.length--;
        return popped;
    }

    @Override
    public final T popBack() {
        assert this.length > 0 : "Violation of: this /= <>";

        //Get data of previous node and make that postBack
        Node newPostBack = this.postBack.previous;
        T popped = newPostBack.data;
        this.postBack = newPostBack;

        this.length--;
        return popped;
    }

    @Override
    public final Iterator<T> iterator() {
        return new Deque4Iterator();
    }

    /**
     * Implementation of {@code Iterator} interface for {@code Deque4}.
     */
    private final class Deque4Iterator implements Iterator<T> {

        /**
         * Current node in the linked list.
         */
        private Node current;

        /**
         * No-argument constructor.
         */
        private Deque4Iterator() {
            this.current = Deque4.this.preFront.next;
        }

        @Override
        public boolean hasNext() {
            return this.current.next != Deque4.this.postBack;
        }

        @Override
        public T next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            T x = this.current.data;
            this.current = this.current.next;
            return x;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }
    }

}
