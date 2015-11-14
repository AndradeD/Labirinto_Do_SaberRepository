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
public class Monstro {
    String nome;
    Posicao posicao = new Posicao();
    Personagem personagem = new Personagem();
    public boolean ativo = true;
    
    public int getPx (){
        return posicao.getPosicaoX();
    }
    
     public int getPy (){
        return posicao.getPosicaoY();
    }
    
    public void mata(){
       if (personagem.verificaVida() == 0)
           personagem.morre();
    }
    
    public void morre(){
        if (personagem.getPx() == posicao.posicaoXFinalLab() && personagem.getPy() == posicao.posicaoYFinalLab())
            ativo = false;
    }
}
