/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael
 */
public class Ranking {
    int TempoTotal = 0;
    private FileWriter fileWriter;
    private BufferedReader readFile;    
    private BufferedWriter writeFile;
    public String[] VetorNomes = new String[100];
    public String[] VetorPontos = new String[100];
    File fileRanking = new File("C:\\Users\\daniel.quintana\\Desktop\\Labirinto_Do_SaberRepository-master\\PCS-Labirinto1\\build\\classes\\pcs\\labirinto\\Ranking.txt");
    
    public Ranking() {
        String line = null;
        int i = 0;
        int j = 0;
        try {
            openFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while ((line = readFile.readLine()) != null) {
                if(line.startsWith("-"))
                {
                    VetorNomes[i] = line;
                    i++;
                }
                if (line.startsWith("P")){
                    VetorPontos[j] = line;
                    j++;
                }
                
            }

        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
     public void openFile() throws FileNotFoundException, IOException
    {
        fileWriter = new FileWriter(fileRanking,true);
        readFile = new BufferedReader(new FileReader(fileRanking));        
    }
    
    public void close() throws IOException
    {
        readFile.close();
        fileWriter.close();
    }
    
    public void setPont(){        
         String Nome;   
         
         Nome = JOptionPane.showInputDialog(null, "Nome do Jogador :");
        try {
            openFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fileWriter.append("-"+Nome+System.getProperty("line.separator"));
        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fileWriter.append("P"+TempoTotal+System.getProperty("line.separator"));
        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            close();
        } catch (IOException ex) {
            Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void getNomesAndPontos(String[] nome,String[] ponto){
        for (int i = 0; i < nome.length;i++){
            if (nome[i] != null){
             System.out.println(nome[i] +": "+ ponto[i]);       
            }
        }
    }
    
    
}
