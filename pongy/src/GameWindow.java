import java.awt.RenderingHints.Key;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameWindow extends BasicGameState
{
	private static HashMap<String, String> _options = OptionsReaderSetter.get();
	private static int _ID = 2;
	private Raquette _raquette1;
	private Raquette _raquette2;
	private Balle _balle;
	private static int _TAILLEFRAMEX = Integer.parseInt(_options.get("window_size_x"));
	private static int _TAILLEFRAMEY = Integer.parseInt(_options.get("window_size_y"));
	private static int _TAILLERAQUETTEX = Raquette.getTAILLE_X();
	private static int _TAILLERAQUETTEY = Raquette.getTAILLE_Y();;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		_raquette1 = new Raquette(0,_TAILLEFRAMEY/2-_TAILLERAQUETTEY/2);
		_raquette2 = new Raquette(_TAILLEFRAMEX-_TAILLERAQUETTEX ,_TAILLEFRAMEY/2-_TAILLERAQUETTEY/2);
		_balle = new Balle(_TAILLEFRAMEX/2, _TAILLEFRAMEY/2,10);
		_balle.set_vitessex(-2);
		_balle.set_vitessey(0);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawLine(_TAILLEFRAMEX/2, 0, _TAILLEFRAMEX/2, _TAILLEFRAMEY);
		g.fill(_raquette1);
		g.fill(_raquette2);
		g.fill(_balle);
		System.out.println(_balle.getCenterX());
		System.out.println(_balle.getCenterY());
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		float fdelta = delta;
		Input input = container.getInput();
		if(input.isKeyDown(Integer.parseInt(_options.get("up1"))))
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
		
		
		//physique balle
		_balle.deplacer(_balle.getCenterX()+(_balle.get_vitessex()*delta/10), _balle.getCenterY()+(_balle.get_vitessey()*delta/10));
		
		if(_raquette1.getMaxY() > _TAILLEFRAMEY)
		{
			_raquette1.setY( _TAILLEFRAMEY- _raquette1.getTAILLE_Y());
		}
		if(_raquette1.getMaxY() < _raquette1.getTAILLE_Y())
		{
			_raquette1.setY(0);
		}
		
		
	}

	public int getID()
	{
		return _ID;
	}

	

}
