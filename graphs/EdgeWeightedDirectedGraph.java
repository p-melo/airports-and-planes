/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import edu.princeton.cs.algs4.Bag;
import java.util.Iterator;

/**
 *
 * @author Modusaleatorios
 */
public class EdgeWeightedDirectedGraph
{
    /**The number of vertices in this weighted digraph.*/
    private final int v;
    /**The number of edges in this weighted digraph.*/
    private int e;
    /**adj[v] = the adjency list for vertex v.*/
    private Bag<AirlineConnection>[] adj;
    /**indegree[v] = the indegree of vertex v.*/
    private int [] indegree;
    
    /**Initializes an empty edge-weighted digraph with v vertices and 0 edges.
     * @param  v the number of vertices
     * @throws IllegalArgumentException if {@code v < 0}
     */
    public EdgeWeightedDirectedGraph(int v)
    {
        if (v < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.v = v;
        this.e = 0;
        this.indegree = new int[v];
        this.adj = (Bag<AirlineConnection>[]) new Bag[v];
        for (int i = 0; i < v; i++)
        {
            adj[i] = new Bag<AirlineConnection>();
        }
    }
    
    /**  
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param v the tail vertex
     * @param w the head vertex
     * @param distance the weight as distance
     * @param windVelocity the weight as wind velocity
     * @param altitude the weight as altitude
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightedDirectedGraph(int v, int w, double distance, double windVelocity, int altitude)
    {
        this(v);
        
        if (this.e < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < this.e; i++)
        {
            validateVertex(v);
            validateVertex(w);
            
            addEdge(new AirlineConnection(v, w, distance, windVelocity, altitude));
        }
    }
    
    /**throw an IllegalArgumentException unless {@code 0 <= v < V}
     * @param v 
     */
    private void validateVertex(int v)
    {
        if (v < 0 || v >= this.v)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (this.v-1));
    }
    
    /**Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(AirlineConnection e)
    {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        this.e++;
    }
    
    public void removeEdge(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        Bag<AirlineConnection> bag_v = new Bag<>();
        
        for (Iterator iterator = adj[v].iterator(); iterator.hasNext();)
        {
            AirlineConnection nextEdge = (AirlineConnection) iterator.next();
            if (nextEdge.from() != v || nextEdge.to() != w)
            {
                bag_v.add(nextEdge);
            }
            else
            {
                indegree[w]--;
                this.e--;
            }
        }
        
        adj[v] = bag_v;
    }
    
    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<AirlineConnection> adj(int v)
    {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v)
    {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v)
    {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<AirlineConnection> edges()
    {
        Bag<AirlineConnection> list = new Bag<AirlineConnection>();
        for (int i = 0; i < this.v; i++)
        {
            for (AirlineConnection connection : adj(i))
            {
                list.add(connection);
            }
        }
        return list;
    }
    
    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int getNumberOfVertices()
    {
        return this.v;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int getNumberOfEdges()
    {
        return this.e;
    }
}
