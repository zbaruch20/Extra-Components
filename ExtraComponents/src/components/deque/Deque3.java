package components.deque;

import java.util.Iterator;

import components.stack.Stack;
import components.stack.Stack1L;

/**
 * {@code Deque} represented as a pair of {@code Stack}s, with implementations
 * of primary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            Argument type of {@code Deque} entries
 *
 * @correspondence this = $this.left + rev($this.right)
 */
public class Deque3<T> extends DequeSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Left stack.
     */
    private Stack<T> left;

    /**
     * Right stack.
     */
    private Stack<T> right;

    /**
     * Moves the bottom element of {@code st1} to the bottom element of
     * {@code st2}, keeping the former fixed concatenated with the latter
     * reverse, and resulting in length of the former decreased by one.
     *
     * @param <T>
     *            type of {@code Stack} entries
     * @param s1
     *            the {@code Stack} to shift an element from
     * @param s2
     *            the {@code Stack} to shift an element to
     * @updates leftStack, rightStack
     * @ensures <pre>
     * s1 * rev(s2) = #s1 * rev(#s2)  and
     * |s1| = |#s1| - 1
     * </pre>
     */
    private static <T> void shift(Stack<T> s1, Stack<T> s2) {
        assert s1 != null : "Violation of: leftStack is not null";
        assert s2 != null : "Violation of: rightStack is not null";

        //Flip stacks, move an element from st1 to st2, flip back
        s1.flip();
        s2.flip();

        s2.push(s1.pop());

        s1.flip();
        s2.flip();
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.left = new Stack1L<>();
        this.right = new Stack1L<>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Deque3() {
        this.createNewRep();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

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
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Deque<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Deque3<?> : ""
                + "Violation of: source is of dynamic type Sequence3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Deque3<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Deque3<T> localSource = (Deque3<T>) source;
        this.left = localSource.left;
        this.right = localSource.right;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final int length() {
        return this.left.length() + this.right.length();
    }

    @Override
    public final void pushFront(T x) {
        //Push to left stack
        this.left.push(x);
    }

    @Override
    public final void pushBack(T x) {
        //Push to right stack
        this.right.push(x);
    }

    @Override
    public final T popFront() {
        assert this.length() > 0 : "Violation of: this /= <>";

        //Shift to left if left is currently empty
        if (this.left.length() == 0) {
            shift(this.right, this.left);
        }

        return this.left.pop();
    }

    @Override
    public final T popBack() {
        assert this.length() > 0 : "Violation of: this /= <>";

        //Shift to right if right is currently empty
        if (this.right.length() == 0) {
            shift(this.left, this.right);
        }

        return this.right.pop();
    }

    @Override
    public final Iterator<T> iterator() {
        //Shift all elements to left and return iterator of left
        while (this.right.length() > 0) {
            shift(this.right, this.left);
        }
        return this.left.iterator();
    }

}
