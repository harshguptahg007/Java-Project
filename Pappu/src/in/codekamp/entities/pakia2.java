package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class pakia2 extends Entity {
    public int i=0;
    public Image[] images={Resources.p1,Resources.p2,Resources.p3,Resources.p4,Resources.p5,Resources.p6,Resources.p7,Resources.p8};

    public pakia2(int x, int y) {
        super(x, y);
        this.isHidden=true;
    }

    @Override
    public Image getImage() {
        this.i++;
        this.i=this.i%8;
        return images[i];
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
        if(this.isHidden==false){
            this.xvel=10;
            this.ygenerator();
        }
        if(this.x>1000){
            this.isHidden=true;
            this.xvel=0;
            this.x=500;
            this.y=250;
        }
    }

    public void ygenerator(){
        Random random=new Random();
        int r=random.nextInt(2);
        if(r==0)
            this.y=-10;
        else if (r==1)
            this.y+=10;
    }
}
