import outils.Vecteur2D;

public abstract class Comportement {
	protected Pigeon pigeon;
	protected Map map;
	protected Vecteur2D direction;
	protected float vitesse;
	
	public abstract void ExecuteComportement();
	public abstract void NewNourriture(Nourriture newNour);
}
