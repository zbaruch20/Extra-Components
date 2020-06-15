package components.graph;

import java.util.Comparator;

import components.queue.Queue;

/**
 * {@code Graph} enhanced with secondary methods.
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Graph.Vertex} identifiers.
 */
public interface Graph<T> extends GraphKernel<T> {

    /*
     * Nested interfaces ------------------------------------------------------
     */

    /**
     * A graph vertex. The only way to obtain a reference to a graph vertex is
     * from {@code Graph}'s {@code vertexSet} method.
     *
     * @param <T>
     *            type of {@code Vertex} identifier
     * @mathmodel type Vertex is modeled by VERTEX
     * @initially <pre>
     * (T id):
     *  ensures this = (id, WHITE)
     * </pre>
     */
    interface Vertex<T> {

        /**
         * The possible colors of a vertex, mainly used for Breadth-First Search
         * and Depth-First Search applications.
         */
        enum Color {
            /**
             * Colors.
             */
            WHITE, GRAY, CHARCOAL, BLACK
        }

        /**
         * Returns {@code this.identifier}.
         *
         * @return {@code this.identifier}
         * @ensures identifier = this.identfier
         */
        T identifier();

        /**
         * Returns {@code this.color}.
         *
         * @return {@code this.color}
         * @ensures color = this.color
         */
        Color color();

        /**
         * Sets the color of {@code this} to {@code c}.
         *
         * @param c
         *            the color replacing the old one
         * @ensures this.color = c
         */
        void setColor(Color c);
    }

    /**
     * A graph edge. The only way to obtain a reference to a graph edge is from
     * {@code Graph}'s {@code edgeSet} method.
     *
     * @param <T>
     *            type of identifier of {@code Vertex}es in {@code Edge}
     * @mathmodel type Edge is modeled by EDGE
     * @initially <pre>
     * (Vertex u, Vertex v):
     *  ensures this = (u, v)
     * </pre>
     */
    interface Edge<T> {

        /**
         * Returns {@code this.u}, the origin vertex.
         *
         * @return {@code this.u}
         * @ensures origin = this.u
         */
        Vertex<T> origin();

        /**
         * Returns {@code this.v}, the terminal vertex.
         *
         * @return {@code this.v}
         * @ensures terminal = this.v
         */
        Vertex<T> terminal();

    }

    /*
     * Secondary methods ----------------------------------------------------
     */

    /**
     * Reports the transpose of {@code this}. The transpose of a directed graph
     * is simply the existing graph but with the "direction" of each edge
     * reversed, i.e. if a graph contains the edge (u, v), then the transpose of
     * the graph contains the edge (v, u).
     *
     * @return the transpose of {@code this}
     * @aliases reference {@code Graph.Vertex} identifiers
     * @requires this.directed = true
     * @ensures <pre>
     * transpose.directed = true  and
     * transpose.V = this.V  and
     * for all e: EDGE
     *   where (e is in this.E)
     *  ((e.v, e.u) is in transpose.E)
     * </pre>
     */
    Graph<T> transpose();

    /**
     * Reports the Breadth-First "Forest" of {@code this}, represented as a
     * {@code Sequence} of {@code Tree}s. When applicable, the vertices are
     * processed in the ordering as provided by {@code order}.
     *
     * @param order
     *            ordering to process vertices
     * @return the Breadth-First "Forest" of {@code this}
     * @aliases reference entries of elements of {@code breadthFirstForest}
     * @requires IS_TOTAL_PREORDER(order)
     * @ensures <pre>
     * breadthFirstForest = [string of Breadth-First trees representing
     *  Breadth-First Traversal of this, where the vertices are processed
     *  in the order provided by order]
     * </pre>
     */
    //Sequence<Tree<T>> breadthFirstForest(Comparator<T> order);

    /**
     * Reports the Breadth-First {@code Tree} of this from the source vertex
     * with the identifier {@code id}.
     *
     * @param id
     *            the identifier of the {@code Vertex} to be the source of the
     *            Breadth-First Tree
     * @return the Breadth-First {@code Tree} from the source vertex with the
     *         identifier {@code id}
     * @requires <pre>
     * there exists v: VERTEX
     *   where (v is in this.V)
     *  (v.identifier = id)
     * </pre>
     * @ensures <pre>
     * breadthFirstTreeFromSource = [Breadth-First tree representing
     *  Breadth-First Traversal of this, from a single source vertex
     *  with identifier id]
     * </pre>
     */
    //Tree<T> breadthFirstTreeFromSource(T id);

