package batailleNavale;

public class SousMarin extends Bateau {
	public SousMarin(){
		this.taille = 3;
		this.portee = 4;
		this.impacts = 0;		
	}
	@Override
	public String toString() {
		return "Sous-Marin (port�e " + this.portee + ") : " + (100-50*impacts) + "% de vie restante.";
	}

}
