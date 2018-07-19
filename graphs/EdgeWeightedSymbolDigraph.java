/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;


import classes.Aeroporto;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

/**
 *
 * @author Modusaleatorios
 */
public class EdgeWeightedSymbolDigraph
{
    /**An ordered symbol table using the key to correspond to an Integer value.*/
    private ST<String, Integer> st;
    /**A String array using the Integer value to correspond to a String (key).*/
    private String[] keys;
    /**The Edge Weighted Directed Graph using the Integer values.*/
    private EdgeWeightedDirectedGraph ewDigraph;
    
    /**Initializes an Edge Weighted Directed Graph from a file using the specified delimiter.
     * Each line in the file contains the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param fileName the name of the file
     * @param delimiter the delimiter between fields
     */
    public EdgeWeightedSymbolDigraph(String fileName, String delimiter)
    {
        this.st = new ST<>();
        
        In in = new In(fileName);
        
        while (in.hasNextLine())
        {
            String[] inputLine = in.readLine().split(delimiter);
            st.put(inputLine[0], st.size());
        }
        
        keys = new String[st.size()];
        for (String name : st.keys())
        {
            keys[st.get(name)] = name;
        }
        
        ewDigraph = new EdgeWeightedDirectedGraph(st.size());
        in = new In(fileName);
        while (in.hasNextLine())
        {
            String[] inputArguments = in.readLine().split(delimiter);
            
            int v = st.get(inputArguments[0]);
            for (int i = 1; i < inputArguments.length; i += 4)
            {
                int w = st.get(inputArguments[i]);
                double distance = Double.parseDouble(inputArguments[i+1]);
                double windVelocity = Double.parseDouble(inputArguments[i+2]);
                int altitude = Integer.parseInt(inputArguments[i+3]);
                ewDigraph.addEdge(new AirlineConnection(v, w, distance, windVelocity, altitude));
            }
        }
    }
    
    /**Sets the graph's edges (AirlineConnection) active or inactive, using the
     * attribute continent as a filter property.
     * @param continent the continent to use as filter.
     * @param AirportRBBST the {@code Red Black BST} containing the airports.
     * @param activation the activation status for the edges: {@code true} if it is active, {@code false} if it is inactive.
     */
    public void setContinentActive(String continent, RedBlackBST<String, Aeroporto> AirportRBBST, boolean activation)
    {
        for (String airportCodeIter : AirportRBBST.keys())
        {
            if (AirportRBBST.get(airportCodeIter).getMyCidade().getContinente().compareTo(continent) == 0)
            {
                for (AirlineConnection connectionIter : this.ewDigraph.adj(indexOf(airportCodeIter)))
                {
                    connectionIter.setActiveStatus(activation);
                }
            }
        }
    }
    
    /**
     * Does the digraph contain the vertex named {@code s}?
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s)
    {
        return st.contains(s);
    }
    
    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s)
    {
        return st.get(s);
    }
    
    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public String nameOf(int v)
    {
        validateVertex(v);
        return keys[v];
    }
    
    /**
     * Returns the digraph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the digraph.
     *
     * @return the digraph associated with the symbol digraph
     */
    public EdgeWeightedDirectedGraph getEdgeWeightedDirectedGraph()
    {
        return ewDigraph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v)
    {
        int amountV = ewDigraph.getNumberOfVertices();
        if (v < 0 || v >= amountV)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (amountV - 1));
    }
}