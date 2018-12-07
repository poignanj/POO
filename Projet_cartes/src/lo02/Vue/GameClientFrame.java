package lo02.Vue;

import javax.swing.JFrame;

import lo02.Modele.Humain;
import lo02.Serveur.Client;

/**
 * 
 * @author Jean-Jacques Poignant
 *
 */
public class GameClientFrame extends JFrame {
	public GameClientFrame() {
		this.setTitle("(  /°3°)/ CLIENT 8 américain ! \\(°D°\\  )");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new GameClientPanel(new Humain(2)));
		this.setVisible(true);
	}
	public static void main(String[] args) {
		//Client.Instance("127.0.0.1").start();
		new GameClientFrame();
	}
}
