package pcs.labirinto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import pcs.labirinto.Personagem;
import pcs.labirinto.Ranking;
import pcs.labirinto.Usuário;

import java.util.Scanner;
import static java.lang.System.exit;
import java.util.List;

public class Labirinto {

    public static void main(String[] args) throws IOException {
        int option = -1;
        char menuoption;
        String TipoMateria = "";
        int Dificuldade = 0;
        Pergunta pergunta = new Pergunta();
        char respJ;
        Respostas resposta = new Respostas();
        Ranking rank = new Ranking();
        Usuário usuario = new Usuário();
        char respP = 0;
        
        Scanner ler = new Scanner(System.in);

        do{
            menuoption = 0;
            respJ = 0;
            try{
            System.out.println("Escolha uma opção:");
            System.out.println("1 -- Jogar");
            System.out.println("2 -- Ranking");
            System.out.println("3 -- Sair");
            option = ler.nextInt();
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
                    if (option == 1) {    
                       TipoMateria = usuario.escolheMateria();
                       Dificuldade = usuario.escolheDificuldade();
                        System.out.println("Iniciando partida fácil...");
                        usuario.iniciaPartida(TipoMateria,Dificuldade);
                    }else if (option == 3)
                        usuario.finalizaAplicacao();
                    else if (option == 2) {
                     //   for (int i = 0; i <= rank.nome.size() - 1; i++) {
                     //       System.out.println(rank.nome.get(i) + " : " + rank.ListaPontuacao.get(i));
                    }
                        System.out.println("Deseja voltar para o menu ? (s/n)");
                        menuoption = ler.next().charAt(0);
                                            
        }while(menuoption == 's');
    }
}
