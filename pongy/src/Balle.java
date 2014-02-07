import org.newdawn.slick.geom.Circle;


public class Balle extends Circle {

	private int _vitessex;
	private int _vitessey;
	public int get_vitessex() {
		return _vitessex;
	}
	public void set_vitessex(int _vitessex) {
		this._vitessex = _vitessex;
	}
	public int get_vitessey() {
		return _vitessey;
	}
	public void set_vitessey(int _vitessey) {
		this._vitessey = _vitessey;
	}
	public Balle(float centerPointX, float centerPointY, float radius) {
		super(centerPointX, centerPointY, radius);
		// TODO Auto-generated constructor stub
	}
	public void deplacer(float x, float y)
	{
		this.setCenterX(x);
		this.setCenterY(y);
	}

}
