/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 *         Permet à L'IA de donner une carte
 *
 */
public class DonIAniveau1 implements DonDeCarte {

	@Override
	/**
	 * Donne une carte de l'IA à un joueur
	 * 
	 * @param cTalon
	 *            la carte du talon
	 * @param main
	 *            la main du joueur actuel
	 * 
	 * @return carte la carte choisie
	 */
	public Carte choixDonCarte(Carte cTalon, ArrayList<Carte> main) {
		int i;
		int k;
		int[] compteur = { 0, 0, 0, 0, 0, 0 };
		int min = main.size();
		for (i = 0; i < main.size(); i++) {
			if (main.get(i).getCouleur() <= 3)
				compteur[main.get(i).getCouleur()]++;
		}
		for (k = 0; k < compteur.length; k++) {
			if (compteur[k] > 0 && min >= compteur[k]) {
				min = k;
			}
		}
		ArrayList<Carte> temp = new ArrayList<>();
		for (i = 0; i < main.size(); i++)
			if (main.get(i).getCouleur() == min)
				temp.add(main.get(i));

		return main.remove(main.indexOf(temp.get(0)));

	}
}
