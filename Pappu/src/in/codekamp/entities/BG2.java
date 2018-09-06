package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;

public class BG2 extends Entity {
    public BG2(int x, int y) {
        super(x, y);//gets the coordinates from the states
        this.xvel = -10;
    }

    @Override
    public Image getImage() {
        return Resources.background;
    }//takes the background image

    @Override
    public int getWidth() {
        return 1000;
    }//width

    @Override
    public int getHeight() {
        return 500;
    }//height

    public void update() {
        super.update();
        if (this.x + 1000 < 0)
            this.x = 990;//sets the background

    }
}
