package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;

public class ForkHead2 extends Entity {

    public ForkHead2(int x, int y) {
        super(x, y);
        this.xvel-=10;
    }

    @Override
    public Image getImage() {
        return Resources.fork_head2;
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
        if(Fork2.showFork2==true)
            this.isHidden=true;

        if(this.x<0){
            this.isHidden=false;
            this.x=2406;
        }
        this.yGenerator();
        this.setBound();
    }

    public void yGenerator(){
        if(Fork2.s==0)
            this.y=297;
        else if(Fork2.s==1)
            this.y=287;
        else if(Fork2.s==2)
            this.y=301;

    }
}
