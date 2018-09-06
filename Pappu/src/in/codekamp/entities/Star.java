package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Star extends Entity {

    public Star(int x, int y) {
        super(x, y);
        this.x-=10;
        this.xgenerator();
    }

    @Override
    public Image getImage() {
        return Resources.star;
    }

    @Override
    public int getWidth() {
        return 41;
    }

    @Override
    public int getHeight() {
        return 39;
    }

    public void update(){
        super.update();
        if(this.x<0){
            this.isHidden=false;
            this.xgenerator();
        }
    }

    public void xgenerator(){
        Random random=new Random();
        int r=random.nextInt(3);
        if(r==0)
            this.x=2000;
        else if(r==1)
            this.x=5000;
        else if(r==2){
            this.isHidden=true;
            this.x=5000;
        }
    }
}
