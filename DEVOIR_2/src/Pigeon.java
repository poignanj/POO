import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import outils.*;

public class Pigeon extends Thread {
	private Map map;
	private Vecteur2D position;
	private Comportement comportementActuel;
	public static float LARGEUR = 5;
	private Circle representation;
	
	public Vecteur2D GetPosition() {
		return position;
	}
	
	public void SetPosition(Vecteur2D v) {
		this.position = v;
	}
	
	
	public Pigeon(Vecteur2D position, Map map) {
		this.position = position;
		this.map = map;
		comportementActuel = new Idle(this, map);
		representation= new Circle();
		representation.setCenterX((double)position.x);
		representation.setCenterY((double) position.y);
		representation.setRadius(LARGEUR);
		representation.setFill(Color.DIMGRAY);
		representation.setStrokeWidth(1);  
	}

	@Override
	public void run() {
		comportementActuel.ExecuteComportement();
	}
	
	public void NewDanger(Danger newDanger) {
		comportementActuel = new Fuite(this, newDanger, map);
	}

	public void NoMoreDanger() {
		if(comportementActuel instanceof Fuite)
		{
			RedifineComportement(new Idle(this, map));
		}
	}
	
	public void NewNourriture(Nourriture newNourriture) {
		comportementActuel.NewNourriture(newNourriture);
	}
	
	public void NourritureNotAvailable(Nourriture notAvailable) {
		if(comportementActuel instanceof AllerManger)
		{
			((AllerManger)comportementActuel).NourritureNotAvailable(notAvailable);
		}
	}
	
	public void RedifineComportement(Comportement newComportement) {
		comportementActuel = newComportement;
	}
	
	public Circle getRepresentation() {
		return this.representation;
	}
}
