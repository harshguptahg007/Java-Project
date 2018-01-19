package Entity;

import Audio.AudioPlayer;
import TileMap.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;//for reading spritesheets
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject 
{
	
	//player stuff
	private static int fireRegen = 2;
	private static int fireDelay = 100;
	private static int health = 5;
	private static int level;
	private static int maxHealth = 5;
	private static int fire = 2500;
	private static int maxFire = 2500;
	private static int lives = 3;
	private static int maxLives = 3;
	private boolean dead;
	private boolean blinking;//it blinks when the player collides with enemy
	private long blinkTimer;//time for which blinking takes place
	private double maxJumpHeight;
	
	// fireball
	private boolean firing;//for keyboard input
	private int fireCost;
	private int fireBallDamage;
	private ArrayList<FireBall> fireBalls;
	
	// scratch
	private boolean scratching;//for keyboard input
	private int scratchDamage;
	private int scratchRange;//how far it can reach
	private static boolean mute;
	
	// gliding
	private boolean gliding;
	
	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2, 8, 1, 2, 4, 2, 5};//to take no of images of dragon from resources
	
	// animation actions-states of dragon
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	
	private HashMap<String, AudioPlayer> sfx;//for sound effects
	public Player(TileMap tm) 
	{
		
		super(tm);
		
		width = 30;
		height = 30;
		cwidth = 20;//width on screen
		cheight = 20;//height on screen
		
		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true;//initially facing right
		
		fireBallDamage = 5;
		fireCost=200;
		
		fireBalls = new ArrayList<FireBall>();//creating fireball array list
		
		scratchDamage = 8;
		scratchRange = 40;
		
		// load sprites
		try 
		{
			
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playersprites.gif"));
			//to read images from resource folder
			
			sprites = new ArrayList<BufferedImage[]>();
			for(int i = 0; i < 7; i++) //loop till total no of sprites
			{	
				BufferedImage[] bi =new BufferedImage[numFrames[i]];//taking total image in each line in playersprites
				
				for(int j = 0; j < numFrames[i]; j++) 
				{
					//taking image according to scratching or not bcoz scratching image takes twice width than normal image
					if(i != SCRATCHING) 
					{
						bi[j] = spritesheet.getSubimage(j * width,i * height,width,	height);
					}
					else {
						bi[j] = spritesheet.getSubimage(j * width * 2,i * height,width * 2,	height);
					}
					
				}
				
				sprites.add(bi);//adding the selected image in sprites
				
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;//initially idle state
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		sfx = new HashMap<String,AudioPlayer>();//sound effects
		//loading sound effects for various action
		sfx.put("jump",new AudioPlayer("/SFX/jump.mp3"));
		sfx.put("scratch",new AudioPlayer("/SFX/scratch.mp3"));
		sfx.put("fire",new AudioPlayer("/SFX/fire.mp3"));
		sfx.put("explosion",new AudioPlayer("/SFX/explosion.mp3"));
	}
	
	public boolean getFalling()
	{
		return falling;
	}
	public int getHealth()
	{
		return health; 
	}
	public int getLives()
	{
		return lives; 
	}
	public int getMaxLives()
	{
		return maxLives;
	}
	public int getMaxHealth()
	{
		return maxHealth; 
	}
	public int getFire()
	{ 
		return fire; 
	}
	public int getMaxFire()
	{
		return maxFire; 
	}
	public void setFire(int fire)
	{
		this.fire = fire;
	}
	public boolean isDead()
	{
		return dead;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	public int getLevel() 
	{
		return level;
	}
	public void setMaxFire(int maxFire)
	{
		this.maxFire = maxFire;
		fire = maxFire;
	}
	public void setLives(int lives)
	{
		this.lives = lives;
	}
	public void increaseHealth(int health)
	{
		this.health += health;
	}
	public void setFireDelay(int fireDelay)
	{
		this.fireDelay = fireDelay;
	}
	public void setFireRegen(int fireRegen)
	{
		this.fireRegen = fireRegen;
	}
	public int getFireDelay() 
	{
		return fireDelay;
	}
	public void kill()
	{
		dead = true;//player becomes dead
	}

	public void setFiring()
	{
		firing = true;
	}
	public void setScratching()
	{
		scratching = true;
	}
	public void setGliding(boolean b)
	{
		gliding = b;
	}
	public void setMute(boolean mute)
	{
		this.mute = mute;
	}
	public boolean getMute()
	{
		return mute;
	}
	
	public void checkAttack(ArrayList<Enemy> enemies) 
	{
		// loop through enemies
		for(int i = 0; i < enemies.size(); i++) 
		{
			Enemy e = enemies.get(i);

			// scratch attack
			if(scratching) 
			{
				
				if(facingRight) 
				{
					if(e.getx() > x &&e.getx() < x + scratchRange &&e.gety() > y - height / 2 && e.gety() < y + height / 2)
					//if the enemy is within the range of scratch attack and within the upperlimit and lowerlimit of scratch attack
					{
						e.hit(scratchDamage);
					}
				}
				else 
				{
					if(e.getx() < x &&e.getx() > x - scratchRange &&e.gety() > y - height / 2 && e.gety() < y + height / 2)
					//if the enemy is within the range of scratch attack and within the upperlimit and lowerlimit of scratch attack
					{
						e.hit(scratchDamage);
					}
				}
			}
			
			// fireballs
			for(int j = 0; j < fireBalls.size(); j++) //loop from zero to size of the array of the fireball arraylist
			{
				if(fireBalls.get(j).intersects(e)) //e is the location of the enemy
				{
					e.hit(fireBallDamage);
					fireBalls.get(j).setHit();//get() method is the in-built method in Arraylist class  which gives the position of the
					//fireball and setHit() method resets the fireballs
					break;
				}
			}
			
			// check enemy collision with player
			if(intersects(e)) 
			{
				if(e.getBounce()&&falling&&dy>0)//if player jumps on the enemy
				{
					e.hit(5);
					bounce();//if player jumps on the enemy then the player will bounce back
				}
				else
				{
					hit(e.getDamage());//how much damage has been done to the player from enemy by collision
				}
			}
		}
	}

	public void checkObjects(ArrayList<Object> objects)
	{
	// loop through objects
	for(int i = 0; i < objects.size(); i++) 
	{
		Object o = objects.get(i);
		
		// check object collision
		if(intersects(o)) {
				if(o.getBounce()&&falling&&dy>0&& y < o.gety()-3)
				{
					bounce();
				}
				else if(!o.getBounce())
				{
					o.kill();
				}
		}
		
	}
	
}
	
	public void hit(int damage) 
	{
		if(blinking)
			return;
		else
		{
		health -= damage;
		if(facingRight)
			dx=-3;//if damage is done to the player by collision with enemy then the player will fall back by 3 pixels towards left if facing right
		if(!facingRight)
			dx=+3;//if damage is done to the player by collision with enemy then the player will fall back by 3 pixels towards right if not facing right
		if(health < 0)//if the enemy does damage to the player
			health = 0;
		if(health == 0)
			dead = true;//if player has died
		blinking = true;
		blinkTimer = System.nanoTime();
		}
	}

	public void bounce()
	{
		setJumping(true);
		numJumps = 1;
	}

	public void loseLife()//if player dies then this function is called
	{
		lives--;
		health = 5;
	}
	private void getNextPosition()//next position of player
	{
		// movement
		if(left) //for movement in left direction the speed becomes negative
		{
			dx -= moveSpeed;
			if(dx < -maxSpeed) 
			{
				dx = -maxSpeed;
			}
		}
		else if(right)//for movement in left direction the speed becomes positive
		{
			dx += moveSpeed;
			if(dx > maxSpeed) 
			{
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) 
			{
				dx -= stopSpeed;
				if(dx < 0)
				{
					dx = 0;
				}
			}
			else if(dx < 0) 
			{
				dx += stopSpeed;
				if(dx > 0) 
				{
					dx = 0;
				}
			}
		}
		
		// cannot move while attacking, except in air
		if((currentAction == SCRATCHING || currentAction == FIREBALL) && !(jumping || falling))
		{
			dx = 0;
		}
		
		// jumping
		if(jumping && numJumps <2&& dy >=0) 
		{
			sfx.get("jump").play();
			dy = jumpStart;
			numJumps++;
			falling = true;
		}

		// falling
		if(falling)
		{
			if(dy > 0 && gliding) //if player is in air and gliding
				dy += fallSpeed * 0.1;//slowly falls to the ground
			else //if player is in air and not gliding
				dy += fallSpeed;
			
			if(dy > 0) //falling will start
				jumping = false;
			if(dy < 0 && !jumping) 
				dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) 
				dy = maxFallSpeed;
			
		}
		
	}
	
	public void update() //gets updated everytime whenever the game loop updates
	{
		if(health!= 0)
		{
			dead = false;
		}
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check attack has stopped
		if(currentAction == SCRATCHING) 
		{//to stop the dragon from scratching all the time
			if(animation.hasPlayedOnce())
				scratching = false;
		}
		if(currentAction == FIREBALL) 
		{//to stop the dragon from throwing fire all the time
			if(animation.hasPlayedOnce())
				firing = false;
		}
		
		// fireball attack
		fire += fireRegen;
		if(fire > maxFire)
			fire = maxFire;//fir should not exceed max fire
		if(firing && currentAction != FIREBALL) 
		{
			if(fire > fireCost) 
			{
				sfx.get("fire").play();
				
				fire -= fireCost;
				FireBall fb = new FireBall(tileMap, facingRight);
				fb.setPosition(x, y);
				fireBalls.add(fb);
			}
		}
		
		// update fireballs
		for(int i = 0; i < fireBalls.size(); i++) 
		{
			fireBalls.get(i).update();
			if(fireBalls.get(i).shouldRemove()) 
			{
				fireBalls.remove(i);
				i--;
			}
		}
		
		// check done blinking
		if(blinking)
		{
			long elapsed =(System.nanoTime() - blinkTimer) / 1000000;
			if(elapsed > 1000) 
			{
				blinking = false;
			}
		}
		
		// set animation effects for every action
		if(scratching) 
		{
			if(currentAction != SCRATCHING) 
			{
				sfx.get("scratch").play();
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(50);
				width = 60;
			}
		}
		else if(firing) 
		{
			if(currentAction != FIREBALL) 
			{
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(fireDelay);
				width = 30;
			}
		}
		else if(dy > 0) 
		{
			if(gliding) 
			{
				if(currentAction != GLIDING) 
				{
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 30;
				}
			}
			else if(currentAction != FALLING) 
			{
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else if(dy < 0) 
		{
			if(currentAction != JUMPING) 
			{
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if(left || right) 
		{
			if(currentAction != WALKING) 
			{
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(40);
				width = 30;
			}
		}
		else 
		{
			if(currentAction != IDLE) //for standing
			{
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 30;
			}
		}
		
		animation.update();
		
		// setting direction
		if(currentAction != SCRATCHING && currentAction != FIREBALL) 
		{
			if(right) 
				facingRight = true;
			if(left) 
				facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) 
	{
		
		setMapPosition();
		
		// draw fireballs
		for(int i = 0; i < fireBalls.size(); i++) 
		{
			fireBalls.get(i).draw(g);
		}
		
		// draw player
		if(blinking)
		{
			long elapsed =(System.nanoTime() - blinkTimer) / 1000000;
			if(elapsed / 100 % 2 == 0)//this will give the effect of blinking every 100 millisecond 
			{
				return;
			}
		}
		super.draw(g);
		
	}
}
