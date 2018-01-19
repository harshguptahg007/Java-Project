package TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileMap 
{
	// position
	private static double x;
	private static double y;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	private double tween;//smoothly scroll the camera towards the player
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;//in pixels
	private int height;//in pixels
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	// drawing
	//we only draw those tiles which are present on the screen.
	private int rowOffset;//tells us which row to start drawing
	private int colOffset;//tells us which column to start drawing
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize)
	{
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;//basically 10 tiles(240/30+2)
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;//(320/30+2)
		tween = 0.07;
	}
	
	public void loadTiles(String s) 
	{//only loads the image doesn't prints the image on the screen	
		try 
		{

			tileset = ImageIO.read(getClass().getResourceAsStream(s));//getting tile map image
			numTilesAcross = tileset.getWidth() / tileSize;//it results in 21 tile across
			tiles = new Tile[2][numTilesAcross];//creating 2 rows of tiles on the ground
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) 
			{
				subimage = tileset.getSubimage(	col * tileSize,	0,	tileSize,tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);//for 1st row of normal tiles
				subimage = tileset.getSubimage(	col * tileSize,	tileSize,tileSize,	tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);//for  2nd row of blocked tiles 
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) //load map file into memory
	{	
		try 
		{
			
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));//file handling occurs here
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;//width in pixels
			height = numRows * tileSize;//height in pixels
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+";//whitespace used to separate map text
			for(int row = 0; row < numRows; row++) 
			{
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++) 
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public int getTileSize() 
	{
		return tileSize; 
	}
	public static double getx() 
	{
		return x; 
	}
	public static double gety() 
	{
		return y; 
	}
	public int getWidth() 
	{
		return width; 
	}
	public int getHeight()
	{
		return height; 
	}
	
	public int getType(int row, int col) 
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setTween(double d) 
	{
		tween = d; 
	}
	
	public void setPosition(double x, double y) 
	{
		
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	private void fixBounds() 
	{ // so that the image does not goes out of bounds
		if(x < xmin) x = xmin;		
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g) 
	{
		
		for(int row = rowOffset;row < rowOffset + numRowsToDraw;row++) 
		{	
			if(row >= numRows) 
			break;
			
			for(int col = colOffset;col < colOffset + numColsToDraw;col++)
			{
				if(col >= numCols) 
					break;
				
				if(map[row][col] == 0) 
					continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(),	(int)x + col * tileSize,(int)y + row * tileSize,null);
				
			}	
		}	
	}
}
