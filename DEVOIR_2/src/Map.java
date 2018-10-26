import java.util.ArrayList;

import outils.Vecteur2D;

public class Map {
	private ArrayList<Pigeon> pigeons;
	private ArrayList<Nourriture> nourritures;
	private ArrayList<Nourriture> spoiledNourritures; //Pour pouvoir les afficher
	public static float TAILLEX = 100;
	public static float TAILLEY = 100;
	
	public ArrayList<Nourriture> GetNourritures() {
		return nourritures;
	}
	
	public ArrayList<Pigeon> GetPigeons() {
		return pigeons;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean outOfBounds(Vecteur2D v) {
		return ((v.x < (float) 0) || (v.x > Map.TAILLEX) || (v.y < (float) 0) || (v.y > Map.TAILLEY));
	}
	
	public void NourritureNotAvailable(Nourriture notAvailable, boolean spoiled) {
		nourritures.remove(notAvailable);
		if(spoiled)
			spoiledNourritures.add(notAvailable);
		pigeons.forEach(p -> p.NourritureNotAvailable(notAvailable));
	}
}
