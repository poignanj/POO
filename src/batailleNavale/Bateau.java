package batailleNavale;

import outils.*;

public abstract class Bateau {
	protected int taille;
	protected int portee;
	protected int impacts;
	private Tuple position;
	
	public Tuple getPosition() {
		return position;
	}
	public void setPosition(Tuple position) {
		this.position = position;
	}
}

