/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg17032_vooscompanhias;

import managers.*;
import classes.*;
import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.RedBlackBST;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author Modusaleatorios
 */
public class MainProjeto
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        BD baseDados = new BD();
        CalendarDate data = new CalendarDate(1, 1, 2016);
        
        //carregarFicheiroAeroportos(baseDados);
        
        //carregarFicheiroAvioes(baseDados);
        //carregarFicheiroAeroportos(baseDados);
        //testeModeloAviao(baseDados.getListagemModelosAvioes());
        
        menuPrincipal(baseDados, data);
    }
    
    private static void menuPrincipal(BD baseDados, CalendarDate data)
    {
        Scanner input = new Scanner(System.in);
        Integer menuChoice;
        
        do
        {
            System.out.println();
            System.out.println("1 - Teste : Aviões     : Carregar do Ficheiro");
            System.out.println("2 - Teste : Aeroportos : Carregar do Ficheiro");
            System.out.println("3 - Teste : Operações");
            System.out.println("4 - Teste : Aviões     : Gravar em Ficheiro");
            System.out.println("5 - Teste : Aeroportos : Gravar em Ficheiro");
            System.out.print("6 - SAIR\n>> ");
            
            menuChoice = input.nextInt();
            switch(menuChoice)
            {
                case 1:
                    carregarFicheiroAvioes(baseDados);
                    break;
                case 2:
                    carregarFicheiroAeroportos(baseDados);
                    baseDados.setLigacoesAereasRBST();
                    break;
                case 3:
                    if ((baseDados.getRBSTAeroportos().isEmpty()) || (baseDados.getRBSTAvioes().isEmpty()))
                    {
                        System.out.println("!! TEM QUE CARREGAR OS FICHEIROS !!");
                        break;
                    }
                    menuOperacoes(baseDados, data);
                    break;
                case 4:
                    //gravarFicheiroAvioes(baseDados);
                    break;
                case 5:
                    //gravarFicheiroAeroportos(baseDados);
                    break;
            }
        } while (menuChoice != 6);
    }
    
    private static void menuOperacoes(BD baseDados, CalendarDate data)
    {
        Scanner input = new Scanner(System.in);
        Integer menuChoice;
        
        do
        {
            System.out.println();
            System.out.println(" 1 - Teste : Imprimir Info sobre Avião");
            System.out.println(" 2 - Teste : Imprimir Info sobre Aeroporto");
            System.out.println(" 3 - Teste : Imprimir Aviões em Aeroporto");
            System.out.println(" 4 - Teste : Imprimir Aroportos em País/Continente");
            System.out.println(" 5 - Teste : UM MES DE VIAGENS");
            System.out.println(" 6 - Teste : Imprimir Viagens com Origem/Destino num Aeroporto");
            System.out.println(" 7 - Teste : Imprimir Viagens de um Avião");
            System.out.println(" 8 - Teste : Imprimir Viagens num Periodo de Tempo");
            System.out.println(" 9 - Teste : Imprimir Voos com Origem/Destino num Aeroporto");
            System.out.println("10 - Teste : Imprimir Aeroporto com mais Tráfego");
            System.out.println("11 - Teste : Imprimir Voo com mais Passageiros");
            System.out.println("12 - Teste : Imprimir Aeroporto com mais Passageiros");
            System.out.println("13 - Teste : Inserir/Remover/Editar novo elemento");
            System.out.println("14 - Teste : Calcular custo/consumo de viagem");
            System.out.print("15 - SAIR\n>> ");
            
            menuChoice = input.nextInt();
            switch(menuChoice)
            {
                case 1:
                    menuImprimirInfoAviao(baseDados);
                    break;
                case 2:
                    menuImprimirInfoAeroporto(baseDados);
                    break;
                case 3:
                    menuImprimirAvioesInAeroporto(baseDados);
                    break;
                case 4:
                    menuImprimirAeroportoInPais(baseDados);
                    break;
                case 5:
                    passarUmMes(baseDados, data);
                    break;
                case 6:
                    imprimirAeroportoViagensOrigDest(baseDados);
                    break;
                case 7:
                    imprimirAviaoViagens(baseDados);
                    break;
                case 10:
                    imprimirAeroportoMaisTrafego(baseDados);
                    break;
                case 11:
                    imprimirAviaoMaisPassageiros(baseDados);
                    break;
                case 12:
                    imprimirAeroportoMaisPassageiros(baseDados);
                    break;
                case 14:
                    imprimirVoosRBST(baseDados);
                    break;
            }
        } while (menuChoice != 15);
    }
    
    private static void imprimirVoosRBST(BD baseDados)
    {
        RedBlackBST<String, Voo> voosRBST = baseDados.getVoosRBST();
        
        for (String idVoo : voosRBST.keys())
        {
            Voo vooIterado = voosRBST.get(idVoo);
            System.out.println(vooIterado);
        }
    }
    
    private static void imprimirAviaoMaisPassageiros(BD baseDados)
    {
        RedBlackBST<String, Aviao> avioesRBST = baseDados.getRBSTAvioes();
        
        RedBlackBST<Integer, Aviao> paxAvioesRBST = new RedBlackBST<>();
        for (String a : avioesRBST.keys())
        {
            paxAvioesRBST.put(avioesRBST.get(a).getQtPax(), avioesRBST.get(a));
        }
        
        Aviao aviaoMaisPax = paxAvioesRBST.get(paxAvioesRBST.max());
        System.out.println("Aviao que transportou mais passageiros: " + aviaoMaisPax.getNomeAviao() + " : " + aviaoMaisPax.getQtPax() + " pax");
    }
    
    private static void imprimirAeroportoMaisPassageiros(BD baseDados)
    {
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        
        RedBlackBST<Integer, Aeroporto> paxAeroportosRBST = new RedBlackBST<>();
        for (String a : aeroportosRBST.keys())
        {
            paxAeroportosRBST.put(aeroportosRBST.get(a).getQtPax(), aeroportosRBST.get(a));
        }
        
        Aeroporto aeroportoMaisPax = paxAeroportosRBST.get(paxAeroportosRBST.max());
        System.out.println("Aeroporto por onde passaram mais passageiros: " + aeroportoMaisPax.getNome() + " : " + aeroportoMaisPax.getQtPax() + " pax");
    }
    
    private static void imprimirAeroportoMaisTrafego(BD baseDados)
    {
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        
        RedBlackBST<Integer, Aeroporto> trafegoAeroportosRBST = new RedBlackBST<>();
        for (String a : aeroportosRBST.keys())
        {
            trafegoAeroportosRBST.put(aeroportosRBST.get(a).getQtVoos(), aeroportosRBST.get(a));
        }
        
        Aeroporto aeroportoMaisTrafego = trafegoAeroportosRBST.get(trafegoAeroportosRBST.max());
        System.out.println("Aeroporto com mais tráfego: " + aeroportoMaisTrafego.getNome() + " : " + aeroportoMaisTrafego.getQtVoos() + " voos");
    }
    
    private static void imprimirAviaoViagens(BD baseDados)
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        RedBlackBST<String, Aviao> avioesRBST = baseDados.getRBSTAvioes();
        
        do
        {
            System.out.println();
            System.out.println("Escolha o Aviao:");
            for (String a : avioesRBST.keys())
            {
                Aviao aviaoIterado = avioesRBST.get(a);
                System.out.println(avioesRBST.rank(a) + " - " + aviaoIterado.getNomeAviao() + " : " + aviaoIterado.getMyVoosRBST().size());
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > avioesRBST.size()));
        
        Aviao aviaoChoice = avioesRBST.get(avioesRBST.select(menuChoice));
        System.out.println();
        System.out.println("Nome do Aviao: " + aviaoChoice.getNomeAviao());
        RedBlackBST<String, Voo> voosRBST = aviaoChoice.getMyVoosRBST();
        for (String idVoo : voosRBST.keys())
        {
            Voo vooIterado = voosRBST.get(idVoo);
            System.out.println("- Partida-" + vooIterado.getMyAeroportoOrigem().getIdAeroporto() + " : Destino-" + vooIterado.getMyAeroportoDestino().getIdAeroporto() + " : Data-" + vooIterado.getData() + " : Avião-" + vooIterado.getMyAviao().getNomeAviao() + " : Nº Escalas-" + vooIterado.getMyLigacoesAereas().size());
        }
    }
    
    private static void imprimirAeroportoViagensOrigDest(BD baseDados)
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        
        do
        {
            System.out.println();
            System.out.println("Escolha o Aeroporto:");
            for (String a : aeroportosRBST.keys())
            {
                Aeroporto aeroportoIterado = aeroportosRBST.get(a);
                Integer voosTotais = aeroportoIterado.getMyVoosPartidas().size() + aeroportoIterado.getMyVoosChegadas().size();
                System.out.println(aeroportosRBST.rank(a) + " - " + aeroportoIterado.getNome() + " : " + voosTotais);
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > aeroportosRBST.size()));
        
        Aeroporto aeroportoChoice = aeroportosRBST.get(aeroportosRBST.select(menuChoice));
        System.out.println();
        System.out.println("Nome do Aeroporto: " + aeroportoChoice.getNome());
        System.out.println("Viagens com Partida neste Aeroporto:");
        RedBlackBST<String, Voo> voosPartidasRBST = aeroportoChoice.getMyVoosPartidas();
        for (String idVoo : voosPartidasRBST.keys())
        {
            Voo vooIterado = voosPartidasRBST.get(idVoo);
            System.out.println("- " + vooIterado.getIdVoo() + " : Partida-" + vooIterado.getMyAeroportoOrigem().getIdAeroporto() + " : Destino-" + vooIterado.getMyAeroportoDestino().getIdAeroporto() + " : Data-" + vooIterado.getData().toString() + " : Avião-" + vooIterado.getMyAviao().getNomeAviao() + " : Nº Escalas-" + vooIterado.getMyLigacoesAereas().size());
        }
        System.out.println("Viagens com Destino neste Aeroporto:");
        RedBlackBST<String, Voo> voosChegadasRBST = aeroportoChoice.getMyVoosChegadas();
        for (String idVoo : voosChegadasRBST.keys())
        {
            Voo vooIterado = voosChegadasRBST.get(idVoo);
            System.out.println("- " + vooIterado.getIdVoo() + " : Partida-" + vooIterado.getMyAeroportoOrigem().getIdAeroporto() + " : Destino-" + vooIterado.getMyAeroportoDestino().getIdAeroporto() + " : Data-" + vooIterado.getData().toString() + " : Avião-" + vooIterado.getMyAviao().getNomeAviao() + " : Nº Escalas-" + vooIterado.getMyLigacoesAereas().size());
        }
    }
    
    private static void passarUmMes(BD baseDados, CalendarDate data)
    {
        Integer mes = data.getMonth();
        
        do
        {
            
        }
         while (data.getMonth() == mes);
    }
    
    private static void menuImprimirInfoAviao(BD baseDados)
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        BinarySearchST<String, CompanhiaAerea> companhiaAereaBSST = baseDados.getCompanhiasAereasBSST();
        
        do
        {
            System.out.println();
            System.out.println("Escolha a Companhia Aerea:");
            for (String a : companhiaAereaBSST.keys())
            {
                CompanhiaAerea companhiaIterada = companhiaAereaBSST.get(a);
                System.out.println(companhiaAereaBSST.rank(a) + " - " + companhiaIterada.getNome());
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > companhiaAereaBSST.size()));
        
        CompanhiaAerea companhiaChoice = companhiaAereaBSST.get(companhiaAereaBSST.select(menuChoice));
        ArrayList<Aviao> avioesAL = companhiaChoice.getListagemAvioes();
        
        do
        {
            System.out.println();
            System.out.println("Escolha um Avião:");
            for (Aviao aviaoIterado : avioesAL)
            {
                System.out.println(avioesAL.indexOf(aviaoIterado) + " - " + aviaoIterado.getNomeAviao());
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > avioesAL.size()));
        
        Aviao aviaoChoice = avioesAL.get(menuChoice);
        System.out.println();
        System.out.println("Companhia Aerea: " + aviaoChoice.getMyCompanhiaAerea().getNome());
        System.out.println("Nome do Aviao: " + aviaoChoice.getNomeAviao());
        System.out.println("Modelo do Aviao: " + aviaoChoice.getMyModeloAviao().getNome());
        System.out.println("Velocidade de Cruzeiro: " + aviaoChoice.getMyModeloAviao().getVelocidadeCruzeiro() + " km/h");
        System.out.println("Altitude de Cruzeiro: " + aviaoChoice.getMyModeloAviao().getAltitudeCruzeiro() + " km");
        System.out.println("Distancia Maxima: " + aviaoChoice.getMyModeloAviao().getDistanciaMax() + " km");
        System.out.println("ID do Aeroporto: " + aviaoChoice.getMyAeroporto());
        System.out.println("Capacidade de Passageiros: " + aviaoChoice.getMyModeloAviao().getPassageirosMax() + " pax");
        System.out.println("Capacidade de Deposito: " + aviaoChoice.getMyModeloAviao().getCombustivelMax() + " lt");
    }
    
    private static void menuImprimirInfoAeroporto(BD baseDados)
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        
        do
        {
            System.out.println();
            System.out.println("Escolha o Aeroporto:");
            for (String a : aeroportosRBST.keys())
            {
                Aeroporto aeroportoIterado = aeroportosRBST.get(a);
                System.out.println(aeroportosRBST.rank(a) + " - " + aeroportoIterado.getNome() + " : " + aeroportoIterado.getIdAeroporto());
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > aeroportosRBST.size()));
        
        Aeroporto aeroportoChoice = aeroportosRBST.get(aeroportosRBST.select(menuChoice));
        System.out.println();
        System.out.println("Nome do Aeroporto: " + aeroportoChoice.getNome());
        System.out.println("ID do Aeroporto: " + aeroportoChoice.getIdAeroporto());
        System.out.println("Cidade: " + aeroportoChoice.getMyCidade().getNome());
        System.out.println("País: " + aeroportoChoice.getMyCidade().getPais());
        System.out.println("Continente: " + aeroportoChoice.getMyCidade().getContinente());
        System.out.println("Classificação: " + aeroportoChoice.getClassificacao());
        System.out.println("Aviões neste Aeroporto: " + aeroportoChoice.getListagemAvioes().size());
        System.out.println("Ligações Aéreas deste Aeroporto: " + aeroportoChoice.getMyListaLigacoesAereas().size());
    }
    
    private static void menuImprimirAvioesInAeroporto(BD baseDados)
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        
        do
        {
            System.out.println();
            System.out.println("Escolha o Aeroporto:");
            for (String a : aeroportosRBST.keys())
            {
                Aeroporto aeroportoIterado = aeroportosRBST.get(a);
                System.out.println(aeroportosRBST.rank(a) + " - " + aeroportoIterado.getNome() + " : " + aeroportoIterado.getListagemAvioes().size());
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > aeroportosRBST.size()));
        
        Aeroporto aeroportoChoice = aeroportosRBST.get(aeroportosRBST.select(menuChoice));
        System.out.println();
        System.out.println("Nome do Aeroporto: " + aeroportoChoice.getNome());
        System.out.println();
        RedBlackBST<String, Aviao> avioesRBST = aeroportoChoice.getListagemAvioes();
        for (String nomeAviao : avioesRBST.keys())
        {
            Aviao aviaoIterado = avioesRBST.get(nomeAviao);
            System.out.println("Aviao#" + avioesRBST.rank(nomeAviao) + " " + aviaoIterado.getNomeAviao() + " : " + aviaoIterado.getMyModeloAviao().getNome());
        }
    }
    
    private static void menuImprimirAeroportoInPais(BD baseDados)
    {
        Scanner input = new Scanner(System.in);
        int menuChoice;
        RedBlackBST<String, Aeroporto> aeroportosRBST = baseDados.getRBSTAeroportos();
        BinarySearchST<String, String> paisBSST = new BinarySearchST<>();
        
        for (String nomeAeroporto : aeroportosRBST.keys())
        {
            paisBSST.put(aeroportosRBST.get(nomeAeroporto).getMyCidade().getPais(), nomeAeroporto);
        }
        
        do
        {
            System.out.println();
            System.out.println("Escolha o Pais:");
            for (String paisIterado : paisBSST.keys())
            {
                System.out.println(paisBSST.rank(paisIterado) + " - " + paisIterado);
            }
            System.out.print(">> ");
            menuChoice = input.nextInt();
        } while ((menuChoice < 0) || (menuChoice > paisBSST.size()));
        
        String paisChoice = paisBSST.select(menuChoice);
        System.out.println();
        for (String a : aeroportosRBST.keys())
        {
            Aeroporto aeroportoIterado = aeroportosRBST.get(a);
            if (aeroportoIterado.getMyCidade().getPais().compareTo(paisChoice) == 0)
            {
                System.out.println(aeroportoIterado.getNome() + " : " + aeroportoIterado.getIdAeroporto() + " - " + aeroportoIterado.getMyCidade().getNome());
            }
        }
    }
    
    public static void testeModeloAviao(BinarySearchST<String, ModeloAviao> modAvioesBSST)
    {
        for (String nomeModelo : modAvioesBSST.keys())
        {
            System.out.println(modAvioesBSST.get(nomeModelo));
        }
        
        System.out.println("Consumo numa viagem de 8000 km de altura e a 10000 km distância: " + modAvioesBSST.get("Airbus A340").consumoViagem(8000, 10000) + " lt/km");
        System.out.println("Consumo numa viagem de 7500 km de altura e a 10000 km distância: " + modAvioesBSST.get("Airbus A340").consumoViagem(7500, 10000) + " lt/km");
        System.out.println("Consumo numa viagem de 9000 km de altura e a 10000 km distância: " + modAvioesBSST.get("Airbus A340").consumoViagem(9000, 10000) + " lt/km");
        System.out.println("Consumo numa viagem de 8000 km de altura e a 20000 km distância: " + modAvioesBSST.get("Airbus A340").consumoViagem(8000, 20000) + " lt/km");
    }
    
    public static void carregarFicheiroAeroportos(BD baseDados)
    {
        Ficheiros.lerFicheiro("airports.txt", baseDados);
        
        for (int i = 0; i < baseDados.getRBSTAeroportos().size(); i++)
        {
            String a = String.valueOf(baseDados.getRBSTAeroportos().select(i));
            System.out.println(baseDados.getAeroporto(a));
        }
        
        System.out.println();
        System.out.println(baseDados.getAeroporto("OPO").getMyCidade().getNome() + " - lat: " + baseDados.getAeroporto("OPO").getMyCidade().getLatitude() + "º lon: " + baseDados.getAeroporto("OPO").getMyCidade().getLongitude() + "º");
        System.out.println(baseDados.getAeroporto("CDG").getMyCidade().getNome() + " - lat: " + baseDados.getAeroporto("CDG").getMyCidade().getLatitude() + "º lon: " + baseDados.getAeroporto("CDG").getMyCidade().getLongitude() + "º");
        System.out.println();
        System.out.println(baseDados.getAeroporto("OPO").getMyCidade().getNome() + " --> " + baseDados.getAeroporto("OPO").getMyCidade().distanciaCidades(baseDados.getAeroporto("CDG").getMyCidade()) + " km --> " + baseDados.getAeroporto("CDG").getMyCidade().getNome());
    }
    
    public static void carregarFicheiroAvioes(BD baseDados)
    {
        Ficheiros.lerFicheiro("airplanes.txt", baseDados);
        
        for (int i = 0; i < baseDados.getRBSTAvioes().size(); i++)
        {
            String a = String.valueOf(baseDados.getRBSTAvioes().select(i));
            System.out.println(baseDados.getAviao(a));
        }
    }
    
    public static void imprimirAeroporto(String idAeroporto, RedBlackBST<String, Aeroporto> aeroportosRBST)
    {
        System.out.println(aeroportosRBST.get(idAeroporto));
    }
    public static void imprimirAviao(String nomeAviao, RedBlackBST<String, Aviao> avioesRBST)
    {
        System.out.println(avioesRBST.get(nomeAviao));
    }
    public static void imprimirAvioesEmAeroporto(String idAeroporto, RedBlackBST<String, Aeroporto> aeroportosRBST)
    {
        for (String a : aeroportosRBST.get(idAeroporto).getListagemAvioes().keys())
        {
            System.out.println(aeroportosRBST.get(idAeroporto).getListagemAvioes().get(a));
        }
    }
    public static void imprimirAeroportosEmPaisContinente(String nomePaisContinente, BinarySearchST<String, Location> cidadesBSST)
    {
        for (String a : cidadesBSST.keys())
        {
            if ((cidadesBSST.get(a).getPais().compareTo(nomePaisContinente) == 0) || (cidadesBSST.get(a).getContinente().compareTo(nomePaisContinente) == 0))
            {
                for (int i = 0; i < cidadesBSST.get(a).getListagemAeroportos().size(); i++)
                {
                    System.out.println(cidadesBSST.get(a).getAeroporto(i));
                }
            }
        }
    }
}