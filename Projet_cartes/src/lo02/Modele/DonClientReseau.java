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
 * Permet au client réseau de choisir quelle carte il va donner
 *
 */
public class DonClientReseau implements DonDeCarte {
	private Socket sock;
	
	public DonClientReseau(Socket sock) {
		this.sock = sock;
	}

	@Override
	/**
	 * Permet de choisir quelle carte le client réseau va donner
	 * 
	 * @param cTalon la carte du talon
	 * @param main la main du joueur actuel
	 * 
	 * @return carte la carte choisie
	 */
	public Carte choixDonCarte(Carte cTalon, ArrayList<Carte> main) {
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("DON");
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
			if(splitResponse[0] == "DON")
				res = Carte.fromString(splitResponse[1]);
			else
				res = choixDonCarte(cTalon, main);
		}catch(Exception e) {
			res = choixDonCarte(cTalon, main);
		}
		
		return res;
	}
}
