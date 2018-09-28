package batailleNavale;

import java.util.ArrayList;
import java.util.Scanner;

import outils.*;

public class PlacerNavireConsole implements PlacerNavire {
	public Tuple placer(Bateau b, ArrayList<Bateau> flotte) {
		Tuple t = new Tuple(new Coordonnee(0, 0), new Coordonnee(0, 0));
		System.out.println("Vous placez un " + b.toString());
		
		Coordonnee c1 = ChoixEntree.ChoixCoordonnee('A', 'J', 1, 10, "Placez le point de départ de votre bateau.");

		ArrayList<Coordonnee> choixPossible = new ArrayList<Coordonnee>();
		
		if (c1.x-b.taille>=0) {
			choixPossible.add(new Coordonnee(c1.x-b.taille, c1.y));
		}
		
		if (c1.x+b.taille<Carte.TAILLE) {
			choixPossible.add(new Coordonnee(c1.x+b.taille, c1.y));
		}
		
		if (c1.y-b.taille>=0) {
			choixPossible.add(new Coordonnee(c1.x, c1.y-b.taille));
		}
		
		if (c1.y+b.taille<Carte.TAILLE) {
			choixPossible.add(new Coordonnee(c1.x, c1.y+b.taille));
		}		
			
		Coordonnee c2 = ChoixEntree.ChoixCoordonneeParmiListe(choixPossible, 'A', 'J', 1, 10, "Veuillez entrer le numéro d'une des coordonnée, choix possible :" + choixPossible.toString());
		
		if(c1.x < c2.x || c1.y < c2.y)
		{
			t.debut = c1;
			t.fin = c2;
		}
		else
		{
			t.debut = c2;
			t.fin = c1;
		}
		
		b.setPosition(t);
		
		boolean valide = true;
		for(int i = 0; i < flotte.size(); i++)
		{
			if(flotte.get(i) == b)
				break;
			else
				if(flotte.get(i).collision(b))
					valide = false;
		}
		
		if(valide)
			return t;
		else
		{
			System.out.println("Cette position rentre en collision avec un bateau déjà placé, choisissez une nouvelle position.");
			return placer(b, flotte);
		}
	}
}
