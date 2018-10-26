
public class Fuite extends Comportement {
	private Danger danger;
	
	public Fuite(Pigeon pigeon, Danger danger, Map map) {
		this.danger = danger;
		this.pigeon = pigeon;
		this.map = map;
	}

	@Override
	public void ExecuteComportement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NewNourriture(Nourriture newNour) {}
}
