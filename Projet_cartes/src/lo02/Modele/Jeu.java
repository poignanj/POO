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
 *         Définit la partie et le jeu
 */
public class Jeu extends Observable {
	private final int NBCARTEMAINDEPART = 7;
	private String nom;
	private String regles;
	private Table plateau;
	private ArrayList<Joueur> listeJoueurs;
	private int sens = 1;
	int tour;

	/**
	 * Constructeur de Jeu
	 */
	public Jeu() {
		this.nom = "Partie de Test";
		this.regles = "whatever";
		this.listeJoueurs = new ArrayList<Joueur>();
		this.plateau = new Table();
		this.listeJoueurs.add(new Humain(0));
		for (int i = 0; i < 2; i++)
			this.listeJoueurs.add(new IAfacile(i + 1));
		this.listeJoueurs.add(new IAniveau1(3));

	}

	/**
	 * Initialise la partie
	 */
	private void initialiserPartie() {
		plateau.melanger();
		for (int j = 0; j < NBCARTEMAINDEPART; j++) {
			for (int i = 0; i < listeJoueurs.size(); i++) {
				listeJoueurs.get(i).piocherCarte(plateau.piocher());
			}
		}
		// inutile au 8 américain (rêgles française)
		plateau.ajoutCarteTalon(plateau.piocher());
	}

