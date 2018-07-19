package classes;


import Exceptions.OutOfRangeValuesException;
import java.util.ArrayList;

/**<p>The Location class represents a place in the map. This class contains as attributes to instance a location with geographical coordinates and identify it with a name, country and continent.</p>
 * <p>The Location class contains methods to instantiate objects of this class and calculate distance between them.</p>
 * <p>The instantiation of a null Location is not possible.</p>
 * @author Plácido Melo
 */
public class Location
{
    /**The name of this location. It can be the name of a city, such as "London", or the name of a place, such as "Stonehenge".*/
    private String nome;
    /**The country in which this location is.*/
    private String pais;
    /**The continent in which this location is.*/
    private String continente;
    /**The latitude value of this location. This value ranges between -180.0º and 180.0º. A positive value represents the northern or upper half, and a negative value represents the southern or lower half.*/
    private double latitude;
    /**The longitude value of this location. This value ranges between -180.0º and 180.0º. A positive value represents the eastern, or right half, and a negative value represents the western, or left, half.*/
    private double longitude;
    /**The ArrayList of airports existing in this location.*/
    private ArrayList<Aeroporto> myAeroportos;
    
    /**This constructor instantiates a Location class object with given values for name, country and continent, as well as latitude and longitude.
     * @param nome the string value for the Location's name.
     * @param pais the string value for the Location's country.
     * @param continente the string value for the Location's continent.
     * @param latitude the double value for the Location's latitude.
     * @param longitude the double value for the Location's longitude.
     */
    public Location(String nome, String pais, String continente, double latitude, double longitude)
    {
        this.nome = nome;
        this.pais = pais;
        this.continente = continente;
        this.latitude = latitude;
        this.longitude = longitude;
        this.myAeroportos = new ArrayList<>();
    }

    /**<p>Returns the distance between this location and another in kilometers.</p>
     * <p>This method uses the coordinates of each location to calculate the distance.</p>
     * @param otherLocation the other location to get the measurement.
     * @return a distance in kilometers.
     */
    public Integer distanciaCidades(Location otherLocation)
    {
        double dx1 = Math.toRadians(this.longitude);
        double dy1 = Math.toRadians(this.latitude);
        double dx2 = Math.toRadians(otherLocation.getLongitude());
        double dy2 = Math.toRadians(otherLocation.getLatitude());
        double r = 6371000;
        
        double a = Math.pow(Math.sin((dy2 - dy1)/2), 2) + (Math.cos(dy1) * Math.cos(dy2)) * Math.pow(Math.sin((dx2 - dx1)/2), 2);
        double x = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        
        return (int)((r * x) / 1000);
    }
    
    /**Returns the delta of two latitude coordinates, or two longitude coordinates. The delta is the distance between the two coordinates got from the subtraction of the start to the final.
     * @param xInicio the start coordinate.
     * @param xFinal the final coordinate.
     * @return the delta of start and final coordinates.
     */
    public static Double deltaCoordenadas(double xInicio, double xFinal)
    {
        return xFinal - xInicio;
    }
    
    /**Returns a string with the city's, country's and continent's name of this location.
     * @return a string with this location's information.
     */
    public String stringCityCountryContinent()
    {
        return this.nome + ", " + this.pais + ", " + this.continente;
    }
    
    /**Returns a string with the coordinates of this location.
     * @return a string with coordinates.
     */
    public String stringCoordinates()
    {
        String lat, lon;
        
        if (this.latitude < 0)
        {
            lat = "S " + String.valueOf(this.latitude * (-1));
        } else
        {
            lat = "N " + String.valueOf(this.latitude);
        }
        
        if (this.longitude < 0)
        {
            lon = "W " + String.valueOf(this.longitude * (-1));
        } else
        {
            lon = "E " + String.valueOf(this.longitude);
        }
        
        return lat + "º, " + lon + "º";
    }
    
    
    
    
    
    // Getters -----------------------------------------------------------------
    
    /**Getter for the name of this location. It can be the name of a city, such as London, or the name of a place, such as Stonehenge.
     * @return the location's name.
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**Getter for the name of the country this location belongs to.
     * @return the location's country.
     */
    public String getPais()
    {
        return this.pais;
    }
    
    /**Getter for the name of the continent this location belongs to.
     * @return the location's continent.
     */
    public String getContinente()
    {
        return this.continente;
    }
    
    /**Getter for the latitude value of this location.
     * @return the location's latitude value.
     */
    public double getLatitude()
    {
        return this.latitude;
    }
    
    /**Getter for the longitude value of this location.
     * @return the location's longitude value.
     */
    public double getLongitude()
    {
        return this.longitude;
    }
    
    /**Getter for the airport on the index position in the ArrayList of airports.
     * @param index the position in the ArrayList.
     * @return the airport on the index position.
     */
    public Aeroporto getAeroporto(Integer index)
    {
        return this.myAeroportos.get(index);
    }
    
    /**Getter for the ArrayList of airports of this location.
     * @return the location's ArrayList of airports.
     */
    public ArrayList<Aeroporto> getListagemAeroportos()
    {
        return this.myAeroportos;
    }
    
    // Setters -----------------------------------------------------------------
    
    /**Setter for the name of this location. It can be the name of a city, such as London, or the name of a place, such as Stonehenge.
     * @param nome the city's name to set
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**Setter for the name of the country this location belongs to.
     * @param pais the location's country to set.
     */
    public void setPais(String pais)
    {
        this.pais = pais;
    }

    /**Setter for the name of the continent this location belongs to.
     * @param continente the location's continent to set.
     */
    public void setContinente(String continente)
    {
        this.continente = continente;
    }

    /**<p>Setter for the coordinates of this location.</p>
     * <p>Latitude is the vertical coordinate (North - South). The North, or the upper half, is represented as a positive double, and the South, or lower half, is represented as a negative double.</p>
     * <p>Longitude is the horizontal coordinate (East - West).The East, or the right half, is represented as a positive double, and the West, or left half, is represented as a negative double.</p>
     * @param latitude the location's latitude to set.
     * @param longitude the location's longitude to set.
     * @throws OutOfRangeValuesException thrown if the value is out of specified ranges
     */
    public void setLocalizacao(double latitude, double longitude) throws OutOfRangeValuesException
    {
        if ((latitude < -180) || (latitude > 180) || (longitude < -180) || (longitude > 180))
        {
            throw new OutOfRangeValuesException("O valor introduzido encontra-se fora dos limites validáveis (-180 - 180)");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**Adds one airport into the ArrayList of airports in this location.
     * @param airport the airport to add.
     */
    public void addAeroporto(Aeroporto airport)
    {
        this.myAeroportos.add(airport);
    }
}