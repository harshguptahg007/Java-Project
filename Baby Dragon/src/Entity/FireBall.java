package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.imageio.ImageIO;
import Audio.AudioPlayer;
import TileMap.TileMap;

/* this class basically loads the images for fireball and sounds that it will make for fireball and it also checks
 *  whether the fireball has collided or not. if the fireball has collided it will reset the fireball means it will
 *  make the fireball ready to shoot.
 *  */
public class FireBall extends MapObject
{

	private boolean hit, remove;
	private BufferedImage[] sprites;
	private BufferedImage[] hitSprites;
	private HashMap<String, AudioPlayer> sfx;//taking sound effects as key value pairs
	public FireBall(TileMap tm, boolean right)
	{
		super(tm);
		sfx = new HashMap<String,AudioPlayer>();
		sfx.put("explosion",new AudioPlayer("/SFX/explosion.mp3"));
		facingRight = right;
		moveSpeed = 3.8;
		if(right)
			dx = moveSpeed;
		
		else
			dx = -moveSpeed;
		
		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;
		
		//load sprites
		try{
			//loadin images for fireball and the image after the fireball hits something
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/fireball.gif"));
			sprites = new BufferedImage[4];//stores images of fireball
			for(int i = 0; i<sprites.length; i++)
			{
				sprites[i]=spritesheet.getSubimage(i*width, 0, width, height);
			}
			hitSprites = new BufferedImage[3];//stores images of fireball after hitting something
			for(int i = 0; i<hitSprites.length; i++)
			{
				hitSprites[i]=spritesheet.getSubimage(i*width, height, width, height);//(x,y,width,height)
				//x and y are given for the particular image in the complete image. by giving height in y, we
				// access the second row of the complete image
			}
			animation = new Animation();
			animation.setFrames(sprites);
			animation.setDelay(70);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setHit()//reloads the fireball attack
	{
		sfx.get("explosion").play();
		if(hit)
			return;
		hit = true;
		animation.setFrames(hitSprites);
		animation.setDelay(70);
		dx = 0;
	}
	
	public boolean shouldRemove()
	{
		return remove;
	}
	
	public void update()
	{
		checkTileMapCollision();
		if(x<=0 || x >= tileMap.getWidth()-width)
			setHit();
		setPosition(xtemp, ytemp);
		if(dx == 0 && !hit)
		{
			setHit();
		}
		animation.update();
		if(hit&&animation.hasPlayedOnce())
		{
			remove=true;
		}
		
	}
	public void draw(Graphics2D g)
	{
		setMapPosition();
		super.draw(g);
	}
}
