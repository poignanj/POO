import outils.*;

public class Pigeon extends Thread {
	private Map map;
	private Vecteur2D position;
	private Comportement comportementActuel;
	
	public Vecteur2D GetPosition() {
		return position;
	}
	
	public Pigeon(Vecteur2D position, Map map) {
		this.position = position;
		this.map = map;
		comportementActuel = new Idle(this, map);
	}

	@Override
	public void run() {
		comportementActuel.ExecuteComportement();
	}
	
	public void NewDanger(Danger newDanger) {
		comportementActuel = new Fuite(this, newDanger);
	}
	
	public void NewNourriture(Nourriture newNourriture) {
		comportementActuel.NewNourriture(newNourriture);
	}
	
	public void RedifineComportement(Comportement newComportement) {
		comportementActuel = newComportement;
	}
}
