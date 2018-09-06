package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;

public class BG1 extends Entity {//for background

    public BG1(int x, int y) {//size comes from the states
        super(x, y);
        this.xvel=-10;
    }

    @Override
    public Image getImage() {
        return Resources.background;
    }//takes image

    @Override
    public int getWidth() {
        return 1000;
    }//width

    @Override
    public int getHeight() {
        return 500;
    }//height

    public void update(){//sets the background
        super.update();
        if(this.x+1000<0)
            this.x=990;
    }
}
