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
		  char axisX = (char) (x + 'A' - 1 );
		  char axisY = (char) (y + '0');
		  return "" + axisX + axisY;
		  
	  }
}
