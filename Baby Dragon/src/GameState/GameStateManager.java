package GameState; 

//this class is used to load various states, levels, updates and draws each level
public class GameStateManager
{
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 5;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int HELPSTATE = 2;
	public static final int LEVEL2STATE = 3;
	public static final int LEVEL3STATE = 4;

	
	public GameStateManager()
	{
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
	
	}
	private void loadState(int state)//loads the state
	{
		if(state ==MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		if(state == LEVEL2STATE)
			gameStates[state] = new Level2State(this);
		if(state == LEVEL3STATE)
			gameStates[state] = new Level3State(this);
		if(state == HELPSTATE)
			gameStates[state] = new HelpState(this);
		
	}
	private void unloadState(int state)//to free some memory
	{
		gameStates[state] = null;
	}
	//to load another state previous state should be unloaded
	public void setState(int state) //change states
	{
		unloadState(currentState);//unloads a state first
		currentState = state;
		loadState(state); //loads the new current state
		
	}
	
	public void update()
	{
		try
		{
		gameStates[currentState].update();//updates the current state for every action
		}
		catch(Exception e)
		{
			e.printStackTrace();//prints the error
		}
	}
	public void draw(java.awt.Graphics2D g)
	{
		try
		{
		gameStates[currentState].draw(g);//draws the current state on the screen
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}
	public void keyPressed(int k)
	{
		gameStates[currentState].keyPressed(k);
	}
	public void keyReleased(int k)
	{
		gameStates[currentState].keyReleased(k);
	}
}
