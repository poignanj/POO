package lo02.Vue;

import javax.swing.JFrame;

import lo02.Modele.ChoixCouleurHumainIHM;
import lo02.Modele.Humain;
import lo02.Modele.Jeu;
import lo02.Serveur.Serveur;
/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 */
public class GameFrame extends JFrame {
	/**
	 * Crée la fenere de l'application
	 * @param h Humain joueur
	 */
	public GameFrame(Humain h) {
		this.setTitle("(  /°3°)/  8 américain ! \\(°D°\\  )");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new GamePanel(h));
		this.setVisible(true);
	}
	/**
	 * Renvoie le composant correspondant à l'affichage du jeu
	 * @return GamePanel GamePanel correspondant à l'affichage du jeu 
	 */
	public GamePanel getGamePanel() {
		return ((GamePanel) this.getContentPane());
	}
	/**
	 * Fonction main, le jeu est lancé depuis là, et l'observeur y est associé aux observables.
	 * @param args arguments de main. Non utilisés
	 */
	public static void main(String[] args) {
		Serveur.Instance().open();
		GameClientFrame gc= new GameClientFrame();
		Jeu j = new Jeu();
		GameFrame g = new GameFrame((Humain) j.getListeJoueurs().get(0));
		j.addObserver(g.getGamePanel());
		for (int i = 0; i < j.getListeJoueurs().size(); i++) {
			j.getListeJoueurs().get(i).addObserver(g.getGamePanel());
			if (j.getListeJoueurs().get(i) instanceof Humain)
				((ChoixCouleurHumainIHM) j.getListeJoueurs().get(i).getChoix()).addObserver(g.getGamePanel());
		} 
		j.getTable().addObserver(g.getGamePanel());
		
		
		j.jouer();
	}
}
