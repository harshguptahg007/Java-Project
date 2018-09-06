package in.codekamp.screens;

import in.codekamp.main.GamePanel;
import java.awt.*;

abstract public class Screen {

    abstract public void update();
    abstract public void draw(Graphics g);
    abstract public void keyPressed(int keyCode);
    abstract public void keyReleased(int keyCode);
    abstract public void mousePressed(int x,int y);
    abstract public void mouseReleased(int x,int y);

}
