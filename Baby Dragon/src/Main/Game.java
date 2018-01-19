package Main;
import javax.swing.JFrame;

//this class is the entry point of the game which makes the JFrame to implement swing class components
public class Game
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Baby Dragon");//title of the window
		window.setContentPane(new GamePanel());//loads the game panel class for update(),init(), draw() methods.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exiting the JFrame
		window.setResizable(false);//does not let you to maximize the screen
		window.pack();//to pack the windows decorations around the content
		window.setVisible(true);//to display the contents of the screen
	}
}
