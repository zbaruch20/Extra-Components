package components.graph;

import components.graph.Graph.Edge;
import components.graph.Graph.Vertex;
import components.set.Set;
import components.standard.Standard;

/**
 * Graph kernel component with primary methods. (Note: by package-wide
 * convention, all references are non-null.)
 *
 * @author Zach Baruch
 *
 * @param <T>
 *            type of {@code Graph.Vertex} identifiers
 * @mathsubtypes <pre>
 * VERTEX is (
 *   identifier: T,
 *   color: Color
 *  )
 *  exemplar v
 *  constraint
 *   [the initial value of v.color is WHITE]
 *
 * EDGE is (
 *   u: VERTEX,
 *   v: VERTEX
 *  )
 *  exemplar e
 *  constraint
 *   if u /= v
 *    then u.identifier /= v.identifier
 *
 * GRAPH_MODEL is (
 *   V: finite set of VERTEX,
 *   E: finite set of EDGE,
 *   directed: boolean
 *  )
 *  exemplar G
 *  constraint
 *   |G.V| >= 0  and
 *   |G.E| >= 0  and
 *   for all v: VERTEX
 *     where (v is in G.V)
 *    ([v.identifier is unique])  and
 *   for all e: EDGE
 *     where (e is in G.E)
 *    (e.u, e.v is in V)  and
 *   if directed = false
 *    then for all u, v: VERTEX
 *      where (u, v is in G.V)
 *     (if (u, v) is in G.E
 *       then (v, u) is in G.E)
 * </pre>
 * @mathmodel type {@code GraphKernel} is modeled by GRAPH_MODEL
 * @initially <pre>
 * (boolean directed):
 *  ensures this = ({}, {}, directed)
 * </pre>
 */
public interface GraphKernel<T> extends Standard<Graph<T>> {

    /*
     * Kernel methods ----------------------------------------------------------
     */

    /**
     * Adds a new vertex with identifier {@code id} to {@code this}.
     *
     * @param id
     *            identifier of new vertex
     * @aliases reference id
     * @updates this
     * @requires <pre>
     * for all v: VERTEX
     *   where (v is in this.V)
     *  (v.identifier /= id)
     * </pre>
     * @ensures <pre>
     * this.V = #this.V union {(id, WHITE)}
     * </pre>
     */
    void addVertex(T id);

    /**
     * Removes the vertex with identifier {@code id} and all associated edges
     * from {@code this}.
     *
     * @param id
     *            identifier of vertex to remove
     * @updates this
     * @requires <pre>
     * there exists v: VERTEX
     *   where (v is in this.V)
     *  (v.identifier = id)
     * </pre>
     * @ensures <pre>
     * this.V = #this.V \ {(id, ?)}  and
     * this.E = #this.E \ [finite set of EDGE containing (id, ?) as
     *   one or more of its connecting vertices]
     * </pre>
     */
    void removeVertex(T id);

    /**
     * Constructs an edge connecting the vertex with identifier {@code u} to the
     * vertex with identifier {@code v}. If {@code this} is an undirected graph,
     * an edge connecting the vertex with identifier {@code v} to the vertex
     * with identifier {@code u} as well.
     *
     * @param u
     *            identifier of origin vertex
     * @param v
     *            identifier of terminal vertex
     * @updates this
     * @requires <pre>
     * there exists v1: VERTEX
     *   where (v1 is in this.V)
     *  (v1.identifier = u)  and
     * there exists v2: VERTEX
     *   where (v2 is in this.V)
     *  (v2.identifier = v)  and
     * ((u, ?), (v, ?)) is not in this.E
     * </pre>
     * @ensures <pre>
     * if this.directed = true
     *  then this.E = #this.E union {((u, ?), (v, ?))}
     *  else this.E = #this.E union {((u, ?), (v, ?)), ((v, ?), (u, ?))}
     * </pre>
     */
    void addEdge(T u, T v);

    /**
     * Removes the edge connecting the vertex with identifier {@code u} to the
     * vertex with identifier {@code v}. If {@code this} is an undirected graph,
     * the edge connecting the vertex with identifier {@code v} to the vertex
     * with identifier {@code u} is removed as well.
     *
     * @param u
     *            identifier of origin vertex
     * @param v
     *            identifier of terminal vertex
     * @updates this
     * @requires <pre>
     * there exists v1: VERTEX
     *   where (v1 is in this.V)
     *  (v1.identifier = u)  and
     * there exists v2: VERTEX
     *   where (v2 is in this.V)
     *  (v2.identifier = v)  and
     * ((u, ?), (v, ?)) is in this.E
     * </pre>
     * @ensures <pre>
     * if this.directed = true
     *  then this.E = #this.E \ {((u, ?), (v, ?))}
     *  else this.E = #this.E \ {((u, ?), (v, ?)), ((v, ?), (u, ?))}
     * </pre>
     */
    void removeEdge(T u, T v);

    /**
     * Reports whether {@code this} is a directed graph.
     *
     * @return true if {@code this} is a directed graph, false otherwise.
     * @ensures isDirected = this.directed
     */
    boolean isDirected();

    /**
     * Returns a {@code Set} containing the vertices in {@code this}.
     *
     * @return {@code this.V}
     * @ensures vertexSet = this.V
     */
    Set<Vertex<T>> vertexSet();

    /**
     * Returns a {@code Set} containing the edges in {@code this}.
     *
     * @return {@code this.E}
     * @ensures edgeSet = this.E;
     */
    Set<Edge<T>> edgeSet();
}
