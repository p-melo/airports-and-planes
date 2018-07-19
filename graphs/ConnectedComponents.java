/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;


/**
 * The <b>ConnectedComponents</b> class represents a data type for 
 * determining the connected components in an weighted directed graph.
 * The <b>getId</b> operation determines in which connected component
 * a given vertex lies; the <b>connected</b> operation
 * determines whether two vertices are in the same connected component;
 * the <b>count</b> operation determines the number of connected
 * components; the <b>size</b> operation determines the number
 * of vertices in the connect component containing a given vertex; and the
 * <b>conexo</b> operation determines if the graph is "conexo", which means
 * that each and every vertex is connected to all of the others.

 * <p>
 * The <em>component identifier</em> - <b>id</b> - of a connected component is
 * one of the vertices in the connected component: two vertices have the same
 * component identifier if and only if they are in the same connected component.
 * </p>

 * <p>
 * This implementation uses depth-first search. The constructor takes time
 * proportional to <b>V</b> + <b>E</b> (in the worst case), where <b>V</b> is
 * the number of vertices and <b>E</b> is the number of edges. Afterwards,
 * the <b>getId</b>, <b>count</b>, <b>connected</b>, and <b>getSize</b>
 * operations take constant time.
 * </p>
 * <p>
 * For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * </p>
 *
 * @author Plácido Melo (based on the work of Robert Sedgewick and Kevin Wayne)
 */
public class ConnectedComponents
{
    /**marked[v] represents if the vertex v has been marked or not. <em>true</em> if it has been marked.*/
    private boolean[] marked;
    /**id[v] represents the id of the connected component where v is.*/
    private int[] id;
    /**size[id] represents the number of vertices in id component.*/
    private int[] size;
    /**The number of connected components in this graph.*/
    private int count;
    
    /**{@code constructor} for the connected components of an
     * {@code EdgeWeightedDirectedGraph}.
     * @param g1 the weighted directed graph.
     */
    public ConnectedComponents(EdgeWeightedDirectedGraph g1)
    {
        this.marked = new boolean[g1.getNumberOfVertices()];
        this.id = new int[g1.getNumberOfVertices()];
        this.size = new int[g1.getNumberOfVertices()];
        
        for (int v = 0; v < g1.getNumberOfVertices(); v++)
        {
            if (!this.marked[v])
            {
                dfs(g1, v);
                this.count++;
            }
        }
    }
    
    /**Depth-First Search for an {@code EdgeWeightedDirectedGraph}.
     * @param g1 the Edge Weighted Directed Graph.
     * @param v the starting vertex.
     */
    private void dfs(EdgeWeightedDirectedGraph g1, int v)
    {
        this.marked[v] = true;
        this.id[v] = count;
        this.size[this.count]++;
        
        for (AirlineConnection edgeIter : g1.adj(v))
        {
            if (edgeIter.getActiveStatus())
            {
                int w = edgeIter.to();
                if (!marked[w])
                {
                    dfs(g1, w);
                }
            }
        }
    }
    
    /**Method to evaluate if two vertices are connected or not.
     * @param v the first vertex.
     * @param w the second vertex.
     * @return {@code true} if vertices {@code v} and {@code w} are in the
     *          same connected component; {@code false} otherwise.
     * @throws IllegalArgumentException unless {@code 0 <= v < max}
     * @throws IllegalArgumentException unless {@code 0 <= w < max}
     */
    public boolean connected(int v, int w)
    {
        validateVertex(v);
        validateVertex(w);
        return getId(v) == getId(w);
    }
    
    /**Method to evaluate if the graph is "conexo", which means, that all the
     * vertices have paths to all other vertices.
     * @return {@code true} if graph is "conexo", {@code false} otherwise.
     */
    public boolean conexo()
    {
        if (this.count == 1)
        {
            for (int v = 0; v < this.id.length; v++)
            {
                for (int i = 0; i < this.getSize(v); i++)
                {
                    if (!this.connected(v, i))
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    /**Method to validate if a vertex is within the graph.
     * @param v vertex to be analysed.
     * @throws IllegalArgumentException unless {@code 0 <= v < max}
     */
    private void validateVertex(int v)
    {
        int amountV = this.marked.length;
        if (v < 0 || v >= amountV)
        {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (amountV - 1));
        }
    }
    
    
    
    // Getters -----------------------------------------------------------------
    
    /**Method to get the component id of the connected component containing vertex {@code v}.
     * @param v the vertex.
     * @return the component id of the connected component containing vertex {@code v}.
     * @throws IllegalArgumentException unless {@code 0 <= v < max}
     */
    public int getId(int v)
    {
        validateVertex(v);
        return this.id[v];
    }
    
    /**Method to get the number of vertices in the connected component containing
     * vertex {@code v}.
     * @param v the vertex.
     * @return the number of vertices in the connected component containing vertex {@code v}.
     * @throws IllegalArgumentException unless {@code 0 <= v < max}
     */
    public int getSize(int v)
    {
        validateVertex(v);
        return this.size[this.id[v]];
    }
    
    /**Method to get the number of connected components in the graph.
     * @return the number of connected components in the graph.
     */
    public int getCount()
    {
        return this.count;
    }
}
