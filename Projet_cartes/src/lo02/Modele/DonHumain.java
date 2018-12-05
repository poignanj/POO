/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 * Permet à l'humain de choisir quelle carte il va donner
 *
 */
public class DonHumain implements DonDeCarte {

	@Override
	/**
	 * Permet de choisir quelle carte le joueur humain va donner
	 * 
	 * @param cTalon la carte du talon
	 * @param main la main du joueur actuel
	 * 
	 * @return carte la carte choisie
	 */
	public Carte choixDonCarte(Carte cTalon, ArrayList<Carte> main) {
		String s = "";
		for (int i = 0; i < main.size(); i++)
			s = s + main.get(i).toString() + "; ";
		System.out.print(s);
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir la carte à donner :");
		int position = sc.nextInt();
		System.out.println("Vous allez donner la carte se trouant à la position : " + position + " : "
				+ main.get(position).toString());
		// sc.close();
		return main.remove(position);
	}

}
