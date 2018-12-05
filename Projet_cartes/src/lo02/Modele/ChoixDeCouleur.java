package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 * Interface pour le choix de couleur lors d'un changement de couleur de la pile
 */
public interface ChoixDeCouleur {
	public int choixCouleur(int couleur, ArrayList<Carte> main);
}
