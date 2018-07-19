package classes;

import java.util.ArrayList;


public class ModeloAviao
{
    private String nome;
    private Integer velocidadeCruzeiro;
    private Integer altitudeCruzeiro;
    private Integer distanciaMax;
    private Integer passageirosMax;
    private Integer combustivelMax;
    private ArrayList<Aviao> listagemAvioes;

    
    /**
     * construtor para a classe ModeloAviao
     * @param nome                  (String)    nome do modelo do avião
     * @param velocidadeCruzeiro    (int)       velocidade de cruzeiro do modelo
     * @param altitudeCruzeiro      (int)       altitude de cruzeiro do modelo
     * @param distanciaMax          (int)       distancia máxima de viagem entre 2 aeroportos
     * @param passageirosMax        (int)       quantidade máxima de passageiros que transporta
     * @param combustivelMax        (int)       capacidade máxima do depósito de combustível
     */
    public ModeloAviao(String nome, int velocidadeCruzeiro, int altitudeCruzeiro, int distanciaMax, int passageirosMax, int combustivelMax)
    {
        this.nome = nome;
        this.velocidadeCruzeiro = velocidadeCruzeiro;
        this.altitudeCruzeiro = altitudeCruzeiro;
        this.distanciaMax = distanciaMax;
        this.passageirosMax = passageirosMax;
        this.combustivelMax = combustivelMax;
        this.listagemAvioes = new ArrayList<>();
    }
    
    /**
     * função que calcula e retorna o consumo de combustível ao fim de uma viagem
     * @param altitude  (int)   altitude, em km, de voo durante a viagem
     * @param distancia (int)   distancia viajada, em km
     * @return          (int)   combustível consumido pela viagem, em litros
     */
    public Integer consumoViagem(Integer altitude, Integer distancia)
    {
        if (distancia > this.distanciaMax)
        {
            System.out.println("Distancia fora do limite");
            return 0;
        }
        
        if ((altitude > this.altitudeCruzeiro) || (altitude < this.altitudeCruzeiro))
        {
            System.out.println("acima ou abaixo da altitude de cruzeiro");
            return (mediaDeConsumo() + ((int)(mediaDeConsumo() * 0.1f) * Math.abs(multiplicador(altitude - this.altitudeCruzeiro, 1000))));
        }
        
        System.out.println("altitude de cruzeiro");
        return mediaDeConsumo();
    }
    
    private Integer multiplicador(Integer dividendo, Integer divisor)
    {
        int result = Math.abs(dividendo / divisor);
        if ((dividendo % divisor) != 0)
        {
            result++;
        }
        
        if (dividendo < 0)
        {
            return result * (-1);
        }
        return result;
    }
    
    /**
     * Calcula e retorna a média de consumo do avião como um valor inteiro
     * @return (int) média de consumo de unidade de combustivel por unidade de distância
     */
    public Integer mediaDeConsumo()
    {
        return (this.combustivelMax / this.distanciaMax);
    }
    
    @Override
    public String toString()
    {
        String str = this.nome;
        str = str.concat("\tvel.cruzeiro: ").concat(String.valueOf(this.velocidadeCruzeiro)).concat(" km/h");
        str = str.concat("\talt.cruzeiro: ").concat(String.valueOf(this.altitudeCruzeiro)).concat(" km");
        str = str.concat("\tdist.máxima: ").concat(String.valueOf(this.distanciaMax)).concat(" km");
        str = str.concat("\tpass.máximo: ").concat(String.valueOf(this.passageirosMax)).concat(" pax");
        str = str.concat("\tcomb.máximo: ").concat(String.valueOf(this.combustivelMax)).concat(" lt");
        
        return str;
    }

    
    
    /**
     * @return the nome
     */
    public String getNome()
    {
        return this.nome;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }

    /**
     * @return the velocidadeCruzeiro
     */
    public Integer getVelocidadeCruzeiro()
    {
        return this.velocidadeCruzeiro;
    }
    public void setVelocidadeCruzeiro(int velocidadeCruzeiro)
    {
        this.velocidadeCruzeiro = velocidadeCruzeiro;
    }

    /**
     * @return the altitudeCruzeiro
     */
    public Integer getAltitudeCruzeiro()
    {
        return this.altitudeCruzeiro;
    }
    public void setAltitudeCruzeiro(int altitudeCruzeiro)
    {
        if ((altitudeCruzeiro < 20000) || (altitudeCruzeiro > 40000) || ((altitudeCruzeiro % 1000) != 0))
        {
            System.out.println("A Altitude de Cruzeiro deve ser um valor entre 20,000 e 40,000 e múltiplo de 1,000!");
            return;
        }
        this.altitudeCruzeiro = altitudeCruzeiro;
    }

    /**
     * @return the distanciaMax
     */
    public Integer getDistanciaMax()
    {
        return this.distanciaMax;
    }
    public void setDistanciaMax(int distanciaMax)
    {
        this.distanciaMax = distanciaMax;
    }

    /**
     * @return the passageirosMax
     */
    public Integer getPassageirosMax()
    {
        return this.passageirosMax;
    }
    public void setPassageirosMax(int passageirosMax)
    {
        this.passageirosMax = passageirosMax;
    }

    /**
     * @return the combustivelMax
     */
    public Integer getCombustivelMax()
    {
        return this.combustivelMax;
    }
    public void setCombustivelMax(int combustivelMax)
    {
        this.combustivelMax = combustivelMax;
    }
    
    public ArrayList getListagemAviao()
    {
        return this.listagemAvioes;
    }
    public void addAviao(Aviao nomeAviao)
    {
        this.listagemAvioes.add(nomeAviao);
    }
}