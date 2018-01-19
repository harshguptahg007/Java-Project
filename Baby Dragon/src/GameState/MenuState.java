package GameState;

import Audio.AudioPlayer;
import TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

//this class contains the menustate of the game, ai for color change of the options, onclick method for various options chosen
public class MenuState extends GameState 
{
	//variable and object declarations
	private Background bg;
	private AudioPlayer bgMusic;
	private static boolean mute;
	private int currentChoice = 0;
	private String[] options = {"Start","Help","Quit"};
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm) 
	{
		this.gsm = gsm;
		
		try 
		{	
			bg = new Background("/Backgrounds/menubg.gif", 1);
			//here we do not give the path bcoz resource folder is already in the build path
			bg.setVector(-0.1, 0); //set position of the vector
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic",Font.PLAIN,28);
			
			font = new Font("Arial", Font.PLAIN, 12);
			bgMusic = new AudioPlayer("/Music/zeldatheme.mp3");//taking music from resource folder
			bgMusic.loop();//starting music in a  loop so that it may never end until stop condition becomes true
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() 
	{
		bg.update();
	}
	
	public void draw(Graphics2D g) 
	{
		
		// draw background
		bg.draw(g);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Baby Dragon", 80, 70);//position is given
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++)//for writing options on the menuscreen
		{
			if(i == currentChoice) 
			{
				g.setColor(Color.BLACK); //current option color is black
			}
			else 
			{
				g.setColor(Color.RED); //alternate color of options is red
			}
			g.drawString(options[i], 145, 140 + i * 15);
		}
		
	}
	
	
	private void select() 
	{
		if(currentChoice == 0) 
		{
			gsm.setState(GameStateManager.LEVEL1STATE); //start
			bgMusic.stop();
		}
		if(currentChoice == 1) 
		{
			gsm.setState(GameStateManager.HELPSTATE); //help
			bgMusic.stop();
		}
		if(currentChoice == 2) 
		{ //quitting the game
			System.exit(0);
			bgMusic.stop();
		}
	}
	
	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_ENTER)
		{
			select();
		}
		if(k == KeyEvent.VK_UP) 
		{
			currentChoice--;
			if(currentChoice == -1) 
			{//if the cursor goes beyond the start then it should go on quit
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) 
		{
			currentChoice++;
			if(currentChoice == options.length) 
			{
				//if the cursor goes beyond the quit then it should go on start
				currentChoice = 0;
			}
		}
		
		if(k == KeyEvent.VK_M && !mute)
		{
			mute = true;
			bgMusic.stop();
			System.out.println(mute);
		}
		else if(k == KeyEvent.VK_M && mute)
		{
			mute = false;
			bgMusic.loop();
			System.out.println("false");
		}
		
	}
	public void keyReleased(int k) {}
	public static boolean getMute()
	{
		return mute;
	}
	
}