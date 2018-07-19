package classes;


public class LigacaoAerea
{
    private String idLigacao;
    private Integer distancia;
    private Integer altitude;
    private Integer velocidadeVento;
    private Aeroporto [] myAeroportos = {null, null};

    public LigacaoAerea(String idLigacao, Integer altitude, Integer velocidadeVento, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino)
    {
        this.idLigacao = idLigacao;
        this.altitude = altitude;
        this.velocidadeVento = velocidadeVento;
        this.myAeroportos[0] = aeroportoOrigem;
        this.myAeroportos[1] = aeroportoDestino;
        
        this.distancia = aeroportoOrigem.getMyCidade().distanciaCidades(aeroportoDestino.getMyCidade());
    }
    
    @Override
    public String toString()
    {
        String str = "".concat(this.idLigacao);
        str = str.concat(";").concat(String.valueOf(this.distancia)).concat(";").concat(String.valueOf(this.altitude));
        str = str.concat(";").concat(String.valueOf(this.velocidadeVento));
        str = str.concat(";").concat(this.myAeroportos[0].getIdAeroporto()).concat(";").concat(this.myAeroportos[1].getIdAeroporto());
        
        return str;
    }
    
    /**
     * @return the idLigacao
     */
    public String getIdLigacao()
    {
        return this.idLigacao;
    }
    /**
     * @param idLigacao the idLigacao to set
     */
    public void setIdLigacao(String idLigacao)
    {
        this.idLigacao = idLigacao;
    }

    /**
     * @return the distancia
     */
    public Integer getDistancia()
    {
        return this.distancia;
    }

    /**
     * @return the altitude
     */
    public Integer getAltitude()
    {
        return this.altitude;
    }
    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(Integer altitude)
    {
        this.altitude = altitude;
    }

    /**
     * @return the velocidadeVento
     */
    public Integer getVelocidadeVento()
    {
        return this.velocidadeVento;
    }
    /**
     * @param velocidadeVento the velocidadeVento to set
     */
    public void setVelocidadeVento(Integer velocidadeVento)
    {
        this.velocidadeVento = velocidadeVento;
    }

    /**
     * @param index the indexed value of the airport
     * @return the myAeroportos
     */
    public Aeroporto getMyAeroporto(Integer index)
    {
        return myAeroportos[index];
    }
    /**
     * @param index the index value of the aeroporto
     * @param aeroporto the aeroporto to set
     */
    public void setMyAeroportos(Integer index, Aeroporto aeroporto)
    {
        this.myAeroportos[index] = aeroporto;
    }
}