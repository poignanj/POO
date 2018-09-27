package batailleNavale;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import outils.ChoixEntree;
import outils.Coordonnee;

public class FaireFeuConsole implements FaireFeu {
	
	public Coordonnee tirer(Bateau b) {
		Coordonnee c = new Coordonnee();
		String s = "";
		System.out.println("Les cases suivantes sont à portée de votre bateau :");
		// Création de la liste des cases à portée
		ArrayList<Coordonnee> ciblesPossibles = new ArrayList<>();
		if (b.getPosition().debut.x == b.getPosition().fin.x) {
			for (int i = b.getPosition().debut.y; i <= b.getPosition().fin.y; i++) {
				for (int j = b.getPosition().debut.x - b.portee; j <= b.getPosition().debut.x + b.portee; j++) {
					// Les bateaux ne pourront tirer que sur leurs cotés afin d'alleger ce morceau
					// du code
					if(!(j < 0 || j > Carte.TAILLE - 1)) {
						ciblesPossibles.add(new Coordonnee(j, i));
						s += (new Coordonnee(j, i)).toString() + " ";
					}
				}
			}
		}
		else {
			for (int i = b.getPosition().debut.x; i <= b.getPosition().fin.x; i++) {
				for (int j = -b.portee; j <= b.portee; j++) {
					// Les bateaux ne pourront tirer que sur leurs cotés afin d'alleger ce morceau
					// du code
					if(!(b.getPosition().debut.y + j < 0 || b.getPosition().debut.y + j > Carte.TAILLE - 1)) {
						ciblesPossibles.add(new Coordonnee(i, b.getPosition().debut.y + j));
						s += (new Coordonnee(i, b.getPosition().debut.y + j)).toString() + " ";
					}
				}
			}
		}
		s += "\nOù voulez-vous tirer?";
		c = ChoixEntree.ChoixCoordonneeParmiListe(ciblesPossibles, 'A', 'J', 1, 10, s);
		return c;
	}

}
