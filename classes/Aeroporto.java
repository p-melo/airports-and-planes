package classes;

import java.util.ArrayList;
import edu.princeton.cs.algs4.RedBlackBST;
import graphs.AirlineConnection;
import graphs.EdgeWeightedSymbolDigraph;

/**<p>The Aeroporto class represents an airport. The airport is where airplanes land and set off. Each instantiated airport will keep record of its airline connections and every flights that passed by it.</p>
 * <p>The Aeroporto class has methods to instantiate an airport and to get information about its atributes.</p>
 * <p>The instantiation of a null airport is not possible.</p>
 * @see Location
 * @see RedBlackBST
 * @author Plácido Melo
 */
public class Aeroporto
{
    /**The Aeroporto's official name.*/
    private String nome;
    /**The Aeroporto's code. The airport's international IATA's 3 letter code.*/
    private String idAeroporto;
    /**The Aeroporto's classification. Its range goes from 0.0 to 10.0*/
    private double classificacao;
    /**The Aeroporto's location. The Location where the airport is situated.*/
    private Location myCidade;
    /**The Aeroporto's quantity of flights. The amount of flights that already passed by this airport.*/
    private Integer qtVoos;
    //**The Aeroporto's quantity of runways. The amount of runways in this airport. It defines the maximum number of flights landing and/or departing in this airport.*/
    //private Integer qtPistas;
    /**The Aeroporto's quantity of passengers. The amount of passengers that already passed by this airport.*/
    private Integer qtPax;
    /**The Aeroporto's airplanes. A Red Black Search Tree with the airplanes stationed in the airport on that given moment.*/
    private RedBlackBST<String, Aviao> myAvioesRBBST;
    /**The Aeroporto's airline connections. The airline connections this airport has.*/
    private ArrayList<LigacaoAerea> myLigacoesAereas;
    private ArrayList<AirlineConnection>myAirlineConnections;
    /**The Aeroporto's departure flights. The departure flights made from this airport.*/
    private RedBlackBST<String, Voo> myVoosPartidasRBBST;
    /**The Aeroporto's arrival flights. The arrival flights made to this airport.*/
    private RedBlackBST<String, Voo> myVoosChegadasRBBST;
    /**The Aeroporto's boolean value for filters.*/
    private boolean active;
    
    /**Aeroporto class constructor.
     * @param nomeAeroporto The official name of the airport.
     * @param idAeroporto The airport's international 3 letter code.
     * @param classificacao The airport's classification.
     * @param cidade The airport's Location.
     * @see Location
     */
    public Aeroporto(String nomeAeroporto, String idAeroporto, double classificacao, Location cidade)
    {
        this.nome = nomeAeroporto;
        this.idAeroporto = idAeroporto;
        this.classificacao = classificacao;
        this.myCidade = cidade;
        this.qtVoos = 0;
        this.qtPax = 0;
        this.myAvioesRBBST = new RedBlackBST<>();
        this.myLigacoesAereas = new ArrayList<>();
        this.myAirlineConnections = new ArrayList<>();
        this.myVoosPartidasRBBST = new RedBlackBST<>();
        this.myVoosChegadasRBBST = new RedBlackBST<>();
        this.active = true;
    }

    @Override
    public String toString()
    {
        String str = "";
        str = str.concat(this.nome).concat(", ").concat(this.idAeroporto);
        str = str.concat(", ").concat(this.myCidade.getNome()).concat(", ").concat(this.myCidade.getPais()).concat(", ").concat(this.myCidade.getContinente());
        str = str.concat(", ").concat(String.valueOf(this.classificacao));
        str = str.concat(", Aviões (").concat(String.valueOf(this.myAvioesRBBST.size())).concat(")");
        str = str.concat(", Ligações Aéreas (").concat(String.valueOf(this.myLigacoesAereas.size())).concat(")");
        
        return str;
    }

    public void listarAvioes()
    {
        for (String airplaneCode : this.myAvioesRBBST.keys())
        {
            System.out.println(this.myAvioesRBBST.get(airplaneCode).toString());
        }
    }
    
    
    
    // Getters -----------------------------------------------------------------

