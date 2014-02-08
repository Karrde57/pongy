import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;


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
	private static int _TAILLERAQUETTEY = Raquette.getTAILLE_Y();
	private Audio _oggEffect;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException
	{
		File directoryfile = new File(GameManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		try
		{
			_oggEffect = AudioLoader.getAudio("OGG", ResourceLoader.getResourceAsStream(directoryfile.getParentFile().toString() + "\\music\\pong.ogg"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(testcolisiongauche(_balle,_raquette1))
		{
			_balle.set_vitessex((int) (_balle.get_vitessex() * (-1)));
			_balle.set_vitessey((int) (_balle.get_vitessey() + (_raquette1.getCenterY()-_balle.getCenterY())/30) * (-1));
			_oggEffect.playAsSoundEffect(1.0f, 1.0f, false);
		}
		if(testcolisiondroite(_balle,_raquette2))
		{
			_balle.set_vitessex((int) (_balle.get_vitessex() * (-1)));
			_balle.set_vitessey((int) (_balle.get_vitessey() + (_raquette2.getCenterY()-_balle.getCenterY())/30) * (-1));
			_oggEffect.playAsSoundEffect(1.0f, 1.0f, false);
		}
		
	}

	public int getID()
	{
		return _ID;
	}
	private boolean testcolisiongauche(Balle balle, Raquette raq1)
	{
		if(balle.getMinY() <= raq1.getMaxY() && balle.getMaxY() >= raq1.getMinY())
		{
			if(balle.getMinX() <= raq1.getMaxX())
					return true;
		}

		return false;
	}
	private boolean testcolisiondroite(Balle balle, Raquette raq2)
	{

		if(balle.getMinY() <= raq2.getMaxY() && balle.getMaxY() >= raq2.getMinY())
		{
			if(balle.getMaxX() >= raq2.getMinX())
					return true;
		}
		return false;
	}
	
	

}
