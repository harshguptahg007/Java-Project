package in.codekamp.main;

import javax.swing.*;

public class Game {

    public static final int GAME_HEIGHT = 1000;//defining the height of the panel
    public static final int GAME_WIDTH = 500;//defining the width of the panel

    public static void main(String [] args){

        JFrame frame = new JFrame();
        GamePanel panel=new GamePanel();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

}
