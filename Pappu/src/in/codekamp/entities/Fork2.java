package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Fork2 extends Entity  {

    public static int s=0;
    public static boolean showFork2=false;

    public Fork2(int x, int y) {
        super(x, y);
        this.xvel-=10;
    }

    @Override
    public Image getImage() {
        return Resources.fork_handle2;
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
        Fork2.showFork2=this.isHidden;
    }

    public void ygenerator(){
        Random random = new Random();
        Fork2.s=random.nextInt(3);
        if(Fork2.s==0)
            this.y=-15;
        else if(Fork2.s==1)
            this.y=-25;
        else if(Fork2.s==2)
            this.y=-11;
    }

}
