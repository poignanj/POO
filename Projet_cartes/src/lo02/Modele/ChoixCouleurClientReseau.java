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
 *	Permet de choisir la couleur que le joueur en r�seau veut jouer
 */

public class ChoixCouleurClientReseau implements ChoixDeCouleur {
	private Socket sock;
	
	public ChoixCouleurClientReseau(Socket sock)
	{
		this.sock = sock;
	}

	@Override
	/**
	 * Permet de choisir la couleur que le joueur en r�seau veut jouer
	 * Attend une r�ponse du client

	 * @param couleur la couleur que l'on ne peut choisir
	 * @param main la main du joueur qui doit choisir
	 * @return c la couleur renvoy�e
	 */
	public int choixCouleur(int couleur, ArrayList<Carte> main) {
		Socket sock = null;
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("COULEUR " + couleur);
			writer.flush();

            response = Outil.read(reader);
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
		
		int res = -1;
		
		//Si la r�ponse du client serveur n'est pas valide, on redemande
		try {
			String[] splitResponse = response.split(" ");
			if(splitResponse[0] == "COULEUR")
				res = Integer.parseInt(splitResponse[1]);
			else
				res = choixCouleur(couleur, main);
		}catch(Exception e) {
			res = choixCouleur(couleur, main);
		}
		
		return res;
	}
}
