package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Coin extends Entity {

    public Image[] images={Resources.c1,Resources.c2};
    public int i=0;

    public Coin(int x, int y) {
        super(x, y);
        this.xvel-=10;
        this.xgenerator();
    }

    @Override
    public Image getImage() {
        return images[this.i];
    }

    @Override
    public int getWidth() {
        return 30;
    }

    @Override
    public int getHeight() {
        return 30;
    }

    public void update(){
        super.update();
        if(this.x<0){
            this.isHidden=false;
            this.xgenerator();
            this.i++;
            this.i%=2;
        }
    }

    public void xgenerator(){
        Random random = new Random();
        int r=random.nextInt(3);
        if(r==0)
            this.x=4500;
        else if(r==1)
            this.x=6500;
        else if(r==2){
            this.x=6500;
            this.isHidden=true;
        }
    }
}
