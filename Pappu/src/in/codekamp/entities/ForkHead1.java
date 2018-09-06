package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class ForkHead1 extends Entity {

    public ForkHead1(int x, int y) {
        super(x, y);
        this.xvel-=10;
    }

    @Override
    public Image getImage() {
        return Resources.fork_head;
    }

    @Override
    public int getWidth() {
        return 33;
    }

    @Override
    public int getHeight() {
        return 59;
    }

    public void update(){
        super.update();
        if(Fork1.showFork1==true)
            this.isHidden=true;

        if(this.x<0){
            this.isHidden=false;
            this.x=2406;
        }
        this.yGenerator();
        this.setBound();
    }

    public void yGenerator(){
        if(Fork1.r==0)
            this.y=140;
        else if(Fork1.r==1)
            this.y=132;
        else if(Fork1.r==2)
            this.y=147;

    }
}
