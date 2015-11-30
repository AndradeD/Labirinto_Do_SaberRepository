package pcs.labirinto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import pcs.labirinto.Personagem;
import pcs.labirinto.Ranking;
import pcs.labirinto.Usuário;

import java.util.Scanner;
import static java.lang.System.exit;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Labirinto extends JPanel implements Runnable{

     Usuário usuario = new Usuário();
     private Image fundo;
     private boolean jogo = false;
     private Font minhafonte = new Font("Arial", 0, 20);
     Personagem personagem = new Personagem();
     private Image[] vidaP = new Image[3];
    
   /* public static void main(String[] args) throws IOException {
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
    }*/
    
    
    private class KeyboardAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            usuario.key_states[e.getKeyCode()] = false;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            usuario.key_states[e.getKeyCode()] = true;
        }
    }

    public Labirinto() {
        setDoubleBuffered(true);
        setFocusable(true);
        load();
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void run() {
        double btime, dtime = 0;
        btime = System.currentTimeMillis();
        while (true) {
            update(dtime / 1000);
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            dtime = (System.currentTimeMillis() - btime);
            btime = System.currentTimeMillis();
        }
    }
    
    private void load() {
        fundo = new ImageIcon(this.getClass().getResource("preta.jpg")).getImage();
    }
    
    private void update(double dt) {
        if (usuario.key_states[KeyEvent.VK_N]) {
            jogo = true;
        }
        if (jogo)
        {
            
            
        }
    }
    
    private void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(minhafonte);
        if (jogo) {

            if (personagem.verificaVida()== 3) {
                g2d.drawImage(vidaP[0], 5, 5, null);
                g2d.drawImage(vidaP[1], 45, 5, null);
                g2d.drawImage(vidaP[2], 85, 5, null);
            } else if (personagem.verificaVida()== 2) {
                g2d.drawImage(vidaP[0], 5, 5, null);
                g2d.drawImage(vidaP[1], 45, 5, null);
            } else if (personagem.verificaVida()== 1) {
                g2d.drawImage(vidaP[0], 5, 5, null);
            }
            
            if (personagem.verificaVida()> 0) {
                personagem.draw(g2d);
            } else {
                g2d.drawString("GAME OVER", 400, 400);
            }
            
        } else {
            g2d.setColor(Color.BLACK);
            g2d.drawString("Labirinto do Saber", 250, 100);
            String str = "NOVO JOGO(Aperte N)";
            String str1 = "RECORDES(Aperte R)";
            String str2 = "SAIR(Aperte S)";

            g2d.drawString(str, 250, 200);
            g2d.drawString(str1, 250, 250);
            g2d.drawString(str2, 250, 300);

        }
    }
}
