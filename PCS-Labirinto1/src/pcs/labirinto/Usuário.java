/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcs.labirinto;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author Rafael
 */
public class Usuário {

    String nome;
    String DisciplinaEscolhida = "";
    String DificuldadeEscolhida = "";
    Scanner scan = new Scanner(System.in);
    Posicao posicao;
    public boolean[] key_states = new boolean[256];

    public Usuário() {
        posicao = new Posicao(0,0);
        
    }
    
    public void update(double dt) {

        if ((key_states[KeyEvent.VK_RIGHT]) && (posicao.x < 650)) {
            posicao.x = posicao.x + (200 * dt);
        }
        if ((key_states[KeyEvent.VK_UP]) && (posicao.y > -40)) {
            posicao.y = posicao.y - (200 * dt);
        }
        if ((key_states[KeyEvent.VK_LEFT]) && (posicao.x > -50)) {
            posicao.x = posicao.x - (200 * dt);
        }
        if ((key_states[KeyEvent.VK_DOWN]) && (posicao.y < 450)) {
            posicao.y = posicao.y + (200 * dt);
        }
    }
}
