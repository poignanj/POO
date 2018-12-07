package lo02.Modele;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Outils.Outil;
import lo02.Serveur.Client;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author Jean-Jacques Poignant et Guillaume Paris
 *
 *	
 */
public class DonneesClientReseau extends Observable implements Observer {
	private Joueur client;
	private ArrayList<Integer> nbrCartes;
	private int tourDeJeu;
	private Carte talon;
	private Socket serveur;
	
	public DonneesClientReseau(Joueur j) {
		super();
		client = j;
		client.addObserver(this);
		tourDeJeu = 0;
		nbrCartes = new ArrayList<>();
		serveur = Client.Instance("127.0.0.1").getSocket();
		
	}
	
	/**
	 * Fonction qui �coute le serveur et d�code ses messages.
	 */
	public void serverListener() {
		try {
			PrintWriter writer = new PrintWriter(serveur.getOutputStream());
			BufferedInputStream reader = new BufferedInputStream(serveur.getInputStream());
			while(!serveur.isClosed()) {
				String response = "";
		        response = Outil.read(reader);
		        
		        System.out.println(response);

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
				case "PIOCHE" : pioche(splitResponse[1]);
				  				break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void poserCarte(PrintWriter writer) {
		Carte res = null;
		res = client.getPose().poser(this.talon,client.getMain());
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
		res = client.getDon().choixDonCarte(this.talon, this.client.getMain());
		writer.write("DON " + res.toString());
		writer.flush();
	}

	private void choixCouleur(PrintWriter writer) {
		int res = -1;
		res = client.getChoix().choixCouleur(talon.getCouleur(), this.client.getMain());
		writer.write("COULEUR " + res);
		writer.flush();
	}

	private void majTalon(String talon) {
		try {
			this.talon = Carte.fromString(talon);
			this.setChanged();
			this.notifyObservers(this.talon);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void majNbrCartes(int joueur, int nbrCartes) {
		this.nbrCartes.set((joueur+2) %4 , nbrCartes);
		setChanged();
		this.notifyObservers(nbrCartes);
	}

	private void majTour(int joueur) {
		this.tourDeJeu = (joueur+2) %4;
	}

	private void pioche(String carte) {
		try {
			this.talon = Carte.fromString(carte);
			//TODO Je sais pas ce que je dois faire ici '^^
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getNumJoueur() {
		return this.tourDeJeu;
	}
	
	public Humain getHumain() {
		return (Humain) this.client;
	}

	public Carte getPremiereCarteTalon() {
		return talon;
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		this.notifyObservers(arg);
	}

}
