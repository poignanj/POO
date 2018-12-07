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
 *         Permet à un client réseau de poser une carte
 *
 */

public class PoseClientReseau implements PoseDeCarte {
	@Override
	/**
	 * Pose une carte sur le talon
	 * 
	 * @param cTalon
	 *            carte du talon
	 * @param main
	 *            main du joueur
	 * 
	 * @return carte carte choisie
	 */
	public Carte poser(Carte cTalon, ArrayList<Carte> main) {
		Socket sock = Serveur.Instance().getClient();
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("POSE/");
			writer.flush();

            response = Outil.read(reader);
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
		
		Carte res = null;
		
		//Si la réponse du client serveur n'est pas valide, on redemande
		try {
			String[] splitResponse = response.split(" ");
			if(splitResponse[0] == "PIOCHE")
			{}
			else if(splitResponse[0] == "POSE")
				res = Carte.fromString(splitResponse[1]);
			else
				res = poser(cTalon, main);
		}catch(Exception e) {
			res = poser(cTalon, main);
		}
		return res;
	}
}

