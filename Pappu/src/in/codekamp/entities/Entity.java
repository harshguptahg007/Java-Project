package in.codekamp.entities;

import java.awt.*;

abstract public class Entity {
    public int x=0;
    public int y=0;
    public int xvel=0;
    public int yvel=0;
    public int xaccn=0;
    public int yaccn=0;
    public boolean isHidden =false;
    public Rectangle rectangle;

    public Entity(int x,int y)
    {
        this.x=x;
        this.y=y;
        this.xvel=0;
        this.yvel=0;
        this.xaccn=0;
        this.yaccn=0;
        this.isHidden =false;
        this.rectangle=new Rectangle();
    }

    public void update()
    {
        this.xvel+=this.xaccn;
        this.x+=this.xvel;

        this.yvel+=this.yaccn;
        this.y+=this.yvel;

        this.setBound();
    }

    public boolean isColliding(Entity otherEntity)
    {
        this.setBound();
        otherEntity.setBound();
        if(this.isHidden ==true||otherEntity.isHidden ==true)
            return false;
        else
            return this.rectangle.intersects(otherEntity.rectangle);
    }

    public void setBound()
    {
        this.rectangle.setBounds(this.x,this.y,this.getWidth()-10,this.getHeight()-10);
    }
    abstract public Image getImage();
    abstract public int getWidth();
    abstract public int getHeight();
}
