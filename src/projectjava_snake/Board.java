/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectjava_snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class Board extends JPanel implements ActionListener, Commons {

    boolean leftDirection = false;
    boolean rightDirection = true;
    boolean upDirection = false;
    boolean downDirection = false;
    
    Snake snake;
    Apple apple;

    private boolean inGame = true;
    
    private Menu menu;

    private Timer timer;
    private Image imgBall;
    private Image imgApple;
    private Image imgHead;
    
    private static JLabel statusbar;
    
    public static enum STATE
    {
        MENU,
        GAME
    };
    public static STATE state = STATE.MENU;


    public Board() {
        addKeyListener(new TAdapter());
        addMouseListener(new MouseInput());
        setBackground(Color.BLACK);
        setFocusable(true);
        statusbar = SnakeGame.getStatusBar();

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        menu = new Menu();
        
        loadImages();
        initGame();

    }
    
    

    private void initGame() {

        snake = new Snake();
        apple = new Apple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon(getClass().getResource("/resources/dot.png"));
        imgBall = iid.getImage();

        ImageIcon iia = new ImageIcon(getClass().getResource("/resources/apple.png"));
        imgApple = iia.getImage();

        ImageIcon iih = new ImageIcon(getClass().getResource("/resources/head.png"));
        imgHead = iih.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if(state == STATE.GAME)
        {
            if (inGame) {

                g.drawImage(imgApple, apple.getApple_x(), apple.getApple_y(), this);

                for (int z = 0; z < snake.getDots(); z++) {
                    if (z == 0) {
                        g.drawImage(imgHead, snake.getSnakeX()[z], snake.getSnakeY()[z], this);
                    } else {
                        g.drawImage(imgBall, snake.getSnakeX()[z], snake.getSnakeY()[z], this);
                    }
                }

                Toolkit.getDefaultToolkit().sync();

            } else {

                gameOver(g);
            }  
        }
        else
        {
            menu.render(g);
        }
    }
    
    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }
    
    private void checkCollision() {

        for (int z = snake.getDots(); z > 0; z--) {

            if ((z > 4) && (snake.getSnakeX()[0] == snake.getSnakeX()[z]) && (snake.getSnakeY()[0] == snake.getSnakeY()[z])) {
                inGame = false;
            }
        }

        if (snake.getSnakeY()[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (snake.getSnakeY()[0] < 0) {
            inGame = false;
        }

        if (snake.getSnakeX()[0] >= B_WIDTH) {
            inGame = false;
        }

        if (snake.getSnakeX()[0] < 0) {
            inGame = false;
        }
        
        if (!inGame) {
            timer.stop();
        }
    }
    
    private void checkApple() {

        if ((snake.getSnakeX()[0] == apple.getApple_x()) && (snake.getSnakeY()[0] == apple.getApple_y())) {

            snake.setDots(snake.getDots() + 1);
            
            apple = new Apple();
        }
    }
    
    private void move() {

        int[] x = snake.getSnakeX();
        int[] y = snake.getSnakeY();
        
        for (int z = snake.getDots(); z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
        
        snake.setX(x);
        snake.setY(y);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    public class TAdapter extends KeyAdapter implements Commons {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

}
