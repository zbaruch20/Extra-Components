package components.graph;

import java.util.Comparator;
import java.util.Iterator;

import components.queue.Queue;
import components.set.Set;

/**
 * Layered implementations of secondary methods for {@code Graph}.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Graph.Vertex} identifiers
 */
public abstract class GraphSecondary<T> implements Graph<T> {

    /*
     * Private members ---------------------------------------------------
     */

    /**
     * Reports whether {@code this} contains a {@code Vertex} whose identifier
     * is {@code id}. This method is used for assertion checking.
     *
     * @param id
     *            identifier to check
     * @return true if {@code this} contains a vertex whose identifier is
     *         {@code id}
     * @ensures <pre>
     * identifierInVertices =
     *  there exists v: VERTEX
     *    where (v is in this.V)
     *   (v.identifier = id)
     * </pre>
     */
    private boolean identifierInVertices(T id) {
        assert id != null : "Violation of: id is not null";

        Set<Vertex<T>> vertices = this.vertexSet();
        Iterator<Vertex<T>> verticesIt = vertices.iterator();

        while (verticesIt.hasNext()) {
            Vertex<T> v = verticesIt.next();
            if (v.identifier().equals(id)) {
                return true;
            }
        }

        return false;
    }

    /*
     * Nested classes ----------------------------------------------------
     */

    /**
     * Straightforward implementation of {@code Vertex} interface.
     *
     * @param <T>
     *            type of {@code Vertex} identifiers
     */
    protected static final class SimpleVertex<T> implements Vertex<T> {

        /**
         * Identifier of {@code this}.
         */
        private T id;

        /**
         * Color of {@code this}.
         */
        private Color color;

        /**
         * Constructor.
         *
         * @param id
         *            identifier of {@code this}
         */
        public SimpleVertex(T id) {
            this.id = id;
            this.color = Color.WHITE;
        }

        @Override
        public T identifier() {
            return this.id;
        }

        @Override
        public Color color() {
            return this.color;
        }

        @Override
        public void setColor(Color c) {
            this.color = c;
        }

    }

    /**
     * Straightforward implementation of {@code Edge} interface.
     *
     * @param <T>
     *            type of identifier of {@code Vertex}es in {@code Edge}
     */
    protected static final class SimpleEdge<T> implements Edge<T> {

        /**
         * Origin vertex.
         */
        private Vertex<T> u;

        /**
         * Terminal vertex.
         */
        private Vertex<T> v;

        /**
         * Constructor.
         *
         * @param u
         *            origin vertex
         * @param v
         *            terminal vertex
         */
        public SimpleEdge(Vertex<T> u, Vertex<T> v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public Vertex<T> origin() {
            return this.u;
        }

        @Override
        public Vertex<T> terminal() {
            return this.v;
        }

    }

    /*
     * Secondary methods -------------------------------------------
     */

    @Override
    public final Graph<T> transpose() {
        assert this.isDirected() : "Violation of: this.directed = true";

        //TODO - Complete method body
        return null;
    }

    @Override
    public final Queue<T> breadthFirstTraversal(Comparator<T> order) {
        assert order != null : "Violation of: order is not null";
        //Other precondition not checked

        //TODO - Complete method body
        return null;
    }

    @Override
    public final Queue<T> breadthFirstTraversalFromSource(T id) {
        assert id != null : "Violation of: id is not null";
        assert this.identifierInVertices(
                id) : "Violation of: there exists v: VERTEX\n"
                        + "  where (v is in this.V)\n (v.identifier = id)";

        //TODO - Complete method body
        return null;
    }

    @Override
    public final Queue<T> depthFirstTraversal(Comparator<T> order) {
        assert order != null : "Violation of: order is not null";
        //Other precondition not checked

        //TODO - Complete method body
        return null;
    }

    @Override
    public final Queue<T> depthFirstTraversalFromSource(T id) {
        assert id != null : "Violation of: id is not null";
        assert this.identifierInVertices(
                id) : "Violation of: there exists v: VERTEX\n"
                        + "  where (v is in this.V)\n (v.identifier = id)";

        //TODO - Complete method body
        return null;
    }

    @Override
    public final boolean hasCycle() {
        //TODO - Complete method body
        return false;
    }

    @Override
    public final boolean isBipartite() {
        //TODO - Complete method body
        return false;
    }

    @Override
    public final Queue<T> topologicalSort() {
        assert this.isDirected() : "Violation of: this.directed = true";
        assert !this
                .hasCycle() : "Violation of: [this does not contain any cycles]";

        //TODO - Complete method body
        return null;
    }

}
