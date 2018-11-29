package lo02.Vue;

import java.util.ArrayList;

import javax.swing.JPanel;

import lo02.Controleur.ControleurCarte;
import lo02.Modele.Carte;
import lo02.Modele.Humain;

/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 */
public class HandPanel extends JPanel {
	public boolean clicked = false;
	public int indexClicked = 0;

	/**
	 * Constructeur simple
	 */
	public HandPanel() {
		this.setName("main");
	}

	/**
	 * Constructeur passant la main du joueur, le joueur et la carte du talon
	 * Affiche les cartes en main en indique lesquelles sont jouables (à finir)
	 * 
	 * @param main
	 *            ArrayList de cartes contenant la main du joueur
	 * @param h
	 *            joueur humain
	 * @param carte
	 *            carte du talon
	 */
	public HandPanel(ArrayList<Carte> main, Humain h, Carte carte) {
		this.setName("main");
		for (int i = 0; i < main.size(); i++) {
			CarteLabel j = new CarteLabel(main.get(i), carte);
			j.setIndexMain(i);
			new ControleurCarte(j, h);
			this.add(j);
		}
	}

}
