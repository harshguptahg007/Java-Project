package GameState;

import Main.GamePanel;
import TileMap.*;
import Entity.*;
import Entity.Object;
import Entity.Enemies.*;
import Entity.Objects.ExtraHeart;
import Audio.AudioPlayer;
import TileMap.score;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class Level1State extends GameState 
{
	private AudioPlayer bgMusic;
	private AudioPlayer item;
	private TileMap tileMap;
	private Background bg;
	Random rand = new Random();

	protected int score=0;
	private static Player player;
	private boolean deathScreen, gameOver, levelStart, messagePlayed;
	private long deathScreenTimer, levelStartTimerDiff, levelStartTimer = 0,startTimer=0;
	private long deathScreenDelay = 2000;//the dead screen will be there for 2 seconds
	
	private ArrayList<Enemy> enemies; //for list of enemies
	private ArrayList<Explosion> explosions; //for explosions on enemies death
	private ArrayList<Object> objects;
	private ArrayList<Text> texts;
	
	private HUD hud;//for player health and chances left and fire attacks remaining
	
	public Level1State(GameStateManager gsm) 
	{
		this.gsm = gsm;
		init();
		
	}
	
	public void init() //initializes everyhting at the start
	{
		startTimer=System.nanoTime();
		levelStart = true;
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");//loads tileset using the function from tilemap
		tileMap.loadMap("/Maps/level1-1.map");//loads the map from text file present in resources
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.07);

		bg = new Background("/Backgrounds/grassbg1.gif", 0.1);//loads the background image
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		player.setLevel(1);
		
		//health and lives carries over to level
		player.setMute(MenuState.getMute());
		
		populateEnemies();
		
		explosions = new ArrayList<Explosion>();
		objects = new ArrayList<Object>();
		texts = new ArrayList<Text>();
		
		hud = new HUD(player);
		
		bgMusic = new AudioPlayer("/Music/level1-1.mp3");
		item = new AudioPlayer("/SFX/item.mp3");
		if(!player.getMute())
		{
		bgMusic.loop();
		}

		
	}
	
	private void populateEnemies() 
	{
		enemies = new ArrayList<Enemy>();
		
		Slugger s;
		Point[] points = new Point[] {
			new Point(1460, 200),
			new Point(860, 200),
			new Point(1525, 200),
			new Point(1680, 200),
			new Point(1800, 200)
		};//different locations for snail
		for(int i = 0; i < points.length; i++) 
		{
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);//placing snails at different locations
			enemies.add(s);
		}
		Spider spider;
		Point[] spiderpoints = new Point[] {
			new Point(1190, rand.nextInt(75)),
			new Point(1235, rand.nextInt(75)),
			new Point(2530, rand.nextInt(100)),
			new Point(2600, rand.nextInt(100)),
			new Point(2565, rand.nextInt(100))
		};//different locations of spider
		for(int i = 0; i < spiderpoints.length; i++) 
		{
			spider = new Spider(tileMap, spiderpoints[i].x, 00);
			spider.setPosition(spiderpoints[i].x, spiderpoints[i].y);//placing spiders at different locations
			enemies.add(spider);
		}
		Boss1 boss = new Boss1(tileMap);
		//2650, 200 boss
		boss.setPosition(2650, 200);//placing the red wala snail
		enemies.add(boss);
	}
	private void createExtraHeart(int x, int y)
	{
		
		ExtraHeart e = new ExtraHeart(tileMap);
		e.setPosition(x,y);
		objects.add(e);
		
	}

	public void update()
	{
		if(levelStartTimer == 0&& levelStart)
		{
			levelStartTimer = System.nanoTime();
		}
		else
		{
			levelStartTimerDiff = (System.nanoTime() - levelStartTimer)/1000000;//to take time in milliseconds
			if(levelStartTimerDiff > 5000)//if 5 seconds have passed after starting the level then
			{
				levelStartTimerDiff = 0;//set levelStartTimerDifference to zero and levelstart=false so that thi level should not be loaded again
				levelStart = false;
			}
		}
		if(startTimer>10000) {
			score += 10;
			startTimer=System.nanoTime();
			System.out.println(score);
		}

		//change to lvl 2 
		if(player.getx()>tileMap.getWidth()-player.getWidth() && enemies.size() ==0)
		//if the position of player is more than the width of the map-player's width  and all enemies have died then load another level
		{
			gsm.setState(GameStateManager.LEVEL3STATE);
			bgMusic.stop();
		}
		if(player.getx()>tileMap.getWidth()-player.getWidth() && enemies.size() != 0 && !messagePlayed)
		//if player has reached end of map and not killed all enemies then the message will pop up
		{
			JOptionPane.showMessageDialog(null, "You must kill all of the enemies before advancing to the next level!");
			messagePlayed = true;
		}
		
		//if player falls off map
		if(player.gety()>= tileMap.getHeight()-player.getHeight())
		{
			player.kill();//player dies here
		}
		//top of map
		if(player.gety() < 0)
		{
			player.setPosition(player.getx(), 0);//sets the y coordinate of player to 0 with the same x coordinate
			//basically not allowing the player to get beyond the height of map
		}
		if(player.isDead())
		{
			player.setPosition(100, 100);//initial position of the player
			deathScreen = true;
			deathScreenTimer = System.nanoTime();
			player.loseLife();//no. of chances get decreased here
			player.setHealth(5);//new set of health
		}
		if (player.getLives() == 0)//if no of chances are zero then
		{
			gameOver = true;//game over screen is displayed
		}
		
		// update player
		player.update();
		//camera will focus on the dragon
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),GamePanel.HEIGHT / 2 - player.gety());
		
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies
		player.checkAttack(enemies);
		player.checkObjects(objects);
		
		// update all enemies
		for(int i = 0; i < enemies.size(); i++) 
		{
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) 
			{
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));//where ever enemy dies exposion images are seen
				if(rand.nextInt(10)<2)//condition can be anything bcoz hearts have to be generated randomly
				{
				createExtraHeart(e.getx(),e.gety());//creating extra lives randomly
				}
			}
		}
		//update all objects
		for(int i = 0; i<objects.size(); i++)
		{
			Object o = objects.get(i);
			o.update();
			if(o.isDead())
			{
				objects.remove(i);
				i--;
				if(player.getHealth()<player.getMaxHealth())
				{
				
				player.increaseHealth(1);//increasing the life of the player
				texts.add(new Text(GamePanel.WIDTH/2,player.gety(),3000, "+1 Health!"));//printing +1 Health on the Screen for 3 seconds
				item.play();
				}
				else
				{
					texts.add(new Text(GamePanel.WIDTH/2,player.gety(),3000, "Already Max Health!"));
				}
			}
		}
		//update texts
		for(int i = 0; i<texts.size(); i++)
		{
			Text t = texts.get(i);
			if(texts.get(i).update())
			{
				texts.remove(i);
				i--;//claening the array list
			}
		}
		// update explosions
		for(int i = 0; i < explosions.size(); i++) 
		{
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) 
			{
				explosions.remove(i);
				i--;//claening the array list
			}
		}
		
	}
	public static Player getPlayer()
	{
		return player;
	}
	
	public void draw(Graphics2D g) 
	{	
		// draw bg
		bg.draw(g);

		// draw tilemap
		tileMap.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) 
		{
			enemies.get(i).draw(g);
		}
		//draw objects
		for(int i = 0; i<objects.size(); i++)
		{
			objects.get(i).draw(g);
		}
		// draw explosions
		for(int i = 0; i < explosions.size(); i++) 
		{
			explosions.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
		
		// draw hud
		hud.draw(g);
		//draw level text
		g.setFont((new Font ("Comic Sans MS", Font.PLAIN, 18)));
		String s = "Level 1: Grass Fields";
		int length = (int) g.getFontMetrics().getStringBounds(s,g).getWidth();
		int alpha = (int) (255 * Math.sin(3.14 * levelStartTimerDiff / 5000));
		if(alpha > 255)
			alpha = 255;
		g.setColor(new Color(255, 255, 255, alpha));
		int ypos = (int)levelStartTimerDiff/5;
		if (ypos <=GamePanel.HEIGHT/2) 
			g.drawString(s, GamePanel.WIDTH/2 - length/2, ypos);
		else
		{
			g.drawString(s, GamePanel.WIDTH/2 - length/2, GamePanel.HEIGHT/2);
		}
		//draw texts
		for (int i = 0; i<texts.size();i++)
		{
			texts.get(i).draw(g);
		}
		//draw death screen
		if (deathScreen == true)
		{
				long currentTime = System.nanoTime();
				long elapsed = (currentTime-deathScreenTimer)/1000000;
					if (elapsed >= deathScreenDelay)
					{
						levelStart = true;
						levelStartTimer = 0;
					}
					if(elapsed < deathScreenDelay)
					{
						g.setColor(Color.BLACK);//color is set to black
						g.fillRect(0,0,GamePanel.WIDTH,GamePanel.HEIGHT);//rectangle of complete screen size is made
						Font font = new Font("Arial", Font.PLAIN, 14);//font is selected
						g.setFont(font);
						g.setColor(Color.RED);
							if(!gameOver)
							{
								g.drawString("You Died!", GamePanel.WIDTH/2 - 30, GamePanel.HEIGHT/2);
								player.setHealth(player.getMaxHealth());
							}
							else if(gameOver)
							{
								g.drawString("GAME OVER", GamePanel.WIDTH/2 - 40, GamePanel.HEIGHT/2);
								bgMusic.stop();
							}

						}
						
						else{//after 2 seconds the menu option should come up
							deathScreen = false;
							if(gameOver)
							{
								gsm.setState(GameStateManager.MENUSTATE);	
								gameOver = false;
							}
						}
	
		}
	}

	
	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_LEFT) 
			player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if(k == KeyEvent.VK_A) 
			player.setLeft(true);
		if(k == KeyEvent.VK_D) 
			player.setRight(true);
		if(k == KeyEvent.VK_UP) 
			player.setJumping(true);
		if(k == KeyEvent.VK_DOWN) 
			player.setDown(true);
		if(k == KeyEvent.VK_W) 
			player.setJumping(true);
		if(k == KeyEvent.VK_E)
			player.setGliding(true);
		if(k == KeyEvent.VK_R) 
			player.setScratching();
		if(k == KeyEvent.VK_F) 
			player.setFiring();
		if(k == KeyEvent.VK_M && !player.getMute()) 
		{
			player.setMute(true);bgMusic.stop();
		}
		else if(k == KeyEvent.VK_M && player.getMute()) 
		{
			player.setMute(false);bgMusic.loop();
		}
		
	}
	
	public void keyReleased(int k) 
	{
		if(k == KeyEvent.VK_LEFT) 
			player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) 
			player.setRight(false);
		if(k == KeyEvent.VK_UP) 
			player.setJumping(false);
		if(k == KeyEvent.VK_DOWN) 
			player.setDown(false);
		if(k == KeyEvent.VK_W) 
			player.setJumping(false);
		if(k == KeyEvent.VK_E) 
			player.setGliding(false);
		if(k == KeyEvent.VK_A) 
			player.setLeft(false);
		if(k == KeyEvent.VK_D) 
			player.setRight(false);
	}
	
}
