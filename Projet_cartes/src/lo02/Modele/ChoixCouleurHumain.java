/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jean-Jacques Poignant et Elise Poignant 
 * Permet de choisir la couleur que le joueur humain veut jouer (dans la console)
 * 
 */
public class ChoixCouleurHumain implements ChoixDeCouleur {

	/*
	 * (non-Javadoc)
	 * 
	 * @see lo02.gameCore.ChoixDeCouleur#choixCouleur(int, java.util.ArrayList)
	 */
	@Override

	/**
	 * Permet de choisir une couleur parmi les couleurs des cartes que possède le
	 * joueur
	 * 
	 * @param couleur la couleur que l'on ne peut choisir
	 * @param main la main du joueur qui doit choisir
	 * @return c la couleur renvoyée
	 */
	public int choixCouleur(int couleur, ArrayList<Carte> main) {
		// affiche les cartes du joueur
		String s = "";
		for (int i = 0; i < main.size(); i++)
			s = s + main.get(i).toString() + "; ";
		System.out.print(s);
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir la couleur que vous voulez jouer :");
		int c = sc.nextInt();
		while (c == couleur) {
			System.out.println("Veuillez saisir la couleur que vous voulez jouer :");
			c = sc.nextInt();
		}
		// sc.close();
		return c;
	}

}
