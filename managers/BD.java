/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Graph;
import classes.*;


/**
 *
 * @author Modusaleatorios
 */
public class BD
{
    private RedBlackBST<String, Aeroporto> aeroportosRBST;
    private BinarySearchST<String, Location> cidadesBSST;
    
    private RedBlackBST<String, Aviao> avioesRBST;
    private BinarySearchST<String, ModeloAviao> modAvioesBSST;
    private BinarySearchST<String, CompanhiaAerea> companhiasAereasBSST;
    
    private RedBlackBST<String, Voo> voosRBST;
    
    private Graph grafoLigacoes;
    private RedBlackBST<String, LigacaoAerea> ligacaoAereaRBST;
    
    
    public BD()
    {
        this.aeroportosRBST = new RedBlackBST<>();
        this.cidadesBSST = new BinarySearchST<>();
        this.avioesRBST = new RedBlackBST<>();
        this.modAvioesBSST = new BinarySearchST<>();
        this.companhiasAereasBSST = new BinarySearchST<>();
        this.voosRBST = new RedBlackBST<>();
        this.ligacaoAereaRBST = new RedBlackBST<>();
    }
    
    /**
     * creates a new Airport, if it doesn't already exists one with those attributes.
     * It then goes through every existing Airplanes and places, in the created Airport, every Airplane referred to itself.
     * @param nomeAeroporto the name of the Airport.
     * @param idAeroporto the id-code of the Airport
     * @param classificacao the Airport classification
     * @param nomeCidade the name of the Airport's Town
     * @param nomePais the name of the Airport's country
     * @param nomeContinente the name of the Airport's continent
     * @param latitude the town's latitude
     * @param longitude the town's longitude
     */
    public void setAeroporto(String nomeAeroporto, String idAeroporto, double classificacao, String nomeCidade, String nomePais, String nomeContinente, double latitude, double longitude)
    {
        if (!this.aeroportosRBST.contains(idAeroporto))
        {
            this.setCidade(nomeCidade, nomePais, nomeContinente, latitude, longitude);
            this.aeroportosRBST.put(idAeroporto, new Aeroporto(nomeAeroporto, idAeroporto, classificacao, this.cidadesBSST.get(nomeCidade)));
            
            for (String nomeAviao : avioesRBST.keys())
            {
                if (avioesRBST.get(nomeAviao).getMyAeroporto().compareTo(idAeroporto) == 0)
                {
                    this.aeroportosRBST.get(idAeroporto).addMyAviao(this.avioesRBST.get(nomeAviao));
                }
            }
        }
    }
    
    public void setLigacoesAereasRBST()
    {
        this.grafoLigacoes = GrafoAeroportos.createGrafo(this.aeroportosRBST, this.ligacaoAereaRBST);
        for (String idAeroportoPartida : this.aeroportosRBST.keys())
        {
            Aeroporto aeroportoIterado = this.aeroportosRBST.get(idAeroportoPartida);
            for (String idLigacaoAerea : this.ligacaoAereaRBST.keys())
            {
                LigacaoAerea ligacaoAereaIterada = this.ligacaoAereaRBST.get(idLigacaoAerea);
                if (ligacaoAereaIterada.getMyAeroporto(0).getIdAeroporto().compareTo(idAeroportoPartida) == 0)
                {
                   aeroportoIterado.addMyLigacaoAerea(ligacaoAereaIterada);
                }
            }
        }
    }
    
    public Aeroporto getAeroporto(String idAeroporto)
    {
        return this.aeroportosRBST.get(idAeroporto);
    }
    public RedBlackBST<String, Aeroporto> getRBSTAeroportos()
    {
        return this.aeroportosRBST;
    }
    
    public void setCidade(String nomeCidade, String nomePais, String nomeContinente, double latitude, double longitude)
    {
        if (!this.cidadesBSST.contains(nomeCidade))
        {
            this.cidadesBSST.put(nomeCidade, new Location(nomeCidade, nomePais, nomeContinente, latitude, longitude));
        }
    }
    
    public Location getCidade(String nomeCidade)
    {
        return this.cidadesBSST.get(nomeCidade);
    }
    
    public void setAviao(String nomeAviao,
            String nomeModelo, Integer velocidadeCruzeiro, Integer altitudeCruzeiro, Integer distanciaMax, Integer passageirosMax, Integer combustivelMax,
            String companhiaAerea,
            String idAeroporto)
    {
        if (!this.avioesRBST.contains(nomeAviao))
        {
            this.setModeloAviao(nomeModelo, velocidadeCruzeiro, altitudeCruzeiro, distanciaMax, passageirosMax, combustivelMax);
            this.setCompanhiaAerea(companhiaAerea);
            
            this.avioesRBST.put(nomeAviao, new Aviao(nomeAviao, this.getModeloAviao(nomeModelo), this.getCompanhiaAerea(companhiaAerea), idAeroporto));
            
            this.getModeloAviao(nomeModelo).addAviao(this.avioesRBST.get(nomeAviao));
            this.getCompanhiaAerea(companhiaAerea).addAviao(this.avioesRBST.get(nomeAviao));
            
            if (this.aeroportosRBST.contains(idAeroporto))
            {
                this.aeroportosRBST.get(idAeroporto).addMyAviao(this.avioesRBST.get(nomeAviao));
            }
        }
    }
    
