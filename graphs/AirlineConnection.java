/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;


/**The {@code AirlineConnection} class represents a weighted edge in an Edge Weighted Directed Graph.
 * Each edge consists of two integers (naming the two vertices) and three double-value weights.
 * <p>The data type provides methods for accessing the two endpoints of the directed edge and the weights.</p>
 * @author Plácdo Melo
 */
public class AirlineConnection
{
    /**The starting vertex.*/
    private final int v;
    /**The ending vertex*/
    private final int w;
    /**The weight of distance.*/
    private final double distance;
    /**The weight of windVelocity.*/
    private final double windVelocity;
    /**The weight of altitude.*/
    private final int altitude;
    /**The connection availability.*/
    private boolean active;
    
    /**Initializes a Directed Weighted Edge from point {@code v} to point {@code w}, with the weights of altitude, wind velocity and altitude.
     * @param v the starting vertex.
     * @param w the ending vertex.
     * @param distance the weight as a distance.
     * @param windVelocity the weight as wind velocity.
     * @param altitude the weight as altitude.
     */
    public AirlineConnection(int v, int w, double distance, double windVelocity, Integer altitude)
    {
        if (!Integer.class.isInstance(v)) throw new IllegalArgumentException("Starting vertex is not an integer");
        if (!Integer.class.isInstance(w)) throw new IllegalArgumentException("Ending vertex is not an integer");
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(distance)) throw new IllegalArgumentException("Distance is not a number");
        if (Double.isNaN(windVelocity)) throw new IllegalArgumentException("Wind Velocity is not a number");
        if (!Integer.class.isInstance(altitude)) throw new IllegalArgumentException("Distance is not an integer");
        
        this.v = v;
        this.w = w;
        this.distance = distance;
        this.windVelocity = windVelocity;
        this.altitude = altitude;
        this.active = true;
    }
    
    /**Returns the starting vertex of the weighted directed edge.
     * @return the starting vertex.
     */
    public int from()
    {
        return this.v;
    }
    
    /**Returns the ending vertex of the weighted directed edge.
     * @return the ending vertex.
     */
    public int to()
    {
        return this.w;
    }
    
    /**Returns the distance weight of the weighted directed edge.
     * @return the distance weight.
     */
    public double getDistanceWeight()
    {
        return this.distance;
    }
    
    /**Returns the wind velocity weight of the weighted directed edge.
     * @return the wind velocity weight.
     */
    public double getWindVelocityWeight()
    {
        return this.windVelocity;
    }
    
    /**Returns the altitude weight of the weighted directed edge.
     * @return the altitude weight.
     */
    public double getAltitudeWeight()
    {
        return this.altitude;
    }
    
    /**Getter for the availability of this connection.
     * @return {@code true} if this connection is available, {@code false} otherwise.
     */
    public boolean getActiveStatus()
    {
        return this.active;
    }
    
    /**Setter for the availability of this connection.
     * @param activeStatus {@code true} means the connection is active, {@code flase} means it's not.
     */
    public void setActiveStatus(boolean activeStatus)
    {
        this.active = activeStatus;
    }
    
    @Override
    public String toString()
    {
        return this.v + "->" + this.w + " " + String.format("%5.2f", this.distance) + "km " + String.format("%5.2f", this.windVelocity) + "km/h " + this.altitude + "km";
    }
}
