/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author Rafael
 */
public class Usuário {

    String nome;
    String DisciplinaEscolhida = "";
    int DificuldadeEscolhida = 0;
    Scanner scan = new Scanner(System.in);
    Posicao posicao = new Posicao();
    public boolean[] key_states = new boolean[256];

    public Usuário(){
        
        
    }
    
    public String escolheMateria() {
        System.out.println("Escolha uma disciplina:");
        System.out.println("M -- Matemática");
        System.out.println("H -- História");
        System.out.println("G -- Geografia");
        DisciplinaEscolhida = scan.next();

        return DisciplinaEscolhida.toLowerCase();
    }

    public int escolheDificuldade() {
        System.out.println("Escolha uma dificuldade:");
        System.out.println("1 -- Facil");
        System.out.println("2 -- Media");
        System.out.println("3 -- Dificil");
        DificuldadeEscolhida = Integer.parseInt(scan.next());

        return DificuldadeEscolhida;
    }

    public void escolheLabirinto() {
    }

    public void iniciaPartida(String disciplina,int Dificuldade) {
        Pergunta pergunta = new Pergunta();
        
        char respP = 0;
        char respJ = 0;
        
        if (Dificuldade == 1) {
            respP = pergunta.getPerguntaFacil(disciplina);
            try {
                respJ = scan.next().charAt(0);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (respJ == respP) {
                System.out.println("Voce acertou, pode continuar!!");
            } else {
                System.out.println("Voce errou");
            }

            System.out.print("Fim de Jogo. Ranking: ");
                    //        usuario.escreveNome();                    
            //        for (int i = 0; i <= rank.nome.size() - 1; i++) {
            //        System.out.println(rank.nome.get(i) + " : " + rank.ListaPontuacao.get(i));

        } else if (Dificuldade == 2) {

            respP = pergunta.getPerguntaFacil(disciplina);
            respJ = scan.next().charAt(0);
            if (respJ == respP) {
                System.out.println("Voce acertou, pode continuar!!");
            } else {
                System.out.println("Voce errou");
            }

            System.out.print("Fim de Jogo. Ranking: ");
                    //        usuario.escreveNome();                    
            //        for (int i = 0; i <= rank.nome.size() - 1; i++) {
            //        System.out.println(rank.nome.get(i) + " : " + rank.ListaPontuacao.get(i));

        } else if (Dificuldade == 3) {

            respP = pergunta.getPerguntaFacil(disciplina);
            respJ = scan.next().charAt(0);
            if (respJ == respP) {
                System.out.println("Voce acertou, pode continuar!!");
            } else {
                System.out.println("Voce errou");
            }

            System.out.print("Fim de Jogo. Ranking: ");
                    //        usuario.escreveNome();                    
            //        for (int i = 0; i <= rank.nome.size() - 1; i++) {
            //        System.out.println(rank.nome.get(i) + " : " + rank.ListaPontuacao.get(i));

        }
    }

    public void finalizaAplicacao() {
        exit(0);
    }

    public void escreveNome() {
    }

    public void selecionaResposta() {
    }
    
 
    
   // @Override
    public void update(double dt) {

        if ((key_states[KeyEvent.VK_RIGHT]) && (posicao.x < 650)) {
            posicao.x = posicao.x + (200 * dt);
        }
        if ((key_states[KeyEvent.VK_UP]) && (posicao.y > -40)) {
            posicao.y = posicao.y - (200 * dt);
        }
        if ((key_states[KeyEvent.VK_LEFT]) && (posicao.x > -50)) {
            posicao.x = posicao.x - (200 * dt);
        }
        if ((key_states[KeyEvent.VK_DOWN]) && (posicao.y < 450)) {
            posicao.y = posicao.y + (200 * dt);
        }
    }
}
