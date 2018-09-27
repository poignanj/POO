package batailleNavale;

import java.util.ArrayList;

import outils.*;

public class Carte {
	final static int TAILLE = 10;
	private int carte[][] = new int[Carte.TAILLE][Carte.TAILLE]; // outil graphique
	private ArrayList<Bateau> flotte = new ArrayList<Bateau>();
	private PlacerNavire placer;
	public SelectionnerNavire selection;
	private FaireFeu faireFeu;
	private DeplacerNavire deplacer;

	public Carte(PlacerNavire placer, SelectionnerNavire selection, FaireFeu faireFeu, DeplacerNavire deplacer) {
		// ajouter les interfaces
		this.placer = placer;
		this.selection = selection;
		this.faireFeu = faireFeu;
		this.deplacer = deplacer;

		// Créer et placer la flotte
		flotte.add(new PorteAvion());
		flotte.add(new ContreTorpilleur());
		flotte.add(new SousMarin());
		flotte.add(new Torpilleur());
		flotte.add(new Croiseur());
		for (int i = 0; i < Carte.TAILLE; i++)
			for (int j = 0; j < Carte.TAILLE; j++)
				this.carte[i][j] = 0;
		this.placerFlotte();
	}

	public void placerFlotte() {
		// Parcours de la flotte et placement à chaque itération. Methode selon
		// l'interface
		for (int i = 0; i < this.flotte.size(); i++) {
			System.out.println("\n\n\n");
			System.out.println(this.toString());
			Tuple placement = this.placer.placer(this.flotte.get(i));
			this.flotte.get(i).setPosition(placement);
			this.majCarte();
		}
	}

	private void majCarte() {
		// mise à jour du plateau pour visualisation externe
		for (int i = 0; i < Carte.TAILLE; i++)
			for (int j = 0; j < Carte.TAILLE; j++)
				this.carte[i][j] = 0;
		for (int k = 0; k < flotte.size(); k++)
			if (flotte.get(k).getPosition() != null)
			{
				if (flotte.get(k).getPosition().debut.x == flotte.get(k).getPosition().fin.x)
					for (int i = flotte.get(k).getPosition().debut.y; i <= flotte.get(k).getPosition().fin.y; i++)
						carte[flotte.get(k).getPosition().debut.x][i] = k + 1;
				else
					for (int i = flotte.get(k).getPosition().debut.x; i <= flotte.get(k).getPosition().fin.x; i++)
						carte[i][flotte.get(k).getPosition().debut.y] = k + 1;
			}
	}

	public Coordonnee tirer(Coordonnee c) {
		// recevoir le tir de l'autre joueur puis tirer. Methode faisant jouer le joueur
		// en question.
		//Renvoie la coordonnée du tir du joueur en cours ou null si le joueur n'a plus de bateaux
		
		//TODO: interfacer l'output
		System.out.println(this.toString());
		if (!this.recevoirTir(c)) {
			this.deplacer.deplacer(this.selection.selectionner(this.flotte, "déplacer"), flotte);
			majCarte();
			System.out.println(this.toString());
		}
		if(!this.flotte.isEmpty())
		return this.faireFeu.tirer(this.selection.selectionner(this.flotte, "utiliser pour tirer"));
		else return null;
	}

	private boolean recevoirTir(Coordonnee c) {
		// vérifie si le missile a touché un navire et renvoie true dans ce cas
		boolean touche = false;
		if (c == null)
			return false;
		for (int i = 0; i < this.flotte.size(); i++) {
			if (this.flotte.get(i).collision(c)) {
				touche = true;
				this.flotte.get(i).dommage();
			}
			if (this.flotte.get(i).impacts >= 2) {
				this.flotte.remove(i);
				i--;
			}
		}
		return touche;
	}
	
	public String toString() {
		String s = "   ABCDEFGHIJ\n";
		for (int i = 0; i < Carte.TAILLE; i++) {
			s += String.format("%2d ", i+1);
			for (int j = 0; j < Carte.TAILLE; j++)
				s += this.carte[i][j];
			s += "\n";
		}
		return s;
	}

}
