package in.codekamp.main;

import in.codekamp.Resources.Resources;
import in.codekamp.screens.LoadingScreen;
import in.codekamp.screens.Screen;
import in.codekamp.screens.Stage2Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements MouseListener,KeyListener,Runnable {
    public static boolean gamePaused=false;
    public static int score=0;
    public static Screen currentScreen;
    public int count=0;

    public GamePanel(){
        this.setPreferredSize(new Dimension(Game.GAME_HEIGHT,Game.GAME_WIDTH));
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        this.requestFocus();
        Resources.load();
        GamePanel.currentScreen = new LoadingScreen();

        Thread thread=new Thread(this);
        thread.setName("game thread");
        thread.start();
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        GamePanel.currentScreen.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        GamePanel.currentScreen.keyReleased(e.getKeyCode());
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        GamePanel.currentScreen.mousePressed(e.getX(),e.getY());
    }

    public void mouseReleased(MouseEvent e) {
        GamePanel.currentScreen.mouseReleased(e.getX(),e.getY());
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void run() {
        Graphics imageGraphics;
        Graphics panelGraphics;
        Image mainImage = new BufferedImage(1000,500,BufferedImage.TYPE_INT_RGB);

        while(true)
        {
            try{
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(gamePaused)
                continue;

            if(GamePanel.currentScreen instanceof Stage2Screen) {
                if (this.count % 10 == 0) {
                    score++;
                    this.count = 0;
                }
            }

            GamePanel.currentScreen.update();
            imageGraphics=mainImage.getGraphics();
            imageGraphics.clearRect(0,0,1000,500);
            GamePanel.currentScreen.draw(imageGraphics);
            imageGraphics.dispose();

            panelGraphics=this.getGraphics();
            panelGraphics.drawImage(mainImage,0,0,null);
            panelGraphics.dispose();


            if(GamePanel.currentScreen instanceof Stage2Screen)
                this.count++;

        }
    }
}
