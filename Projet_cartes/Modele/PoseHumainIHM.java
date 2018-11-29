/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 *         Permet à l'humain de poser une carte
 *
 */
public class PoseHumainIHM implements PoseDeCarte {
	public int numCarteClique = -1;

	@Override
	/**
	 * @param cTalon
	 *            carte du talon
	 * @param main
	 *            main du joueur
	 * 
	 * @return carte carte choisie
	 */
	public Carte poser(Carte cTalon, ArrayList<Carte> main) {
		numCarteClique = -1;
		boolean peutJouer = false;
		// parcourt la main du joueur
		for (int i = 0; i < main.size(); i++) {
			if (main.get(i).estJouableSur(cTalon))
				peutJouer = true;
		}
		if (peutJouer) {
			while (numCarteClique == -1) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			while (!main.get(numCarteClique).estJouableSur(cTalon)) {
				numCarteClique = -1;
				while (numCarteClique == -1) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			return main.remove(numCarteClique);

		}

		else {
			return null;
		}
	}

	/**
	 * Permet au controleur de specifier qu'une carte a été cliquée
	 * 
	 * @param i numéro de la carte dans la main
	 */
	public void setNumClique(int i) {
		this.numCarteClique = i;
	}

}
