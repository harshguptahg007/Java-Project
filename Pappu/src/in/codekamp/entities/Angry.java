package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Angry extends Entity {



    public Angry(int x, int y) {//x and y coordinates comes from the different states
        super(x, y);
        this.generator();
    }

    @Override
    public Image getImage() {
        return Resources.angry_pakia;
    }//gets the image

    @Override
    public int getWidth() {
        return 52;
    }//width

    @Override
    public int getHeight() {
        return 51;
    }//height

    public void update(){
        super.update();
        if(this.x<0||this.y<0||this.y>500){
            this.generator();
        }
    }

    public void generator(){//different locations for the entity
        Random random=new Random();
        int r=random.nextInt(5);
        switch (r){//extra cases are there to have extra entities on screen... agr extra nae rkh rhe h to bhut pas
            // pas aa ja rhe h sb
            case 0:
            case 2:
                this.x=500;
                this.y=510;
                this.yvel=-25;
                this.yaccn=1;
                this.xvel=-15;
                this.isHidden=true;
                break;
            case 1:
            case 3:
                this.x=800;
                this.y=510;
                this.yvel=-25;
                this.yaccn=1;
                this.xvel=-15;
                this.isHidden=true;
                break;
            case 4:
                this.x=700;
                this.y=510;
                this.yvel=-25;
                this.yaccn=1;
                this.xvel=-15;
                this.isHidden=false;
                Resources.angry.play();
                break;
        }
    }
}
