import outils.*;

public class Pigeon extends Thread {
	private Map map;
	private Vecteur2D position;
	private Comportement comportementActuel;
	
	public Pigeon(Vecteur2D position, Map map) {
		this.position = position;
		this.map = map;
		comportementActuel = new Idle(this, map);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
	public void NewDanger(Danger newDanger) {
		
	}
	
	public void NewNourriture(Nourriture newNourriture) {
		
	}
	
	public void RedifineComportement(Comportement newComportement) {
		comportementActuel = newComportement;
	}
}
