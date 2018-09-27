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
		flotte.add(new PorteAvion());
		flotte.add(new ContreTorpilleur());
		flotte.add(new SousMarin());
		flotte.add(new Torpilleur());
		flotte.add(new Croiseur());
		for (int i = 0; i < Carte.TAILLE; i++)
			for (int j = 0; j < Carte.TAILLE; j++)
				this.carte[i][j] = 0;
		this.placerFlotte();
		this.majCarte();
	}

	public void placerFlotte() {
		for (int i = 0; i < this.flotte.size(); i++) {
			System.out.println("BOB : " + i + this.flotte.get(i).toString());
			Tuple placement = this.placer.placer(this.flotte.get(i));
			this.flotte.get(i).setPosition(placement);
		}
	}

	private void majCarte() {
		for (int k = 0; k < flotte.size(); k++)
			if (flotte.get(k).getPosition().debut.x == flotte.get(k).getPosition().fin.x)
				for (int i = flotte.get(k).getPosition().debut.y; i < flotte.get(k).getPosition().fin.y; i++)
					carte[flotte.get(k).getPosition().debut.x][i] = k + 1;
			else
				for (int i = flotte.get(k).getPosition().debut.x; i < flotte.get(k).getPosition().fin.x; i++)
					carte[i][flotte.get(k).getPosition().debut.y] = k + 1;
	}

	public Coordonnee tirer(Coordonnee c) {
		if (this.recevoirTir(c))
			this.deplacer.deplacer(this.selection.selectionner(this.flotte));
		return this.faireFeu.tirer(this.selection.selectionner(this.flotte));
	}

	private boolean recevoirTir(Coordonnee c) {
		boolean touche = false;
		for (int i = 0; i < this.flotte.size(); i++) {
			if (this.flotte.get(i).colision(c)) {
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

}
