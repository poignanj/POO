package batailleNavale;

import java.util.Scanner;

import outils.*;

public class PlacerNavireConsole implements PlacerNavire {

	// TODO: faire v�rification de position selon cases et taille bateau
	// Ordoner debut et fin selon x ou y en fonction de l'in�galit� (une valeure
	// sera en doublon)
	// Passer lettre > entier. Pour le moment, seuls les entier sont accept�s
	// /!\ tableaux de 0 � 9 mais inputs de A � J et 1 � 10
	// verifier la NON-superposition avec un autre navire !!! possibilit� de devoir
	// modifier l'interface & param�tres (exemple: changement pour flotte + index)
	public Tuple placer(Bateau b) {
		Tuple t = new Tuple(new Coordonnee(0, 0), new Coordonnee(0, 0));
		System.out.println("Vous placez un " + b.toString());
		t.debut = ChoixEntree.ChoixCoordonnee('A', 'J', 1, 10, "Placez le point de d�part de votre bateau.");
		//t.debut.y = ChoixEntree.ChoixPlageChar('A', 'J', "Placez le point de d�part de votre bateau.", false) - 'A';
		//t.debut.x = ChoixEntree.ChoixPlageInt(1, 10, "") - 1;
		//Je suis en train d'arranger �a
		t.fin = ChoixEntree.ChoixCoordonnee('A', 'J', 1, 10, "Placez le point d'arriv�e de votre bateau.");
		//t.fin.y = ChoixEntree.ChoixPlageChar('A', 'J', "Placez le point d'arriv�e de votre bateau.", false) - 'A';
		//t.fin.x = ChoixEntree.ChoixPlageInt(1, 10, "") - 1;
		return t;
	}
}
