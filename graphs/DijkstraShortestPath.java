/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import classes.Aviao;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 *
 * @author Modusaleatorios
 */
public class DijkstraShortestPath
{
    private Aviao airplane;
    private double[] distTo;
    private AirlineConnection[] edgeTo;
    private IndexMinPQ<Double> pQueue;
    private String weightType;
    
    public DijkstraShortestPath(EdgeWeightedDirectedGraph g1, Aviao airplane, int s, String weightType)
    {
        this.airplane = airplane;
        this.weightType = weightType;
        
        double weight;
        for (AirlineConnection edgeIter : g1.edges())
        {
            weight = getWeight(edgeIter);

            if (weight < 0)
            {
                throw new IllegalArgumentException("edge " + edgeIter + " has negative weight");
            }
        }
        
        this.distTo = new double[g1.getNumberOfVertices()];
        this.edgeTo = new AirlineConnection[g1.getNumberOfVertices()];
        
        validateVertex(s);
        
        for (int v = 0; v < g1.getNumberOfVertices(); v++)
        {
            this.distTo[v] = Double.POSITIVE_INFINITY;
        }
        this.distTo[s] = 0.0;
        
        this.pQueue = new IndexMinPQ<>(g1.getNumberOfVertices());
        this.pQueue.insert(s, distTo[s]);
        while (!this.pQueue.isEmpty())
        {
            int v = this.pQueue.delMin();
            for (AirlineConnection edgeIter : g1.adj(v))
            {
                if (edgeIter.getActiveStatus())
                {
                    relax(edgeIter);
                }
            }
        }
        
        assert check(g1, s);
    }
    
    private void relax(AirlineConnection edge)
    {
        int v = edge.from(), w = edge.to();
        
        if (this.distTo[w] > this.distTo[v] + getWeight(edge))
        {
            distTo[w] = distTo[v] + getWeight(edge);
            edgeTo[w] = edge;
            if (this.pQueue.contains(w))
            {
                this.pQueue.decreaseKey(w, distTo[w]);
            }
            else
            {
                this.pQueue.insert(w, distTo[w]);
            }
        }
    }
    
    private double getWeight(AirlineConnection edge)
    {
        switch (this.weightType)
        {
            case "shortest":
                return edge.getDistanceWeight();
            case "economic":
                double cruiseVelocity2 = this.airplane.getMyModeloAviao().getVelocidadeCruzeiro();
                double tailWind = edge.getWindVelocityWeight();
                double cruiseAltitude = this.airplane.getMyModeloAviao().getAltitudeCruzeiro();
                double edgeAltitude = edge.getAltitudeWeight();
                double fuelConsuption = this.airplane.getMyModeloAviao().mediaDeConsumo();
                double distance2 = edge.getDistanceWeight();
                return (fuelConsuption * distance2) + ((-20 * tailWind) * (distance2 / 1000)) + ((((Math.abs(cruiseAltitude - edgeAltitude))/1000) * 200) * (distance2 / cruiseVelocity2));
            case "direct":
                return 1.0;
            case "quickest":
                double distance4 = edge.getDistanceWeight();
                double cruiseVelocity4 = this.airplane.getMyModeloAviao().getVelocidadeCruzeiro();
                return distance4 / cruiseVelocity4;
            case "economic default (only cruise altitude)":
                double fuelConsuption5 = this.airplane.getMyModeloAviao().mediaDeConsumo();
                double distance5 = edge.getDistanceWeight();
                return fuelConsuption5 * distance5;
            default:
                return 1.0;
        }
    }
    
    public double getDistTo(int v)
    {
        validateVertex(v);
        return this.distTo[v];
    }
    
    public boolean hasPathTo(int v)
    {
        validateVertex(v);
        return this.distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    public Iterable<AirlineConnection> pathTo(int v)
    {
        validateVertex(v);
        if (!hasPathTo(v))
        {
            return null;
        }
        Stack<AirlineConnection> path = new Stack<>();
        for (AirlineConnection edge = this.edgeTo[v]; edge != null; edge = this.edgeTo[edge.from()])
        {
            path.push(edge);
        }
        return path;
    }
    
    private boolean check(EdgeWeightedDirectedGraph g1, int s)
    {
        for (AirlineConnection edge : g1.edges())
        {
            if (getWeight(edge) < 0)
            {
                System.err.println("negative edge weight detected");
                return false;
            }
        }
        
        if (this.distTo[s] != 0.0 || this.edgeTo[s] != null)
        {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for ( int v = 0; v < g1.getNumberOfVertices(); v++)
        {
            if (v == s) continue;
            if (this.edgeTo[v] == null && this.distTo[v] != Double.POSITIVE_INFINITY)
            {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }
        
        for (int v = 0; v < g1.getNumberOfVertices(); v++)
        {
            for (AirlineConnection edge : g1.adj(v))
            {
                int w = edge.to();
                if (distTo[v] + getWeight(edge) < distTo[w])
                {
                    System.err.println("edge " + edge + " not relaxed");
                    return false;
                }
            }
        }
        
        for (int w = 0; w < g1.getNumberOfVertices(); w++)
        {
            if (edgeTo[w] == null) continue;
            AirlineConnection edge = edgeTo[w];
            int v = edge.from();
            if (w != edge.to()) return false;
            if (distTo[v] + getWeight(edge) != distTo[w])
            {
                System.err.println("edge " + edge + " on shortest path not tight");
                return false;
            }
        }
        
        return true;
    }
    
    private void validateVertex(int v)
    {
        int amountV = distTo.length;
        if (v < 0 || v >= amountV)
        {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (amountV - 1));
        }
    }
}
