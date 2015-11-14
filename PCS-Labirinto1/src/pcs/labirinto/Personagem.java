/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

/**
 *
 * @author Rafael
 */
public class Personagem {
    String nome;
    int vida;
    Posicao posicao = new Posicao();
    public boolean ativo = true;
    
     public int getPx (){
        return posicao.getPosicaoX();
    }
    
     public int getPy (){
        return posicao.getPosicaoY();
    }
    
    public void morre(){
        ativo = false;
      System.out.println("Você perdeu!");
    }
    
    public void subtraiVida(){
        vida--;
    }
    
    public int verificaVida(){
       return vida;
    }
}
