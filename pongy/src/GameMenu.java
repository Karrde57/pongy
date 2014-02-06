import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class GameMenu extends BasicGameState
{
	public static int _ID = 1; 
	public StateBasedGame sbg;
	@Override
	public void init(GameContainer container, StateBasedGame sbg) throws SlickException
	{
		this.sbg = sbg;
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.drawString("1. Jouer", 100, 100);
		g.drawString("2. Jouer multijoueur", 100, 120);
		g.drawString("3. Options", 100, 140);
		g.drawString("4. Quitter", 100, 160);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID()
	{
		return _ID;
	}
	public void keyReleased(int key, char c)
	{
		switch (key)
		{
		case Input.KEY_1:
			System.out.println("lol");
			GameWindow game = new GameWindow();
			this.sbg.enterState(game.getID());
			break;
		case Input.KEY_2:
			// TODO: Implement later
			break;
		case Input.KEY_3:
			// TODO: Implement later
			break;
		case Input.KEY_4:
			
			break;
		default:
			break;
		}
	}
}
