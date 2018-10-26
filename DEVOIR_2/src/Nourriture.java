import outils.*;

public class Nourriture extends Thread {
	private Map map;
	private Vecteur2D position;
	private long timeBeforeSpoiled;
	
	public Vecteur2D GetPosition() {
		return position;
	}
	
	public Nourriture(Vecteur2D position, Map map, long timeBeforeSpoiled) {
		this.position = position;
		this.map = map;
		this.timeBeforeSpoiled = timeBeforeSpoiled;
	}
	
	public void Consume() {
		map.NourritureNotAvailable(this, false);
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
		}
	}
}
