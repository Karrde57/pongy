import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import org.lwjgl.util.Color;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;


public class GameManager extends StateBasedGame
{
	private static HashMap<String, Integer> _options = OptionsReaderSetter.get();
	
	private static String _titre = "Pongy";
	public GameManager()
	{
		
		super(_titre);

	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException
	{
		
		this.addState(new GameMenu());
		this.addState(new GameWindow());
		this.addState(new OptionsWindow());
		
	}
	 public static void main(String[] args) throws SlickException
	{
		 
		 AppGameContainer container = new AppGameContainer(new GameManager());
		 container.setDisplayMode(_options.get("window_size_x"), _options.get("window_size_y"), false); // fenêtre de 1280*768 fullscreen =false !! 
		 container.setVSync(true);
		 container.setMultiSample(64);
		 container.setTargetFrameRate(_options.get("fps"));
		 container.setVerbose(true);
		 container.start();
	}



}
