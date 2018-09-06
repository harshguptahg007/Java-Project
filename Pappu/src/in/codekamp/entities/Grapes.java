package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Grapes extends Entity {

    public Grapes(int x, int y) {
        super(x, y);
        this.xvel-=10;
        this.xGenerator();
    }

    @Override
    public Image getImage() {
        return Resources.berries;
    }

    @Override
    public int getWidth() {
        return 30;
    }

    @Override
    public int getHeight() {
        return 42;
    }

    public void update(){
        super.update();
        if(this.x<0){
            this.isHidden=false;
            this.xGenerator();
        }
    }

    public void xGenerator(){
        Random random=new Random();
        int r=random.nextInt(3);
        if(r==0)
            this.x=3000;
        else if(r==1)
            this.x=5000;
        else if(r==2){
            this.x=7000;
            this.isHidden=true;
        }
    }
}
