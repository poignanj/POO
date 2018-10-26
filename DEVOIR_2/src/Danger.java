import outils.Vecteur2D;
import java.util.Random;

public class Danger extends Thread {
	private Map map;
	private Vecteur2D position;
	private long timeDanger = 3000;
	private long timeSleep = 50;
	private boolean dangerOn = false;
	private float probability;
	private float probabilityMin = 0.1f;
	private float probabilityMax = 0.7f;
	private Random rand;
	
	
	public Vecteur2D GetPosition() {
		return position;
	}
	
	public Danger(Map map) {
		this.map = map;
		RedifineProbability();
		rand = new Random();
	}
	
	private void DangerOn() {
		//TODO fixer position
		dangerOn = true;
		map.GetPigeons().forEach(p -> p.NewDanger(this));
	}
	
	private void DangerOff() {
		dangerOn = false;
		map.GetPigeons().forEach(p -> p.NoMoreDanger());
		RedifineProbability();
	}
	
	private void RedifineProbability() {
		probability = rand.nextFloat()* (probabilityMax - probabilityMin) + probabilityMin;
	}

	@Override
	public void run() {
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
					if(rand.nextFloat() < probability)
					{
						DangerOn();
					}
				}
				
			}
		}
	}
}
