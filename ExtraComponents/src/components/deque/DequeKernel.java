package components.deque;

import components.standard.Standard;

/**
 * Double-ended queue component with primary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            Argument type of {@code DequeKernel} entries
 * @mathmodel <pre>
 * type DequeKernel is modeled by string of T
 * </pre>
 * @initially <pre>
 * ():
 *   ensures
 *   this = <>
 * </pre>
 */
public interface DequeKernel<T> extends Standard<Deque<T>>, Iterable<T> {

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();

    /**
     * Adds {@code x} to the front of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference x
     * @updates this
     * @ensures this = < x > * #this
     */
    void pushFront(T x);

    /**
     * Adds {@code x} to the back of {@code this}.
     *
     * @param x
     *            the entry to be added
     * @aliases reference x
     * @updates this
     * @ensures this = #this * < x >
     */
    void pushBack(T x);

    /**
     * Removes x from the front of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = < popFront > * this
     */
    T popFront();

    /**
     * Removes x from the back of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires this /= <>
     * @ensures #this = this * < popBack >
     */
    T popBack();

}
