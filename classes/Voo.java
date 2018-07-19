package classes;

import edu.princeton.cs.algs4.BinarySearchST;


/**The Voo class reflects a flight course made by one airplane from an origin airport to a destination airport, carrying a certain amount of passengers.
 * @author Plácido Melo
 */
public class Voo
{
    /**The ID of the flight. The ID is produced out of the concatenation of the origin airport's code, the date and the airport's flight order*/
    private String idVoo;
    /**The date of the flight. The date in which the plane departs from the origin airport.*/
    private CalendarDate data;
    /**The number of passengers that the flight carries. The number of passengers shall never exceed the capacity of the plane making the flight.*/
    private Integer numPassageiros;
    /**The distance of the complete flight. The complete distance is not a straight line, but a sum of all the connections that compose the complete flight*/
    private Integer distancia;
    /**The duration of the complete flight. The complete duration of the flight is composed of the sum of the duration of the various connections.*/
    private Integer duracao;
    /**The consumption of the total flight. The full consumption of the entire flight is the sum of the consumption of each connection.*/
    private Integer consumo;
    /**The plane making the flight. The plane making the flight.*/
    private Aviao myAviao;
    /**An array with the origin and destination airport. The array will always have only 2 airports: the index 0 has the origin airport and the index 1 has the destination airport.*/
    private Aeroporto [] myAeroportos = {null, null};
    /**A binary search tree with all the connecting airports. The connecting airports are stored in this binary search tree in the same order of the flight.*/
    private BinarySearchST<Integer, LigacaoAerea> myLigacoesAereas;

    
    public Voo(int numPassageiros, CalendarDate data, Aviao myAviao, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino, BinarySearchST<Integer, LigacaoAerea> ligacoesAereas)
    {
        this.numPassageiros = numPassageiros;
        this.data = data;
        this.myAviao = myAviao;
        this.myAeroportos[0] = aeroportoOrigem;
        this.myAeroportos[1] = aeroportoDestino;
        this.myLigacoesAereas = ligacoesAereas;
        
        Integer distanciaTotal = 0, consumoTotal = 0;
        for (Integer i : this.myLigacoesAereas.keys())
        {
            distanciaTotal += this.myLigacoesAereas.get(i).getDistancia();
            consumoTotal += this.myAviao.getMyModeloAviao().consumoViagem(this.myLigacoesAereas.get(i).getAltitude(), this.myLigacoesAereas.get(i).getDistancia());
        }
        this.distancia = distanciaTotal;
        this.duracao = distanciaTotal / this.myAviao.getMyModeloAviao().getVelocidadeCruzeiro();
        this.consumo = consumoTotal;
        
        this.idVoo = aeroportoOrigem.getIdAeroporto().concat(".").concat(data.getOrderedDate()).concat(".").concat(String.valueOf(aeroportoOrigem.getQtVoos()));
        //aeroportoOrigem.setQtVoos(aeroportoOrigem.getQtVoos() + 1);
    }
    
    @Override
    public String toString()
    {
        String str = "".concat(this.idVoo).concat(";").concat(this.data.toString());
        str = str.concat(";").concat(this.myAeroportos[0].getIdAeroporto()).concat(" --> ").concat(this.myAeroportos[1].getIdAeroporto());
        str = str.concat(";").concat(String.valueOf(this.numPassageiros)).concat(" pax");
        str = str.concat(";").concat(String.valueOf(this.distancia)).concat(" km");
        str = str.concat(";").concat(String.valueOf(this.duracao)).concat(" h");
        str = str.concat(";").concat(String.valueOf(this.consumo)).concat(" lt");
        str = str.concat(";").concat(this.myAviao.getNomeAviao()).concat(" - ").concat(this.myAviao.getMyCompanhiaAerea().getNome());
        
        return str;
    }

    
    // Getters e Setters -------------------------------------------------------
    
    /**Getter for the flight's ID. The flight's ID is an alpha-numeric code created by the concatenation of the origin airport code, the date, and the order of flight on that airport.
     * @return the flight's ID
     */
    public String getIdVoo()
    {
        return this.idVoo;
    }
    
    /**Getter for the flight's date. The flight's date is the date in which the flight took off from the origin airport.
     * @return the flight's date
     */
    public CalendarDate getData()
    {
        return this.data;
    }
    
    /**Getter for the flight's number of passengers. The flight's number of passengers is the number of passengers this flight had.
     * @return the number of passengers
     */
    public Integer getNumPassageiros()
    {
        return this.numPassageiros;
    }
    /**Setter for the flight's number of passengers. The flight's number of passengers is the number of passengers this flight had.
     * @param numPassageiros the number of passengers to set
     */
    public void setNumPassageiros(Integer numPassageiros)
    {
        this.numPassageiros = numPassageiros;
    }
    
    /**Getter for the flight's distance. The flight's distance is the sum of all the connections the flight has to make to get to its destination.
     * @return the distance of the complete flight
     */
    public Integer getDistancia()
    {
        return this.distancia;
    }

    /**Getter for the flight's duration. The flight's duration depends on the distance and the plane's speed.
     * @return the flight's duration
     */
    public Integer getDuracao()
    {
        return this.duracao;
    }

    /**Getter for the flight's plane. The plane which made the flight.
     * @return the flight's plane
     */
    public Aviao getMyAviao()
    {
        return this.myAviao;
    }
    /**Setter for the flight's plane. The plane to make the flight.
     * @param aviao the plane to set
     */
    public void setMyAviao(Aviao aviao)
    {
        this.myAviao = aviao;
    }
    
    /**Getter for the flight's origin airport. The airport in which the plane has started the flight.
     * @return the flight's origin airport
     */
    public Aeroporto getMyAeroportoOrigem()
    {
        return this.myAeroportos[0];
    }
    /**Setter for the flight's origin airport. The airport in which the plane has started the flight.
     * @param aeroportoOrigem the flight's origin airport
     */
    public void setMyAeroportoOrigem(Aeroporto aeroportoOrigem)
    {
        this.myAeroportos[0] = aeroportoOrigem;
    }
    /**Getter for the flight's destination airport. The airport in which the plane ends the flight.
     * @return the flight's destination airport
     */
    public Aeroporto getMyAeroportoDestino()
    {
        return this.myAeroportos[1];
    }
    /**Setter for the flight's destination airport. The airport in which the plane ends the flight.
     * @param aeroportoDestino the flight's destination airport
     */
    public void setMyAeroportoDestino(Aeroporto aeroportoDestino)
    {
        this.myAeroportos[1] = aeroportoDestino;
    }

    /**Getter for the binary search tree with the connecting airports during the flight.
     * @return the list of the connecting airports
     */
    public BinarySearchST<Integer, LigacaoAerea> getMyLigacoesAereas()
    {
        return this.myLigacoesAereas;
    }
    /**Setter for a connecting airport. Adds a connecting airport to the binary search tree of connecting airports of the flight.
     * @param ligacaoAerea the connecting airport to add
     */
    public void addMyLigacoesAereas(LigacaoAerea ligacaoAerea)
    {
        this.myLigacoesAereas.put(myLigacoesAereas.size(), ligacaoAerea);
    }
}