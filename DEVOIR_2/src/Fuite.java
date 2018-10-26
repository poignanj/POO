
public class Fuite extends Comportement {
	private Danger danger;
	
	public Fuite(Pigeon pigeon, Danger danger) {
		this.danger = danger;
		this.pigeon = pigeon;
	}

	@Override
	public void ExecuteComportement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NewNourriture(Nourriture newNour) {}
}
