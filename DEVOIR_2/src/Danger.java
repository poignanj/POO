import outils.Vecteur2D;

public class Danger extends Thread {
	private Map map;
	private Vecteur2D position;
	private float timeEndFuite;
	private boolean dangerOn = false;
	private float probability;
	private float probabilityMin = 0.1f;
	private float probabilityMax = 0.7f;
	
	public Danger(Map map) {
		this.map = map;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
}
