package batailleNavale;

public class PorteAvion extends Bateau{
	public PorteAvion(){
		this.taille = 5;
		this.portee = 2;
		this.impacts = 0;
	}
	
	public String toString() {
		return "Porte-Avion : " + (100-50*impacts) + "% de vie restante.";
	}
}