    /**
     * Reports the Breadth-First Traversal of {@code this}, represented as a
     * {@code Queue}. When applicable, the vertices are processed in the
     * ordering as provided by {@code order}.
     *
     * @param order
     *            ordering to process vertices
     * @return the Breadth-First Traversal of {@code this}
     * @requires IS_TOTAL_PREORDER(order)
     * @ensures <pre>
     * breadthFirstTraversal = [the Breadth-First Traversal of this, where the
     *  vertices are processed in the order provided by order]
     * </pre>
     */
    Queue<T> breadthFirstTraversal(Comparator<T> order);

    /**
     * Reports the Breadth-First Traversal of {@code this} from the source
     * vertex with identifier {@code id}.
     *
     * @param id
     *            identifier of the {@code Vertex} to be the source of the
     *            traversal
     * @return the Breadth-First Traversal of {@code this} from the source
     *         vertex with identifier {@code id}
     * @requires <pre>
     * there exists v: VERTEX
     *   where (v is in this.V)
     *  (v.identifier = id)
     * </pre>
     * @ensures <pre>
     * breadthFirstTraversalFromSource = [the Breadth-First Traversal of this
     *  from source vertex whose identifier is id]
     * </pre>
     */
    Queue<T> breadthFirstTraversalFromSource(T id);

    /**
     * Reports the Depth-First Traversal of {@code this}, represented as a
     * {@code Queue}. When applicable, the vertices are processed in the
     * ordering as provided by {@code order}.
     *
     * @param order
     *            ordering to process vertices
     * @return the Depth-First Traversal of {@code this}
     * @requires IS_TOTAL_PREORDER(order)
     * @ensures <pre>
     * depthFirstTraversal = [the Depth-First Traversal of this, where the
     *  vertices are processed in the order provided by order]
     * </pre>
     */
    Queue<T> depthFirstTraversal(Comparator<T> order);

    /**
     * Reports the Depth-First Traversal of {@code this} from the source vertex
     * with identifier {@code id}.
     *
     * @param id
     *            identifier of the {@code Vertex} to be the source of the
     *            traversal
     * @return the Depth-First Traversal of {@code this} from the source vertex
     *         with identifier {@code id}
     * @requires <pre>
     * there exists v: VERTEX
     *   where (v is in this.V)
     *  (v.identifier = id)
     * </pre>
     * @ensures <pre>
     * depthFirstTraversalFromSource = [the Depth-First Traversal of this from
     *  source vertex whose identifier is id]
     * </pre>
     */
    Queue<T> depthFirstTraversalFromSource(T id);

    /**
     * Reports whether {@code this} contains a cycle. A graph contains a cycle
     * iff it contains a path such that the first and last entries are the same
     * vertex, i.e. a path from a vertex to itself via other vertices.
     *
     * @return true if {@code this} contains a cycle, false otherwise
     * @ensures <pre>
     * hasCycle = [this contains a cycle]
     * </pre>
     */
    boolean hasCycle();

    /**
     * Reports whether {@code this} is a bipartite graph. A graph is bipartite
     * iff it does not contain any odd-length cycles.
     *
     * @return true if {@code this} is a bipartite graph, false otherwise
     * @ensures <pre>
     * isBipartite = [this contains a cycle] => [no cycles are of odd length]
     * </pre>
     */
    boolean isBipartite();

    /**
     * Reports the Topological Sort of {@code this}, represented as a
     * {@code Queue}. A Topological Sort of a graph is a Depth-First Traversal
     * of the graph, sorted by decreasing finishing time.
     *
     * @return the Topological Sort of {@code this}
     * @requires <pre>
     * this.directed = true  and
     * [this does not contain any cycles]
     * </pre>
     * @ensures <pre>
     * topologicalSort = [Topological Sort of this, as determined by a depth-first search
     *  and sorted by decreasing finishing time]
     * </pre>
     */
    Queue<T> topologicalSort();
}
