import java.util.ArrayList;
import java.util.Random;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import outils.Vecteur2D;


public class Map extends Application {
	private ArrayList<Pigeon> pigeons;
	private ArrayList<Nourriture> nourritures;
	private ArrayList<Nourriture> spoiledNourritures; //Pour pouvoir les afficher
	public static float TAILLEX = 600;
	public static float TAILLEY = 600;
	private Random rand = new Random();
	public static int NBMAXPIGEON = 12;
	public  static long SPOILTIME = 5000;
	private Danger danger;
	
	public ArrayList<Nourriture> GetNourritures() {
		return nourritures;
	}
	
	public ArrayList<Pigeon> GetPigeons() {
		return pigeons;
	}

	public static void main(String[] args) {
		 launch(args);
	}
	
	public boolean outOfBounds(Vecteur2D v) {
		return ((v.x < (float) 0) || (v.x > Map.TAILLEX - 20) || (v.y < (float) 0) || (v.y > Map.TAILLEY - 20));
	}
	
	public void NourritureNotAvailable(Nourriture notAvailable, boolean spoiled) {
		nourritures.remove(notAvailable);
		
		if(spoiled)
			spoiledNourritures.add(notAvailable);
		pigeons.forEach(p -> p.NourritureNotAvailable(notAvailable));
	}
	public Map getMap() {
		return this;
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Pane root = new Pane(); 
	    Scene scene = new Scene(root, TAILLEX, TAILLEY);
		pigeons = new ArrayList<Pigeon>();
		nourritures = new ArrayList<Nourriture>();
		spoiledNourritures = new ArrayList<Nourriture>();
		danger = new Danger(getMap());
		danger.start();
		root.getChildren().add(danger.getRepresentation()); 
	/*	float x1 = (float) (rand.nextFloat()* scene.getWidth());
		float y1 = (float) (rand.nextFloat()* scene.getHeight());
		Nourriture n = new Nourriture(new Vecteur2D(x1,11), getMap(), 50000);
    	n.start();
    	nourritures.add(n);
    	root.getChildren().add(n.getRepresentation());
		*/
		int nbPigeon = rand.nextInt(NBMAXPIGEON);
		if (nbPigeon==0)nbPigeon=1;
		for (int i = 0;i<nbPigeon;i++) {
			float x = (float) (rand.nextFloat()* scene.getWidth()- 20);
			float y = (float) (rand.nextFloat()* scene.getHeight() - 20);
			Pigeon p = new Pigeon(new Vecteur2D(x, y), this);
			p.start();
			pigeons.add(p);
			root.getChildren().add(p.getRepresentation());
			
		}

		 
	      EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
		         @Override 
		         public void handle(MouseEvent e) { 
		            
		            if(e.getButton() == MouseButton.PRIMARY) {
		            
		            	Nourriture n = new Nourriture(new Vecteur2D((float)e.getSceneX(),(float)e.getSceneY()), getMap(), 5000);
		            	n.start();
		            	nourritures.add(n);
		            	root.getChildren().add(n.getRepresentation());
		            	
		            	////////////////
		            	////////////////
		            	//Notification des pigeons
		            	////////////////
		            	////////////////
		            	for (int i= 0;i< pigeons.size();i++) {
		            		pigeons.get(i).NewNourriture(n);
		            	}
		            
		            } else if (e.getButton() == MouseButton.SECONDARY) {
		            	//spoiledNourritures.get(0);
		            	if (spoiledNourritures.size()!=0) {
		            	root.getChildren().remove(spoiledNourritures.get(0).getRepresentation());
		            	spoiledNourritures.remove(0);
		            	}
		            }
		            
		         } 
		      };  
	      scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);  
	      //Setting the fill color to the scene 
	      scene.setFill(Color.LAVENDER);  
	      
	      //Setting title to the Stage 
	      primaryStage.setTitle("Pigeon");       
	         
	      //Adding scene to the stage 
	      primaryStage.setScene(scene); 
	         
	      //Displaying the contents of the stage 
	      primaryStage.show(); 

		
	}
}
