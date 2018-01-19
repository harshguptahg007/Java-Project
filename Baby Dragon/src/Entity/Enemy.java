package Entity;

import TileMap.TileMap;

public class Enemy extends MapObject
{
	//variable declarations
	protected int health, maxHealth, damage, rank;
	protected boolean dead, bounce, dropsBounce;
	protected boolean blinking;//it blinks when the player collides with enemy
	protected long blinkTimer;//time for which blinking takes place

	public Enemy(TileMap tm)
	{
		super(tm);
	}
	public boolean isDead(){return dead;}
	public void kill(){dead = true;}
	public int getDamage(){return damage;}
	public boolean getBounce(){return bounce;}
	public int getRank(){return rank;}
	public void hit(int damage)
	{
		if(dead|| blinking)
			return;
		health -= damage;
		if (health<0)
		{
			health = 0;
		}
		if(health ==0)
		{
			dead = true;
		}
		blinking = true;
		blinkTimer = System.nanoTime();
	}
	public void update(){}

}
