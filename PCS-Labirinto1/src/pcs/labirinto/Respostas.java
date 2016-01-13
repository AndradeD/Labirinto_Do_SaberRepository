/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Rafael
 */
public class Respostas {
    
    private ArrayList<Character> respostas;
    Arquivo arq;
    char certa;
    Boolean errada;
    int PosicaoLinha = 0;
    
    public Respostas(){
        respostas = arq.accessRespostas();
    }
    public char getRespCerta(int index) {
        
        return certa = respostas.get(index);
          
    }
    public List<String> getRespErrada() {    // por que precisa retornar uma resposta errada ??
        List<String> ex = null;
        return ex;
    }
}