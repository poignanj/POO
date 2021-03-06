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
		Socket sock = Serveur.Instance().getClient();
		String response = "";
		
		try {
			PrintWriter writer = new PrintWriter(sock.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(sock.getInputStream());
			
			writer.write("POSE/");
			writer.flush();

            response = Outil.read(reader);
            System.out.println("SERVEUR " + response);
		 }catch(SocketException e){
            System.err.println("LA CONNEXION A ETE INTERROMPUE !");
         }catch (IOException e) {
            e.printStackTrace();
         }
		
		Carte res = null;
		
		//Si la r�ponse du client serveur n'est pas valide, on redemande
		try {
			String[] splitResponse = response.split(" ");
			if(splitResponse[0].equals("POSE")) {
				res = Carte.fromString(splitResponse[1]);
			}
			else if(splitResponse[0].equals("PIOCHE"))
			{}
			if(res != null && !main.get(getHandCard(res, main)).estJouableSur(cTalon)) {
				System.out.println(main.get(getHandCard(res, main)).estJouableSur(cTalon));
				res = poser(cTalon, main);
			}
		}catch(Exception e) {
			res = poser(cTalon, main);
		}
		if(res != null)
			return main.remove(getHandCard(res, main));
		else
			return null;
		}
		private int getHandCard(Carte c,ArrayList<Carte> main) {
			for(Carte ca : main) {
				if(c.getCouleur() == ca.getCouleur() && ca.getValeur()==c.getValeur()) return main.indexOf(ca);
			}
			return -1;
		}
}

