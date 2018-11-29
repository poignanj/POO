/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *permet à l'IA de choisir la couleur qu'elle veut qur le tas
 */
public class ChoixCouleurIAfacile implements ChoixDeCouleur {

	@Override
	/**
	 * L'IA choisi couleur qu'elle veut jouer
	 * 

	 * @param couleur la couleur que l'on ne peut choisir
	 * @param main la main du joueur qui doit choisir
	 * @return c la couleur renvoyée
	 */
	public int choixCouleur(int couleur, ArrayList<Carte> main) {
		int i = 0;
		while (i < main.size() && (main.get(i).getCouleur() == couleur || main.get(i).getCouleur() > 3)) {
			i++;
		}
		if (i < main.size())
			return main.get(i).getCouleur();
		else
			return new Random().nextInt(4);
	}

}
