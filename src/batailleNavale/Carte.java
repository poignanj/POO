package batailleNavale;

import java.util.ArrayList;

import outils.Tuple;

public class Carte {
	final static int TAILLE = 10;
	private int carte[][]; // outil graphique
	private ArrayList<Bateau> flotte = new ArrayList<Bateau>();
	private PlacerNavire placer;
	public SelectionnerNavire selection;

	public Carte() {
		for (int i = 0; i < Carte.TAILLE; i++)
			for (int j = 0; j < Carte.TAILLE; j++)
				carte[i][j] = 0;
		this.placerFlotte();
	}

	public void placerFlotte() {
		Bateau b = new PorteAvion();
		Tuple placement = this.placer.placer(b);
		b.setPosition(placement);
		flotte.add(b);
	}

	private void majCarte() {
		for (int k = 0; k < flotte.size(); k++)
			if (flotte.get(k).getPosition().debut.x == flotte.get(k).getPosition().fin.x)
				for (int i = flotte.get(k).getPosition().debut.y; i < flotte.get(k).getPosition().fin.y; i++)
					carte[flotte.get(k).getPosition().debut.x][i] = k+1;
			else
				for (int i = flotte.get(k).getPosition().debut.x; i < flotte.get(k).getPosition().fin.x; i++)
					carte[i][flotte.get(k).getPosition().debut.y] = k+1;
	}

	public Bateau selectionnerBateau() {
		return this.selection.selectionner(this.flotte);
	}

}
