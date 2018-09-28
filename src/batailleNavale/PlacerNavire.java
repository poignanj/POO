package batailleNavale;

import java.util.ArrayList;

import outils.*;

public interface PlacerNavire {
	public abstract Tuple placer(Bateau b, ArrayList<Bateau> flotte);
}
