/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *définit comment IAniveau1 pose des cartes
 */
public class PoseIAniveau1 implements PoseDeCarte {

	@Override
	/**
	 * pose une carte sur le talon 
	 * @param cTalon carte du talon
	 * @param main main du joueur
	 * 
	 * @return carte carte choisie
	 */
	public Carte poser(Carte cTalon, ArrayList<Carte> main) {

		int i;
		ArrayList<Carte> carteJouable = new ArrayList<>();
		// on parcours la main de l'IA et on stocke les carte jouable dans une liste
		for (i = 0; i < main.size(); i++) {
			if (main.get(i).estJouableSur(cTalon)) {
				carteJouable.add(main.get(i));
			}
		}
		// l'IA ne peut pas jouer de carte
		if (carteJouable.isEmpty()) {
			return null;
		}

		// l'IA peut jouer une carte
		else {

			int max = 0;
			int maxIndice = 0;
			int indiceCarteRejouable = 0;
			int indiceSauterTour = 0;
			int indiceDonnerCarte = 0;
			int indiceChangerSens = 0;

			// on parcourt les cartes jouables
			for (int j = 0; j < carteJouable.size(); j++) {

				// l'IA va comparer les cartes pour faire piocher un maximum de carte à
				// l'adversaire
				if (carteJouable.get(j).getVar().getPiocher() > max) {
					max = carteJouable.get(j).getVar().getPiocher();
					maxIndice = j;
				}

				// L'IA regarde si elle possède une carte pour rejouer
				if (carteJouable.get(j).getVar().getEffet()[5]) {
					indiceCarteRejouable = j;
				}

				// L'IA regarde si elle possède une carte pour sauter le tour d'un adversaire
				if (carteJouable.get(j).getVar().getEffet()[6]) {
					indiceSauterTour = j;
				}

				// L'IA regarde si elle possède une carte pour donner une carte à l'adversaire
				if (carteJouable.get(j).getVar().getEffet()[7]) {
					indiceDonnerCarte = j;
				}

				// L'IA regarde si elle possède une carte pour changer le sens de la partie
				if (carteJouable.get(j).getVar().getEffet()[4]) {
					indiceChangerSens = j;
				}

			}

			// l'IA fait piocher
			if (max > 0)
				return carteJouable.remove(maxIndice);
			// sinon elle rejoue
			else if (indiceCarteRejouable != 0)
				return main.remove(main.indexOf(carteJouable.remove(indiceCarteRejouable)));
			// sinon elle saute le tour de l'adversaire
			else if (indiceSauterTour != 0)
				return main.remove(main.indexOf(carteJouable.remove(indiceSauterTour)));
			// sinon elle donne une carte
			else if (indiceDonnerCarte != 0)
				return main.remove(main.indexOf(carteJouable.remove(indiceDonnerCarte)));
			// sinon elle change de sens
			else if (indiceChangerSens != 0)
				return main.remove(main.indexOf(carteJouable.remove(indiceChangerSens)));
			// sinon elle joue la première carte qu'elle peut jouer
			else
				return main.remove(main.indexOf(carteJouable.remove(0)));
		}
	}

}
