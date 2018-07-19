/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testers;

import java.util.ArrayList;
import edu.princeton.cs.algs4.*;
import classes.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import managers.BD;

/**
 *
 * @author Modusaleatorios
 */
public class TestClass
{

    public static void main(String[] args) throws IOException
    {
        lastTest();
    }
    
    public static FileReader abrirFicheiro(String fName)
    {
        try
        {
            File f = new File("build//classes//database", fName);
            FileReader fr = new FileReader(f);
            return fr;
        } catch (FileNotFoundException fnfException)
        {
            System.err.println("O ficheiro não foi encontrado - Programa Terminado");
            System.exit(1);
        }
        return null;
    }
    
    public static void lerFicheiro(String fl, BD bd)
    {
        FileReader fr = abrirFicheiro(fl);
        BufferedReader br = new BufferedReader(fr);
        String line;
        
        try
        {
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {
                instanciaAeroporto(line, bd);
                /*
                System.out.println(line);
                StringTokenizer str = new StringTokenizer(line, ";");
                while (str.hasMoreTokens())
                {
                    System.out.println(str.nextToken());
                }
                */
            }
        } catch (IOException iOException)
        {
            System.err.println("Erro ao ler o ficheiro - Programa Terminado");
            System.exit(1);
        }
        
        try
        {
            br.close();
            fr.close();
        } catch (IOException iOException)
        {
            System.err.println("Erro ao fechar o ficheiro - Programa Terminado");
            System.exit(1);
        }
    }
    
    private static void instanciaAeroporto(String inputLine, BD bd)
    {
        StringTokenizer str = new StringTokenizer(inputLine, ";");
        
        String nomeAeroporto = str.nextToken();
        System.out.println(nomeAeroporto);
        String idAeroporto = str.nextToken();
        System.out.println(idAeroporto);
        String cidade = str.nextToken();
        System.out.println(cidade);
        String pais = str.nextToken();
        System.out.println(pais);
        String continente = str.nextToken();
        System.out.println(continente);
        Double classificacao = Double.parseDouble(str.nextToken());
        System.out.println(classificacao);
        Double latitude = Double.parseDouble(str.nextToken());
        System.out.println(latitude);
        Double longitude = Double.parseDouble(str.nextToken());
        System.out.println(longitude);

        bd.setAeroporto(nomeAeroporto, idAeroporto, classificacao, cidade, pais, continente, latitude, longitude);
    }
    
    public static void lastTest()
    {
        //Lista AEROPORTOS
        BD baseDados = new BD();
        //Ficheiros.abrirFicheiro("C:\\Users\\Modusaleatorios\\Documents\\NetBeansProjects\\17032_VoosCompanhias\\src\\database\\airports.txt");
        lerFicheiro("airports.txt", baseDados);
        //Ficheiros.fecharFicheiro();
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        //Aviao
        ModeloAviao modeloAviao = new ModeloAviao("Airbus340", 871, 8000, 5900, 380, 274876);
        
        Graph grafoAeroportos = new Graph(aeroportosRBST.size());
        
        
        for (String nomeAeroportoStart : aeroportosRBST.keys())
        {
            Aeroporto aeroportoStart = aeroportosRBST.get(nomeAeroportoStart);
            int indexAeroportoStart = aeroportosRBST.rank(nomeAeroportoStart);
            
            for (String nomeAeroporto : aeroportosRBST.keys())
            {
                Aeroporto aeroportoIterado = aeroportosRBST.get(nomeAeroporto);
                int indexAeroportoIterado = aeroportosRBST.rank(nomeAeroporto);

                if (aeroportoStart.getIdAeroporto().compareTo(aeroportoIterado.getIdAeroporto()) != 0)
                {
                    if (aeroportoStart.getMyCidade().distanciaCidades(aeroportoIterado.getMyCidade()) <= modeloAviao.getDistanciaMax())
                    {
                        grafoAeroportos.addEdge(indexAeroportoStart, indexAeroportoIterado);
                    }
                }
            }
        }
        
        Aeroporto aeroportoStart = aeroportosRBST.get("OPO");
        int indexAeroportoStart = aeroportosRBST.rank("OPO");
        
        System.out.println("Avião - Dist Max: " + modeloAviao.getDistanciaMax());
        System.out.println("Partida: " + aeroportoStart.getMyCidade().getNome());
        System.out.println("Chegadas:");
        for (int i : grafoAeroportos.adj(indexAeroportoStart))
        {
            Aeroporto aeroportoIterado = aeroportosRBST.get(aeroportosRBST.select(i));
            int distancia = aeroportoStart.getMyCidade().distanciaCidades(aeroportosRBST.get(aeroportosRBST.select(i)).getMyCidade());
            System.out.println("- " + aeroportoIterado.getMyCidade().getNome() + " - " + distancia);
        }
        
        Aeroporto aeroportoFinish = aeroportosRBST.get("GIG");
        int indexAeroportoFinish = aeroportosRBST.rank("GIG");
        
        BreadthFirstPaths bFP = new BreadthFirstPaths(grafoAeroportos, indexAeroportoStart);
        if (bFP.hasPathTo(indexAeroportoFinish))
        {
            System.out.println(aeroportoStart.getMyCidade().getNome() + " --> " + aeroportoFinish.getMyCidade().getNome());
            
            for (int i : bFP.pathTo(indexAeroportoFinish))
            {
                System.out.println("+ " + aeroportosRBST.get(aeroportosRBST.select(i)).getMyCidade().getNome() + " +");
            }
        }
    }
}
