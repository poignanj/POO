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
		tourDeJeu = 2;
		nbrCartes = new ArrayList<>();
		for(int i = 0; i < 4; i++)
			nbrCartes.add(0);
		serveur = Client.Instance().getSocket();
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

				String[] messages = response.split("/");
				for(int i = 0; i < messages.length; i++)
				{
			        System.out.println("CLIENT " + messages[i]);

					String[] splitResponse = messages[i].split(" ");
					
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
		int j = joueur;
		this.nbrCartes.set(j , nbrCartes);
		setChanged();
		this.notifyObservers(10 * nbrCartes + j);
	}

	private void majTour(int joueur) {
		this.tourDeJeu = joueur;
		setChanged();
		this.notifyObservers((float) joueur);
	}

	private void pioche(String carte) {
		try {
			Carte c = Carte.fromString(carte);
			this.getHumain().getMain().add(c);
			setChanged();
			this.notifyObservers();
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
