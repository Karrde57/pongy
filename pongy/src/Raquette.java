import org.newdawn.slick.geom.Rectangle;


public class Raquette extends Rectangle
{
	private static int TAILLE_X = 10;
	private static int TAILLE_Y = 200;
	private static int INIT_POS_X= 0;
	private static int INIT_POS_Y = 300;
	
	public Raquette()
	{
		super(INIT_POS_X, INIT_POS_Y, TAILLE_X, TAILLE_Y);
	}
	public void monter()
	{
		this.setY(this.getY()-1);
		
	}
	public void descendre()
	{
		this.setY(this.getY()+1);
	}

}
