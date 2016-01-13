/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author daniel.quintana
 */
public class Parede {
    Posicao posicao;
    private Image imagem = new ImageIcon(this.getClass().getResource("labirintoMathFacilmod.png")).getImage(); 
 
    public Parede(){
      posicao = new Posicao(100,100);
    }
    
    public double getPx (){
        return posicao.getPosicaoX();
    }
    
     public double getPy (){
        return posicao.getPosicaoY();
    }
     
     public double getWidth(){
         return 949;         
     }
     
     public double getHeight(){
         return 855;
     }
     
     public void draw(Graphics2D g2d){
         g2d.drawImage(imagem, (int)posicao.x, (int)posicao.y,null);           
     }
    
    
    
}
