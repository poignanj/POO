package lo02.Serveur;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

import lo02.Modele.Carte;
import lo02.Modele.Jeu;
import lo02.Modele.Joueur;
import lo02.Modele.Table;

/**
 * 
 * @author Guillaume Paris
 *
 *	Prévient le client réseau des mise à jour du jeu
 */
public class ObserverUpdateClient implements Observer {
	@Override
	/**
	 * Met à jour les éléments le nécessitant quand ceux-ci s'update.
	 */
	public void update(Observable o, Object arg) {
		if (o instanceof Joueur) {
			this.majNbrCartes(((Joueur) o).getNumJoueur(), ((Joueur) o).getMain().size());
		}
		if (o instanceof Table) {
			this.majTalon(((Table) o).getPremiereCarteTalon());
		}
		if (o instanceof Jeu) {
			this.majTourJoueur((int) arg);
		}
	}

	/**
	 * Envoie au client la mise à jour du nombre de cartes d'un joueur
	 * @param numJoueur joueur qu'on met à jour
	 * @param nbrCartes nombre de carte du joueur
	 */
	private void majNbrCartes(int numJoueur, int nbrCartes) {
		Socket sock = Serveur.Instance().getClient();
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			
			writer.write("NBRCARTES " + numJoueur + " " + nbrCartes);
			writer.flush();
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
	}

	/**
	 * Envoie au client la mise à jour de la carte du talon
	 * @param premiereCarteTalon carte du talon
	 */
	private void majTalon(Carte premiereCarteTalon) {
		Socket sock = Serveur.Instance().getClient();
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			
			writer.write("TALON " + premiereCarteTalon.toString());
			writer.flush();
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
	}

	/**
	 * Envoie au client le joueur dont c'est le tour
	 * @param joueur joueur dont c'est le tour
	 */
	private void majTourJoueur(int joueur) {
		Socket sock = Serveur.Instance().getClient();
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			
			writer.write("TOUR " + joueur);
			writer.flush();
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
	}

}
