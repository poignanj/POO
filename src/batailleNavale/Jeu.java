package batailleNavale;

import outils.Coordonnee;

public class Jeu {
	private Carte joueur1;
	private Carte joueur2;

	public Jeu() {
		joueur1 = new Carte(new PlacerNavireConsole(), new SelectionnerNavireConsole(), new FaireFeuConsole(),
				new DeplacerNavireConsole());
		joueur2 = new Carte(new PlacerNavireConsole(), new SelectionnerNavireConsole(), new FaireFeuConsole(),
				new DeplacerNavireConsole());
		this.tourDeJeu();
	}

	private void tourDeJeu() {
		Coordonnee c = null;
		while (true) {
			c = joueur1.tirer(c);
			if (c == null) {
				System.out.println("Joueur 2 remporte la partie !!!");
				break;
			}
			c = joueur2.tirer(c);
			if (c == null) {
				System.out.println("Joueur 1 remporte la partie !!!");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Jeu j = new Jeu();

	}

}