    /**Getter for the name of this airport. It returns the official airport's name.
     * @return the name of this airport, as a String.
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**Getter for the code of this airport. It returns the international airport's IATA 3 letter code.
     * @return the code of this airport, as a String.
     */
    public String getIdAeroporto()
    {
        return this.idAeroporto;
    }
    
    /**Getter for the classification of this airport. It returns the classification value, as a double, for this airport.
     * @return the classification of this airport, as a double.
     */
    public double getClassificacao()
    {
        return this.classificacao;
    }
    
    /**Getter for the location of this airport. It returns the location of this airport, with continent, country, city and coordinates information.
     * @return the location of this airport, as a Location instance.
     */
    public Location getMyCidade()
    {
        return this.myCidade;
    }
    
    /**Getter for the quantity of flights for this airport. It returns the quantity of flights that already passed by this airport.
     * @return the quantity of flights for this airport, as an Integer.
     */
    public Integer getQtVoos()
    {
        return this.qtVoos;
    }
    
    /**Getter for the quantity of passengers in this airport. It returns the quantity of passengers that already passed by this airport.
     * @return the quantity of passengers for this airport, as an Integer.
     */
    public Integer getQtPax()
    {
        return this.qtPax;
    }
    
    /**Getter for the Aviao instance in this airport. It returns the airplane corresponding to the name passed on as a parameter, if it is landed in this airport.
     * @param nomeAviao the Aviao name to be returned.
     * @return the airplane, if it exists in this airport's list, as an Aviao instance.
     */
    public Aviao getMyAviao(String nomeAviao)
    {
        if (this.myAvioesRBBST.contains(nomeAviao))
        {
            return this.myAvioesRBBST.get(nomeAviao);
        }
        return null;
    }
    
    /**Getter for the list of airplanes in landed this airport. It returns the list of airplanes landed in this moment, at this airport.
     * @return the list of airplanes in this airport, as a Red Black Search Tree.
     */
    public RedBlackBST<String, Aviao> getListagemAvioes()
    {
        return this.myAvioesRBBST;
    }
    
    /**Getter for the Active Status of this airport.
     * @return {@code true} if the airport is active, {@code false} if it's inactive.
     */
    public boolean getActiveStatus()
    {
        return this.active;
    }
    
    
    
    // Setters -----------------------------------------------------------------
    
    /**Setter for the name of this airport. This will set the airport's official name with the parameter value.
     * @param nomeAeroporto a String with the name of this airport.
     */
    public void setNome(String nomeAeroporto)
    {
        this.nome = nomeAeroporto;
    }

    /**Setter for the code of this airport. This will set the international airport's IATA 3 letter code with the parameter value.
     * @param idAeroporto a String with the code of this airport.
     */
    public void setIdAeroporto(String idAeroporto)
    {
        this.idAeroporto = idAeroporto;
    }

    /**Setter for the classification f this airport. This will set the classification for this airport with the parameter value.
     * @param classificacao a double with the classification value for this airport.
     */
    public void setClassificacao(double classificacao)
    {
        this.classificacao = classificacao;
    }

    /**Setter for the location of this airport. This will set the location where this airport is situated, with continent, country, city and coordinates information, with the parameter value.
     * @param myCidade a Location instance with the location information of this airport.
     */
    public void setMyCidade(Location myCidade)
    {
        this.myCidade = myCidade;
    }
    
    /**Setter for the quantity of flights for this airport. This will set the quantity of flights that already passed by this airport with the parameter value.
     * @param qtVoos an Integer with the value for the number of flights.
     */
    public void setQtVoos(Integer qtVoos)
    {
        this.qtVoos = qtVoos;
    }
    
    /**Setter for the quantity of passengers for this airport. This will set the quantity of passengers that already passed by this airport with the parameter value.
     * @param qtPax an Integer with the value for the number of passengers.
     */
    public void setQtPax(Integer qtPax)
    {
        this.qtPax = qtPax;
    }

    /**Setter for an airplane in this airport. This will set the airplane in the parameter value to this airport's list of airplanes landed.
     * @param aviao the airplane to add to the airport's list.
     */
    public void addMyAviao(Aviao aviao)
    {
        this.myAvioesRBBST.put(aviao.getNomeAviao(), aviao);
        aviao.setMyAeroporto(this.idAeroporto);
    }
    
