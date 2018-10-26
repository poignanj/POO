import java.util.ArrayList;

public class Map {
	private ArrayList<Pigeon> pigeons;
	private ArrayList<Nourriture> nourritures;
	private ArrayList<Nourriture> spoiledNourritures; //Pour pouvoir les afficher
	
	public ArrayList<Nourriture> GetNourritures() {
		return nourritures;
	}
	
	public ArrayList<Pigeon> GetPigeons() {
		return pigeons;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void NourritureNotAvailable(Nourriture notAvailable, boolean spoiled) {
		nourritures.remove(notAvailable);
		if(spoiled)
			spoiledNourritures.add(notAvailable);
		pigeons.forEach(p -> p.NourritureNotAvailable(notAvailable));
	}
}
