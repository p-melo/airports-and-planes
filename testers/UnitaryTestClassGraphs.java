/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import classes.Aviao;
import classes.CompanhiaAerea;
import classes.Location;
import classes.ModeloAviao;
import graphs.AirlineConnection;
import graphs.ConnectedComponents;
import graphs.DijkstraShortestPath;
import graphs.EdgeWeightedSymbolDigraph;
import javax.accessibility.AccessibleAttributeSequence;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

/**
 *
 * @author Modusaleatorios
 */
public class UnitaryTestClassGraphs
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //preencherEdgeWeightedSymbolDigraph();
        //verificarEdgeWeightedSymbolDigraphConexo();
        verificarEdgeWeightedSymbolDigraph_ShortestPath();
    }
    
    public static void preencherEdgeWeightedSymbolDigraph()
    {
        String fileName = ".//src//database//graph.txt";
        EdgeWeightedSymbolDigraph ewsDigraph = new EdgeWeightedSymbolDigraph(fileName, ";");
        
        System.out.println("v = " + ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices());
        System.out.println("e = " + ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfEdges());
        
        for (int i = 0; i < ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices(); i++)
        {
            System.out.print(ewsDigraph.nameOf(i) + " ->");
            for (AirlineConnection j : ewsDigraph.getEdgeWeightedDirectedGraph().adj(i))
            {
                System.out.print(" " + ewsDigraph.nameOf(j.to()));
            }
            System.out.println();
        }
    }
    
    public static void verificarEdgeWeightedSymbolDigraphConexo()
    {
        String fileName = ".//src//database//graph.txt";
        EdgeWeightedSymbolDigraph ewsDigraph = new EdgeWeightedSymbolDigraph(fileName, ";");
        
        System.out.println("v = " + ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices());
        System.out.println("e = " + ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfEdges());
        
        for (int i = 0; i < ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices(); i++)
        {
            System.out.print(ewsDigraph.nameOf(i) + " ->");
            for (AirlineConnection j : ewsDigraph.getEdgeWeightedDirectedGraph().adj(i))
            {
                System.out.print(" " + ewsDigraph.nameOf(j.to()));
            }
            System.out.println();
        }
        
        ConnectedComponents cc = new ConnectedComponents(ewsDigraph.getEdgeWeightedDirectedGraph());
        System.out.println(cc.getCount());
        for (int v = 0; v < ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices(); v++)
        {
            System.out.print(v + " (" + cc.getSize(v) + ") -> ");
            
            for (int i = 0; i < cc.getSize(v); i++)
            {
                System.out.print(" " + i + "[" + cc.connected(v, i) + "]");
            }
            System.out.println();
        }
        System.out.println("É conexo: " + cc.conexo());
    }
    
    public static void verificarEdgeWeightedSymbolDigraph_ShortestPath()
    {
        String fileName = ".//src//database//graph.txt";
        EdgeWeightedSymbolDigraph ewsDigraph = new EdgeWeightedSymbolDigraph(fileName, ";");
        
        System.out.println("v = " + ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices());
        System.out.println("e = " + ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfEdges());
        
        for (int i = 0; i < ewsDigraph.getEdgeWeightedDirectedGraph().getNumberOfVertices(); i++)
        {
            System.out.print(ewsDigraph.nameOf(i) + " ->");
            for (AirlineConnection j : ewsDigraph.getEdgeWeightedDirectedGraph().adj(i))
            {
                System.out.print(" " + ewsDigraph.nameOf(j.to()));
            }
            System.out.println();
        }
        
        CompanhiaAerea companhia = new CompanhiaAerea("TAP Air");
        ModeloAviao modelo = new ModeloAviao("Airbus", 871, 8000, 16700, 380, 274876);
        Aviao airplane = new Aviao("Piriquito", modelo, companhia, "OPO");
        
        System.out.println();
        System.out.println("média de consumo do avião: " + airplane.getMyModeloAviao().mediaDeConsumo() + " lt/km");
        
        System.out.println();
        DijkstraShortestPath sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "shortest");
        int a = 15;
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " km");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "economic");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + (int)sp.getDistTo(a) + " lt. of fuel");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "direct");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " connections");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "quickest");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " hour");
        
        System.out.println();
        for (AirlineConnection ac : sp.pathTo(a))
        {
            System.out.print(ewsDigraph.nameOf(ac.from()) + " -> " + ewsDigraph.nameOf(ac.to()));
            System.out.println(" = " + ac.getDistanceWeight() + " km");
        }
        
        System.out.println();
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "economic default (only cruise altitude)");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " lt");
        for (AirlineConnection ac : sp.pathTo(a))
        {
            System.out.print(ewsDigraph.nameOf(ac.from()) + " -> " + ewsDigraph.nameOf(ac.to()));
            System.out.println(" = " + (ac.getDistanceWeight() * airplane.getMyModeloAviao().mediaDeConsumo()) + " lt");
        }
        
        for (AirlineConnection edgeIter : ewsDigraph.getEdgeWeightedDirectedGraph().adj(1))
        {
            edgeIter.setActiveStatus(false);
        }
        for (AirlineConnection edgeIter : ewsDigraph.getEdgeWeightedDirectedGraph().adj(7))
        {
            edgeIter.setActiveStatus(false);
        }
        
        System.out.println();
        System.out.println("Lisbon removed");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "shortest");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " km");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "economic");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + (int)sp.getDistTo(a) + " lt. of fuel");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "direct");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " connections");
        sp = new DijkstraShortestPath(ewsDigraph.getEdgeWeightedDirectedGraph(), airplane, 0, "quickest");
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.hasPathTo(a));
        System.out.println(ewsDigraph.nameOf(0) + " -> " + ewsDigraph.nameOf(a) + " = " + sp.getDistTo(a) + " hour");
        
        System.out.println();
        for (AirlineConnection ac : sp.pathTo(a))
        {
            System.out.print(ewsDigraph.nameOf(ac.from()) + " -> " + ewsDigraph.nameOf(ac.to()));
            System.out.println(" = " + ac.getDistanceWeight() + " km");
        }
    }
}
