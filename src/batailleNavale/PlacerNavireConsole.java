package batailleNavale;

import java.util.Scanner;

import outils.*;

public class PlacerNavireConsole implements PlacerNavire {

	// TODO: faire vérification de position selon cases et taille bateau
	// Ordoner debut et fin selon x ou y en fonction de l'inégalité (une valeure
	// sera en doublon)
	// Passer lettre > entier. Pour le moment, seuls les entier sont acceptés
	// /!\ tableaux de 0 à 9 mais inputs de A à J et 1 à 10
	// verifier la NON-superposition avec un autre navire !!! possibilité de devoir
	// modifier l'interface & paramètres (exemple: changement pour flotte + index)
	public Tuple placer(Bateau b) {
		Tuple t = new Tuple(new Coordonnee(0, 0), new Coordonnee(0, 0));
		System.out.println("Vous placez un " + b.toString());
		t.debut.x = ChoixEntree.ChoixPlageChar('A', 'J', "Placez le point de départ de votre bateau.", false) - 'A';
		System.out.print("1-10 :");
		t.debut.y = ChoixEntree.ChoixPlageInt(1, 10, "") - 1;
		//Je suis en train d'arranger ça
		t.debut.x = ChoixEntree.ChoixPlageChar('A', 'J', "Placez le point d'arrivée de votre bateau.", false) - 'A';
		System.out.print("1-10 :");
		t.debut.y = ChoixEntree.ChoixPlageInt(1, 10, "") - 1;
		return t;
	}
}
