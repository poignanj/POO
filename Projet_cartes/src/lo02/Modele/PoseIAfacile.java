/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *         définit comment IAfacile pose des cartes
 */
public class PoseIAfacile implements PoseDeCarte {

	@Override
	/**
	 * Pose une carte sur le talon
	 * 
	 * @param cTalon
	 *            carte du talon
	 * @param main
	 *            main du joueur
	 * 
	 * @return carte carte choisie
	 */
	public Carte poser(Carte cTalon, ArrayList<Carte> main) {
		int i = 0;
		while (i < main.size() && !main.get(i).estJouableSur(cTalon))
			i++;
		if (i >= main.size())
			return null;
		// elle va jouer la première carte jouable de sa main
		else
			return main.remove(i);
	}

}
