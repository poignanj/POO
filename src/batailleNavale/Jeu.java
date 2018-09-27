package batailleNavale;

import outils.Coordonnee;

public class Jeu {
	private Carte c1;
	private Carte c2;
	
	public Jeu() {
		c1 = new Carte(new PlacerNavireConsole(), new SelectionnerNavireConsole(), new FaireFeuConsole(), new DeplacerNavireConsole());
		c2 = new Carte(new PlacerNavireConsole(), new SelectionnerNavireConsole(), new FaireFeuConsole(), new DeplacerNavireConsole());
		this.tourDeJeu();
	}
	private void tourDeJeu() {
		Coordonnee c = null;
		while(true) {
		c = c1.tirer(c);
		c = c2.tirer(c);
		}
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		Jeu j = new Jeu();

	}

}
