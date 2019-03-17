/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava_snake;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Admin
 */
public class MouseInput implements MouseListener {


    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        //public Rectangle playButton = new Rectangle(B_WIDTH/2 - 20, 200, 50, 25);
        if(mx >= (Commons.B_WIDTH/2 - 20) && mx <= (Commons.B_WIDTH/2 - 20 + 50))
        {
            if(my >= 200 && my <= 225)
            {
                Board.state = Board.STATE.GAME;
            }
            
            if(my >= 300 && my <= 325)
            {
                System.exit(1);
            }
        }
        //public Rectangle quitButton = new Rectangle(Commons.B_WIDTH/5 + 75, 200, 50, 25);
        

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    
    
}
