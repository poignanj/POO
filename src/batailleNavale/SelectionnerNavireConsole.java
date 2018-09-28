package batailleNavale;

import java.util.ArrayList;
import java.util.Scanner;

import outils.ChoixEntree;

public class SelectionnerNavireConsole implements SelectionnerNavire {
	public Bateau selectionner(ArrayList<Bateau> b, String action) {
		for(int i = 0; i < b.size(); i++)
			System.out.println((i+1) + " : " + b.get(i).toString());
		int choix = ChoixEntree.ChoixPlageInt(1, b.size(), "Choisissez le bateau que vous souhaitez " + action + ": ");
		return b.get(choix-1);
	}

}
