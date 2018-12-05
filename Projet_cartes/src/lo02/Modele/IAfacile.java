/**
 * 
 */
package lo02.Modele;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 * Définit l'IA facile
 *
 */
public class IAfacile extends Joueur {

	/**
	 * Constructeur de IAfacile
	 * 
	 * @param numJoueur emplacement du joueur
	 * 
	 */
	public IAfacile(int numJoueur) {
		this.choix = new ChoixCouleurIAfacile();
		this.don = new DonIAfacile();
		this.pose = new PoseIAfacile();
		this.numJoueur = numJoueur;
	}

	/**
	 * Constructeur de IAfacile
	 * 
	 * @param choix interface correspondante au niveau de choix accordé pour le joueur
	 * @param don interface correspondante au niveau de choix accordé pour le joueur
	 * @param pose interface correspondante au niveau de choix accordé pour le joueur
	 * @param numJoueur emplacement du joueur sur la table
	 */
	public IAfacile(ChoixDeCouleur choix, DonDeCarte don, PoseDeCarte pose, int numJoueur) {
		super(choix, don, pose, numJoueur);

	}
}
