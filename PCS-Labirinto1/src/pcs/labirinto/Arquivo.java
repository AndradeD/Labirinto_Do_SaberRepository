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
import java.util.ArrayList;
import sun.applet.Main;
/**
 *
 * @author Rafael
 */
public final class Arquivo {
    private BufferedReader readFile;
    private String filePerguntas = null;
    
    private ArrayList<Character> answers;
    private ArrayList<String> questionsF;
    private ArrayList<String> questionsM;
    private ArrayList<String> questionsD;
    
    String disciplina;
    int nivel;
    
    int tag;
    
    public static void main(String args[]) throws IOException{   // temporariamente.
        Arquivo arq = new Arquivo("m", -1);
    }
    
    public Arquivo(String d, int nivel) throws IOException {
        disciplina = d;
        this.nivel = nivel;
        
        if (disciplina.toLowerCase().equals("m")){
            if(nivel == -1)
                filePerguntas = "C:\\Users\\daniel.quintana\\Desktop\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\PerguntasMatFacil.txt";
            else if(nivel == 0)
                filePerguntas = "C:\\Users\\Hp\\Downloads\\Labirinto_Do_SaberRepository-master (2)\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\PerguntasMatMedio.txt";
            else if (nivel == 1)
                filePerguntas = "C:\\Users\\Hp\\Downloads\\Labirinto_Do_SaberRepository-master (2)\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\PerguntasMatDificil.txt";
            
        }else if (disciplina.toLowerCase().equals("h")){
            if(nivel == -1)
                filePerguntas = "C:\\Users\\ccet\\Downloads\\Labirinto_Do_SaberRepository-master\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\src\\pcs\\labirinto\\PerguntasHistFacil.txt";
            else if(nivel == 0)
                filePerguntas = "C:\\Users\\ccet\\Downloads\\Labirinto_Do_SaberRepository-master\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\src\\pcs\\labirinto\\PerguntasHistMedio.txt";
            else if (nivel == 1)
                filePerguntas = "C:\\Users\\ccet\\Downloads\\Labirinto_Do_SaberRepository-master\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\src\\pcs\\labirinto\\PerguntasHistDificil.txt";
        }else if (disciplina.toLowerCase().equals("g")){
            if(nivel == -1)
                filePerguntas = "C:\\Users\\ccet\\Downloads\\Labirinto_Do_SaberRepository-master\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\src\\pcs\\labirinto\\PerguntasGeoFacil.txt";
            if(nivel == 0)
                filePerguntas = "C:\\Users\\ccet\\Downloads\\Labirinto_Do_SaberRepository-master\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\src\\pcs\\labirinto\\PerguntasGeoMedio.txt";
            if(nivel == -1)
                filePerguntas = "C:\\Users\\ccet\\Downloads\\Labirinto_Do_SaberRepository-master\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\src\\pcs\\labirinto\\PerguntasGeoDificl.txt";
        }
        
        makeRespostas();
        makePerguntas();
                
    }
    
    private void openFile() throws FileNotFoundException
    {
        readFile = new BufferedReader(new FileReader(filePerguntas));
    }
    
    private void close() throws IOException
    {
        readFile.close();
    }
    
    private void makeRespostas() throws FileNotFoundException, IOException{
        String line = null;
        answers = new ArrayList<>();
        openFile();
        
        try {
            while(readFile.ready())
            {
                line = readFile.readLine();
                if(line.startsWith("R"))
                {
                    answers.add(line.charAt(1));
                    System.out.println(line.substring(1,line.length()));                    
                }
            }
        } catch (Exception e) {
            System.out.println( "Erro pra abrir : '" +  filePerguntas + "'");                
        }
        
        close();
    }
    
    public void makePerguntas() throws FileNotFoundException, IOException
    {
        String line = "";
        questionsF = new ArrayList<>();
        questionsM = new ArrayList<>();
        questionsD = new ArrayList<>();
        openFile();
        
        try {
            while(readFile.ready())
            {
                String aux = readFile.readLine();
                if(aux.startsWith("R")){
                    line = line + aux.substring(1);
                                       
                }else if(aux.equals("-")){
                        if(nivel == -1){
                            System.out.println(line);
                            questionsF.add(line);
                            line = "";
                        }else if(nivel == 0){
                            System.out.println(line);
                            questionsM.add(line);
                            line = "";
                        }else if(nivel == 1){
                            System.out.println(line);
                            questionsD.add(line);
                            line = "";
                        }
                }else
                    line = line + aux;
            }
        }
        catch(FileNotFoundException e) {
            System.out.println( "Erro pra abrir : '" +  filePerguntas + "'");                
        }
        catch(IOException ex) {System.out.println("Erro pra ler :'" + filePerguntas + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        close();
    }
    
    public ArrayList<Character> accessRespostas(){
        return answers;
    }
    
    public ArrayList<String> accessPerguntasF(){
        return questionsF;
    }
    
    public ArrayList<String> accessPerguntasM(){
        return questionsM;
    }
    
    public ArrayList<String> accessPerguntasD(){
        return questionsD;
    }
}