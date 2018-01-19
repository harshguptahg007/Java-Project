package Main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.JPanel;
import GameState.GameStateManager;

//this class contains the game loop which calls the update() and draw() method with the help of gamestatemanager class.
public class GamePanel extends JPanel implements Runnable, KeyListener
{
	//dimensions
	public static final int WIDTH = 320, HEIGHT = 240, SCALE = 2; 
	//gives the size of the game screen which is 640 by 480 as multiplied by the scale variable
	
	//game thread
	private Thread thread;
	private boolean running;//to check whether the game is running or not.
	private int FPS = 60;//frames per second
	private long targetTime = 1000/FPS;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); //size of the screen becomes 640 by 480
		setFocusable(true); //input lene ke liye JFrame se, agar false krde to phle window pe click krna hoga
		requestFocus();//used to make the component get input focus.??????
		
	}
	public void addNotify()//this method gets called whenever a new input is to be taken in the thread
	{
		super.addNotify();
		if (thread==null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();//starts the Thread
		}
	}
	public void init() //this function is used to initialise everything.
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);//object creation
		//TYPE_INT_RGB ---represents an image with 8-bit RGB color components packed into integer pixels.
		g = (Graphics2D) image.getGraphics();//object creation
		running = true;//game has started
		gsm = new GameStateManager();
	}
	public void run() //game loop
	{
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while (running)
		{
			start = System.nanoTime();//starts timer in nano seconds
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed/1000000;//division to convert it into milliseconds
			if (wait<0)
			{
			wait = 5;
			}
			try {
				thread.sleep(wait);//suspends execution for some period
			} catch (InterruptedException e) {
				e.printStackTrace();//prints the error on the console
			}
		}
		
	}
	private void update()
	{
		gsm.update();
	}
	private void draw()
	{
		gsm.draw(g);
	}
	private void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT *SCALE, null);
		g2.dispose();//deletes the created images at the end
	}
	public void keyPressed(KeyEvent key) 
	{
		gsm.keyPressed(key.getKeyCode());//getKeyCode returns a virtual keycode for key pressed
	}
	public void keyReleased(KeyEvent key) 
	{	
		gsm.keyReleased(key.getKeyCode());//getKeyCode returns a virtual keycode for key released
	}
	public void keyTyped(KeyEvent key){}
}
