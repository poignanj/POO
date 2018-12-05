/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *         Permet à l'IA de choisir la couleur à jouer
 */
public class ChoixCouleurIAniveau1 implements ChoixDeCouleur {

	/*
	 * (non-Javadoc)
	 * 
	 * @see lo02.gameCore.ChoixDeCouleur#choixCouleur(int, java.util.ArrayList)
	 */
	@Override
	/**
	 * Permet à L'IA de choisir la couleur qu'elle veut jouer
	 * 
	 * @param couleur
	 *            la couleur que l'on ne peut choisir
	 * @param main
	 *            la main du joueur qui doit choisir
	 * @return c la couleur renvoyée
	 */
	public int choixCouleur(int couleur, ArrayList<Carte> main) {
		int i;
		int j;
		int[] compteur = { 0, 0, 0, 0, 0, 0 };
		int max = 0;
		for (i = 0; i < main.size(); i++) {
			compteur[main.get(i).getCouleur()]++;
		}
		for (j = 0; j < compteur.length; j++) {
			if (compteur[max] <= compteur[j])
				max = j;
		}
		return max;
	}

}
