import java.awt.RenderingHints.Key;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameWindow extends BasicGameState
{
	private static int _ID = 2;
	private Raquette raquette1;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		raquette1 = new Raquette();
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.fill(raquette1);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_Z))
		{
			raquette1.monter();
		}
		else if(input.isKeyDown(Input.KEY_S))
		{
			raquette1.descendre();
		}
		
	}

	public int getID()
	{
		return _ID;
	}

	

}
