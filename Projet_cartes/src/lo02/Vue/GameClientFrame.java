package lo02.Vue;

import javax.swing.JFrame;

import lo02.Modele.Humain;

/**
 * 
 * @author Jean-Jacques Poignant
 *
 */
public class GameClientFrame extends JFrame {
	public GameClientFrame() {
		this.setTitle("(  /°3°)/ CLIENT 8 américain ! \\(°D°\\  )");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new GamePanel(new Humain(2)));
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new GameClientFrame();
	}
}
