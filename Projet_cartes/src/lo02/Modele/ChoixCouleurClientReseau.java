package lo02.Modele;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Outils.Outil;
import lo02.Serveur.Serveur;

/**
 * @author Guillaume Paris
 *
 *	Permet de choisir la couleur que le joueur en réseau veut jouer
 */

public class ChoixCouleurClientReseau implements ChoixDeCouleur {

	@Override
	/**
	 * Permet de choisir la couleur que le joueur en réseau veut jouer
	 * Attend une réponse du client

	 * @param couleur la couleur que l'on ne peut choisir
	 * @param main la main du joueur qui doit choisir
	 * @return c la couleur renvoyée
	 */
	public int choixCouleur(int couleur, ArrayList<Carte> main) {
		Socket sock = Serveur.Instance().getClient();
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("COULEUR " + couleur + "/");
			writer.flush();

            response = Outil.read(reader);
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
		
		int res = -1;
		
		//Si la réponse du client serveur n'est pas valide, on redemande
		try {
			String[] splitResponse = response.split(" ");
			if(splitResponse[0].equals("COULEUR"))
				res = Integer.parseInt(splitResponse[1]);
			else
				res = choixCouleur(couleur, main);
		}catch(Exception e) {
			res = choixCouleur(couleur, main);
		}
		
		return res;
	}
}
