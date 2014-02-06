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
	private Raquette _raquette1;
	private Raquette _raquette2;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		_raquette1 = new Raquette(0,300);
		_raquette2 = new Raquette(100,300);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.fill(_raquette1);
		g.fill(_raquette2);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		float fdelta = delta;
		Input input = container.getInput();
		if(input.isKeyDown(Input.KEY_Z))
		{
			_raquette1.monter(fdelta/2);
		}
		else if(input.isKeyDown(Input.KEY_S))
		{
			_raquette1.descendre(fdelta/2);
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			_raquette2.monter(fdelta/2);
		}
		else if(input.isKeyDown(Input.KEY_DOWN))
		{
			_raquette2.descendre(fdelta/2);
		}
		
	}

	public int getID()
	{
		return _ID;
	}

	

}
