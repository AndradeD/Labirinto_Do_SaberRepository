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



/**
 *
 * @author Rafael
 */
public class Pergunta {
    final int F = -1;
    final int M = 0;
    final int D = 1;
    String Displina;
    int PosicaoLinha = 0;
    
    
    public char getPerguntaFacil(String disciplina)
    {
        String filePerguntas = null;
        String line = null;
        char resp = 0;
        
        if (disciplina.toLowerCase().equals("m"))
            filePerguntas = "C:\\Users\\Daniel\\Desktop\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\PerguntasMat.txt";
        
        if (disciplina.toLowerCase().equals("h"))
            filePerguntas = "C:\\Users\\Daniel\\Desktop\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\PerguntasHist.txt";
        
        try {
                // FileReader reads text files in the default encoding.
                FileReader file = new FileReader(filePerguntas);

                // Always wrap FileReader in BufferedReader.
                BufferedReader bufferedReader = new BufferedReader(file);
                while((line = bufferedReader.readLine()) != null) {
                    if(line.startsWith("R")){
                        resp = line.charAt(1);
                        System.out.println(line.substring(1,line.length()));
                    }else
                    System.out.println(line);
                }
                // Always close files.
                bufferedReader.close();         
            }
        
        catch(FileNotFoundException e) {
            System.out.println( "Erro pra abrir : '" +  filePerguntas + "'");                
        }
        catch(IOException ex) {System.out.println("Erro pra ler :'" + filePerguntas + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
    return resp;
    }
    
    public String getPerguntaMedio(String Disciplina){String ex = ""; return ex;}
    
    public String getPerguntaDificil(String Disciplina){String ex = ""; return ex;}
    
    
}
