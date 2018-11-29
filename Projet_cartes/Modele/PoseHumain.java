/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *         Permet à humain de poser une carte
 *
 */
public class PoseHumain implements PoseDeCarte {

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
		// affiche les cartes du joueur
		String s = "";
		for (int i = 0; i < main.size(); i++)
			s = s + main.get(i).toString() + "; ";
		System.out.print(s + "\n");
		boolean peutJouer = false;

		// parcourt la main du joueur
		for (int i = 0; i < main.size(); i++) {

			if (main.get(i).estJouableSur(cTalon))
				peutJouer = true;
		}

		if (peutJouer) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Veuillez saisir la position de la carte que vous voulez jouer :");
			int position = sc.nextInt();
			System.out.println("Vous avez saisi le nombre : " + position);

			while (!main.get(position).estJouableSur(cTalon)) {
				System.out.println("Veuillez saisir la position de la carte que vous voulez jouer :");
				position = sc.nextInt();
				System.out.println("Vous avez saisi le nombre : " + position);
			}
			return main.remove(position);

		}

		else {
			return null;
		}
	}

}
