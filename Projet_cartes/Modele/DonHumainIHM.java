/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 *         Permet à l'humain de choisir quelle carte il va donner
 */
public class DonHumainIHM implements DonDeCarte {

	private int numCarteClique;

	@Override
	/**
	 * Permet à l'humain de choisir quelle carte il va donner
	 * 
	 * @param cTalon
	 *            la carte du talon
	 * @param main
	 *            la main du joueur actuel
	 * 
	 * @return carte la carte choisie
	 */
	public Carte choixDonCarte(Carte cTalon, ArrayList<Carte> main) {
		numCarteClique = -1;
		while (numCarteClique == -1) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return main.remove(numCarteClique);

	}
	/**
	 * 
	 * @param i numéro de la carte dans la main
	 */
	public void setNumClique(int i) {
		this.numCarteClique = i;
	}

}
