package outils;

public class Coordonnee {
	  public int x; 
	  public int y; 
	  
	  public Coordonnee(int x, int y) { 
	    this.x = x; 
	    this.y = y; 
	  }
	  public Coordonnee() { 
		    this.x = 0; 
		    this.y = 0; 
	  }
	  
	  public String toString() {
		  char axisY = (char) (y + 'A');
		  return "" + axisY + (x+1);
	  }
	  
	  @Override
	  public boolean equals(Object autre){
	      if (autre == null) return false;
	      if (autre == this) return true;
	      if (!(autre instanceof Coordonnee))return false;
	      Coordonnee autreCoordonnee = (Coordonnee)autre;
	      return x == autreCoordonnee.x && y == autreCoordonnee.y;
	  }
}