    /**Remover for an airplane from this airport. This will remove the airplane passed on as a parameter from this airport's list of airplanes landed.
     * @param aviao the airplane to be removed from the airport's list.
     */
    public void removeMyAviao(Aviao aviao)
    {
        if (this.myAvioesRBBST.contains(aviao.getNomeAviao()))
        {
            this.myAvioesRBBST.delete(aviao.getNomeAviao());
        }
    }
    
    public void addAirlineConnection(AirlineConnection ac, EdgeWeightedSymbolDigraph ewSymbolDigraph)
    {
        if ((ac.from() == ewSymbolDigraph.indexOf(this.idAeroporto)) || (ac.to() == ewSymbolDigraph.indexOf(this.idAeroporto)))
        {
            this.myAirlineConnections.add(ac);
        }
    }

    /**
     * @param index the indexed position of the LigacaoAerea requested
     * @return the myLigacoesAereas
     */
    public LigacaoAerea getMyLigacaoAerea(Integer index)
    {
        return this.myLigacoesAereas.get(index);
    }
    public ArrayList<LigacaoAerea> getMyListaLigacoesAereas()
    {
        return this.myLigacoesAereas;
    }
    public AirlineConnection getMyAirlineConnectionFrom(int v)
    {
        for (AirlineConnection acIter : this.myAirlineConnections)
        {
            if (acIter.from() == v)
            {
                return acIter;
            }
        }
        
        return null;
    }
    public AirlineConnection getMyAirlineConnectionTo(int w)
    {
        for (AirlineConnection acIter : this.myAirlineConnections)
        {
            if (acIter.to() == w)
            {
                return acIter;
            }
        }
        
        return null;
    }
    public ArrayList<AirlineConnection> getListAirlineConnectionsFromThis(EdgeWeightedSymbolDigraph ewSymbolDigraph)
    {
        ArrayList<AirlineConnection> listACFromThis = new ArrayList<>();
        
        for (AirlineConnection acIter : this.myAirlineConnections)
        {
            if (acIter.from() == ewSymbolDigraph.indexOf(this.idAeroporto))
            {
                listACFromThis.add(acIter);
            }
        }
        
        return listACFromThis;
    }
    public ArrayList<AirlineConnection> getListAirlineConnectionsToThis(EdgeWeightedSymbolDigraph ewSymbolDigraph)
    {
        ArrayList<AirlineConnection> listACToThis = new ArrayList<>();
        
        for (AirlineConnection acIter : this.myAirlineConnections)
        {
            if (acIter.to() == ewSymbolDigraph.indexOf(this.idAeroporto))
            {
                listACToThis.add(acIter);
            }
        }
        
        return listACToThis;
    }
    public ArrayList<AirlineConnection> getListAirlineConnections()
    {
        return this.myAirlineConnections;
    }

    /**
     * @param myLigacaoAerea the LigacaoAerea to add
     */
    public void addMyLigacaoAerea(LigacaoAerea myLigacaoAerea)
    {
        this.myLigacoesAereas.add(myLigacaoAerea);
    }
    
    public RedBlackBST getMyVoosPartidas()
    {
        return this.myVoosPartidasRBBST;
    }
    public Voo getMyVooPartida(String idVoo)
    {
        return this.myVoosPartidasRBBST.get(idVoo);
    }
    public void setMyVooPartida(Voo voo)
    {
        this.myVoosPartidasRBBST.put(voo.getIdVoo(), voo);
    }
    
    public RedBlackBST getMyVoosChegadas()
    {
        return this.myVoosChegadasRBBST;
    }
    public Voo getMyVooChegada(String idVoo)
    {
        return this.myVoosChegadasRBBST.get(idVoo);
    }
    public void setMyVooChegada(Voo voo)
    {
        this.myVoosChegadasRBBST.put(voo.getIdVoo(), voo);
    }
    
    /**Setter for the airport's active status.
     * @param activeStatus {@code true} sets the airport as active, {@code false} sets the airport as inactive.
     */
    public void setActiveStatus(boolean activeStatus)
    {
        this.active = activeStatus;
    }
}