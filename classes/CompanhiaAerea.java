package classes;

import java.util.ArrayList;


public class CompanhiaAerea
{
    private String nome;
    private ArrayList<Aviao> listagemAvioes;

    public CompanhiaAerea(String nome)
    {
        this.nome = nome;
        this.listagemAvioes = new ArrayList<>();
    }

    /**
     * @return the nome
     */
    public String getNome()
    {
        return this.nome;
    }

    /**
     * @return the myAvioes
     */
    public ArrayList<Aviao> getListagemAvioes()
    {
        return this.listagemAvioes;
    }
    public void addAviao(Aviao nomeAviao)
    {
        this.listagemAvioes.add(nomeAviao);
    }
}