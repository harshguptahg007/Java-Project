package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation
{
	//variable declarations
	private BufferedImage[] frames;
	private int currentFrame;
	private long startTime;//starts the timer
	private long delay;//how long between each frame
	private boolean playedOnce;
	
	public Animation()
	{
		playedOnce = false;
	}
	public void setFrames(BufferedImage[] frames)
	{
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
		
	}
	public void setDelay(long d)
	{
		delay = d;
	}
	public void setFrame(int i)
	{
		currentFrame = i;//manually set the frame
	}
	
	public void update()//logic whether or not to move to the next frame.
	{
		if (delay == -1)
			return;// no animation
		
		long elapsed = (System.nanoTime() - startTime)/1000000;//to get in millisecond
		
		if (elapsed>delay)
		{
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length)//make sure that we do not go to past the no of frames
		{
			currentFrame = 0;
			playedOnce = true;
		}
	}
	public int getFrame(){return currentFrame;}
	public BufferedImage getImage(){return frames[currentFrame];}
	public boolean hasPlayedOnce(){return playedOnce;}
}
