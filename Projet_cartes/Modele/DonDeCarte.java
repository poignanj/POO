package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 * Interface pour le don de carte
 *
 */
public interface DonDeCarte {
	public Carte choixDonCarte(Carte cTalon, ArrayList<Carte> main);
}