	/**
	 * Permet de Jouer Lance une partie, s'arrête lors de la victoire d'un des
	 * joueurs. Ne compte pas les points.
	 */
	public void jouer() {
		this.initialiserPartie();
		tour = new Random().nextInt(listeJoueurs.size());
		this.setChanged();
		this.notifyObservers(tour);
		boolean fin = false;
		int paye = 0;
		while (!fin) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			paye = tourJoueur(tour, paye);
			tour = (((tour + sens) % this.listeJoueurs.size()) + this.listeJoueurs.size()) % this.listeJoueurs.size();
			this.setChanged();
			this.notifyObservers(tour);
			fin = (paye == -1);

		}
		System.out.println("Joueur "
				+ ((((tour - sens) % this.listeJoueurs.size()) + this.listeJoueurs.size()) % this.listeJoueurs.size())
				+ " a gagné la partie !");
	}

	/**
	 * Définit le tour d'un joueur
	 * 
	 * @param joueur
	 *            numéro du joueur actuel
	 * @param paiement
	 *            somme de carte à "payer" actuelle
	 * @return paiement somme de carte à "payer" à la fin du tour
	 */
	private int tourJoueur(int joueur, int paiement) {
		if (this.listeJoueurs.get(joueur).getPeutJouer()) {
			Carte cTalon = plateau.getPremiereCarteTalon();
			System.out.println("------------------\n" + this.toString());
			System.out.println("Le paiement est de : " + paiement);
			Carte cJoueur = listeJoueurs.get(joueur).poserCarte(cTalon);
			if (cJoueur != null)
				System.out.println("Joueur " + joueur + " : " + cJoueur.toString());
			if (cJoueur == null && paiement > 0) {
				for (int i = 0; i < paiement; i++)
					this.listeJoueurs.get(joueur).piocherCarte(this.plateau.piocher());
				paiement = 0;
			} else if (cJoueur == null)
				this.listeJoueurs.get(joueur).piocherCarte(this.plateau.piocher());
			else {

				// Traitement des effets de carte
				if (paiement > 0) {
					if (cJoueur.getVar().getEffet()[0] && (cJoueur.getVar().getStopper().contains(cTalon)
							|| cJoueur.getVar().getStopper().isEmpty())) {
						paiement = 0;
						this.plateau.ajoutCarteTalon(cJoueur);
					} else if (cJoueur.getVar().getEffet()[2] && cJoueur.getVar().getContrer().contains(cTalon)) {
						paiement += cJoueur.getVar().getReport();
						this.plateau.ajoutCarteTalon(cJoueur);
					} else {
						System.out.println("Pas de contre...");
						this.listeJoueurs.get(joueur).piocherCarte(cJoueur);
						for (int i = 0; i < paiement; i++)
							this.listeJoueurs.get(joueur).piocherCarte(this.plateau.piocher());
						paiement = 0;
					}
				} else {
					this.plateau.ajoutCarteTalon(cJoueur);
					if (this.listeJoueurs.get(joueur).main.size() != 0) {
						if (cJoueur.getVar().getEffet()[1])
							paiement += cJoueur.getVar().getPiocher();
						if (cJoueur.getVar().getEffet()[3])
							this.plateau.getPremiereCarteTalon()
									.setModCouleur(changerCouleur(this.listeJoueurs.get(joueur), cTalon.getCouleur()));
						if (cJoueur.getVar().getEffet()[4])
							this.sens = -this.sens;
						if (cJoueur.getVar().getEffet()[6])
							this.listeJoueurs
									.get((((joueur + sens) % this.listeJoueurs.size()) + this.listeJoueurs.size())
											% this.listeJoueurs.size())
									.setPeutJouer(false);
						if (cJoueur.getVar().getEffet()[7])
							this.donnerCarte(joueur);
						if (cJoueur.getVar().getEffet()[8])
							this.jouerCouleur(this.listeJoueurs.get(joueur));
						if (cJoueur.getVar().getEffet()[9])
							if (this.exodia(this.listeJoueurs.get(joueur), cJoueur))
								paiement = -1;
						if (cJoueur.getVar().getEffet()[5])
							tourJoueur(joueur, paiement);
					}
				}
			}
			if (this.listeJoueurs.get(joueur).main.isEmpty())
				paiement = -1;
		} else
			this.listeJoueurs.get(joueur).setPeutJouer(true);
		return paiement;

	}

	/**
	 * Permet de changer la couleur du Talon
	 * 
	 * @param j
	 *            joueur actuel
	 * @param couleurActuelle
	 *            couleur Actuelle
	 * @return couleur entier symbolisant le choix du joueur
	 */
	private int changerCouleur(Joueur j, int couleurActuelle) {
		return j.choixCouleur(couleurActuelle);
	}

	/**
	 * Définit une condition d'arrêt du jeu Cette fonction n'est appelée que si un
	 * joueur possède la combinaison de cartes gagnant la partie le cas échéant. Le
	 * nom est une référence à un célèbre jeu de cartes japonais
	 * 
	 * @param joueur
	 *            joueur actuel
	 * @param c
	 *            carte à verifier
	 * @return Boolean si vrai alors le joueur gagne sinon rien
	 */
	private boolean exodia(Joueur joueur, Carte c) {
		ArrayList<Carte> tmp = new ArrayList<>();
		tmp.addAll(joueur.main);
		tmp.add(c);
		return tmp.containsAll(c.getVar().getExodia());
	}

	/**
	 * Donne une carte au joueur suivant
	 * 
	 * @param joueur
	 *            joueur actuel
	 */
	private void donnerCarte(int joueur) {
		this.listeJoueurs
				.get((((joueur + sens) % this.listeJoueurs.size()) + this.listeJoueurs.size())
						% this.listeJoueurs.size())
				.piocherCarte(this.listeJoueurs.get(joueur).choixDonCarte(this.plateau.getPremiereCarteTalon()));
	}

	/**
	 * Fonction non encore implémentée. Permet à un joueur de poser toutes les
	 * cartes de la couleur choisie.
	 * 
	 * @param joueur
	 *            joueur actuel
	 */
	private void jouerCouleur(Joueur joueur) {

	}

	/**
	 * Récupère le nom de la partie
	 * 
	 * @return nom nom de la partie
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Donne le nom à la partie
	 * 
	 * @param nom
	 *            nom de la partie
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Recupère les règles du jeu
	 * 
	 * @return regles règles du jeu sous forme textuelle
	 */
	public String getRegles() {
		return regles;
	}

	/**
	 * Définit les règles
	 * 
	 * @param regles
	 *            règles du jeu sous forme textuelle
	 */
	public void setRegles(String regles) {
		this.regles = regles;
	}

	/**
	 * renvoie la liste des joueurs et le nombre de cartes en main ainsi que le
	 * plateau
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < this.listeJoueurs.size(); i++)
			s = s + i + " : " + this.listeJoueurs.get(i).main.size() + " cartes \n";
		s += this.plateau.toString();
		return s;
	}

	/**
	 * Récupère le plateau
	 * 
	 * @return plateau Pioche+Talon
	 */
	public Table getTable() {
		return this.plateau;
	}

	/**
	 * Définit la liste des joueurs
	 * 
	 * @return listeJoueurs ArrayList contenant les joueurs dans leur ordre de création
	 */
	public ArrayList<Joueur> getListeJoueurs() {
		return this.listeJoueurs;
	}
}
