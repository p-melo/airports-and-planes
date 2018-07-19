package classes;

import edu.princeton.cs.algs4.RedBlackBST;

/**
 * 
 * @author Plácido Melo
 */
public class Aviao
{
    /**The airplane baptism name.*/
    private String nomeAviao;
    /**The airplane model.*/
    private ModeloAviao myModeloAviao;
    /**The airplane airline company.*/
    private CompanhiaAerea myCompanhiaAerea;
    /**The airplane airport reference.*/
    private String myIdAeroporto;
    /**The airplane total quantity of passengers transported.*/
    private Integer qtPax;
    /**The airplane list of flights already flown.*/
    private RedBlackBST<String, Voo> myVoo;
    
    /**{@code constructor} for the Aviao class.
     * @param nomeAviao the airplane's name
     * @param modeloAviao the airplane's model
     * @param companhiaAerea the airplane's airline company
     * @param idAeroporto the code of teh airport where it is landed
     */
    public Aviao(String nomeAviao, ModeloAviao modeloAviao, CompanhiaAerea companhiaAerea, String idAeroporto)
    {
        this.nomeAviao = nomeAviao;
        this.myModeloAviao = modeloAviao;
        this.myCompanhiaAerea = companhiaAerea;
        this.myIdAeroporto = idAeroporto;
        this.qtPax = 0;
        this.myVoo = new RedBlackBST<>();
    }

    @Override
    public String toString()
    {
        String str = "";
        str = str.concat(this.myModeloAviao.getNome()).concat(", ").concat(this.nomeAviao).concat(", ").concat(this.myCompanhiaAerea.getNome());
        str = str.concat(", ").concat(String.valueOf(this.myModeloAviao.getVelocidadeCruzeiro()));
        str = str.concat(", ").concat(String.valueOf(this.myModeloAviao.getAltitudeCruzeiro()));
        str = str.concat(", ").concat(String.valueOf(this.myModeloAviao.getDistanciaMax()));
        str = str.concat(", ").concat(this.myIdAeroporto);
        str = str.concat(", ").concat(String.valueOf(this.myModeloAviao.getPassageirosMax()));
        str = str.concat(", ").concat(String.valueOf(this.myModeloAviao.getCombustivelMax()));
        
        return str;
    }

    /**Getter for the airplane's name.
     * @return the airplane's name.
     */
    public String getNomeAviao()
    {
        return this.nomeAviao;
    }

    /**Getter for the airport where the airplane is landed.
     * @return the airport code.
     */
    public String getMyAeroporto()
    {
        return this.myIdAeroporto;
    }
    
    /**Setter for the airport where the airplane is landed.
     * @param idAeroporto the airport code.
     */
    public void setMyAeroporto(String idAeroporto)
    {
        this.myIdAeroporto = idAeroporto;
    }
    
    /**Getter for the airplane's total amount of passengers already transported.
     * @return the total amount of passengers transported.
     */
    public Integer getQtPax()
    {
        return this.qtPax;
    }
    
    /**Setter for the airplane's total amount of passengers already transported.
     * @param qtPax the total amount of passengers transported.
     */
    public void setQtPax(Integer qtPax)
    {
        this.qtPax = qtPax;
    }

    /**Getter for the airplane's flight of a given date.
     * @param data the date.
     * @return the flight made in that date.
     */
    public Voo getMyVoo(CalendarDate data)
    {
        return this.myVoo.get(data.getOrderedDate());
    }
    
    /**Getter for the airplane's list of flights.
     * @return the airplane's list of flights.
     */
    public RedBlackBST<String, Voo> getMyVoosRBST()
    {
        return this.myVoo;
    }
    
    /**Setter to add a flight to the list of airplane's flights.
     * @param myVoo the flight.
     */
    public void setMyVoo(Voo myVoo)
    {
        this.myVoo.put(String.valueOf(myVoo.getData().getOrderedDate()), myVoo);
    }
    
    /**Getter for the airplane's model.
     * @return the airplane model.
     */
    public ModeloAviao getMyModeloAviao()
    {
        return this.myModeloAviao;
    }
    
    /**Getter for the airplane's airline company.
     * @return the airline company.
     */
    public CompanhiaAerea getMyCompanhiaAerea()
    {
        return this.myCompanhiaAerea;
    }
}