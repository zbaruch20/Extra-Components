package components.graph;

import components.map.Map;
import components.sequence.Sequence;
import components.set.Set;

/**
 * {@code Graph} represented as an adjacency list (here represented as a
 * {@code Sequence<Sequence<T>>}) of {@code Vertex} identifiers and
 * implementations of primary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Graph.Vertex} identifiers
 * @convention <pre>
 * ill get to it
 * </pre>
 * @correspondence this = ill explain later
 */
public class Graph1<T> extends GraphSecondary<T> {

    /*
     * Private members ------------------------------------------------------
     */

    /**
     * Adjacency list representation of {@code this}.
     */
    private Sequence<Sequence<T>> adj;

    /**
     * Map pairing each {@code Vertex} identifier to a unique index in
     * {@code adj}. There is an additional stipulation that each value is unique
     * from 0 to (|identifiersAndIndices| - 1).
     *
     * @maintains <pre>
     * for all i: integer
     *   where (0 <= i < |identifiersAndIndices|)
     *  (i is in RANGE(identifiersAndIndices))
     * </pre>
     */
    private Map<T, Integer> identifiersAndIndices;

    /**
     * Decreases the values of {@code m} such that its invariant is satisfied.
     * The elements should still be in the same relation as they were before the
     * method is called, i.e. the value of one key will always be smaller than
     * the value of another key.
     *
     * @param <T>
     *            generic argument type of {@code m}
     * @param m
     *            map to shift values in
     * @requires [the values in m are unique]
     * @ensures <pre>
     * for all i: integer
     *   where (0 <= i < |identifiersAndIndices|)
     *  (i is in RANGE(identifiersAndIndices))
     * </pre>
     */
    protected static <T> void shiftIndices(Map<T, Integer> m) {
        assert m != null : "Violation of: m is not null";
        //Other precondition not checked cause im too lazy

        //Go through each possible index and shift next highest down if necessary
        for (int i = 0; i < m.size(); i++) {
            if (!m.hasValue(i)) {
                //Find next highest value in m
                int j = i + 1;
                while (!m.hasValue(j)) {
                    j++;
                }

                //Shift it down
                m.replaceValue(m.key(j), i);
            }
        }
    }

    /*
     * Constructors ---------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Graph1() {

    }

    /*
     * Standard methods -----------------------------------------------------
     */

    @Override
    public final Graph<T> newInstance() {
        //TODO - Complete method body
        return null;
    }

    @Override
    public final void clear() {
        //TODO - Complete method body
    }

    @Override
    public final void transferFrom(Graph<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Graph1<?> : ""
                + "Violation of: source is of dynamic type Graph1<?>";

        //TODO - Complete method body
    }

    /*
     * Kernel methods -------------------------------------------------------
     */

    @Override
    public void addVertex(T id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeVertex(T id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addEdge(T u, T v) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeEdge(T u, T v) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDirected() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Vertex<T>> vertexSet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Edge<T>> edgeSet() {
        // TODO Auto-generated method stub
        return null;
    }
}
