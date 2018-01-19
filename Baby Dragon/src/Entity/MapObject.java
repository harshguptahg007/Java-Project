package Entity;

import Main.GamePanel;
import TileMap.TileMap;
import TileMap.Tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class MapObject//superclass for players enemies projectiles
{
	//since it is an abstract class every variable and method should be protected so that it could be inherited

	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// position and vector
	protected double x;
	protected double y;
	protected double dx;//dx and dy are direction object is going
	protected double dy;//
	
	// dimensions
	protected int width;//for reading spritesheets
	protected int height;
	
	// collision box
	protected int cwidth;//used for determining collision with tiles or enemies
	protected int cheight;
	int numJumps;
	
	// collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;//temporary position
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	// movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	// movement attributes
	protected double moveSpeed;//how fast does the object accelerates
	protected double maxSpeed;//how fast object can go
	protected double stopSpeed;//deceleration speed
	protected double fallSpeed;//like gravity
	protected double maxFallSpeed;
	protected double jumpStart;//how high the object can jump
	protected double stopJumpSpeed;
	
	// constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize(); 
	}
	
	public boolean intersects(MapObject o)
	{ //for collision detection with other maap object....we are using rectangle collision detection method
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle((int)x - cwidth,(int)y - cheight,cwidth,cheight);
	}
	
	public void calculateCorners(double x, double y)
	{
		int leftTile = (int)(x - cwidth / 2) / tileSize;//to get the left column to the just left of the player
		int rightTile = (int)(x + cwidth / 2 - 1) / tileSize;//to get the right column to the just right of the player
		int topTile = (int)(y - cheight / 2) / tileSize;//to get top tile
		int bottomTile = (int)(y + cheight / 2 - 1) / tileSize;//-1 bcoz we dont wanna go downwards into the next tile
		
		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);
		
		topLeft = tl == Tile.BLOCKED;//returns boolean value
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;
		
	}
	
	public void checkTileMapCollision()
	{ //checks whether we ran into blocked tile or a normal tile
		currCol = (int)x / tileSize;
		currRow = (int)y / tileSize;
		
		xdest = x + dx;//destination positions
		ydest = y + dy;
		
		xtemp = x;//to keep original x and y
		ytemp = y;
		
		calculateCorners(x, ydest);//for detecting tile collision
		if(dy < 0)
		{//here we are going upwards
			if(topLeft || topRight)
			{//we have hit something upwards
				dy = 0;//stop moving in the upward direction
				ytemp = currRow * tileSize + cheight / 2;//sets to the just next tile tile we have hit
			}
			else
			{
				ytemp += dy;
			}
		}
		if(dy > 0)
		{//we are going down
			if(bottomLeft || bottomRight)
			{//we have hit something downwards
				dy = 0;
				falling = false;
				numJumps =0;
				ytemp = (currRow + 1) * tileSize - cheight / 2;//sets to the just next tile tile we have hit
			}
			else
			{
				ytemp += dy;
			}
		}
		
		calculateCorners(xdest, y);
		if(dx < 0)
		{//moving left
			if(topLeft || bottomLeft)
			{//we have hit blocked tile
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;//sets to the just next tile tile we have hit
			}
			else
			{
				xtemp += dx;
			}
		}
		if(dx > 0)
		{//moving right
			if(topRight || bottomRight)
			{
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;//sets position just to the left of that tile
			}
			else
			{
				xtemp += dx;
			}
		}
		
		if(!falling)
		{
			calculateCorners(x, ydest + 1);
			if(!bottomLeft && !bottomRight)
			{//we are not standing on ground
				falling = true;
			}
		}
		
		
	}
	
	public int getx() { return (int)x; }
	public int gety() { return (int)y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getCWidth() { return cwidth; }
	public int getCHeight() { return cheight; }
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition()
	{
		xmap = tileMap.getx();//where to actually draw the character
		ymap = tileMap.gety();
	}
	
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }
	public void setJumping(boolean b) { jumping = b; }

	public void draw(Graphics2D g)
	{
		
		if(facingRight) {
			g.drawImage(animation.getImage(),(int)(x + xmap - width / 2),(int)(y + ymap - height / 2),null);
		}
		else {
			g.drawImage(animation.getImage(),(int)(x + xmap - width / 2 + width),
					(int)(y + ymap - height / 2),-width,height,	null);
			
		}
		
	}
}
