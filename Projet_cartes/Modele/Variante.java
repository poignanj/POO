package lo02.Modele;

import java.util.*;

/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 *         Définit la variante
 */
public class Variante {

	// chaque case du tableau correspond à un effet (de 0 à 9)
	/*
	 * stopper;faire piocher;contrer;changer couleur;changer sens;rejouer; sauter
	 * tour;donner carte; multicarte; gagner
	 */
	private boolean effet[] = { false, false, false, false, false, false, false, false, false, false };
	private ArrayList<Carte> stopper;
	private ArrayList<Carte> contrer;
	private ArrayList<Carte> exodia;
	private int report = 0;
	private int piocher = 0;
	private int donner = 0;

	/**
	 * Constructeur de variante
	 * 
	 * @param effet
	 *            tableau de booléens : stopper;faire piocher;contrer;changer
	 *            couleur;changer sens;rejouer; sauter tour;donner carte;
	 *            multicarte; gagner
	 */
	public Variante(boolean[] effet) {
		this.effet = effet;
		stopper = new ArrayList<>();
		contrer = new ArrayList<>();
		exodia = new ArrayList<>();
	}

	/**
	 * Affiche l'effet
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < effet.length; i++) {
			if (effet[i]) {
				switch (i) {
				case 0:
					s += "Peut stopper : ";
					if (this.stopper.isEmpty())
						s += "Tout";
					else
						for (int j = 0; j < this.stopper.size(); j++)
							s += this.stopper.get(j).toString() + ", ";
					break;
				case 1:
					s += "Fait piocher : " + this.piocher;
					break;
				case 2:
					s += "Peut contrer : ";
					if (this.contrer.isEmpty())
						s += "Tout";
					else
						for (int j = 0; j < this.contrer.size(); j++)
							s += this.contrer.get(j).toString() + ", ";
					break;
				case 3:
					s += "Peut changer de couleur";
					break;
				case 4:
					s += "Peut changer le tour";
					break;
				case 5:
					s += "Fait rejouer";
					break;
				case 6:
					s += "Fait sauter le tour";
					break;
				case 7:
					s += "permet de donner une carte à un joueur";
					break;
				default:
					s += "whatever";
					break;
				}
				// s += "\n";
				s += ";";
			}
		}
		return s;
	}

	/**
	 * Récupère l'effet
	 * 
	 * @return effet
	 */
	public boolean[] getEffet() {
		return effet;
	}

	/**
	 * Définit l'effet
	 * 
	 * @param effet
	 *            tableau de booléens : stopper;faire piocher;contrer;changer
	 *            couleur;changer sens;rejouer; sauter tour;donner carte;
	 *            multicarte; gagner
	 */
	public void setEffet(boolean effet[]) {
		this.effet = effet;
	}

	/**
	 * renvoie le nombre de cartes que celle-ci fait piocher
	 * 
	 * @return piocher nombre de cartes que celle-ci fait piocher
	 */
	public int getPiocher() {
		return piocher;
	}

	/**
	 * défini le nombre de cartes que celle-ci fait piocher
	 * 
	 * @param piocher
	 *            nombre de cartes que celle-ci fait piocher
	 */
	public void setPiocher(int piocher) {
		this.piocher = piocher;
	}

	/**
	 * Renvoie la liste des cartes dont l'effet est annulé par celle-ci
	 * 
	 * @return stopper liste des cartes dont l'effet est annulé par celle-ci
	 */
	public ArrayList<Carte> getStopper() {
		return stopper;
	}

	/**
	 * Défini la liste des cartes dont l'effet est annulé par celle-ci
	 * 
	 * @param stopper
	 *            liste des cartes dont l'effet est annulé par celle-ci
	 */
	public void setStopper(ArrayList<Carte> stopper) {
		this.stopper = stopper;
	}

	/**
	 * Renvoie la liste des cartes dont l'effet est contré par celle-ci
	 * 
	 * @return contrer liste des cartes dont l'effet est contré par celle-ci
	 */
	public ArrayList<Carte> getContrer() {
		return contrer;
	}

	/**
	 * Défini la liste des cartes dont l'effet est contré par celle-ci
	 * 
	 * @param contrer
	 *            liste des cartes dont l'effet est contré par celle-ci
	 */
	public void setContrer(ArrayList<Carte> contrer) {
		this.contrer = contrer;
	}

	/**
	 * Renvoie les autres cartes du combo gagnant
	 * 
	 * @return exodia les autres cartes du combo gagnant
	 */
	public ArrayList<Carte> getExodia() {
		return exodia;
	}

	/**
	 * Défini les autres cartes du combo gagnant
	 * 
	 * @param exodia
	 *            les autres cartes du combo gagnant
	 */
	public void setExodia(ArrayList<Carte> exodia) {
		this.exodia = exodia;
	}

	/**
	 * Renvoie la valeur du report
	 * 
	 * @return report valeur du report
	 */
	public int getReport() {
		return report;
	}

	/**
	 * Défini la valeur du report
	 * 
	 * @param report
	 *            valeur du report
	 */
	public void setReport(int report) {
		this.report = report;
	}

	/**
	 * Renvoie le nombre de cartes à donner
	 * 
	 * @return donner nombre de cartes à donner
	 */
	public int getDonner() {
		return donner;
	}

	/**
	 * Défini le nombre de cartes à donner
	 * 
	 * @param donner
	 *            nombre de cartes à donner
	 */
	public void setDonner(int donner) {
		this.donner = donner;
	}

}
