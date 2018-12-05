/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 *         Permet à l'IA de donner une carte
 *
 */
public class DonIAfacile implements DonDeCarte {

	@Override

	/**
	 * Donne une carte de la main de l'IA à un joueur
	 * 
	 * @param cTalon
	 *            la carte du talon
	 * @param main
	 *            la main du joueur actuel
	 * 
	 * @return carte la carte choisie
	 */
	public Carte choixDonCarte(Carte cTalon, ArrayList<Carte> main) {
		return main.remove((int) (Math.random() * main.size()));
	}

}
