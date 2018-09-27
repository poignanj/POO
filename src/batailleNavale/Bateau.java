package batailleNavale;

import outils.*;

public abstract class Bateau {
	protected int taille;
	protected int portee;
	protected int impacts;
	private Tuple position;
	
	public abstract String toString();
	
	public Tuple getPosition() {
		return position;
	}

	public void setPosition(Tuple position) {
		this.position = position;
	}

	public boolean colision(Coordonnee c) {
		if ((c.x > position.debut.x && c.x < position.fin.x) && (c.y > position.debut.y && c.y < position.fin.y)){
			this.impacts +=1;
			return true;
		}
		else 
			return false;
	}
}

