package batailleNavale;

public class ContreTorpilleur extends Bateau {
	public ContreTorpilleur() {
		this.taille = 3;
		this.portee = 2;
		this.impacts = 0;		
	}
	@Override
	public String toString() {
		return "Contre-Torpilleur : " + (100-50*impacts) + "% de vie restante.";
	}

}
