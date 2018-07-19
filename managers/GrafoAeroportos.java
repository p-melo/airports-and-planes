/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import classes.*;
import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.BinarySearchST;
import java.util.ArrayList;

/**
 *
 * @author Modusaleatorios
 */
public class GrafoAeroportos
{
    public static Graph createGrafo(RedBlackBST<String, Aeroporto> aeroportosRBST, RedBlackBST<String, LigacaoAerea> ligacoesAereasRBST)
    {
        Graph grafoAeroportos = new Graph(aeroportosRBST.size());
        
        
        for (String idAeroportoStart : aeroportosRBST.keys())
        {
            Aeroporto aeroportoStart = aeroportosRBST.get(idAeroportoStart);
            int indexAeroportoStart = aeroportosRBST.rank(idAeroportoStart);
            
            for (String idAeroportoEnd : aeroportosRBST.keys())
            {
                Aeroporto aeroportoIterado = aeroportosRBST.get(idAeroportoEnd);
                int indexAeroportoIterado = aeroportosRBST.rank(idAeroportoEnd);

                if (aeroportoStart.getIdAeroporto().compareTo(aeroportoIterado.getIdAeroporto()) != 0)
                {
                    if (aeroportoStart.getMyCidade().distanciaCidades(aeroportoIterado.getMyCidade()) <= 5900)
                    {
                        String idLigacao = "".concat(idAeroportoStart).concat(idAeroportoEnd);
                        grafoAeroportos.addEdge(indexAeroportoStart, indexAeroportoIterado);
                        ligacoesAereasRBST.put(idLigacao, new LigacaoAerea(idLigacao, 10000, 1000, aeroportoStart, aeroportoIterado));
                    }
                }
            }
        }
        
        return grafoAeroportos;
    }
    
    public static BinarySearchST<Integer, LigacaoAerea> gravarArrayLigacoesVoo(RedBlackBST<String, Aeroporto> aeroportosRBST, Graph grafoAeroportos, String idPartida, String idChegada, RedBlackBST<String, LigacaoAerea> ligacaoAereaRBST)
    {
        BinarySearchST<Integer, LigacaoAerea> ligacoesAereasBSST = new BinarySearchST<>();
        int indexAeroportoStart = aeroportosRBST.rank(idPartida);
        int indexAeroportoFinish = aeroportosRBST.rank(idChegada);
        int index = 0;
        
        String idAeroportoStart = null, idAeroportoEnd = null;
        
        BreadthFirstPaths bFP = new BreadthFirstPaths(grafoAeroportos, indexAeroportoStart);
        if (bFP.hasPathTo(indexAeroportoFinish))
        {
            for (int i : bFP.pathTo(indexAeroportoFinish))
            {
                idAeroportoEnd = aeroportosRBST.get(aeroportosRBST.select(i)).getIdAeroporto();
                
                if ((idAeroportoStart != null) && (idAeroportoEnd != null))
                {
                    String idLigacao = "".concat(idAeroportoStart).concat(idAeroportoEnd);
                    ligacoesAereasBSST.put(index, ligacaoAereaRBST.get(idLigacao));
                    index++;
                }
                idAeroportoStart = idAeroportoEnd;
                idAeroportoEnd = null;
            }
        }
        return ligacoesAereasBSST;
    }
}