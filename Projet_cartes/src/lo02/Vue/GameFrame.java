package lo02.Vue;

import javax.swing.JFrame;

import lo02.Modele.ChoixCouleurHumainIHM;
import lo02.Modele.Humain;
import lo02.Modele.Jeu;
import lo02.Serveur.ObserverUpdateClient;
import lo02.Serveur.Serveur;
/**
 * 
 * @author Jean-Jacques Poignant, Elise Poignant et Guillaume Paris
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
		Jeu j = new Jeu();
		GameFrame g = new GameFrame((Humain) j.getListeJoueurs().get(0));
		ObserverUpdateClient c = new ObserverUpdateClient();
		j.addObserver(g.getGamePanel());
		j.addObserver(c);
		for (int i = 0; i < j.getListeJoueurs().size(); i++) {
			j.getListeJoueurs().get(i).addObserver(g.getGamePanel());
			j.getListeJoueurs().get(i).addObserver(c);
			if (j.getListeJoueurs().get(i) instanceof Humain)
			{
				((ChoixCouleurHumainIHM) j.getListeJoueurs().get(i).getChoix()).addObserver(g.getGamePanel());
				((ChoixCouleurHumainIHM) j.getListeJoueurs().get(i).getChoix()).addObserver(c);	
			}
		} 
		j.getTable().addObserver(g.getGamePanel());
		j.getTable().addObserver(c);
		Serveur.Instance().open();
		
		j.jouer();
	}
}
