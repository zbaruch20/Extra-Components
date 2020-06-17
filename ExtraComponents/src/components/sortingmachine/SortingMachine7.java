package components.sortingmachine;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.Iterator;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code SortingMachine} represented as a {@code Sequence} (using an embedding
 * of selection sort), with implementations of primary methods.
 *
 * @param <T>
 *            type of {@code SortingMachine} entries
 * @mathdefinitions <pre>
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 * </pre>
 * @convention <pre>
 * IS_TOTAL_PREORDER([relation computed by $this.machineOrder.compare method])
 * </pre>
 * @correspondence <pre>
 * this =
 *   ($this.insertionMode, $this.machineOrder, multiset_entries($this.entries))
 * </pre>
 *
 * @author Zach Baruch
 */
public class SortingMachine7<T> extends SortingMachineSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Insertion mode.
     */
    private boolean insertionMode;

    /**
     * Order.
     */
    private Comparator<T> machineOrder;

    /**
     * Entries.
     */
    private Sequence<T> entries;

    /**
     * Removes and returns the minimum value from {@code s} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param <T>
     *            type of {@code Sequence} entries
     * @param s
     *            the {@code Sequence} to obtain minimum value from
     * @param order
     *            ordering by which to determine minimum value
     * @return the minimum value from {@code s} according to the ordering
     *         provided by the {@code compare} method from {@code order}
     * @updates s
     * @requires <pre>
     * s /= <>  and
     * IS_TOTAL_PREORDER([relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(s * <removeMin>, #s)  and
     * for all x: string of character
     *      where (x is in entries (s))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static <T> T removeMin(Sequence<T> s, Comparator<T> order) {
        assert s != null : "Violation of: s is not null";
        assert order != null : "Violation of: order is not null";
        assert s.length() > 0 : "Violation of: s /= <>";

        int smallestIndex = 0;

        for (int i = 0; i < s.length(); i++) {
            /*
             * Update smallestIndex if the element at i is smaller than the
             * element at smallestIndex.
             */
            if (order.compare(s.entry(i), s.entry(smallestIndex)) < 0) {
                smallestIndex = i;
            }
        }

        return s.remove(smallestIndex);
    }

    /**
     * Creator of initial representation.
     *
     * @param order
     *            total preorder for sorting
     */
    private void createNewRep(Comparator<T> order) {
        this.insertionMode = true;
        this.machineOrder = order;
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * Constructor from order.
     *
     * @param order
     *            total preorder for sorting
     */
    public SortingMachine7(Comparator<T> order) {
        this.createNewRep(order);
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final void clear() {
        this.createNewRep(this.machineOrder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final SortingMachine<T> newInstance() {
        try {
            Constructor<?> c = this.getClass().getConstructor(Comparator.class);
            return (SortingMachine<T>) c.newInstance(this.machineOrder);
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void transferFrom(SortingMachine<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof SortingMachine7<?> : ""
                + "Violation of: source is of dynamic type SortingMachine7<?>";

        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type
         * SortingMachine7<?>, and the ? must be T or the call would not have
         * compiled.
         */
        SortingMachine7<T> localSource = (SortingMachine7<T>) source;
        this.insertionMode = localSource.insertionMode;
        this.machineOrder = localSource.machineOrder;
        this.entries = localSource.entries;
        localSource.createNewRep(localSource.machineOrder);
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void add(T x) {
        assert x != null : "Violation of: x is not null";
        assert this.insertionMode : "Violation of: this.insertion_mode";

        this.entries.add(this.entries.length(), x);
    }

    @Override
    public final void changeToExtractionMode() {
        assert this.insertionMode : "Violation of: this.insertion_mode";

        this.insertionMode = false;
    }

    @Override
    public final boolean isInInsertionMode() {
        return this.insertionMode;
    }

    @Override
    public final Comparator<T> order() {
        return this.machineOrder;
    }

    @Override
    public final T removeFirst() {
        assert !this.insertionMode : "Violation of: not this.insertion_mode";
        assert this.size() > 0 : "Violation of: this.contents /= {}";

        return removeMin(this.entries, this.machineOrder);
    }

    @Override
    public final int size() {
        return this.entries.length();
    }

    @Override
    public final Iterator<T> iterator() {
        //Since entries is a Sequence1L, it has an OSU iterator
        return this.entries.iterator();
    }

}
