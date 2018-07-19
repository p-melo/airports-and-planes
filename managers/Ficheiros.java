/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import static testers.TestClass.abrirFicheiro;

/**The ficheiros class contains the methods to access files. The methods in this class are either static  and serve to open, close, read and write information to a file.
 * @author Modusaleatorios
 */
public class Ficheiros
{
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
                if (fl.compareTo("airports.txt") == 0)
                {
                    instanciaAeroporto(line, bd);
                }
                else if (fl.compareTo("airplanes.txt") == 0)
                {
                    instanciaAviao(line, bd);
                }
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
        String idAeroporto = str.nextToken();
        String cidade = str.nextToken();
        String pais = str.nextToken();
        String continente = str.nextToken();
        Double classificacao = Double.parseDouble(str.nextToken());
        Double latitude = Double.parseDouble(str.nextToken());
        Double longitude = Double.parseDouble(str.nextToken());
        
        bd.setAeroporto(nomeAeroporto, idAeroporto, classificacao, cidade, pais, continente, latitude, longitude);
    }
    
    private static void instanciaAviao(String inputLine, BD bd)
    {
        StringTokenizer str = new StringTokenizer(inputLine, ";");
        
        Integer idAviao = Integer.parseInt(str.nextToken());
        String nomeModeloAviao = str.nextToken();
        String nomeAviao = str.nextToken();
        String companhiaAerea = str.nextToken();
        Integer velocidadeCruzeiro = Integer.parseInt(str.nextToken());
        Integer altitudeCruzeiro = Integer.parseInt(str.nextToken());
        Integer distanciaMax = Integer.parseInt(str.nextToken());
        String idAeroporto = str.nextToken();
        Integer PassageirosMax = Integer.parseInt(str.nextToken());
        Integer CombustivelMax = Integer.parseInt(str.nextToken());
        
        bd.setAviao(nomeAviao, nomeModeloAviao, velocidadeCruzeiro, altitudeCruzeiro, distanciaMax, PassageirosMax, CombustivelMax, companhiaAerea, idAeroporto);
    }
}
