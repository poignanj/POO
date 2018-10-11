package batailleNavale;

public class Torpilleur extends Bateau {
	public Torpilleur(){
		this.taille = 2;
		this.portee = 5;
		this.impacts = 0;		
	}
	@Override
	public String toString() {
		return "Torpilleur (portée " + this.portee + ") : " + (100-50*impacts) + "% de vie restante.";
	}

}
