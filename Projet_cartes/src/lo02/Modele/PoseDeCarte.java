package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 * D�finit l'interface permettant de poser des cartes
 *
 *
 *
 */
public interface PoseDeCarte {
	public Carte poser(Carte cTalon, ArrayList<Carte> main);
}