    public Aviao getAviao(String nomeAviao)
    {
        return this.avioesRBST.get(nomeAviao);
    }
    public RedBlackBST getRBSTAvioes()
    {
        return this.avioesRBST;
    }
    
    public void setModeloAviao(String nomeModelo, int velocidadeCruzeiro, int altitudeCruzeiro, int distanciaMax, int passageirosMax, int combustivelMax)
    {
        if (!this.modAvioesBSST.contains(nomeModelo))
        {
            this.modAvioesBSST.put(nomeModelo, new ModeloAviao(nomeModelo, velocidadeCruzeiro, altitudeCruzeiro, distanciaMax, passageirosMax, combustivelMax));
        }
    }
    
    public ModeloAviao getModeloAviao(String nomeModelo)
    {
        return this.modAvioesBSST.get(nomeModelo);
    }
    public BinarySearchST<String, ModeloAviao> getListagemModelosAvioes()
    {
        return this.modAvioesBSST;
    }
    
    public void setCompanhiaAerea(String nomeCompanhia)
    {
        if (!this.companhiasAereasBSST.contains(nomeCompanhia))
        {
            this.companhiasAereasBSST.put(nomeCompanhia, new CompanhiaAerea(nomeCompanhia));
        }
    }
    
    public CompanhiaAerea getCompanhiaAerea(String nomeCompanhia)
    {
        return this.companhiasAereasBSST.get(nomeCompanhia);
    }
    public BinarySearchST<String, CompanhiaAerea> getCompanhiasAereasBSST()
    {
        return this.companhiasAereasBSST;
    }
    
    public void setVoos(CalendarDate data)
    {
        Integer randomPassengers, randomAirport;
        
        for (String nomeAviao : this.avioesRBST.keys())
        {
            boolean vooPossivel = true;
            Aviao aviaoIterado = this.avioesRBST.get(nomeAviao);
            randomPassengers = (int) (Math.random() * aviaoIterado.getMyModeloAviao().getPassageirosMax());
            randomAirport = (int) (Math.random() * this.aeroportosRBST.size());
            String idArpDestino = this.aeroportosRBST.get(this.aeroportosRBST.select(randomAirport)).getIdAeroporto();
            if (idArpDestino.compareTo(aviaoIterado.getMyAeroporto()) != 0)
            {
                BinarySearchST<Integer, LigacaoAerea> ligacoesVoo = GrafoAeroportos.gravarArrayLigacoesVoo(aeroportosRBST, grafoLigacoes, aviaoIterado.getMyAeroporto(), idArpDestino, ligacaoAereaRBST);
                
                for (Integer i : ligacoesVoo.keys())
                {
                    LigacaoAerea ligacaoAereaIterada = ligacoesVoo.get(i);
                    if (ligacaoAereaIterada.getDistancia() > aviaoIterado.getMyModeloAviao().getDistanciaMax())
                    {
                        vooPossivel = false;
                    }
                }
                if (vooPossivel == false)
                {
                    continue;
                }
                
                Voo v = new Voo(randomPassengers, data, this.avioesRBST.get(nomeAviao), this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()), this.aeroportosRBST.get(idArpDestino), ligacoesVoo);
                
                System.out.println();
                System.out.println(v);
                
                this.voosRBST.put(v.getIdVoo(), v);
                this.avioesRBST.get(nomeAviao).setMyVoo(v);
                this.avioesRBST.get(nomeAviao).setQtPax(this.avioesRBST.get(nomeAviao).getQtPax() + randomPassengers);
                this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()).setMyVooPartida(v);
                this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()).setQtVoos(this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()).getQtVoos() + 1);
                this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()).setQtPax(this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()).getQtPax() + randomPassengers);
                this.aeroportosRBST.get(idArpDestino).setMyVooChegada(v);
                
                this.aeroportosRBST.get(this.avioesRBST.get(nomeAviao).getMyAeroporto()).removeMyAviao(this.avioesRBST.get(nomeAviao));
                this.aeroportosRBST.get(idArpDestino).addMyAviao(this.avioesRBST.get(nomeAviao));
            }
        }
    }
    
    public RedBlackBST<String, Voo> getVoosRBST()
    {
        return this.voosRBST;
    }
}