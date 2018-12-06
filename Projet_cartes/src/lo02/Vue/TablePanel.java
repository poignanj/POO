package lo02.Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant et Vivien Piris
 *
 */
public class TablePanel extends JPanel {
	int nbJoueurs;

	/**
	 * Constructeur par défaut, crée 3 advesaires
	 */
	public TablePanel() {
		
		JButton regle = new JButton("règles");
		regle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup p;
				StringBuilder sb = new StringBuilder();
				sb.append("Les 8 permettent de changer de couleur à n’importe quel moment.\n" + 
						"Les Jokers font piocher + 4 cartes au joueur suivant\n" + 
						"Les Valets font sauter le tour du joueur suivant.\n" + 
						"Les As font changer le sens du jeu.\n" + 
						"Les 2 font font piocher + 2 cartes au joueur suivant\n"+
						"7 Cartes sont distribuée au début du jeu\n"+
						"Une carte ne peut être recouverte que par une carte :\r\n" + 
						"	de même couleur\r\n" + 
						"	de même valeur\r\n" + 
						"	une carte spéciale\r\n" + 
						"Si le joueur ne peut pas jouer une carte est ajouté à sa main\r\n" + 
						"Si le joueur est débarassé de ses cartes il gagne");
				JTextArea j = new JTextArea(sb.toString());
				JFrame ftemp= new JFrame();
				ftemp.add(j);
				JFrame f = new JFrame("Rêgles");
				f.add(new JButton("Close"));
				PopupFactory pf = new PopupFactory();
				p = pf.getPopup(f,ftemp,5,600);
				p.show();
			}
		});
		this.setLayout(new GridLayout(2, 3));
		this.add(new JButton("Menu")); // 0
		this.add(new IAButton("Adversaire 2")); // 1
		this.add(regle); // 2
		this.add(new IAButton("Adversaire 1"));// 3
		this.add(new BoardPanel());// 4
		this.add(new IAButton("Aversaire 3")); // 5
		this.nbJoueurs = 4;
	}

	/**
	 * Ce constructeur change l'apparence en fonction du nombre nbJoueurs
	 * 
	 * @param nbJoueurs
	 *            nombre de joueurs attablés
	 */
	public TablePanel(int nbJoueurs) {
		this.setLayout(new GridLayout(2, 3));
		this.nbJoueurs = nbJoueurs;

// to do changer l'action performed to open a Popup message with the rules
		
		JButton regle = new JButton("rêgles");
		regle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup p;
				StringBuilder sb = new StringBuilder();
				sb.append("Les 8 permettent de changer de couleur à n’importe quel moment.\n" + 
						"Les Jokers font piocher + 4 cartes au joueur suivant\n" + 
						"Les Valets font sauter le tour du joueur suivant.\n" + 
						"Les As font changer le sens du jeu.\n" + 
						"Les 2 font font piocher + 2 cartes au joueur suivant\n"+
						"7 Cartes sont distribuée au début du jeu\n"+
						"Une carte ne peut être recouverte que par une carte :\r\n" + 
						"	de même couleur\r\n" + 
						"	de même valeur\r\n" + 
						"	une carte spéciale\r\n" + 
						"Si le joueur ne peut pas jouer une carte est ajouté à sa main\r\n" + 
						"Si le joueur est débarassé de ses cartes il gagne");
				JTextArea j = new JTextArea(sb.toString());
				JFrame f = new JFrame("Règles");
				f.add(new JButton("Close"));
				PopupFactory pf = new PopupFactory();
				p = pf.getPopup(f,j,5,600);
				
				p.show();
			}
		});
		switch (nbJoueurs) {
		case 2:
			this.add(new JButton("Menu"));
			this.add(new IAButton("Adversaire 1"));
			this.add(regle);
			this.add(new JPanel());
			this.add(new BoardPanel());
			this.add(new JPanel());
			break;
		case 3:
			this.add(new JButton("Menu"));
			this.add(new JPanel());
			this.add(regle);
			this.add(new IAButton("Adversaire 1"));
			this.add(new BoardPanel());
			this.add(new IAButton("Adversaire 2"));
			break;
		default:
			this.add(new JButton("Menu"));
			this.add(new IAButton("Adversaire 2"));
			this.add(regle);
			this.add(new IAButton("Adversaire 1"));
			this.add(new BoardPanel());
			this.add(new IAButton("Aversaire 3"));
			break;
		}
	}

	/**
	 * Retourne le numéro de JComposant correspondant au numéro de l'adversaire
	 * 
	 * @param numJoueur
	 *            emplacement du joueur
	 * @return numComp numéro de JComposant correspondant au numéro de l'adversaire
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
	 * Réinitialise les coleurs de fond des IA
	 */
	public void resetCouleurIA() {
		for (int i = 1; i < this.nbJoueurs; i++)
			this.getComponent(this.getNumCompJoueur(i)).setBackground(Color.WHITE);
	}
}
