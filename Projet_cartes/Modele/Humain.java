/**
 * 
 */
package lo02.Modele;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 * Définit le joueur humain
 */
public class Humain extends Joueur {

	/**
	 * Constructeur de la classe humain
	 * 
	 * @param numJoueur emplacement du joueur
	 */
	public Humain(int numJoueur) {
		this.choix = new ChoixCouleurHumainIHM();
		this.don = new DonHumainIHM();
		this.pose = new PoseHumainIHM();
		this.numJoueur = numJoueur;
	}

	/**
	 * Constructeur de la classe humain
	 * 
	 * @param choix interface correspondante au niveau de choix accordé pour le joueur
	 * @param don interface correspondante au niveau de choix accordé pour le joueur
	 * @param pose interface correspondante au niveau de choix accordé pour le joueur
	 * @param numJoueur emplacement du joueur sur la table
	 */
	public Humain(ChoixDeCouleur choix, DonDeCarte don, PoseDeCarte pose, int numJoueur) {
		super(choix, don, pose, numJoueur);

	}
}
