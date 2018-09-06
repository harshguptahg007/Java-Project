package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;
import java.util.Random;

public class Sad extends Entity {

    public Sad(int x, int y) {
        super(x, y);
        this.generator();
    }

    @Override
    public Image getImage() {
        return Resources.sad_pakia;
    }

    @Override
    public int getWidth() {
        return 52;
    }

    @Override
    public int getHeight() {
        return 51;
    }

    public void update(){
        super.update();
        if(this.x<0||this.y<0||this.y>500){
            this.generator();
        }
    }

    public void generator(){
        Random random=new Random();
        int r=random.nextInt(5);
        switch (r){//extra cases are there to have extra characters on screen... vrna bhut pas
            // pas ho rhe the aur random nae lag rha tha
            case 0:
            case 1:
            case 2:
            case 3:
                this.x=500;
                this.y=510;
                this.yvel=-25;
                this.yaccn=1;
                this.xvel=-15;
                this.isHidden=true;
                break;
            case 4:
                this.x=500;
                this.y=510;
                this.yvel=-25;
                this.yaccn=1;
                this.xvel=-15;
                this.isHidden=false;
                Resources.sad.play();
                break;
        }
    }
}

