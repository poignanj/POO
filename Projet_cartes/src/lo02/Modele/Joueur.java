/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *         D�finit un joueur
 */
public abstract class Joueur extends Observable {
	protected ArrayList<Carte> main = new ArrayList<>();
	protected boolean peutJouer = true;
	protected ChoixDeCouleur choix = new ChoixCouleurIAfacile();
	protected DonDeCarte don = new DonIAfacile();
	protected PoseDeCarte pose = new PoseIAfacile();
	protected int numJoueur;

	/**
	 * Constructeur de Joueur
	 */
	public Joueur() {
	}

	/**
	 * Constructeur de Joueur
	 * 
	 * @param choix
	 *            interface choisie pour le joueur
	 * @param don
	 *            interface choisie pour le joueur
	 * @param pose
	 *            interface choisie pour le joueur
	 * @param numJoueur
	 *            emplacement du joueur
	 */
	public Joueur(ChoixDeCouleur choix, DonDeCarte don, PoseDeCarte pose, int numJoueur) {
		this.choix = choix;
		this.don = don;
		this.pose = pose;
		this.numJoueur = numJoueur;
	}

	/**
	 * R�cup�re la main du joueur
	 * 
	 * @return main
	 */
	public ArrayList<Carte> getMain() {
		return this.main;
	}

	/**
	 * Change la couleur du Talon
	 * 
	 * @param couleur
	 *            couleur voulue sur le talon
	 * @return i retourne un entier corresondant � la couleur
	 */
	public int choixCouleur(int couleur) {
		int i = this.choix.choixCouleur(couleur, this.main);
		this.setChanged();
		this.notifyObservers();
		return i;
	}

	/**
	 * Donne une carte � un joueur
	 * 
	 * @param cTalon
	 *            Carte du Talon
	 * @return c carte choisie
	 */
	public Carte choixDonCarte(Carte cTalon) {
		Carte c = this.don.choixDonCarte(cTalon, this.main);
		this.setChanged();
		this.notifyObservers();
		return c;
	}

	/**
	 * Pose une carte sur le talon
	 * 
	 * @param cTalon
	 *            Carte du Talon
	 * @return c carte choisie
	 */
	public Carte poserCarte(Carte cTalon) {
		Carte c = this.pose.poser(cTalon, this.main);
		this.setChanged();
		this.notifyObservers();
		return c;
	}

	/**
	 * R�cup�re l'interface choisie pour le joueur
	 * 
	 * @return pose interface choisie pour le joueur
	 */
	public PoseDeCarte getPose() {
		return this.pose;
	}

	/**
	 * R�cup�re l'interface choisie pour le joueur
	 * 
	 * @return don interface choisie pour le joueur
	 */
	public DonDeCarte getDon() {
		return this.don;
	}

	/**
	 * R�cup�re l'interface choisie pour le joueur
	 * 
	 * @return choix interface choisie pour le joueur
	 */
	public ChoixDeCouleur getChoix() {
		return this.choix;
	}

	/**
	 * Pioche une carte
	 * 
	 * @param c
	 *            carte � piocher
	 */
	public void piocherCarte(Carte c) {
		if (c != null)
			this.main.add(c);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Affiche la main
	 * 
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < this.main.size(); i++)
			s = s + this.main.get(i).toString() + "; ";
		return s;
	}

	/**
	 * Dire Carte
	 * 
	 * @return direCarte vrai si le joueur a dit "Carte" faux sinon
	 */
	public boolean direCarte() {
		return true;
	}

	/**
	 * Dire Contre Carte
	 * 
	 * @return direContreCarte vrai si le joueur a dit "Contre Carte" faux sinon
	 */
	public boolean direContreCarte() {
		return true;
	}

	/**
	 * Sait si le joueur peut jouer
	 * 
	 * @return peutJouer vrai si le joueur ne doit pas passer son tour faux sinon
	 */
	public boolean getPeutJouer() {
		return peutJouer;
	}

	/**
	 * D�finit si le joueur peut jouer
	 * 
	 * @param peutJouer
	 *            vrai si le joueur ne doit pas passer son tour faux sinon
	 */
	public void setPeutJouer(boolean peutJouer) {
		this.peutJouer = peutJouer;
	}

	/**
	 * R�cup�re le num�ro du joueur
	 * 
	 * @return numJoueur emplacement du joueur
	 */
	public int getNumJoueur() {
		return this.numJoueur;
	}
}
