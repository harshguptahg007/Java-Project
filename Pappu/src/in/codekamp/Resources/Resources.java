package in.codekamp.Resources;

import javax.imageio.ImageIO;
import java.applet.AudioClip;
import java.awt.*;
import java.applet.Applet;
import java.io.IOException;

public class Resources {

    public static AudioClip flap=null;
    public static AudioClip angry=null;
    public static AudioClip sad=null;
    public static AudioClip game=null;
    public static AudioClip ting=null;

    public static Image angry_pakia=null;
    public static Image restart=null;
    public static Image berries=null;
    public static Image background=null;
    public static Image branch=null;
    public static Image cloud1=null;
    public static Image controls=null;
    public static Image fork_handle=null;
    public static Image fork_handle2=null;
    public static Image fork_head=null;
    public static Image fork_head2=null;
    public static Image ground=null;
    public static Image happy=null;
    public static Image log=null;
    public static Image m1=null;
    public static Image m2=null;
    public static Image menu=null;
    public static Image sad_pakia=null;
    public static Image selector=null;
    public static Image stand=null;
    public static Image star=null;
    public static Image wasted=null;
    public static Image pappuf=null;
    public static Image pappuf1=null;
    public static Image pappuf1r=null;
    public static Image pappuf2=null;
    public static Image pappuf2r=null;
    public static Image pappuf3=null;
    public static Image pappuf3r=null;
    public static Image pappuf4=null;
    public static Image branchr=null;

    public static Image p1=null;
    public static Image p1r1=null;
    public static Image p2=null;
    public static Image p2r=null;
    public static Image p3=null;
    public static Image p3r=null;
    public static Image p4=null;
    public static Image p4r=null;
    public static Image p5=null;
    public static Image p5r=null;
    public static Image p6=null;
    public static Image p6r=null;
    public static Image p7=null;
    public static Image p7r=null;
    public static Image p8=null;
    public static Image p8r=null;

    public static Image c1=null;
    public static Image c2=null;

    public static Image pappuf1a=null;
    public static Image pappuf1b=null;
    public static Image pappuf1c=null;
    public static Image pappuf1d=null;
    public static Image pappuf1e=null;
    public static Image pappuf1f=null;
    public static Image pappuf1g=null;
    public static Image pappuf1h=null;
    public static Image pappuf1i=null;
    public static Image pappuf1j=null;

    public static Image score0=null;
    public static Image score1=null;
    public static Image score2=null;
    public static Image score3=null;
    public static Image score4=null;
    public static Image score5=null;
    public static Image score6=null;
    public static Image score7=null;
    public static Image score8=null;
    public static Image score9=null;

    public static Image play=null;
    public static Image pause=null;

