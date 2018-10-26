import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import outils.*;

public class Nourriture extends Thread {
	private Map map;
	private Vecteur2D position;
	private long timeBeforeSpoiled;
	public static float LARGEUR = 5;
	private Circle representation;
	public Vecteur2D GetPosition() {
		return position;
	}
	
	public Nourriture(Vecteur2D position, Map map, long timeBeforeSpoiled) {
		this.position = position;
		this.map = map;
		this.timeBeforeSpoiled = timeBeforeSpoiled;
		representation= new Circle();
		representation.setCenterX((double)position.x);
		representation.setCenterY((double) position.y);
		representation.setRadius(LARGEUR);
		representation.setFill(Color.GOLDENROD);
		representation.setStrokeWidth(1);  
	}
	
	public void Consume() {
		map.NourritureNotAvailable(this, false);
		((Pane) representation.getParent()).getChildren().remove(representation);
	}

	@Override
	public void run() {
		try {
			sleep(timeBeforeSpoiled);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			map.NourritureNotAvailable(this, true);
			representation.setFill(Color.SADDLEBROWN);
			//((Pane) representation.getParent()).getChildren().remove(representation);
		}
	}
	
	public Circle getRepresentation() {
		return this.representation;
	}
}
