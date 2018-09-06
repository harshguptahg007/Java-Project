package in.codekamp.screens;

import in.codekamp.Resources.Resources;
import in.codekamp.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuScreen extends Screen {

    public int i=0,j=0;
    public Image[] images = {Resources.p1, Resources.p2, Resources.p3, Resources.p4, Resources.p5, Resources.p6, Resources.p7, Resources.p8};
    public Image[] volumes = {Resources.m1, Resources.m2};

    public int cnt=0,x=432;
    public static int a[]={0,0,0};
    public Image[] image={Resources.score0,Resources.score1,Resources.score2,Resources.score3,Resources.score4,Resources.score5,Resources.score6,Resources.score7,Resources.score8,Resources.score9};

    @Override
    public void update() {
        if(this.cnt==0) {
            this.cnt = 1;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Resources.menu,0,0,null);

        g.drawImage(volumes[j],950,0,null);
        this.i=this.i%8;
        g.drawImage(images[i],44,293,null);
        this.i++;

        this.x=432;//for drawing score of the player
        for(int i=2;i>=0;i--)
        {
            g.drawImage(image[a[i]],this.x,148,null);
            this.x+=36;
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER)
            GamePanel.currentScreen = new Stage1Screen();
    }

    @Override
    public void keyReleased(int keyCode) {

    }

    @Override
    public void mousePressed(int x, int y) {
        if (x > 732 && x < 943 && y > 149 && y < 218)
            GamePanel.currentScreen = new Stage1Screen();


        if (x > 950 && x < 1000 && y > 0 && y < 40) {//for changing volume controls
            this.j++;
            this.j %= 2;
        }
    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
