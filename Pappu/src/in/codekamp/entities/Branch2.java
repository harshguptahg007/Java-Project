package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Branch2 extends Entity {

    public Branch2(int x, int y) {
        super(x, y);
        this.xvel-=10;
        this.ygenerator();
    }

    @Override
    public Image getImage() {
        return Resources.branchr;
    }

    @Override
    public int getWidth() {
        return 31;
    }

    @Override
    public int getHeight() {
        return 500;
    }

    public void update(){
        super.update();
        if(this.x<0){
            this.isHidden=false;
            this.x=2400;
            this.ygenerator();
        }
        this.setBound();
    }

    public void ygenerator(){
        Random random=new Random();
        int r=random.nextInt(3);
        if(r==0) {
            this.y = 400;
            this.isHidden=false;
        }
        else if(r==1) {
            this.y = 390;
            this.isHidden=false;
        }
        else if(r==2){
            this.y = 390;
            this.isHidden=true;
        }
    }
}
