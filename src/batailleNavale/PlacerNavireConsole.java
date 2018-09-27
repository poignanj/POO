package batailleNavale;

import java.util.Scanner;

import outils.*;

public class PlacerNavireConsole implements PlacerNavire {

	
	public Tuple placer(Bateau b) {
		Tuple t = new Tuple(new Coordonnee(0,0),new Coordonnee(0,0));
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Placez le point de départ de votre bateau. A-J :");
		t.debut.x = sc.nextInt();
		System.out.print("1-10 :");
		t.debut.y = sc.nextInt();
		System.out.print("Placez le point d'arrivée de votre bateau. A-J :");
		t.fin.x = sc.nextInt();
		System.out.print("1-10 :");
		t.fin.y = sc.nextInt();
		//TODO: faire vérification de position selon cases et taille bateau
		// Ordoner debut et fin selon x ou y en fonction de l'inégalité (une valeure sera en doublon)
		return t;
	}
	
}
