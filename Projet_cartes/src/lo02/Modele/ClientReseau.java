package lo02.Modele;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import lo02.Serveur.Serveur;


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

	/**
	 * Pioche une carte
	 * 
	 * @param c
	 *            carte à piocher
	 */
	public void piocherCarte(Carte c) {
		super.piocherCarte(c);
		Serveur s = Serveur.Instance();
		Socket sock = s.getClient();
		if (s ==null) {
			System.out.println("serveur NULL");
		}
		if (sock==null) {
			System.out.println("Client NULL");
		}
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			
			writer.write("PIOCHE " + c.toString());
			writer.flush();
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
	}
}
