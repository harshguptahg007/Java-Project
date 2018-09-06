package in.codekamp.entities;

import in.codekamp.Resources.Resources;

import java.awt.*;

public class Log extends Entity {

    public Log(int x, int y) {
        super(x, y);
        this.xvel=this.xvel-10;
    }

    @Override
    public Image getImage() {
        return Resources.log;
    }

    @Override
    public int getWidth() {
        return 71;
    }

    @Override
    public int getHeight() {
        return 119;
    }

    public void update(){
        super.update();
        if(this.x<-100){
            this.x=-100;
        }
    }
}
