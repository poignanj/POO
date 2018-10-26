import java.util.ListIterator;

public class Idle extends Comportement {
	private Map map;
	private boolean nourrituresChecked = false;
	
	public Idle(Pigeon pigeon, Map map) {
		this.map = map;
		this.pigeon = pigeon;
	}

	@Override
	public void ExecuteComportement() {
		if(!nourrituresChecked) {
			ListIterator<Nourriture> it = map.GetNourritures().listIterator();
			if(it.hasNext())
			{
				Nourriture nourritureProche = it.next();
				float distMin = nourritureProche.GetPosition().Distance(pigeon.GetPosition());
				while(it.hasNext())
				{
					Nourriture tmpNour = it.next();
					float tmpDist = tmpNour.GetPosition().Distance(pigeon.GetPosition());
					if(tmpDist < distMin)
					{
						nourritureProche = tmpNour;
						distMin = tmpDist;
					}
				}
				pigeon.RedifineComportement(new AllerManger(pigeon, nourritureProche, map));
			}
			nourrituresChecked = true;
		}
	}

	@Override
	public void NewNourriture(Nourriture newNour) {
		if(nourrituresChecked)
		{
			pigeon.RedifineComportement(new AllerManger(pigeon, newNour, map));
		}
	}
	public void NewDanger(Danger newDanger) {
		pigeon.RedifineComportement(new Fuite(pigeon, newDanger, map));
	}
}
