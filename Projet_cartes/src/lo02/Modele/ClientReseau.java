package lo02.Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

import Outils.Outil;

/**
 * @author Guillaume Paris
 *
 * Définit le joueur client réseau
 */

public class ClientReseau extends Joueur {

	private Socket sock;
	
	/**
	 * Constructeur de la classe ClientReseau
	 * 
	 * @param numJoueur emplacement du joueur
	 */
	public ClientReseau(int numJoueur, Socket sock) {
		this.sock = sock;
		this.choix = new ChoixCouleurClientReseau(sock);
		this.don = new DonClientReseau(sock);
		this.pose = new PoseClientReseau(sock);
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
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("PIOCHE " + c.toString());
			writer.flush();
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
	}
}
