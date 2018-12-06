package lo02.Vue;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 */
public class IAButton extends JButton {
	int nbCartes;
	String nom;
	/**
	 * Setter pour le nombre de cartes à afficher
	 * @param nbCartes nombre de cartes à afficher
	 */
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}
	/**
	 * Constructeur nommant le bouton
	 * @param nom nom de l'adversaire
	 */
	public IAButton(String nom) {
		super(nom);
		this.nbCartes = 0;
		this.nom = nom;
		this.setFocusPainted(false);
		this.setFont(new Font("Comic sans", Font.BOLD, 12));
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
		this.setEnabled(false);
	}
	/**
	 * Constructeur nommant et indiquant le nombre de cartes en main
	 * @param nom nom de l'adversaire
	 * @param nbCartes nombre de cartes dans la main de l'adversaire
	 */
	public IAButton(String nom, int nbCartes) {
		super(nom + " : " + nbCartes + " Cartes" );
		this.nbCartes = nbCartes;
		this.nom = nom;
		this.setFocusPainted(false);
		this.setFont(new Font("Comic sans", Font.BOLD, 12));
		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);
		this.setEnabled(false);
	}
}
