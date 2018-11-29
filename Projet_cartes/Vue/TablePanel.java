package lo02.Vue;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 */
public class TablePanel extends JPanel {
	int nbJoueurs;

	/**
	 * Constructeur par d�faut, cr�e 3 advesaires
	 */
	public TablePanel() {
		this.setLayout(new GridLayout(2, 3));
		this.add(new JButton("Menu")); // 0
		this.add(new IAButton("Adversaire 2")); // 1
		this.add(new JButton("R�cap")); // 2
		this.add(new IAButton("Adversaire 1"));// 3
		this.add(new BoardPanel());// 4
		this.add(new IAButton("Aversaire 3")); // 5
		this.nbJoueurs = 4;
	}

	/**
	 * Ce constructeur change l'apparence en fonction du nombre nbJoueurs
	 * 
	 * @param nbJoueurs
	 *            nombre de joueurs attabl�s
	 */
	public TablePanel(int nbJoueurs) {
		this.setLayout(new GridLayout(2, 3));
		this.nbJoueurs = nbJoueurs;
		switch (nbJoueurs) {
		case 2:
			this.add(new JButton("Menu"));
			this.add(new IAButton("Adversaire 1"));
			this.add(new JButton("R�cap"));
			this.add(new JPanel());
			this.add(new BoardPanel());
			this.add(new JPanel());
			break;
		case 3:
			this.add(new JButton("Menu"));
			this.add(new JPanel());
			this.add(new JButton("R�cap"));
			this.add(new IAButton("Adversaire 1"));
			this.add(new BoardPanel());
			this.add(new IAButton("Adversaire 2"));
			break;
		default:
			this.add(new JButton("Menu"));
			this.add(new IAButton("Adversaire 2"));
			this.add(new JButton("R�cap"));
			this.add(new IAButton("Adversaire 1"));
			this.add(new BoardPanel());
			this.add(new IAButton("Aversaire 3"));
			break;
		}
	}

	/**
	 * Retourne le num�ro de JComposant correspondant au num�ro de l'adversaire
	 * 
	 * @param numJoueur
	 *            emplacement du joueur
	 * @return numComp num�ro de JComposant correspondant au num�ro de l'adversaire
	 */
	private int getNumCompJoueur(int numJoueur) {
		int numComp;
		switch (this.nbJoueurs) {
		case 2:
			numComp = 1;
		case 3:

			if (numJoueur == 1)
				numComp = 3;
			else
				numComp = 5;
			break;
		default:
			if (numJoueur == 1)
				numComp = 3;
			else if (numJoueur == 2)
				numComp = 1;
			else
				numComp = 5;
			break;
		}
		return numComp;
	}

	/**
	 * Redessine l'adversaire numJoueur avec nbCartes en main
	 * 
	 * @param numJoueur
	 *            emplacement du joueur
	 * @param nbCartes
	 *            nombre de cartes en main
	 */
	public void redrawIA(int numJoueur, int nbCartes) {
		int numComp = this.getNumCompJoueur(numJoueur);
		IAButton a = new IAButton("Adversaire " + numJoueur, nbCartes);
		this.remove(numComp);
		this.add(a, numComp);
	}

	/**
	 * Change la couleur de fond l'IA
	 * 
	 * @param numJoueur
	 *            emplacement du joueur
	 */
	public void changerCouleurJoueur(int numJoueur) {
		int numComp = this.getNumCompJoueur(numJoueur);
		this.getComponent(numComp).setBackground(Color.LIGHT_GRAY);
	}

	/**
	 * R�initialise les coleurs de fond des IA
	 */
	public void resetCouleurIA() {
		for (int i = 1; i < this.nbJoueurs; i++)
			this.getComponent(this.getNumCompJoueur(i)).setBackground(Color.WHITE);
	}
}
