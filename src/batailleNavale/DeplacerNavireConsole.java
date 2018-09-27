package batailleNavale;

import java.util.ArrayList;
import java.util.Scanner;

import outils.ChoixEntree;

public class DeplacerNavireConsole implements DeplacerNavire {

	@Override
	public void deplacer(Bateau b, ArrayList<Bateau> flotte) {
		boolean deplacementValide = true;
		int choix = ChoixEntree.ChoixPlageInt(-2, 2, "De quelle distance voulez-vous déplacer ce bateau?");
		if (b.getPosition().debut.x == b.getPosition().fin.x) {
			b.getPosition().debut.x += choix;
			b.getPosition().fin.x += choix;
			if(b.getPosition().debut.x < 0 || b.getPosition().fin.x > Carte.TAILLE-1) {
				deplacementValide = false;
			}
			else {
				for(int i = 0; i < flotte.size(); i++)
				{
					if(b != flotte.get(i) && b.colision(flotte.get(i)))
					{
						deplacementValide = false;
						break;
					}
				}
			}
			
			if(!deplacementValide)
			{
				b.getPosition().debut.x -= choix;
				b.getPosition().fin.x -= choix;
			}
		}
		else {
			b.getPosition().debut.y += choix;
			b.getPosition().fin.y += choix;
			if(b.getPosition().debut.y < 0 || b.getPosition().fin.y > Carte.TAILLE-1) {
				deplacementValide = false;
			}
			else {
				for(int i = 0; i < flotte.size(); i++)
				{
					if(b != flotte.get(i) && b.colision(flotte.get(i)))
					{
						deplacementValide = false;
						break;
					}
				}
			}
			
			if(!deplacementValide)
			{
				b.getPosition().debut.y -= choix;
				b.getPosition().fin.y -= choix;
			}
		}

		if(!deplacementValide)
		{
			System.out.println("Ce déplacement est impossible, veuillez réessayer.");
			deplacer(b, flotte);
		}
	}

}
