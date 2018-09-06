package in.codekamp.screens;

import in.codekamp.Resources.Resources;
import in.codekamp.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverScreen extends Screen {

    public int a[]={0,0,0};
    public int i=0,x=355,cntt=0,xh=0;

    public int lastScore = 0;

    public Image[] image = {Resources.score0,Resources.score1,Resources.score2,Resources.score3,Resources.score4,Resources.score5,Resources.score6,Resources.score7,Resources.score8,Resources.score9};

    @Override
    public void update() {
        if(this.cntt==0){
            this.cntt=1;
            this.lastScore=GamePanel.score;
            this.scoreGenerator();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Resources.restart,0,0,null);

        this.x=355;
        for(int j=this.i-1;j>=0;j--){
            g.drawImage(image[a[j]],this.x,187,null);
            this.x+=36;
        }

        this.xh=355;
        for(int j=2;j>=0;j--){
            g.drawImage(image[MenuScreen.a[j]],this.xh,142,null);
            this.xh+=36;
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        if(keyCode== KeyEvent.VK_ENTER)
            GamePanel.currentScreen=new LoadingScreen();
    }

    @Override
    public void keyReleased(int keyCode) {

    }

    @Override
    public void mousePressed(int x, int y) {

        if(x>732&&x<940&&y>153&&y<219 ){
            GamePanel.currentScreen=new LoadingScreen();
        }

        if(x>738&&x<937&&y>321&&y<381)
        {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    public void scoreGenerator(){

        while (GamePanel.score>0){
            a[i]=GamePanel.score%10;//taking out the current digit
            GamePanel.score=GamePanel.score/10;//removing that from the score so that it can be added during drawing
            i++;
        }
    }
}
