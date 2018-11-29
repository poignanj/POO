/**
 * 
 */
package lo02.Modele;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 *         définit la Table de jeu
 */
public class Table extends Observable {
	private int nbCarte;
	private ArrayList<Carte> pioche;
	private ArrayList<Carte> talon;

	/**
	 * Constructeur de table
	 */
	public Table() {
		this.pioche = new ArrayList<Carte>();
		this.talon = new ArrayList<Carte>();
		this.nbCarte = 0;
		for (int j = 1; j <= 13; j++) {
			for (int i = 0; i < 4; i++) {
				this.pioche.add(new Carte(j, i));
				this.nbCarte++;
			}
		}
		this.pioche.add(new Carte(0, 4));
		this.pioche.add(new Carte(0, 5));
		this.nbCarte += 2;
		this.initVariante();
	}

	/**
	 * Affiche la pioche et le talon
	 * 
	 */
	public String toString() {
		String s = "";
		/*
		 * s += "Pioche :\n"; for (int i = 0; i < this.pioche.size(); i++) s += i +
		 * " : " + this.pioche.get(i).toString() + " : " +
		 * this.pioche.get(i).getVar().toString() + "\n";
		 */
		s += "Pioche : " + this.pioche.size() + " ; ";
		s += "Talon : " + this.getPremiereCarteTalon().toString();
		return s;
	}

	/**
	 * Cette méthode sert à initialiser la variante du jeu
	 * 
	 * 
	 */
	public void initVariante() {
		for (int i = 0; i < this.pioche.size(); i++) {
			boolean[] tabVar = { false, false, false, false, false, false, false, false, false, false };
			switch (this.pioche.get(i).getValeur()) {
			case 0:
				tabVar[0] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				this.pioche.get(i).getVar().setStopper(new ArrayList<Carte>()); // Exception : si arraylist vide,
																				// toutes les cartes sont
																				// concernées
				break;
			case 1:
				tabVar[1] = true;
				tabVar[2] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				this.pioche.get(i).getVar().setReport(2);
				this.pioche.get(i).getVar().setContrer(new ArrayList<Carte>());
				for (int j = 0; j < this.pioche.size(); j++) {
					if (this.pioche.get(j).getValeur() == 1)
						this.pioche.get(i).getVar().getContrer().add(this.pioche.get(j));
				}
				this.pioche.get(i).getVar().setPiocher(4);
				break;
			case 5:
				tabVar[7] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				break;
			case 7:
				tabVar[6] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				break;
			case 8:
				tabVar[0] = true;
				tabVar[3] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				this.pioche.get(i).getVar().setStopper(new ArrayList<Carte>());
				for (int j = 0; j < this.pioche.size(); j++) {
					if (this.pioche.get(j).getValeur() == 1 || this.pioche.get(j).getValeur() == 0)
						this.pioche.get(i).getVar().getStopper().add(this.pioche.get(j));
				}
				break;
			case 10:
				tabVar[5] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				break;
			case 11:
				tabVar[4] = true;
				this.pioche.get(i).setVariante(new Variante(tabVar));
				break;
			default:
				this.pioche.get(i).setVariante(new Variante(tabVar));
			}
		}
	}

	/**
	 * Mélange le Talon
	 */
	public void melanger() {
		Random rand = new Random();
		ArrayList<Carte> temp = new ArrayList<Carte>();
		int stop = pioche.size();
		for (int i = 0; i < stop; i++) {
			int t = rand.nextInt(pioche.size());
			temp.add(pioche.get(t));
			pioche.remove(t);
		}
		pioche = temp;
	}

	/**
	 * Récupère le nombre de carte
	 * 
	 * @return nbCarte le nombre de cartes total du jeu utilisé
	 */
	public int getNbCarte() {
		return nbCarte;
	}

	/**
	 * Définit le nombre de carte du jeu
	 * 
	 * @param nbCarte
	 *            le nombre de cartes total du jeu utilisé
	 */
	public void setNbCarte(int nbCarte) {
		this.nbCarte = nbCarte;
	}

	/**
	 * Renvoie la première carte de la pioche, mélange le talon et la pioche si
	 * besoin.
	 * 
	 * @return carte première carte de la pioche
	 */
	public Carte piocher() {
		boolean canDraw = true;
		if (pioche.size() < 1 && talon.size() > 1) {
			remplirPioche();
			canDraw = false;
		}
		if (canDraw && !pioche.isEmpty())
			return pioche.remove(pioche.size() - 1);
		else
			return null;
	}

	/**
	 * Récupère la première carte du talon
	 * 
	 * @return carte première carte du talon
	 */
	public Carte getPremiereCarteTalon() {
		if (talon.isEmpty())
			return null;
		else
			return talon.get(talon.size() - 1);
	}

	/**
	 * Ajoute une carte sur le Talon
	 * 
	 * @param c  une Carte
	 */
	public void ajoutCarteTalon(Carte c) {
		talon.add(c);
		this.setChanged();
		this.notifyObservers(this);
	}

	/**
	 * Rempli la pioche
	 */
	private void remplirPioche() {
		Carte top = talon.remove(talon.size() - 1);
		int stop = talon.size();
		for (int i = 0; i < stop; i++) {
			talon.get(i).setModCouleur(talon.get(i).getCouleur());
			pioche.add(talon.get(i));
		}
		talon.clear();
		talon.add(top);
		this.melanger();
	}
}
