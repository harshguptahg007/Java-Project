package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

//class for the text that comes on taking a heart
public class Text
{
private double x,y;
private long start, time, elapsed;
private String s;

	public Text(double x, double y, long time, String s)
	{
		this.s = s;
		this.x = x;
		this.y = y;
		this.time = time;
		start = System.nanoTime();
	}
	public boolean update()
	{
		
		y-= 1;
		elapsed = (System.nanoTime()- start) / 1000000;
		if (elapsed>time)
		{
			
			return true;
			
		}
		
		return false;
		
		
		
		
	}
	public void draw(Graphics2D g)
	{
		g.setFont((new Font ("Comic Sans MS", Font.PLAIN, 12)));
		int length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();//font matrix creates a martix around text  and getStringBounds
		// takes length of the text which is actual length of the text plus some additional length is taken into consideration
		g.setColor(new Color(0,0,0));
		g.drawString(s, (int)(x - length/2), (int)y);
		
	}
	
}