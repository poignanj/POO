/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Jean-Jacques Poignant et Elise Poignant 
 * 
 * Permet de choisir la couleur que le joueur humain veut jouer en cliquant (dans l'interface)
 *
 */
public class ChoixCouleurHumainIHM extends Observable implements ChoixDeCouleur {
	int numCarteClique;
	@Override
	/**
	 * Permet de choisir la couleur que le joueur humain veut jouer en cliquant sur
	 * une des 4 couleurs disponibles
	 * Attend un clic venant du controleur

	 * @param couleur la couleur que l'on ne peut choisir
	 * @param main la main du joueur qui doit choisir
	 * @return c la couleur renvoyée
	 */
	public int choixCouleur(int couleur, ArrayList<Carte> main) {
		this.numCarteClique = -1;
		this.setChanged();
		this.notifyObservers();
		while (numCarteClique == -1) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return numCarteClique;
	}
	/**
	 * 
	 * @param i numéro de la couleur choisie
	 */
	public void setNumClique(int i) {
		this.numCarteClique = i;
	}
}
