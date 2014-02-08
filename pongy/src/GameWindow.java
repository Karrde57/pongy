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
	private static HashMap<String, Integer> _options = OptionsReaderSetter.get();
	private static int _ID = 1;
	private Raquette _raquette1;
	private Raquette _raquette2;
	private Balle _balle;
	private int _score1=0;
	private int _score2=0;
	private static int _TAILLEFRAMEX = _options.get("window_size_x");
	private static int _TAILLEFRAMEY = _options.get("window_size_y");
	private static int _TAILLERAQUETTEX = Raquette.getTAILLE_X();
	private static int _TAILLERAQUETTEY = Raquette.getTAILLE_Y();;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		_raquette1 = new Raquette(0,_TAILLEFRAMEY/2-_TAILLERAQUETTEY/2);
		_raquette2 = new Raquette(_TAILLEFRAMEX-_TAILLERAQUETTEX ,_TAILLEFRAMEY/2-_TAILLERAQUETTEY/2);
		_balle = new Balle(_TAILLEFRAMEX/2, _TAILLEFRAMEY/2,10);
		_balle.set_vitessex(10);
		_balle.set_vitessey(1);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawLine(_TAILLEFRAMEX/2, 0, _TAILLEFRAMEX/2, _TAILLEFRAMEY);
		g.fill(_raquette1);
		g.fill(_raquette2);
		g.fill(_balle);
		g.drawString(_score1+"", _options.get("window_size_x") / 4, 100);
		g.drawString(_score2+"", _options.get("window_size_x") / 4 * 3, 100);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		float fdelta = delta;
		Input input = container.getInput();
		if(input.isKeyDown(_options.get("up1")))
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
		
		//raquette1
		if(_raquette1.getMaxY() >= _TAILLEFRAMEY)
		{
			_raquette1.setY( _TAILLEFRAMEY- _raquette1.getTAILLE_Y());
		}
		if(_raquette1.getMaxY() <= _raquette1.getTAILLE_Y())
		{
			_raquette1.setY(0);
		}
		//raquette2
		if(_raquette2.getMaxY() >= _TAILLEFRAMEY)
		{
			_raquette2.setY( _TAILLEFRAMEY- _raquette2.getTAILLE_Y());
		}
		if(_raquette2.getMaxY() <= _raquette2.getTAILLE_Y())
		{
			_raquette2.setY(0);
		}
		
		//balle bord haut bas
		if(_balle.getMinY() <= 0 || _balle.getMaxY() >= _TAILLEFRAMEY)
		{
			_balle.set_vitessey(_balle.get_vitessey() * (-1));
		}
		
		//balle bord gauche et droit
		if(_balle.getMinX() <= 0 )
		{
			_balle = new Balle(_TAILLEFRAMEX/2, _TAILLEFRAMEY/2,10);
			_balle.set_vitessex(-2);
			_balle.set_vitessey(1);
			_score2++;
		}
		else if(_balle.getMaxX() >= _TAILLEFRAMEX)
		{
			
			_balle = new Balle(_TAILLEFRAMEX/2, _TAILLEFRAMEY/2,10);
			_balle.set_vitessex(-2);
			_balle.set_vitessey(1);
			_score1++;
		}
		
		//balle raquette
		if(testcolision(_balle,_raquette1, _raquette2))
		{
			_balle.set_vitessex(_balle.get_vitessex() * (-1));
		}
		
	}

	public int getID()
	{
		return _ID;
	}
	private boolean testcolision(Balle balle, Raquette raq1, Raquette raq2)
	{
		if(balle.getMinY() <= raq1.getMaxY() && balle.getMaxY() >= raq1.getMinY())
		{
			if(balle.getMinX() <= raq1.getMaxX())
					return true;
		}
		if(balle.getMinY() <= raq2.getMaxY() && balle.getMaxY() >= raq2.getMinY())
		{
			if(balle.getMaxX() >= raq2.getMinX())
					return true;
		}
		return false;
	}
	

}
