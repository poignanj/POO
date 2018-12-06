package lo02.Modele;

/**
 * @author Guillaume Paris
 *
 * Définit le joueur client réseau
 */

public class ClientReseau extends Joueur {

	/**
	 * Constructeur de la classe ClientReseau
	 * 
	 * @param numJoueur emplacement du joueur
	 */
	public ClientReseau(int numJoueur) {
		this.choix = new ChoixCouleurClientReseau();
		this.don = new DonClientReseau();
		this.pose = new PoseClientReseau();
		this.numJoueur = numJoueur;
	}

	/**
	 * Constructeur de la classe ClientReseau
	 * 
	 * @param choix interface correspondante au niveau de choix accordé pour le joueur
	 * @param don interface correspondante au niveau de choix accordé pour le joueur
	 * @param pose interface correspondante au niveau de choix accordé pour le joueur
	 * @param numJoueur emplacement du joueur sur la table
	 */
	public ClientReseau(ChoixDeCouleur choix, DonDeCarte don, PoseDeCarte pose, int numJoueur) {
		super(choix, don, pose, numJoueur);
	}
}
