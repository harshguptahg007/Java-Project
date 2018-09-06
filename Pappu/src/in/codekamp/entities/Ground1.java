package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;

public class Ground1 extends Entity {

    public Ground1(int x, int y) {
        super(x, y);
        this.xvel-=10;
    }

    @Override
    public Image getImage() {
        return Resources.ground;
    }

    @Override
    public int getWidth() {
        return 1000;
    }

    @Override
    public int getHeight() {
        return 500;
    }

    public void update(){
        super.update();
        if(this.x+1000<0)
            this.x=990;
    }
}
