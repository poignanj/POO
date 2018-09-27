package batailleNavale;

public class Croiseur extends Bateau {
	public Croiseur() {
		this.taille = 4;
		this.portee = 2;
		this.impacts = 0;
	}
	@Override
	public String toString() {
		return "Croiseur : " + (100-50*impacts) + "% de vie restante.";
	}

}
