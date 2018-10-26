import outils.Vecteur2D;

public class AllerManger extends Comportement {
	private Nourriture but;
	
	public AllerManger(Pigeon pigeon, Nourriture but, Map map) {
		this.but = but;
		this.pigeon = pigeon;
		this.map = map;
		this.vitesse = (float) 100;
		this.direction = this.but.GetPosition().Soustraction(this.pigeon.GetPosition());
	}

	@Override
	public void ExecuteComportement() {
		Vecteur2D temp = this.pigeon.GetPosition().Addition(this.direction.Multi(this.vitesse));
		if(!this.map.outOfBounds(temp)) {
			this.pigeon.SetPosition(temp);
			this.pigeon.getRepresentation().relocate((double)temp.x, (double)temp.y);
		}
		//this.pigeon.getRepresentation().relocate((double)this.pigeon.GetPosition().x, (double)this.pigeon.GetPosition().y);
		if(this.pigeon.GetPosition().Distance(this.but.GetPosition())<= Pigeon.LARGEUR + Nourriture.LARGEUR) {
			but.Consume();
		}
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
