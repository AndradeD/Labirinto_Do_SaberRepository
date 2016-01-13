/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

import java.awt.AWTEvent;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class Personagem {
    String nome;
    int vida;
    Posicao posicao;
    public boolean ativo = true;
    private Image imagem;
    private BufferedImage imgIntersecao;
    private Image imgIntersecao2;
    private BufferedImage imgCrop;
    public boolean[] key_states = new boolean[256];
    private Rectangle Intersecao = new Rectangle();  
    Ranking ranking = new Ranking();
    private int redValue = -131072;
    private int firstyellowValue = -10240;
    private int secondyellowValue = -262255;
    private int thirdyellowValue = -5354;
    private int fourthyellowValue = -1114358;
    private int fiveyellowValue = -157;
    private int greenValue = -16384251; 
    private int VariavelSomaLabirinto = 100;
    private boolean GameOver = false;
    public boolean controle = false;
    private double xParado = 0;
    private double yParado = 0;
    public double TempoTotal = 0;
    
    public Personagem(){
        vida = 3;
        imagem = new ImageIcon(this.getClass().getResource("sprite01modi.png")).getImage();
        posicao = new Posicao(100,60);
    }
    
     public double getPx (){
        return posicao.getPosicaoX();
    }
    
     public double getPy (){
        return posicao.getPosicaoY();
    }
     
     public double getWidth(){
         return 25;         
     }
     
     public double getHeight(){
         return 30;
     }
    
    public boolean verificaGameOver(){
       return GameOver;
    }
    
    private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
      BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
      return dest; 
    }

    private Boolean HasColision(double dt, Rectangle rectanglePersonagem, Rectangle rectangleParede,Pergunta pergunta){
        double novoX = posicao.x;
        double novoY = posicao.y;
        
        if(key_states[KeyEvent.VK_DOWN]){
            novoY = posicao.y + ((50 * dt) * 3);
            controle = false;
        }
        if(key_states[KeyEvent.VK_UP]){
            novoY = posicao.y - ((50 * dt) * 3);
            controle = false;
        }
        if(key_states[KeyEvent.VK_RIGHT]){
            novoX = posicao.x + ((50 * dt) * 3);
            controle = false;
        }
        if(key_states[KeyEvent.VK_LEFT]){
            novoX = posicao.x - ((50 * dt) * 3);
            controle = false;
        }
        
        if (posicao.x >= 760 && posicao.x <= 770 && posicao.y >= 355 && posicao.y <= 364)
        {
            pergunta.respondida = false;
        }
        if (posicao.y >= 355 && posicao.y <= 364 && posicao.x >= 412 && posicao.x <= 432.5)
        {
            pergunta.respondida = false;
        }
        if (posicao.x >= 130.5 && posicao.x <= 145 && posicao.y >= 512.25 && posicao.y <= 520)
        {
            pergunta.respondida = false;        
        }
        if (posicao.y >= 774.25 && posicao.y <= 783.25 && posicao.x >= 549.25 && posicao.x <= 553)
        {
            pergunta.respondida = false;
        }
        
        System.out.println("Posicao x:"+ posicao.x);
        System.out.println("Posicao y:"+ posicao.y);
        
        int[] pixels = null;
        Intersecao = rectangleParede.intersection(rectanglePersonagem);
        
        Image labirinto = new ImageIcon(this.getClass().getResource("labirintoMathFacilmod.png")).getImage(); 
        
        BufferedImage s = null;
        try {
            s = ImageIO.read(new File("C:\\Users\\daniel.quintana\\Desktop\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\labirintoMathFacilmod.png"));
        } catch (IOException ex) {
            Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if((Intersecao.x > VariavelSomaLabirinto)&&((Intersecao.y > VariavelSomaLabirinto))){
            imgCrop = s.getSubimage(((int)novoX - VariavelSomaLabirinto), ((int)novoY - VariavelSomaLabirinto), Intersecao.width, Intersecao.height);
           // System.out.println("RED" + imgCrop.getRGB(5, 5));            
            
            Boolean hasAnotherPixel = false;
            for(int x=0; x<imgCrop.getWidth(); x++){
                for(int y=0; y<imgCrop.getHeight(); y++){
                    if((imgCrop.getRGB(x, y) != redValue)&&
                       (imgCrop.getRGB(x, y) != firstyellowValue) && 
                       (imgCrop.getRGB(x, y) != secondyellowValue) && 
                       (imgCrop.getRGB(x, y) != thirdyellowValue) && 
                       (imgCrop.getRGB(x, y) != fourthyellowValue) && 
                       (imgCrop.getRGB(x, y) != fiveyellowValue) && 
                       (imgCrop.getRGB(x, y) != greenValue)){
                        hasAnotherPixel = true;
                        break;
                    }                    
                }
            }
            if(hasAnotherPixel)
                return true;
            else if(HasColorPoint(dt, firstyellowValue, rectanglePersonagem, rectangleParede,pergunta)){
                    //PERGUNTA               
                dt = 0;
                    if (!pergunta.respondida){
                         int IndexSelected = 0;
                         
                         String[] Separacao = pergunta.perguntas.get(0).split("\\?");
                         String[] alternativas = Separacao[1].split(";");   
                   
        
                        IndexSelected = JOptionPane.showOptionDialog(
                        null, Separacao[0]+"?", "Responda a pergunta:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, alternativas, alternativas[0]);
                         
                        char respC = pergunta.getRespostas().get(0);
                        int aux = 0;
                        if(respC == 'a')
                            aux = 0;
                        else if(respC == 'b')
                            aux = 1;
                        else if (respC == 'c')
                            aux = 2;
                        else if(respC == 'd')
                            aux = 3;

                        if(IndexSelected == aux){
                            JOptionPane.showMessageDialog(null, "PARABENS VC ACERTOU");
                            TempoTotal = TempoTotal - 250;
                        }else {
                            JOptionPane.showMessageDialog(null, "VOCE ERROU");
                            TempoTotal = TempoTotal + 250;
                        }
                        controle = true;
                        pergunta.respondida = true;                       
                    }
                                    
            } else if (HasColorPoint(dt,greenValue,rectanglePersonagem,rectangleParede,pergunta)){
                 GameOver = true;  
                 ranking.setPont();
                try {
                    ranking.close();
                } catch (IOException ex) {
                    Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
                }
                 return true;                
            } else if (HasColorPoint(dt,secondyellowValue,rectanglePersonagem,rectangleParede,pergunta)){
                //PERGUNTA  
                dt = 0;
                    if (!pergunta.respondida){
                        int IndexSelected = 0;
                         
                         String[] Separacao = pergunta.perguntas.get(1).split("\\?");
                         String[] alternativas = Separacao[1].split(";");   
                   
        
                        IndexSelected = JOptionPane.showOptionDialog(
                        null, Separacao[0]+"?", "Responda a pergunta:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, alternativas, alternativas[0]);
                        
                        char respC = pergunta.getRespostas().get(1);
                        int aux = 0;
                        if(respC == 'a')
                            aux = 0;
                        else if(respC == 'b')
                            aux = 1;
                        else if (respC == 'c')
                            aux = 2;
                        else if(respC == 'd')
                            aux = 3;

                        if(IndexSelected == aux){
                            JOptionPane.showMessageDialog(null, "PARABENS VC ACERTOU");
                            TempoTotal = TempoTotal - 500;
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "VOCE ERROU");
                            TempoTotal = TempoTotal + 500;
                        }
                        
                         controle = true;                  
                        pergunta.respondida = true;
                    }
            }  
            else if (HasColorPoint(dt,thirdyellowValue,rectanglePersonagem,rectangleParede,pergunta)){
                //PERGUNTA                    
                    if (!pergunta.respondida){
                        int IndexSelected = 0;
                         
                         String[] Separacao = pergunta.perguntas.get(2).split("\\?");
                         String[] alternativas = Separacao[1].split(";");   
                   
        
                        IndexSelected = JOptionPane.showOptionDialog(
                        null, Separacao[0]+"?", "Responda a pergunta:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, alternativas, alternativas[0]);
                        
                       char respC = pergunta.getRespostas().get(2);
                        int aux = 0;
                        if(respC == 'a')
                            aux = 0;
                        else if(respC == 'b')
                            aux = 1;
                        else if (respC == 'c')
                            aux = 2;
                        else if(respC == 'd')
                            aux = 3;

                        if(IndexSelected == aux){
                            JOptionPane.showMessageDialog(null, "PARABENS VC ACERTOU");
                            TempoTotal = TempoTotal - 1200;
                        }else {
                            JOptionPane.showMessageDialog(null, "VOCE ERROU");
                            TempoTotal = TempoTotal + 500;
                        }
                        controle = true;
                        pergunta.respondida = true;
                    }
            }
            else if (HasColorPoint(dt,fourthyellowValue,rectanglePersonagem,rectangleParede,pergunta)){
                //PERGUNTA                    
                    if (!pergunta.respondida){
                       int IndexSelected = 0;
                         
                         String[] Separacao = pergunta.perguntas.get(3).split("\\?");
                         String[] alternativas = Separacao[1].split(";");   
                   
        
                        IndexSelected = JOptionPane.showOptionDialog(
                        null, Separacao[0]+"?", "Responda a pergunta:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, alternativas, alternativas[0]);
                       char respC = pergunta.getRespostas().get(3);
                        int aux = 0;
                        if(respC == 'a')
                            aux = 0;
                        else if(respC == 'b')
                            aux = 1;
                        else if (respC == 'c')
                            aux = 2;
                        else if(respC == 'd')
                            aux = 3;

                        if(IndexSelected == aux){
                            JOptionPane.showMessageDialog(null, "PARABENS VC ACERTOU");
                            TempoTotal = TempoTotal - 1500;
                        }else {
                            JOptionPane.showMessageDialog(null, "VOCE ERROU");
                            TempoTotal = TempoTotal + 1050;
                        }
                        controle = true;
                        pergunta.respondida = true;
                    }
            }
            else if (HasColorPoint(dt,fiveyellowValue,rectanglePersonagem,rectangleParede,pergunta)){
                //PERGUNTA                    
                    if (!pergunta.respondida){
                       int IndexSelected = 0;
                         
                         String[] Separacao = pergunta.perguntas.get(4).split("\\?");
                         String[] alternativas = Separacao[1].split(";");   
                           
                        IndexSelected = JOptionPane.showOptionDialog(
                        null, Separacao[0]+"?", "Responda a pergunta:", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, alternativas, alternativas[0]);
                        
                       char respC = pergunta.getRespostas().get(4);
                        int aux = 0;
                        if(respC == 'a')
                            aux = 0;
                        else if(respC == 'b')
                            aux = 1;
                        else if (respC == 'c')
                            aux = 2;
                        else if(respC == 'd')
                            aux = 3;

                        if(IndexSelected == aux){
                            JOptionPane.showMessageDialog(null, "PARABENS VC ACERTOU");
                            TempoTotal = TempoTotal - 1500;
                        }else {
                            JOptionPane.showMessageDialog(null, "VOCE ERROU");
                            TempoTotal = TempoTotal + 1500;
                        }
                        controle = true;
                        pergunta.respondida = true;
                    }
            }
            return false;
        }else
        {
            return false;
        }
    }
    
    private Boolean HasColorPoint(double dt, int colorToDetect, Rectangle rectanglePersonagem, Rectangle rectangleParede,Pergunta pergunta){
        double novoX = posicao.x;
        double novoY = posicao.y;

        if(key_states[KeyEvent.VK_DOWN]){
            novoY = posicao.y + ((50 * dt) * 3);
        }
        if(key_states[KeyEvent.VK_UP]){
            novoY = posicao.y - ((50 * dt) * 3);
        }
        if(key_states[KeyEvent.VK_RIGHT]){
            novoX = posicao.x + ((50 * dt) * 3);
        }
        if(key_states[KeyEvent.VK_LEFT]){
            novoX = posicao.x - ((50 * dt) * 3);
        }
        
        int[] pixels = null;
        Intersecao = rectangleParede.intersection(rectanglePersonagem);
        
     //   Image labirinto = new ImageIcon(this.getClass().getResource("labirintoMathFacilmod.png")).getImage(); 
        
        BufferedImage s = null;
        try {
            s = ImageIO.read(new File("C:\\Users\\daniel.quintana\\Desktop\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\labirintoMathFacilmod.png"));
        } catch (IOException ex) {
            Logger.getLogger(Personagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if((Intersecao.x > VariavelSomaLabirinto)&&((Intersecao.y > VariavelSomaLabirinto))){
            imgCrop = s.getSubimage(((int)novoX - VariavelSomaLabirinto), ((int)novoY - VariavelSomaLabirinto), Intersecao.width, Intersecao.height);
            
            Boolean detectedColor = false;
            for(int x=0; x<imgCrop.getWidth(); x++){
                for(int y=0; y<imgCrop.getHeight(); y++){
                   // System.out.println("COLOR CHECK: " + x + " " + y);
                    if(imgCrop.getRGB(x, y) == colorToDetect){
                        detectedColor = true;
                   //     System.out.println("COLOR FOUND: " + imgCrop.getRGB(5, 5));
                        break;
                    }
                }
            }
            return detectedColor; 
        }else
        {
            return false;
        }
    }
    
    public void update(double dt,Rectangle rectanglePersonagem,Rectangle rectangleParede,Pergunta pergunta) throws InterruptedException{   
          
        
        if ((key_states[KeyEvent.VK_DOWN]) && (posicao.y < 950 && posicao.x > 100))
        {                             
            
           if (controle)
           {
            key_states[KeyEvent.VK_DOWN] = false;
            controle = false;
           }
            
           if(HasColision(dt, rectanglePersonagem, rectangleParede,pergunta))
              dt = 0;
             
          
            imagem  = new ImageIcon(this.getClass().getResource("sprite02modi.png")).getImage();
            posicao.y = posicao.y + (50 * dt);
            rectanglePersonagem.y = (int)posicao.y;
           
            Thread.sleep(5);
            if (key_states[KeyEvent.VK_DOWN]){                
               imagem  = new ImageIcon(this.getClass().getResource("sprite01modi.png")).getImage();  
               posicao.y = posicao.y + (50 * dt);
               rectanglePersonagem.y = (int)posicao.y;
             
                Thread.sleep(5);
               
                if (key_states[KeyEvent.VK_DOWN]){                 
                    imagem  = new ImageIcon(this.getClass().getResource("sprite03modi.png")).getImage();   
                    posicao.y = posicao.y + (50 * dt);
                    rectanglePersonagem.y = (int)posicao.y;

                    Thread.sleep(5);
                }
           
        }
       }
       
        if ((key_states[KeyEvent.VK_RIGHT]) && (posicao.x < 800))
        {
            if (controle)
           {
            key_states[KeyEvent.VK_RIGHT] = false;
            controle = false;
           }                        
            
            if(HasColision(dt, rectanglePersonagem, rectangleParede,pergunta))
              dt = 0;          
            
            imagem = new ImageIcon(this.getClass().getResource("sprite05modi.png")).getImage();  
            posicao.x = posicao.x + (50 * dt);       
            rectanglePersonagem.x = (int)posicao.x;

            Thread.sleep(5);
            if (key_states[KeyEvent.VK_RIGHT]){
             
                imagem = new ImageIcon(this.getClass().getResource("sprite06modi.png")).getImage();                  
                 posicao.x = posicao.x + (50 * dt);                 
                 rectanglePersonagem.x = (int)posicao.x;
                 
                Thread.sleep(5);
                
                
                if (key_states[KeyEvent.VK_RIGHT]){                
                imagem = new ImageIcon(this.getClass().getResource("sprite07modi.png")).getImage();                   
                 posicao.x = posicao.x + (50 * dt);
                 rectanglePersonagem.x = (int)posicao.x;
                 
                Thread.sleep(5);
                }
            }          
        
        }
        
        if ((key_states[KeyEvent.VK_LEFT]) && (posicao.x > -10))
        {
            if (controle)
           {
            key_states[KeyEvent.VK_LEFT] = false;
            controle = false;
           } 
            
            if(HasColision(dt, rectanglePersonagem, rectangleParede,pergunta))
              dt = 0;
            
            imagem = new ImageIcon(this.getClass().getResource("sprite09modi.png")).getImage();
            posicao.x = posicao.x - (50 * dt);
            rectanglePersonagem.x = (int)posicao.x;
            
            Thread.sleep(5);
            if (key_states[KeyEvent.VK_LEFT]){               
                imagem = new ImageIcon(this.getClass().getResource("sprite10modi.png")).getImage();
                posicao.x = posicao.x - (50 * dt);
                rectanglePersonagem.x = (int)posicao.x;
          
                Thread.sleep(5);
                if (key_states[KeyEvent.VK_LEFT]){               
                imagem = new ImageIcon(this.getClass().getResource("sprite12modi.png")).getImage();
                posicao.x = posicao.x - (50 * dt);
                rectanglePersonagem.x = (int)posicao.x;
           
                Thread.sleep(5);
                }
            }          
        
        }
        if ((key_states[KeyEvent.VK_UP]) && (posicao.y > -10))
        {   
            
           if (controle)
           {
            key_states[KeyEvent.VK_UP] = false;
            controle = false;
           } 
            
            if(HasColision(dt, rectanglePersonagem, rectangleParede,pergunta))
              dt = 0;
                        
            imagem = new ImageIcon(this.getClass().getResource("sprite13modi.png")).getImage();
            posicao.y = posicao.y - (50 * dt);
            rectanglePersonagem.y = (int)posicao.y;
           
            Thread.sleep(5);
            if (key_states[KeyEvent.VK_UP]){         
                 imagem = new ImageIcon(this.getClass().getResource("sprite14modi.png")).getImage();
                 posicao.y = posicao.y - (50 * dt);
                 rectanglePersonagem.y = (int)posicao.y;
             
                 Thread.sleep(5);
                 if (key_states[KeyEvent.VK_UP]){          
                 imagem = new ImageIcon(this.getClass().getResource("sprite15modi.png")).getImage();
                 posicao.y = posicao.y - (50 * dt);
                 rectanglePersonagem.y = (int)posicao.y;
  
                 Thread.sleep(5);
                }
                 
        }
        }
    }
    
    public double GetTempoTotal()
    {
        return TempoTotal;
    }
    
     public void draw(Graphics2D g2d) {
         g2d.drawImage(imagem, (int)posicao.x, (int)posicao.y,null);
         g2d.drawImage(imgIntersecao, 300, 100,null);
         
         if(imgCrop != null)
            g2d.drawImage(imgCrop, 600, 100,null);
         
         //imagem = new ImageIcon(this.getClass().getResource("sprite01modi.png")).getImage();
    }

     
}
