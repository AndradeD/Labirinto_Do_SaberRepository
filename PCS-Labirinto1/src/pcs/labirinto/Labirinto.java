package pcs.labirinto;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import pcs.labirinto.Personagem;
import pcs.labirinto.Ranking;
import pcs.labirinto.Usuário;

import java.util.Scanner;
import static java.lang.System.exit;
import java.text.AttributedCharacterIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Labirinto extends JPanel implements Runnable {

    private Usuário usuario;
    private Image fundo;
    private boolean jogo = false;
    private boolean configurando = false;
    private Font minhafonte = new Font("Arial", 0, 20);
    Personagem personagem = new Personagem();
    private Image[] vidaP = new Image[3];   
    Parede parede = new Parede();
    Rectangle rectanglePersonagem = new Rectangle(25,30);
    Rectangle rectangleLabirinto = new Rectangle(100,100,949,855);
    Ranking ranking = new Ranking();    
    Pergunta pergunta;    
    public String TipoMateria = "";
    public int Dificuldade;


    private class KeyboardAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.key_states[e.getKeyCode()] = false;
            usuario.key_states[e.getKeyCode()] = false;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            personagem.key_states[e.getKeyCode()] = true;
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
            try {
                update(dtime / 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            dtime = System.currentTimeMillis() - btime;
            btime = System.currentTimeMillis();
        }
    }

    private void load() {
        addKeyListener(new KeyboardAdapter());
     //   fundo = new ImageIcon(this.getClass().getResource("preta.jpg")).getImage();
        personagem = new Personagem();
        usuario = new Usuário();     
    }

    private void update(double dt) throws InterruptedException {
             
   
        if (jogo == false){
            if(usuario.key_states[KeyEvent.VK_N]) {
            ConfiguraDisciplinaPartida();
            ConfiguraDificuldadePartida();            
            configurando = true;            
            }
        }
        if (usuario.key_states[KeyEvent.VK_S]) {
            System.exit(WIDTH);
        } 
        if (usuario.key_states[KeyEvent.VK_R]) {            
                ranking.getNomesAndPontos(ranking.VetorNomes, ranking.VetorPontos);
            try {
                ranking.close();
            } catch (IOException ex) {
                Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }        
        else if (jogo) {                
                    
                if (personagem.verificaGameOver() == false) {
                    if (personagem.controle){
                    personagem.update(0,rectanglePersonagem,rectangleLabirinto,pergunta);
                    }else
                        personagem.update(dt,rectanglePersonagem,rectangleLabirinto,pergunta);                    
                    
                    if (usuario.key_states[KeyEvent.VK_ESCAPE])
                    {
                        //JPANEL COM OPCAO DE FECHAR O JOGO
                        //if (opcao == true)
                     //   {
                           //System.exit(WIDTH);
                        //}
                    }                    
                    personagem.TempoTotal++;
                }else{
                    ranking.TempoTotal = (int)personagem.GetTempoTotal();
                    if (usuario.key_states[KeyEvent.VK_ESCAPE])
                    {
                        jogo = false;  
                        usuario.key_states[KeyEvent.VK_N] = false;                        
                    }
                }
            
        }        
    
    }

    private void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(minhafonte);
        if (jogo) {            
            parede.draw(g2d);
            g2d.drawImage(fundo, 0, 0,null);
            g2d.setColor(Color.BLACK);
            g2d.drawString(""+personagem.TempoTotal, 600, 100);
            
            if (personagem.verificaGameOver() == false) {
                personagem.draw(g2d);
            } else {
                g2d.drawString("GAME OVER", 400, 50);
                g2d.drawString("Você escapou", 400, 70);               
                
            }

        } else {
            g2d.drawImage(fundo, 0, 0, null);
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
    
       
      public void ConfiguraDisciplinaPartida() {
        int IndexSelected = 0;
        String[] list = new String[]{"Matematica", "Historia", "Geografia"};
        IndexSelected = JOptionPane.showOptionDialog(
                null, "Escolha uma disciplina", "Options", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,list,list[0]);
        
        if (IndexSelected == 0)
         {
             TipoMateria = "m";
         }else
         if (IndexSelected == 1)
         {
             TipoMateria = "h";
         }else
         if (IndexSelected == 2 || IndexSelected == -1)
         {
             TipoMateria = "g";
         }
                
        
    }

    public void ConfiguraDificuldadePartida() {
        int IndexSelected = 0;
        String[] list = new String[]{"Fácil", "Médio", "Difícil"};
        IndexSelected = JOptionPane.showOptionDialog(
                null, "Escolha uma dificuldade", "Options", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE,null,list,list[0]);
         if (IndexSelected == 0)
         {
             Dificuldade = -1;
         }else
         if (IndexSelected == 1)
         {
             Dificuldade = 0;
         }else
         if (IndexSelected == 2 || IndexSelected == -1)
         {
             Dificuldade = 1;
         }      
            try {
               pergunta = new Pergunta(TipoMateria,Dificuldade);
             } catch (IOException ex) {
                 Logger.getLogger(Labirinto.class.getName()).log(Level.SEVERE, null, ex);
             }

        if (!TipoMateria.equals("")) 
        jogo = true;
        
    }
    
}
