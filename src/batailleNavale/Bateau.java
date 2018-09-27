package batailleNavale;

import outils.*;

public abstract class Bateau {
	protected int taille;
	protected int portee;
	protected int impacts;
	private Tuple position;

	public abstract String toString();

	// methodes communes à tous les bateaux sur le traitement
	public Tuple getPosition() {
		return position;
	}

	public void setPosition(Tuple position) {
		this.position = position;
	}

	// methode pour enelever des PVs au bateau
	public void dommage() {
		this.impacts += 1;
	}

	// detecteur de colision missile-bateau
	public boolean colision(Coordonnee c) {
		return ((c.x >= position.debut.x && c.x <= position.fin.x) && (c.y >= position.debut.y && c.y <= position.fin.y));
	}

	// detecteur de colision bateau-bateau
	public boolean colision(Bateau b) {
		return (this.getPosition().debut.x < b.getPosition().fin.x &&
				this.getPosition().fin.x > b.getPosition().debut.x &&
				this.getPosition().debut.y < b.getPosition().fin.y &&
				this.getPosition().fin.y > b.getPosition().debut.y );
	}
}
