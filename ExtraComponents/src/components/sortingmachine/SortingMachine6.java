package components.sortingmachine;

import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.Iterator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * {@code SortingMachine} represented as a {@code Queue} (using an embedding of
 * mergesort), with implementations of primary methods.
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
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * </pre>
 * @convention <pre>
 * IS_TOTAL_PREORDER([relation computed by $this.machineOrder.compare method])  and
 * if not $this.insertionMode then
 *  IS_SORTED($this.entries, [relation computed by $this.machineOrder.compare method])
 * </pre>
 * @correspondence <pre>
 * this =
 *   ($this.insertionMode, $this.machineOrder, multiset_entries($this.entries))
 * </pre>
 *
 * @author Zach Baruch
 */
public class SortingMachine6<T> extends SortingMachineSecondary<T> {

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
    private Queue<T> entries;

    /**
     * Merges {@code q1} and {@code q2} into {@code merged}, a single sorted
     * {@code Queue}. Execution time should be in O({@code |q1 * q2|}).
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q1
     *            first {@code Queue} to be merged
     * @param q2
     *            second {@code Queue} to be merged
     * @param merged
     *            upon return, the merged {@code Queue} from {@code q1} and
     *            {@code q2}
     * @param order
     *            ordering by which to merge entries
     * @clears q1, q2
     * @replaces merged
     * @requires <pre>
     * IS_TOTAL_PREORDER([relation computed by order.compare method])  and
     * IS_SORTED(q1, [relation computed by order.compare method])  and
     * IS_SORTED(q2, [relation computed by order.compare method])
     * </pre>
     * @ensures <pre>
     * perms(merged, #q1 * #q2)  and
     * IS_SORTED(merged, [relation computed by order.compare method])
     * </pre>
     */
    private static <T> void merge(Queue<T> q1, Queue<T> q2, Queue<T> merged,
            Comparator<T> order) {
        assert q1 != null : "Violation of: q1 is not null";
        assert q2 != null : "Violation of: q2 is not null";
        assert merged != null : "Violation of: merged is not null";
        assert order != null : "Violation of: order is not null";
        //Rest of preconditions not checked

        merged.clear();

        //See which element is smaller, then enqueue it to merged
        while (q1.length() > 0 || q2.length() > 0) {
            //Special cases for an empty queue
            if (q1.length() == 0) {
                merged.enqueue(q2.dequeue());
            } else if (q2.length() == 0) {
                merged.enqueue(q1.dequeue());
            } else { //Regular merge
                T front1 = q1.front();
                T front2 = q2.front();
                if (order.compare(front1, front2) < 0) {
                    merged.enqueue(q1.dequeue());
                } else {
                    merged.enqueue(q2.dequeue());
                }
            }
        }
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param <T>
     *            type of {@code Queue} entries
     * @param q
     *            the {@code Queue} to be sorted
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures IS_SORTED(q, [relation computed by order.compare method])
     */
    private static <T> void sort(Queue<T> q, Comparator<T> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        if (q.length() > 1) {
            //Split into two queues
            Queue<T> q1 = q.newInstance();
            Queue<T> q2 = q.newInstance();

            while (q.length() > q1.length()) {
                q1.enqueue(q.dequeue());
            }
            q2.transferFrom(q);

            //Recursively sort the two queues
            sort(q1, order);
            sort(q2, order);

            //Merge the two sorted queues back into q
            merge(q1, q2, q, order);
        }
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
        this.entries = new Queue1L<T>();
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
    public SortingMachine6(Comparator<T> order) {
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
        assert source instanceof SortingMachine6<?> : ""
                + "Violation of: source is of dynamic type SortingMachine6<?>";

        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type
         * SortingMachine6<?>, and the ? must be T or the call would not have
         * compiled.
         */
        SortingMachine6<T> localSource = (SortingMachine6<T>) source;
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

        this.entries.enqueue(x);
    }

    @Override
    public final void changeToExtractionMode() {
        assert this.insertionMode : "Violation of: this.insertion_mode";

        this.insertionMode = false;
        sort(this.entries, this.machineOrder);
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

        return this.entries.dequeue();
    }

    @Override
    public final int size() {
        return this.entries.length();
    }

    @Override
    public final Iterator<T> iterator() {
        //Since entries is a Queue1L, it has an OSU iterator
        return this.entries.iterator();
    }

}
