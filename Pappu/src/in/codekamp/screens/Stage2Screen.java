package in.codekamp.screens;

import in.codekamp.Resources.Resources;
import in.codekamp.entities.*;
import in.codekamp.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Stage2Screen extends Screen {
    java.util.List<Entity> entities;
    java.util.List<Entity> obstacles;
    java.util.List<Entity> assets;
    java.util.List<Entity> pakias;
    java.util.List<Entity> other;
    BG1 bg1;
    BG2 bg2;
    Ground1 ground1;
    Ground2 ground2;
    Log log;
    Pappu pappu;
    public static int count = 0;
    public static int countt = 0;
    public static int xcor = 150;
    Branch1 branch1;
    Branch2 branch2;
    Fork1 fork1;
    ForkHead1 forkhead1;
    Branch3 branch3;
    Fork2 fork2;
    ForkHead2 forkhead2;
    Coin coin1;
    Star star;
    Grapes grapes;
    pakia1 pappu1;
    pakia2 pappu2;
    pakia3 pappu3;
    Angry angry;
    Sad sad;


    public int a[] = {0, 0, 0};
    public int i = 0, x = 0;
    public Image[] image = {Resources.score0, Resources.score1, Resources.score2, Resources.score3, Resources.score4, Resources.score5, Resources.score6, Resources.score7, Resources.score8, Resources.score9};

    public int t = 0;


    public int m = 0;
    public Image[] mute = {Resources.m1, Resources.m2};

    public int p=0;
    public Image[] paused={Resources.pause,Resources.play};


    public Stage2Screen() {
        this.entities = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.assets = new ArrayList<>();
        this.pakias = new ArrayList<>();
        this.other = new ArrayList<>();
        this.bg1 = new BG1(0, 0);
        this.bg2 = new BG2(1000, 0);
        this.ground1 = new Ground1(0, 0);
        this.ground2 = new Ground2(1000, 0);
        this.log = new Log(39, 339);
        this.pappu = new Pappu(33, 293);
        this.branch1 = new Branch1(1000, -200);
        this.branch2 = new Branch2(1000, 400);
        this.fork1 = new Fork1(1600, 199);
        this.forkhead1 = new ForkHead1(1592, 140);
        this.branch3 = new Branch3(2200, -200);
        this.fork2 = new Fork2(2800, -5);
        this.forkhead2 = new ForkHead2(2794, 307);
        this.coin1 = new Coin(4500, 250);
        this.star = new Star(2000, 250);
        this.grapes = new Grapes(3000, 250);
        this.pappu1 = new pakia1(500, 190);
        this.pappu2 = new pakia2(500, 250);
        this.pappu3 = new pakia3(500, 310);
        this.angry = new Angry(700, 510);
        this.sad = new Sad(500, 510);

        this.entities.add(this.bg1);
        this.entities.add(this.bg2);
        this.entities.add(this.ground1);
        this.entities.add(this.ground2);
        this.entities.add(this.log);
        this.entities.add(this.pappu);
        this.entities.add(this.branch1);
        this.entities.add(this.branch2);
        this.entities.add(this.branch3);
        this.entities.add(this.fork1);
        this.entities.add(this.fork2);
        this.entities.add(this.forkhead1);
        this.entities.add(this.forkhead2);
        this.entities.add(this.coin1);
        this.entities.add(this.star);
        this.entities.add(this.grapes);
        this.entities.add(this.pappu1);
        this.entities.add(this.pappu2);
        this.entities.add(this.pappu3);
        this.entities.add(this.angry);
        this.entities.add(this.sad);

        this.obstacles.add(this.branch1);
        this.obstacles.add(this.branch2);
        this.obstacles.add(this.branch3);
        this.obstacles.add(this.fork1);
        this.obstacles.add(this.fork2);
        this.obstacles.add(this.forkhead1);
        this.obstacles.add(this.forkhead2);

        this.assets.add(this.coin1);
        this.assets.add(this.star);
        this.assets.add(this.grapes);

        this.pakias.add(this.pappu1);
        this.pakias.add(this.pappu2);
        this.pakias.add(this.pappu3);


        this.other.add(this.angry);
        this.other.add(this.sad);


    }


    public void update() {
        for (int i = 0; i < this.entities.size(); i++) {
            this.entities.get(i).update();
        }


        if(this.pappu.y<-50||this.pappu.y>500) {
            GamePanel.currentScreen = new GameOverScreen();
            Resources.game.stop();
        }

        this.i = 0;
        this.t = GamePanel.score;
        this.scoreGenerator();


    }


    public void draw(Graphics g) {
        for (int i = 0; i < this.entities.size(); i++) {
            if (!this.entities.get(i).isHidden)
                g.drawImage(this.entities.get(i).getImage(), this.entities.get(i).x, this.entities.get(i).y, null);
        }

        for (int i = 0; i < this.obstacles.size(); i++) {
            //check whether it is visble or not
            if(this.obstacles.get(i).isHidden==false) {
                if (this.pappu.isColliding(this.obstacles.get(i))) {
                    if(Stage2Screen.countt==0) {
                        GamePanel.currentScreen = new GameOverScreen();
                        Resources.game.stop();
                    }
                }
            }
        }

        if (this.pappu.isColliding(this.coin1)) {
            if (this.coin1.isHidden == false) {
                this.coin1.isHidden = true;
                GamePanel.score+=50;
                Resources.ting.play();
            }
        }


        if (this.pappu.isColliding(this.star)) {
            if (this.star.isHidden == false) {
                this.star.isHidden = true;
                Resources.ting.play();
                Stage2Screen.countt = 1;
            }
        }

        if (Stage2Screen.countt == 1) {//this is for star functionality
            g.setColor(Color.black);
            g.fillRect(450,10,150,15);
            g.setColor(Color.ORANGE);
            g.fillRect(450, 10, xcor, 15);
            xcor -= 1;

            if (xcor <= 0) {
                xcor = 150;
                Stage2Screen.countt = 0;
            }
        }

        if (this.pappu.isColliding(this.grapes)) {
            if (this.grapes.isHidden == false) {
                Resources.ting.play();
                this.grapes.isHidden = true;
                this.pappu1.x = this.pappu.x + 80;
                this.pappu1.y = this.pappu.y - 60;

                this.pappu2.x = this.pappu.x + 80;
                this.pappu2.y = this.pappu.y;

                this.pappu3.x = this.pappu.x + 80;
                this.pappu3.y = this.pappu.y + 60;

                this.pappu1.isHidden = false;
                this.pappu2.isHidden = false;
                this.pappu3.isHidden = false;
            }
        }


        for (int i = 0; i < this.pakias.size(); i++) {
            for (int j = 0; j < this.obstacles.size(); j++) {
                if (this.pakias.get(i).isHidden == false && this.obstacles.get(j).isHidden == false) {
                    if (this.pakias.get(i).rectangle.intersects(this.obstacles.get(j).rectangle))
                        this.obstacles.get(j).isHidden = true;
                }
            }
        }

        if (this.pappu.rectangle.intersects(this.angry.rectangle)) {
            if (this.angry.isHidden == false) {
                if(Stage2Screen.countt==0) {
                    this.angry.xvel = 0;
                    this.angry.yvel = 0;
                    this.angry.yaccn = 1;
                }
            }
        }

        if (this.pappu.rectangle.intersects(this.sad.rectangle)) {
            if (this.sad.isHidden == false) {
                if(Stage2Screen.countt==0) {
                    this.sad.xvel = 0;
                    this.sad.yvel = 0;
                    this.sad.yaccn = 1;
                }
            }
        }


        this.x = 0;
        for (int j = i - 1; j >= 0; j--) {
            g.drawImage(image[a[j]], this.x, 0, null);
            this.x = this.x + 36;
        }


        for (int i = 0; i < this.pakias.size(); i++) {
            for (int j = 0; j < this.other.size(); j++) {
                if (this.pakias.get(i).isHidden == false && this.other.get(j).isHidden == false) {
                    if (this.pakias.get(i).rectangle.intersects(this.other.get(j).rectangle))
                        this.other.get(j).isHidden = true;
                }
            }
        }


        g.drawImage(mute[this.m],950,0,null);
        g.drawImage(paused[this.p],950,55,null);

    }


    public void scoreGenerator() {

        while (this.t > 0) {
            a[this.i] = this.t % 10;
            this.t = this.t / 10;
            this.i++;
        }
    }


    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            Stage2Screen.count = 1;
            Resources.flap.play();
            this.pappu.fly();
        }

        if (keyCode == KeyEvent.VK_SPACE) {
            Stage2Screen.count = 1;
            Resources.flap.play();
            this.pappu.accn();
        }

    }

    @Override
    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            Stage2Screen.count = 0;
            Pappu.j = 0;

        }

        if (keyCode == KeyEvent.VK_SPACE) {
            Stage2Screen.count = 0;
            Pappu.j = 0;
        }

    }

    @Override
    public void mousePressed(int x, int y) {

        if (x > 950 && x < 1000 && y > 0 && y < 40) {
            this.m++;
            this.m %= 2;
            if(this.m==1)
                Resources.game.stop();
            else
                Resources.game.loop();
        }
        else
        if(x>950&&x<1000&&y>55&&y<105)
        {
            this.p++;
            this.p%=2;
            GamePanel.gamePaused=!GamePanel.gamePaused;
            if(this.p==0)
                Resources.game.play();
            else
                Resources.game.stop();
        }
        else
        {
            Stage2Screen.count = 1;
            Resources.flap.play();
            this.pappu.fly();
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        Stage2Screen.count = 0;
        Pappu.j = 0;
    }
}

