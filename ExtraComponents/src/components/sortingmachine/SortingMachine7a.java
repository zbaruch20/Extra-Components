package components.sortingmachine;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.Iterator;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code SortingMachine} represented as a {@code Sequence} (using an embedding
 * of insertion sort), with implementations of primary methods.
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
public class SortingMachine7a<T> extends SortingMachineSecondary<T> {

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
     * Inserts the given {@code T} in the {@code Sequence<T>} sorted according
     * to the given {@code Comparator<T>} and maintains the {@code Sequence<T>}
     * sorted.
     *
     * @param <T>
     *            type of {@code Sequence} entries
     * @param s
     *            the {@code Sequence} to insert into
     * @param x
     *            the {@code T} to insert
     * @param order
     *            the {@code Comparator} defining the order for {@code T}
     * @updates s
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(s, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(s, #s * <x>)  and
     * IS_SORTED(s, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void insertInOrder(Sequence<T> s, T x,
            Comparator<T> order) {
        /*
         * Use binary search to determine position of next-smallest element of
         * x, then we can insert x there
         */
        int low = 0;
        int high = s.length();
        int pos = s.length() / 2;

        while (low < high) {
            if (order.compare(s.entry(pos), x) < 0) {
                //x is above entry at pos, update low
                low = pos + 1;
            } else {
                //x is below entry at pos, update high
                high = pos;
            }
            //Update pos
            pos = (low / 2) + (high / 2) + (low % 2 & high % 2);
        }
        s.add(low, x);
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
    public SortingMachine7a(Comparator<T> order) {
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
        assert source instanceof SortingMachine7a<?> : ""
                + "Violation of: source is of dynamic type SortingMachine7<?>";

        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type
         * SortingMachine7a<?>, and the ? must be T or the call would not have
         * compiled.
         */
        SortingMachine7a<T> localSource = (SortingMachine7a<T>) source;
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

        insertInOrder(this.entries, x, this.machineOrder);
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

        return this.entries.remove(0);
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
