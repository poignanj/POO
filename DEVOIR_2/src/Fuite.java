import outils.Vecteur2D;

public class Fuite extends Comportement {
	private Danger danger;
	
	public Fuite(Pigeon pigeon, Danger danger, Map map) {
		this.danger = danger;
		this.pigeon = pigeon;
		this.map = map;
		this.vitesse = (float) 0.1;
		this.direction = this.danger.GetPosition().Soustraction(this.pigeon.GetPosition());
	}

	@Override
	public void ExecuteComportement() {
		// TODO Auto-generated method stub
		Vecteur2D temp = this.pigeon.GetPosition().Addition(this.direction.Multi(this.vitesse * -1));
		if(!this.map.outOfBounds(temp))
			this.pigeon.SetPosition(temp);
	}

	@Override
	public void NewNourriture(Nourriture newNour) {
		pigeon.RedifineComportement(new AllerManger(this.pigeon, newNour,this.map));
	}
}
