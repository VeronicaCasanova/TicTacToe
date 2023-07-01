package tictactoe;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JogoDaVelha extends JPanel implements MouseListener {
    
    Font minhaFontGrande = new Font("Consolas", Font.BOLD, 80);
    Font minhaFontPeq = new Font("Consolas", Font.BOLD, 15);
    Font minhaFontMedia = new Font("Consolas", Font.BOLD, 25);
    
    int matriz[][];
    int jogador;
    int ganhador;
    boolean jogarNovamente;
    
    int vitoria1;
    int vitoria2;
    
    Color cor1;
    
    public JogoDaVelha(){
    
        matriz = new int[3][3];
        jogador = 1;
        ganhador = 0;
        jogarNovamente = false;
        //cor1 = new Color(224, 191, 184); //color rgb golden rose 
        cor1 = new Color(182, 102, 210);
        
        for(int lin = 0; lin < 3; lin++){
            
            for (int col = 0; col < 3; col++){
            
            System.out.print(matriz[lin][col]);
            }
            System.out.println();
        }
    }
    
    @Override
    public void paintComponent(Graphics g2){
        
        Graphics2D g = (Graphics2D) g2.create();
        
        for(int lin = 0; lin < 3; lin++){
            
            for (int col = 0; col < 3; col++){
            
            System.out.print(matriz[lin][col]);
            }
            System.out.println();
        }
        
        if (jogarNovamente){
        
            int jogarNov = new JOptionPane().showConfirmDialog(this, "Deseja jogar novamente?");
            
            if (jogarNov == JOptionPane.OK_OPTION){
                jogarNovamente = false;
                reiniciarJogo();
            }else{
                System.exit(1);
            }
        }
        
        g.setStroke(new BasicStroke(5));
        
        g.setFont(minhaFontGrande);
    
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);
            
        g.setColor(Color.black);
        g.drawLine(30, 200, 560, 200);
        g.drawLine(30, 400, 560, 400);
            
        g.drawLine(200, 30, 200, 570);
        g.drawLine(400, 30, 400, 570);
            
        for(int lin = 0; lin < 3; lin++){

            for (int col = 0; col < 3; col++){
                
                if (matriz[lin][col] == 1){    
                    g.setColor(Color.black);
                    g.drawString("o", 75 + col*200, 125 + lin*200);
                }else if (matriz[lin][col] == 2){
                    g.setColor(cor1);
                    g.drawString("x", 75 + col*200, 125 + lin*200);
                }
            }
        }
        
        if (ganhador != 0){
            
            if (ganhador == 3){
                g.setColor(Color.red);
                g.setFont(minhaFontMedia);
                g.drawString("Deu velha!", 230, 20);
            }else{    
            
                if (ganhador == 1)
                    g.setColor(Color.black);
                else if (ganhador == 2)
                    g.setColor(cor1);
                    g.setColor(Color.red);
                    g.setFont(minhaFontMedia);
                    g.drawString("O jogador " + ganhador + " venceu!", 180, 20);  
                    
            }
            
            jogarNovamente = true;
            
            repaint();
        
            g.setFont(minhaFontPeq);
            g.setColor(Color.black);
            g.drawString("Vitórias: " + vitoria1, 20, 20);
            g.setColor(cor1);
            g.drawString("Vitórias: " + vitoria2, 480, 20);

        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        
        int linha = e.getY()/200;
        int coluna = e.getX()/200;
        System.out.println("Clicou na linha: " + linha);
        System.out.println("Clicou na coluna " + coluna);
        
        if (jogador == 1 && matriz[linha][coluna] == 0){
            matriz[linha][coluna] = 1;
            jogador = 2;
        }else if (jogador == 2 && matriz[linha][coluna] == 0){
            matriz[linha][coluna] = 2;
            jogador = 1;
        }
        
        verificaGanhador();
        
        repaint();
        
    }
    
    private void reiniciarJogo(){
    
        for(int lin = 0; lin < 3; lin++){

            for (int col = 0; col < 3; col++){               
                matriz[lin][col] = 0;
                ganhador = 0;
                
            }
        }    
    }
    
    private void verificaGanhador(){
        
        for (int lin = 0; lin < 3; lin++){
            if (matriz[lin][0] == matriz[lin][1] && matriz[lin][0] == matriz[lin][2] && matriz[lin][0] != 0){
            System.out.println("Houver ganhador");
            ganhador = matriz[lin][0];
            break;
            }
        }
        
        for (int col = 0; col < 3; col++){
            if (matriz[0][col] == matriz[1][col] && matriz[0][col] == matriz[2][col] && matriz[0][col] != 0){
            System.out.println("Houver ganhador");
            ganhador = matriz[0][col];
            break;
            }
        }
        
        if (matriz[0][0] == matriz[1][1] && matriz[0][0] == matriz[2][2] && matriz[0][0] != 0){
            System.out.println("Houver ganhador");
            ganhador = matriz[0][0];
        }
        
        if (matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0] && matriz[0][2] != 0){
            System.out.println("Houver ganhador");
            ganhador = matriz[0][2];
        }
        
        if (ganhador == 1){
            vitoria1++;
        } else if (ganhador == 2){          
            vitoria2++;
        }else{
            
            boolean cheia = true;
            for(int lin = 0; lin < 3; lin++){

                for (int col = 0; col < 3; col++){               
                    if (matriz[lin][col] == 0)
                        cheia = false;
                }
            
            }
            
            if (cheia)
                ganhador = 3;
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
    
    }
    
    @Override
    public void mouseExited(MouseEvent e){
    
    }
    
    @Override
    public void mousePressed(MouseEvent e){
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
    
    }
    
}
