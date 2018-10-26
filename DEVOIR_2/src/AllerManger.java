
public class AllerManger extends Comportement {
	private Nourriture but;
	
	public AllerManger(Pigeon pigeon, Nourriture but, Map map) {
		this.but = but;
		this.pigeon = pigeon;
		this.map = map;
		this.vitesse = (float) 0.1;
		this.direction = this.but.GetPosition().Soustraction(this.pigeon.GetPosition());
	}

	@Override
	public void ExecuteComportement() {
		this.pigeon.GetPosition().Addition(this.direction.Multi(this.vitesse));
	}

	@Override
	public void NewNourriture(Nourriture newNour) {
		if(newNour.GetPosition().Distance(this.pigeon.GetPosition())<= this.but.GetPosition().Distance(this.pigeon.GetPosition()))
			this.but = newNour;
	}
	
	public void NourritureNotAvailable(Nourriture notAvailable) {
		if(notAvailable == but)
			pigeon.RedifineComportement(new Idle(pigeon, map));
	}
}
