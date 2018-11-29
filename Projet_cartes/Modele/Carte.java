package lo02.Modele;

/**
 * @author Jean-Jacques Poignant et Elise Poignant 
 * 
 * La classe carte permet de définir les cartes
 */
public class Carte {
	private int valeur;
	private int couleur;
	private int modCouleur;
	private Variante var;
	private static String[] VALEURS = { "joker", "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen",
			"king" };
	private static String[] COULEURS = { "diamonds", "spades", "hearts", "clubs", "red", "black" };

	/**
	 * Consrtucteur de carte
	 */
	public Carte() {
		this.valeur = 0;
		this.couleur = 0;
	}

	/**
	 * Constructeur de carte avec les paramètre valeur et couleur
	 * 
	 * @param v voir selon VALEURS[]
	 * @param c voir selon COULEURS[]
	 */
	public Carte(int v, int c) {
		this.valeur = v;
		this.couleur = c;
	}

	/**
	 * Constructeur qui permet d'associer à une carte son pouvoir dans la variante
	 * 
	 * @param c Carte à copier
	 */
	public Carte(Carte c) {
		this.valeur = c.valeur;
		this.couleur = c.couleur;
		this.var = c.var;
	}

	/**
	 * Retourne la valeur de la carte
	 * 
	 * @return valeur voir VALEURS[]
	 */
	public int getValeur() {
		return valeur;
	}

	/**
	 * Donne une nouvelle valeur à la carte
	 * 
	 * @param valeur  voir VALEURS[]
	 */
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	/**
	 * Récupère la couleur de la carte
	 * 
	 * @return couleur  voir COULEURS[]
	 */
	public int getCouleur() {
		return couleur + modCouleur;
	}

	/**
	 * Donne une nouvelle couleur à la carte
	 * 
	 * @param couleur  voir COULEURS[]
	 */
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	/**
	 * Affiche la carte dans la console sous la forme "10_of_Carreau"
	 */
	public String toString() {
		return Carte.VALEURS[this.valeur] + "_of_" + Carte.COULEURS[this.couleur];
	}

	/**
	 * renvoie la variante
	 * 
	 * @return var Variante, définit l'effet d'une carte
	 */
	public Variante getVar() {
		return this.var;
	}

	/**
	 * Méthode qui permet de savoir si la carte est jouable en fonction de la carte
	 * qui est sur la pile
	 * 
	 * @param c Carte de référence
	 * @return estJouableSur vrai si l'instance est jouable sur c
	 */
	public boolean estJouableSur(Carte c) {
		boolean estJouable = false;

		estJouable = (c.getCouleur() == this.couleur || c.getCouleur() > 3 || this.couleur > 3)
				|| c.getValeur() == this.valeur || this.getVar().getStopper().contains(c)
				|| this.getVar().getContrer().contains(c);
		return estJouable;
	}

	/**
	 * Définit la variante
	 * 
	 * @param var Variante, définit l'effet d'une carte
	 */
	public void setVariante(Variante var) {
		this.var = var;
	}

	/**
	 * Dans le cas d'une carte pouvant changer la couleur jouée, retourne la couleur choisie après coup.
	 * 
	 * @return modCouleur Voir COULEURS[]
	 */
	public int getModCouleur() {
		return modCouleur;
	}

	/**
	 * Spécifie la nouvelle couleur de la carte lors d'un changement de couleur
	 * 
	 * @param modCouleur Voir COULEURS[]
	 */
	public void setModCouleur(int modCouleur) {
		this.modCouleur = modCouleur - this.couleur;
	}

}
