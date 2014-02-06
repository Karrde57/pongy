import org.newdawn.slick.geom.Rectangle;


public class Raquette extends Rectangle
{
	private static int TAILLE_X = 10;
	private static int TAILLE_Y = 200;
	
	public static int getTAILLE_X() {
		return TAILLE_X;
	}
	public static int getTAILLE_Y() {
		return TAILLE_Y;
	}
	public Raquette(int pos_x, int pos_y)
	{
		super(pos_x, pos_y, TAILLE_X, TAILLE_Y);
	}
	public void monter(float f)
	{
		this.setY(this.getY()-f);
		
	}
	public void descendre(float pixel)
	{
		this.setY(this.getY()+pixel);
	}

}
