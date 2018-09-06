package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Fork1 extends Entity {

    public static int r=0;//used for checking overlapping with other entities
    public static boolean showFork1=false;//used so that no overlapping of forks is not there

    public Fork1(int x, int y) {
        super(x, y);
        this.xvel-=10;
    }

    @Override
    public Image getImage() {
        return Resources.fork_handle;
    }

    @Override
    public int getWidth() {
        return 22;
    }

    @Override
    public int getHeight() {
        return 312;
    }

    public void update(){
        super.update();
        if(this.x<0){
            this.isHidden=false;
            this.x=2400;
        }
        this.ygenerator();
        this.setBound();
        Fork1.showFork1=this.isHidden;
    }

    public void ygenerator(){
        Random random = new Random();
        Fork1.r=random.nextInt(3);
        if(Fork1.r==0)
            this.y=199;
        else if(Fork1.r==1)
            this.y=191;
        else if(Fork1.r==2)
            this.y=206;
    }
}
