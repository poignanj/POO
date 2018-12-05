/**
 * 
 */
package lo02.Modele;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 * Définit l'IAniveau 1
 *
 */
public class IAniveau1 extends Joueur {

	/**
	 * Constructeur de IAniveau1
	 * 
	 * @param numJoueur emplacement du joueur
	 */
	public IAniveau1(int numJoueur) {
		this.choix = new ChoixCouleurIAniveau1();
		this.don = new DonIAniveau1();
		this.pose = new PoseIAniveau1();
		this.numJoueur = numJoueur;
	}

	/**
	 * Constructeur IAniveau1
	 * 
	 * @param choix interface correspondante au niveau de choix accordé pour le joueur
	 * @param don interface correspondante au niveau de choix accordé pour le joueur
	 * @param pose interface correspondante au niveau de choix accordé pour le joueur
	 * @param numJoueur emplacement du joueur sur la table
	 */
	public IAniveau1(ChoixDeCouleur choix, DonDeCarte don, PoseDeCarte pose, int numJoueur) {
		super(choix, don, pose, numJoueur);
	}

}
