package batailleNavale;

import java.util.ArrayList;
import java.util.Scanner;

public class SelectionnerNavireConsole implements SelectionnerNavire {
	// TODO : vérifier input (outOfBounds & navire précis)
	public Bateau selectionner(ArrayList<Bateau> b) {
		for(int i = 0; i < b.size(); i++)
			System.out.println(i + " : " + b.get(i).toString());
		System.out.println("");
		System.out.print("Choisissez le bateau que vous souhaitez : ");
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		return b.get(choix);
	}

}
