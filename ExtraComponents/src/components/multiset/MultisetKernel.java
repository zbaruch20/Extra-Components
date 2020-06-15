package components.multiset;

import components.standard.Standard;

/**
 * Multiset kernel component with primary methods. (Note: by package-wide
 * convention, all references are non-null.)
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Multiset} entries
 * @mathmodel <pre>
 * type MultisetKernel is modeled by finite multiset of T
 * </pre>
 * @initially <pre>
 * ():
 *  ensures
 *   this = {}
 * (Set{@literal <T>} s):
 *  aliases
 *   [all entries of s]
 *  ensures
 *   this = s
 * </pre>
 * @iterator <pre>
 * entries(~this.seen * ~this.unseen) = this  and
 * |~this.seen * ~this.unseen| = |this|
 * </pre>
 */
public interface MultisetKernel<T> extends Iterable<T>, Standard<Multiset<T>> {

    /**
     * Adds {@code x} to {@code this}.
     *
     * @param x
     *            the element to be added
     * @aliases reference x
     * @updates this
     * @ensures <pre>
     * this = #this union {x}
     * </pre>
     */
    void add(T x);

    /**
     * Removes one instance of {@code x} from {@code this}, and returns it.
     *
     * @param x
     *            the element to be removed
     * @return the element removed
     * @updates this
     * @requires x is in this
     * @ensures <pre>
     * this = #this \ {x}
     * </pre>
     */
    T remove(T x);

    /**
     * Removes all instances of {@code x} from {@code this}, and returns one
     * instance of it.
     *
     * @param x
     *            the element(s) to be removed
     * @return one instance of the element(s) removed
     * @updates this
     * @requires x is in this
     * @ensures <pre>
     * this is subset of #this  and
     * x is not in this  and
     * for all y: T where
     *   (y is in #this)
     *  (if y != x then y is in this)
     * </pre>
     */
    T removeAll(T x);

    /**
     * Removes and returns an arbitrary element from {@code this}.
     *
     * @return the element removed from {@code this}
     * @updates this
     * @requires |this| > 0
     * @ensures <pre>
     * removeAny is not in #this  and
     * this = #this \ {removeAny}
     * </pre>
     */
    T removeAny();

    /**
     * Reports the number of occurrences (count) of {@code x} in {@code this}.
     *
     * @param x
     *            the element to get count of
     * @return the count of {@code x} in {@code this}
     * @ensures count = [the number of occurrences of x in this]
     */
    int count(T x);

    /**
     * Reports whether {@code x} is in {@code this}.
     *
     * @param x
     *            the element to be checked
     * @return true iff element is in {@code this}
     * @ensures contains = (x is in this)
     */
    boolean contains(T x);

    /**
     * Reports size (cardinality) of {@code this}.
     *
     * @return the number of elements in {@code this}
     * @ensures size = |this|
     */
    int size();
}
