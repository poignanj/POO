package outils;

public class Vecteur2D {
	public float x, y;
	
	public Vecteur2D(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float Norme() {
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vecteur2D Soustraction(Vecteur2D v) {
		return new Vecteur2D(this.x - v.x, this.y - v.y);
	}
	
	public Vecteur2D Addition(Vecteur2D v) {
		return new Vecteur2D(this.x + v.x, this.y + v.y);
	}
	public Vecteur2D Multi(float m) {
		return new Vecteur2D(this.x * m, this.y * m);
	}
	
	public float Distance(Vecteur2D v) {
		return Soustraction(v).Norme();
	}
}
