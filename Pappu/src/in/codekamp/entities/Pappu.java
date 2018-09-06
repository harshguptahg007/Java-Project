package in.codekamp.entities;

import in.codekamp.Resources.Resources;
import in.codekamp.screens.Stage2Screen;

import java.awt.*;

public class Pappu extends Entity {

    public Image[] images = {Resources.p1r1, Resources.p2r, Resources.p3r, Resources.p4r, Resources.p5r, Resources.p6r, Resources.p7r, Resources.p8r};
    public Image[] images2={Resources.pappuf1,Resources.pappuf1a,Resources.pappuf1b,Resources.pappuf1c,Resources.pappuf1d,Resources.pappuf1e,Resources.pappuf1f,Resources.pappuf1g,Resources.pappuf1h,Resources.pappuf1i, Resources.pappuf1j};
    public int i=0;
    public static int j=0;

    public Pappu(int x, int y) {
        super(x, y);
        this.yaccn=1;
    }

    @Override
    public Image getImage() {
        if(Stage2Screen.count==1){
            this.i++;
            this.i%=8;
            return images[i];
        }
        else{
            j++;
            if(j>=10){
                j=10;
            }
            return images2[j];
        }
    }

    @Override
    public int getWidth() {
        return 60;
    }

    @Override
    public int getHeight() {
        return 59;
    }

    public void update(){
        super.update();
    }

    public void fly(){
        this.yvel=-10;
    }

    public void fall(){
        this.yvel=5;
    }

    public void accn(){
        this.yvel=-20;
    }
}
