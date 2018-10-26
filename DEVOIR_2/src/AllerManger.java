
public class AllerManger extends Comportement {
	private Nourriture but;
	
	public AllerManger(Pigeon pigeon, Nourriture but, Map map) {
		this.but = but;
		this.pigeon = pigeon;
		this.map = map;
	}

	@Override
	public void ExecuteComportement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NewNourriture(Nourriture newNour) {
		// TODO Auto-generated method stub
		
	}
	
	public void NourritureNotAvailable(Nourriture notAvailable) {
		if(notAvailable == but)
			pigeon.RedifineComportement(new Idle(pigeon, map));
	}
}
