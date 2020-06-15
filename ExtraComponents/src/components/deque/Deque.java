package components.deque;

/**
 * {@code DequeKernel} enhanced with secondary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Deque} entries
 */
public interface Deque<T> extends DequeKernel<T> {

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    void flip();

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}.
     * @aliases reference returned by front
     * @requires this /= <>
     * @ensures <pre>
     * < front > is prefix of this
     * </pre>
     */
    T front();

    /**
     * Reports the back of {@code this}.
     *
     * @return the back entry of {@code this}.
     * @aliases reference returned by back
     * @requires this /= <>
     * @ensures <pre>
     * < back > is suffix of this
     * </pre>
     */
    T back();

    /**
     * Replaces the front of {@code this} with {@code x}, and returns the old
     * front.
     *
     * @param x
     *            the new front entry
     * @return the old front entry
     * @aliases reference x
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * < replaceFront > is prefix of #this  and
     * this = < x > * #this[1, |#this|)
     * </pre>
     */
    T replaceFront(T x);

    /**
     * Replaces the back of {@code this} with {@code x}, and returns the old
     * back.
     *
     * @param x
     *            the new back entry
     * @return the old back entry
     * @aliases reference x
     * @updates this
     * @requires this /= <>
     * @ensures <pre>
     * < replaceBack > is suffix of #this  and
     * this = #this[0, |#this| - 1) * < x >
     * </pre>
     */
    T replaceBack(T x);

}
