package lo02.Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Outils.Outil;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * 
 * @author Jean-Jacques Poignant et Guillaume Paris
 *
 *	
 */
public class DonneesClientReseau implements Observable {
	private ArrayList<Carte> cartes;
	private ArrayList<Integer> nbrCartes;
	private Carte talon;
	private Socket serveur;
	
	public DonneesClientReseau() {
		cartes = new ArrayList<>();
		nbrCartes = new ArrayList<>();
		for(int i = 0; i < 4; i++)
		{
			nbrCartes.add(7);
		}
		//TODO
	}

	@Override
	public void addListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeListener(InvalidationListener arg0) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Fonction qui écoute le serveur et décode ses messages.
	 */
	public void serverListener() {
		try {
			PrintWriter writer = new PrintWriter(serveur.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(serveur.getInputStream());
			while(!serveur.isClosed()) {
				String response = "";
		        response = Outil.read(reader);

				String[] splitResponse = response.split(" ");
				
				switch(splitResponse[0]) {
				case "POSE" : poserCarte(writer);
							  break;
				case "DON" : donnerCarte(writer);
							 break;
				case "COULEUR" : choixCouleur(writer);
								 break;
				case "TALON" : majTalon(splitResponse[1]);
							   break;
				case "NBRCARTES" : majNbrCartes(Integer.parseInt(splitResponse[1]), Integer.parseInt(splitResponse[2]));
								   break;
				case "TOUR" : majTour(Integer.parseInt(splitResponse[1]));
							  break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void poserCarte(PrintWriter writer) {
		Carte res = null;
		// TODO Choisir la carte
		if(res == null)
		{
			writer.write("PIOCHE");
			writer.flush();
		}
		else
		{
			writer.write("POSE " + res.toString());
			writer.flush();
		}
	}

	private void donnerCarte(PrintWriter writer) {
		Carte res = null;
		// TODO Choisir la carte
		writer.write("DON " + res.toString());
		writer.flush();
	}

	private void choixCouleur(PrintWriter writer) {
		int res = -1;
		// TODO Choisir la couleur
		writer.write("COULEUR " + res);
		writer.flush();
	}

	private void majTalon(String talon) {
		try {
			this.talon = Carte.fromString(talon);
		} catch (Exception e) {
			// TODO On fait quoi quand le string de la carte n'est pas valide? En théorie ça arrivera pas on peut ignorer
			e.printStackTrace();
		}
	}

	private void majNbrCartes(int joueur, int nbrCartes) {
		this.nbrCartes.set(joueur, nbrCartes);		
	}

	private void majTour(int joueur) {
		// TODO Auto-generated method stub
	}
}
