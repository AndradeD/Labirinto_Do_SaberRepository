/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
/**
 *
 * @author Rafael
 */
public final class Pergunta {
    
    ArrayList<String> perguntas;
    ArrayList<Character> respostas;
    public String disciplina;
    public int nivel;
    public boolean respondida = false;
    
    Arquivo arq;
    
    public static void main(String[] args) throws IOException {   // temporário.
        Pergunta perg = new Pergunta("M", -1);
    }
        
    public Pergunta(String discpl, int nivel) throws IOException
    {
        disciplina = discpl.toLowerCase();
        this.nivel = nivel;
        arq = new Arquivo(discpl, nivel);
        montaPerguntas();
    }
    
    public void montaPerguntas()
    {
        perguntas = new ArrayList<>();
        respostas = new ArrayList<>();
        for(String str : getPergunta(disciplina, nivel))
            perguntas.add(str);
        for(Character chr : getRespostas())
            respostas.add(chr);
    }
  
    public ArrayList<Character> getRespostas(){
        
       return arq.accessRespostas();
    }
    
    public ArrayList<String> getPergunta(String disc, int nivel) {
        
        if(disc == "m"){
            if(nivel == -1)
                return arq.accessPerguntasF();
            else if (nivel == 0)
                return arq.accessPerguntasM();
            else if(nivel == 1)
                return arq.accessPerguntasD();
        }
        else if(disc == "h"){
            if(nivel == -1)
                return arq.accessPerguntasF();
            else if (nivel == 0)
                return arq.accessPerguntasM();
            else if(nivel == 1)
                return arq.accessPerguntasD();
        }
        else if(disc == "g"){
            if(nivel == -1)
                return arq.accessPerguntasF();
            else if (nivel == 0)
                return arq.accessPerguntasM();
            else if(nivel == 1)
                return arq.accessPerguntasD();
        }    
        return null;
    }
 
}