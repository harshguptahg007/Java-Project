package in.codekamp.screens;

import in.codekamp.Resources.Resources;
import in.codekamp.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Stage1Screen extends Screen {
    public int i = 0;
    public Image[] images = {Resources.p1, Resources.p2, Resources.p3, Resources.p4, Resources.p5, Resources.p6, Resources.p7, Resources.p8};

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Resources.background,0,0,null);
        g.drawImage(Resources.ground,0,0,null);
        g.drawImage(Resources.log,39,339,null);
        this.i = this.i % 8;
        g.drawImage(images[i], 33, 293, null);
        this.i++;



    }

    @Override
    public void keyPressed(int keyCode) {

        if(keyCode==KeyEvent.VK_UP) {
            Stage2Screen.count=1;
            Resources.game.loop();
            GamePanel.currentScreen = new Stage2Screen();

        }
    }

    @Override
    public void keyReleased(int keyCode) {

    }

    @Override
    public void mousePressed(int x, int y) {
        if(x>0&&x<1000&&y>0&&y<500){
            Resources.game.loop();
            GamePanel.currentScreen=new Stage2Screen();}

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
