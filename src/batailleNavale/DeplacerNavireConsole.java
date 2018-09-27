package batailleNavale;

import java.util.Scanner;

public class DeplacerNavireConsole implements DeplacerNavire {

	@Override
	public void deplacer(Bateau b) {
		System.out.print("Avant ou arri�re ? (1/-1) :");
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		sc.close();
		if (b.getPosition().debut.x == b.getPosition().fin.x) {
			b.getPosition().debut.x += choix;
			b.getPosition().fin.x += choix;
		}
		else {
			b.getPosition().debut.y += choix;
			b.getPosition().fin.y += choix;
		}
	
	}

}
