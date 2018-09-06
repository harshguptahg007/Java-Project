package in.codekamp.screens;

import in.codekamp.Resources.Resources;
import in.codekamp.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoadingScreen extends Screen {
    private int x=0;

    @Override
    public void update(){
        this.x+=4;
        if(this.x>353){
            GamePanel.currentScreen=new MenuScreen();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Resources.wasted,0,0,null);
        g.setColor(Color.yellow);
        g.fillRect(285,250,this.x,17);
    }

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_ENTER)
            GamePanel.currentScreen=new MenuScreen();
    }

    @Override
    public void keyReleased(int keyCode) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
