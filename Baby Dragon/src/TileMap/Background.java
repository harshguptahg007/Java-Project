package TileMap;

import Main.GamePanel;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;//importing package to encode and decode images, contains ImageReaderS and ImageWriterS

public class Background 
{
	//variable and object declarations
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double moveScale; //creates the parallax effect between background and foreground
	
	public Background(String s, double ms) 
	{
		try 
		{
			image = ImageIO.read(getClass().getResourceAsStream(s)); 
			//takes background image as input from the resource folder
			moveScale = ms;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void setPosition(double x, double y) 
	{
		this.x = (x * moveScale) % GamePanel.WIDTH; //we dont want that the image go off the screen.
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	
	public void setVector(double dx, double dy) 
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() 
	{
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) 
	{ // draws the background image 
		
		g.drawImage(image, (int)x, (int)y, null);
		//x,y are the position from the top-left corner . The observer parameter notifies the application of updates to
		//an image that is loaded asynchronously. It is generally null for BufferedImage class.
		if(x < 0) 
		{
			g.drawImage(image,(int)x + GamePanel.WIDTH,(int)y,null);//for scrolling image when image comes to
			// an end.
		}
		if(x > 0) 
		{
			g.drawImage(image,(int)x - GamePanel.WIDTH,	(int)y,	null);
		}
	}
	
}

