package components.multiset;

import components.set.Set;

/**
 * {@code MultisetKernel} enhanced with secondary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Multiset} entries
 */
public interface Multiset<T> extends MultisetKernel<T> {

    /**
     * Adds to {@code this} all elements of {@code s}, clearing {@code s}.
     *
     * @param s
     *            the {@code Set} whose elements are to be added to {@code this}
     * @updates this
     * @clears s
     * @ensures <pre>
     * this = #this union #s
     * </pre>
     */
    void add(Set<T> s);

    /**
     * Adds to {@code this} all elements of {@code ms}, clearing {@code ms}.
     *
     * @param ms
     *            the {@code Multiset} whose elements are to be added to
     *            {@code this}
     * @updates this
     * @clears ms
     * @ensures <pre>
     * this = #this union #ms
     * </pre>
     */
    void add(Multiset<T> ms);

    /**
     * Removes from {@code this} all elements of {@code s} that are also in
     * {@code this}, leaving {@code s} unchanged, and returns the elements
     * actually removed. If {@code this} contains multiple instances of an item
     * in {@code s}, only one instance is removed.
     *
     * @param s
     *            the {@code Set} whose elements are to be removed from
     *            {@code this}
     * @return the {@code Set} whose elements actually were removed from
     *         {@code this}
     * @updates this
     * @ensures <pre>
     * this = #this \ s  and
     * remove = #this intersection s
     * </pre>
     */
    Set<T> remove(Set<T> s);

    /**
     * Removes from {@code this} all elements of {@code ms} that are also in
     * {@code this}, leaving {@code ms} unchanged, and returns the elements
     * actually removed.
     *
     * @param ms
     *            the {@code Multiset} whose elements are to be removed from
     *            {@code this}
     * @return the {@code Multiset} whose elements actually were removed from
     *         {@code this}
     * @updates this
     * @ensures <pre>
     * this = #this \ ms  and
     * remove = #this intersection ms
     * </pre>
     */
    Multiset<T> remove(Multiset<T> ms);

    /**
     * Reports whether {@code this} is a subset of {@code s}.
     *
     * @param s
     *            the second set
     * @return whether {@code this} is a subset of {@code s}
     * @ensures isSubset = this is subset of s
     */
    boolean isSubset(Set<T> s);

    /**
     * Reports whether {@code this} is a subset of {@code ms}.
     *
     * @param ms
     *            the second multiset
     * @return whether {@code this} is a subset of {@code ms}
     * @ensures isSubset = this is subset of ms
     */
    boolean isSubset(Multiset<T> ms);
}
