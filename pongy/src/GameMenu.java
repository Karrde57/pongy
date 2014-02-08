import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;


public class GameMenu extends BasicGameState
{
	public static int _ID = 0;
	private TrueTypeFont _font;
	private int _posycursor,  _posmenu = 0;
	private static int _POSXCURSOR, _POSXMENU;
	private Rectangle _cursor;
	private static String[] _MENUNAME = new String[]{"Play!", "Play with yout friend!", "Options", "Credits", "Exit" };
	static private HashMap<String, Integer> _options = OptionsReaderSetter.get();
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException
	{
		// SET FONT
		try 
		{
			InputStream inputStream = ResourceLoader.getResourceAsStream(new File(GameManager.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "\\config\\COMICATE.ttf");
	        Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	        awtFont = awtFont.deriveFont(24f); // set font size
	        _font = new TrueTypeFont(awtFont, false);
	        

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}  
		// END SET FONT
		_POSXCURSOR = _options.get("window_size_x") / 4 -20;
		_posycursor=  _options.get("window_size_y") / 2 - 40*_MENUNAME.length / 2 +8;
		_POSXMENU = _options.get("window_size_x") / 4;
		_cursor = new Rectangle(_POSXCURSOR, _posycursor, 10, 10);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
		for(int i=0; i<_MENUNAME.length; i++)
		{
			_font.drawString(_POSXMENU, _options.get("window_size_y") / 2 + 40*i - 40*_MENUNAME.length / 2 , _MENUNAME[i]);
		}
		g.draw(_cursor);

		
	}

	@Override
	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = container.getInput();
		if(input.isKeyPressed(_options.get("up1"))&&_posmenu >0)
		{
			_cursor.setY(_cursor.getY()-40);
			_posmenu--;
		
		}
		if(input.isKeyPressed(_options.get("down1"))&&_posmenu <_MENUNAME.length-1)
		{
			_cursor.setY(_cursor.getY()+40);
			_posmenu++;
		}
		if(input.isKeyPressed(Input.KEY_ENTER))
		{
			sbg.enterState(_posmenu+1);
		}
		
		
	}

	@Override
	public int getID()
	{
		return _ID;
	}
	public void keyReleased(int key, char c)
	{

	}
}
