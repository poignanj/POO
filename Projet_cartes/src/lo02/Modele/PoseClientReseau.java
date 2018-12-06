package lo02.Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Outils.Outil;

/**
 * @author Guillaume Paris
 *
 *         Permet � un client r�seau de poser une carte
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
		Socket sock = null;
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("POSE");
			writer.flush();

            response = Outil.read(reader);
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
         }catch (IOException e) {
            e.printStackTrace();
         }
		
		Carte res = null;
		
		//Si la r�ponse du client serveur n'est pas valide, on consid�re qu'il pioche
		try {
			String[] splitResponse = response.split(" ");
			if(splitResponse[0] == "POSE")
				res = Carte.fromString(splitResponse[1]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
