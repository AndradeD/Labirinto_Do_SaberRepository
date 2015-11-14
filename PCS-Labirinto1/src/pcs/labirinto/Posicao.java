/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

/**
 *
 * @author Daniel
 */
public class Posicao {  
    int x;
    int y;
    
    public int getPosicaoX(){
        return x;
    }
    public int getPosicaoY(){
        return y;
    }
    public void setPosicaoX(int NewX){
        x = NewX;
    }
    public void setPosicaoY(int NewY){
        y = NewY;
    }      
    public int posicaoXFinalLab(){
        x = 78;
        return x;
    }
    public int posicaoYFinalLab(){        
        y = 78;
        return y;
    }
}
