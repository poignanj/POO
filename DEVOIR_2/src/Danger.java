import outils.Vecteur2D;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Danger extends Thread {
	private Map map;
	private Vecteur2D position;
	private long timeDanger = 3000;
	private long timeSleep = 500;
	private boolean dangerOn = false;
	private float probability;
	private float probabilityMin = 0.1f;
	private float probabilityMax = 0.7f;
	private Random rand;
	
	private Circle representation;
	
	
	public Vecteur2D GetPosition() {
		return position;
	}
	
	public Danger(Map map) {
		this.map = map;
 
		rand = new Random();
		representation= new Circle();
		representation.setCenterX(0);
		representation.setCenterY(0);
		this.position= new Vecteur2D(0, 0);
		representation.setRadius(5f);
		representation.setFill(Color.RED);
		representation.setStrokeWidth(1);  
		representation.setVisible(false);
	}
	
	private void DangerOn() {
		dangerOn = true;
		double x = rand.nextDouble()* representation.getScene().getWidth();
		double y = rand.nextDouble()* representation.getScene().getHeight();
		representation.setCenterX(x);
		representation.setCenterY(y);
		
		this.position.x= (float) x;
		this.position.y=(float) y;
		representation.setVisible(true);
		
		map.GetPigeons().forEach(p -> p.NewDanger(this));
	}
	
	private void DangerOff() {
		dangerOn = false;
		map.GetPigeons().forEach(p -> p.NoMoreDanger());
		RedifineProbability();
		representation.setVisible(false);
	}
	
	private void RedifineProbability() {
		//probability = rand.nextFloat()* (probabilityMax - probabilityMin) + probabilityMin;
		probability = rand.nextFloat();
		if (probability>probabilityMax) {
			probability= probabilityMax;
			
		} else if (probability<probabilityMin) {
			probability=probabilityMin;
		}
	}

	@Override
	public void run() {
		RedifineProbability();
		while(true) {
			if(dangerOn) {
				try {
					sleep(timeDanger);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					DangerOff();
				}
			}
			else
			{
				try {
					sleep(timeSleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					System.out.println("endsleep"+ probability);
					if(rand.nextFloat() < probability)
					{
						DangerOn();
					}
				}
				
			}
		}
	}
	public Circle getRepresentation() {
		return this.representation;
	}
}
