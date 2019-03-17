/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava_snake;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import static projectjava_snake.Commons.B_HEIGHT;
import static projectjava_snake.Commons.B_WIDTH;

/**
 *
 * @author Admin
 */
public class GameStart extends JPanel implements ActionListener{
    
    private enum STATE
    {
        MENU,
        GAME
    };
    
    private STATE state = STATE.MENU;
    public GameStart()
    {
        ///addKeyListener(new Board.TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
    }
    
    private void tick()
    {
        if(state == STATE.MENU)
        {
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
