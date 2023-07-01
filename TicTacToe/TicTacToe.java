package tictactoe;

import javax.swing.JFrame;

/**@author veronica**/

public class TicTacToe {
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame ("Tic Tac Toe");
        frame.setSize(600,630);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        
        JogoDaVelha velha = new JogoDaVelha();
        velha.setBounds(0,0,600,600);
        frame.add(velha);
        
        frame.addMouseListener(velha);
    }
    
}