    public static void load()
    {
        try {
            flap= Applet.newAudioClip(Resources.class.getResource("audios/flap.wav"));
            Resources.angry = Applet.newAudioClip(Resources.class.getResource("audios/jump1.wav"));
            Resources.sad = Applet.newAudioClip(Resources.class.getResource("audios/jump2.wav"));
            Resources.game = Applet.newAudioClip(Resources.class.getResource("audios/pappu-pakia2.3.wav"));
            Resources.ting = Applet.newAudioClip(Resources.class.getResource("audios/ting.wav"));
            angry_pakia= ImageIO.read(Resources.class.getResource("Images/angry_pakia.png"));
            restart= ImageIO.read(Resources.class.getResource("Images/anu1.png"));
            berries= ImageIO.read(Resources.class.getResource("Images/berries.png"));
            background= ImageIO.read(Resources.class.getResource("Images/bg_combined.png"));
            branch= ImageIO.read(Resources.class.getResource("Images/branch.png"));
            branchr= ImageIO.read(Resources.class.getResource("Images/branch2.png"));
            cloud1= ImageIO.read(Resources.class.getResource("Images/cloud1.png"));
            controls= ImageIO.read(Resources.class.getResource("Images/controls.png"));
            fork_handle= ImageIO.read(Resources.class.getResource("Images/fork_handle.png"));
            fork_handle2= ImageIO.read(Resources.class.getResource("Images/fork_handle_flipped.png"));
            fork_head= ImageIO.read(Resources.class.getResource("Images/fork_head.png"));
            fork_head2= ImageIO.read(Resources.class.getResource("Images/fork_head_flipped.png"));
            ground= ImageIO.read(Resources.class.getResource("Images/ground.png"));
            happy= ImageIO.read(Resources.class.getResource("Images/happy_pakia.png"));
            log= ImageIO.read(Resources.class.getResource("Images/log.png"));
            m1= ImageIO.read(Resources.class.getResource("Images/m1.png"));
            m2= ImageIO.read(Resources.class.getResource("Images/m2.png"));
            menu= ImageIO.read(Resources.class.getResource("Images/menu.png"));
            p1= ImageIO.read(Resources.class.getResource("Images/p1.png"));
            pappuf= ImageIO.read(Resources.class.getResource("Images/pappuf.png"));
            pappuf1= ImageIO.read(Resources.class.getResource("Images/pappuf1.png"));
            pappuf1r= ImageIO.read(Resources.class.getResource("Images/pappuf1.5.png"));
            pappuf2= ImageIO.read(Resources.class.getResource("Images/pappuf2.png"));
            pappuf2r= ImageIO.read(Resources.class.getResource("Images/pappuf2.5.png"));
            pappuf3= ImageIO.read(Resources.class.getResource("Images/pappuf3.png"));
            pappuf3r= ImageIO.read(Resources.class.getResource("Images/pappuf3.5.png"));
            pappuf4= ImageIO.read(Resources.class.getResource("Images/pappuf4.png"));
            p1r1= ImageIO.read(Resources.class.getResource("Images/p1r1.png"));
            p2= ImageIO.read(Resources.class.getResource("Images/p2.png"));
            p2r= ImageIO.read(Resources.class.getResource("Images/p2r.png"));
            p3= ImageIO.read(Resources.class.getResource("Images/p3.png"));
            p3r= ImageIO.read(Resources.class.getResource("Images/p3r.png"));
            p4= ImageIO.read(Resources.class.getResource("Images/p4.png"));
            p4r= ImageIO.read(Resources.class.getResource("Images/p4r.png"));
            p5= ImageIO.read(Resources.class.getResource("Images/p5.png"));
            p5r= ImageIO.read(Resources.class.getResource("Images/p5r.png"));
            p6= ImageIO.read(Resources.class.getResource("Images/p6.png"));
            p6r= ImageIO.read(Resources.class.getResource("Images/p6r.png"));
            p7= ImageIO.read(Resources.class.getResource("Images/p7.png"));
            p7r= ImageIO.read(Resources.class.getResource("Images/p7r.png"));
            p8= ImageIO.read(Resources.class.getResource("Images/p8.png"));
            p8r= ImageIO.read(Resources.class.getResource("Images/p8r.png"));
            sad_pakia= ImageIO.read(Resources.class.getResource("Images/sad_pakia.png"));
            selector= ImageIO.read(Resources.class.getResource("Images/selector.png"));
            stand= ImageIO.read(Resources.class.getResource("Images/stand.png"));
            star= ImageIO.read(Resources.class.getResource("Images/star.png"));
            wasted=ImageIO.read(Resources.class.getResource("Images/wasted.jpg"));
            c1=ImageIO.read(Resources.class.getResource("Images/c1.png"));
            c2=ImageIO.read(Resources.class.getResource("Images/c2.png"));

            pappuf1a= ImageIO.read(Resources.class.getResource("Images/pappuf1a.png"));
            pappuf1b= ImageIO.read(Resources.class.getResource("Images/pappuf1b.png"));
            pappuf1c= ImageIO.read(Resources.class.getResource("Images/pappuf1c.png"));
            pappuf1d= ImageIO.read(Resources.class.getResource("Images/pappuf1d.png"));
            pappuf1e= ImageIO.read(Resources.class.getResource("Images/pappuf1e.png"));
            pappuf1f= ImageIO.read(Resources.class.getResource("Images/pappuf1f.png"));
            pappuf1g= ImageIO.read(Resources.class.getResource("Images/pappuf1g.png"));
            pappuf1h= ImageIO.read(Resources.class.getResource("Images/pappuf1h.png"));
            pappuf1i= ImageIO.read(Resources.class.getResource("Images/pappuf1i.png"));
            pappuf1j= ImageIO.read(Resources.class.getResource("Images/pappuf1j.png"));

            score0 = ImageIO.read(Resources.class.getResource("Images/font_big_0.png"));
            score1 = ImageIO.read(Resources.class.getResource("Images/font_big_1.png"));
            score2 = ImageIO.read(Resources.class.getResource("Images/font_big_2.png"));
            score3 = ImageIO.read(Resources.class.getResource("Images/font_big_3.png"));
            score4 = ImageIO.read(Resources.class.getResource("Images/font_big_4.png"));
            score5 = ImageIO.read(Resources.class.getResource("Images/font_big_5.png"));
            score6 = ImageIO.read(Resources.class.getResource("Images/font_big_6.png"));
            score7 = ImageIO.read(Resources.class.getResource("Images/font_big_7.png"));
            score8 = ImageIO.read(Resources.class.getResource("Images/font_big_8.png"));
            score9 = ImageIO.read(Resources.class.getResource("Images/font_big_9.png"));

            play= ImageIO.read(Resources.class.getResource("Images/play.png"));
            pause= ImageIO.read(Resources.class.getResource("Images/pause.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
