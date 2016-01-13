/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Daniel
 */
public class NewJFrame extends JFrame {

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        setTitle("LabirintoDoSaber");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Labirinto());
        setSize(1000, 900);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
        /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
}
