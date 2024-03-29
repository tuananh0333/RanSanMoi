/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava_snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static projectjava_snake.Commons.B_HEIGHT;
import static projectjava_snake.Commons.B_WIDTH;

/**
 *
 * @author Admin
 */
public class SnakeGame extends JFrame {
    
    private static JLabel statusbar;
    
    public SnakeGame()
    {
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        statusbar = new JLabel("Your score: 0");
        add(statusbar, BorderLayout.SOUTH);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static JLabel getStatusBar() {

        return statusbar;
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new SnakeGame();
            ex.setVisible(true);
        });
    }
}
