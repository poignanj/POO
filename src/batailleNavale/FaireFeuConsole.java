package batailleNavale;

import java.util.Scanner;

import outils.Coordonnee;

public class FaireFeuConsole implements FaireFeu {

	//WARNING : non vérification du outOfBound ni de l'entrée user
	public Coordonnee tirer(Bateau b) {
		Coordonnee c= new Coordonnee();
		System.out.println("Les cases suivantes sont à portée de votre bateau :");
		String s = "";
		if (b.getPosition().debut.x == b.getPosition().fin.x)
			for (int i = b.getPosition().debut.y; i < b.getPosition().fin.y; i++)
				for (int j = -b.portee; j <= b.portee; j++)
					// Les bateaux ne pourront tirer que sur leurs cotés afin d'alleger ce morceau
					// du code
					// for (int k = -b.portee; k <= b.portee; k++)
					s += new Coordonnee(b.getPosition().debut.x + j, i).toString() + "; ";
		else
			for (int i = b.getPosition().debut.x; i < b.getPosition().fin.x; i++)
				for (int j = -b.portee; j <= b.portee; j++)
					// Les bateaux ne pourront tirer que sur leurs cotés afin d'alleger ce morceau
					// du code
					// for (int k = -b.portee; k <= b.portee; k++)
					s += new Coordonnee(i, b.getPosition().debut.y + j).toString()+ "; ";
		System.out.println(s);
		Scanner sc = new Scanner(System.in);
		System.out.print("Choississez sur laquelle tirer. A-J :");
		c.x = sc.nextInt();
		System.out.print("1-10 :");
		c.y = sc.nextInt();
		sc.close();
		return c;
	}

}
