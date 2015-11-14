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
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class Respostas {

    String certa;
    Boolean errada;
    int PosicaoLinha = 0;

    public String getRespCerta() {
        // The name of the file to open.
        String fileName = "C:\\Users\\Daniel\\Desktop\\PCS-Labirinto\\build\\classes\\pcs\\labirinto\\RespostasMath.txt";
        
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
          //  FileReader flr = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Daniel\\Desktop\\PCS-Labirinto\\build\\classes\\pcs\\labirinto\\RespostasMath.txt"));
            
            
            while((line = br.readLine()) != null) {                
                System.out.println(line);
                if (line.indexOf("R:") != -1){
                    certa = line.replace("R:", "");
                    PosicaoLinha++;
                    break;
                }
            }

            // Always close files.
            br.close();
            
        } catch (FileNotFoundException ex) {System.out.println("Unable to open file '"+ fileName + "'");
        
        } catch (IOException ex) {System.out.println("Error reading file '"+ fileName + "'");
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return certa;
    }

    public List<String> getRespErrada() {
        List<String> ex = null;
        return ex;
    }

}
