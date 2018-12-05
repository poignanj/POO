package lo02.Vue;

import java.awt.Dimension;

import javax.swing.JPanel;

import lo02.Modele.Carte;
/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 * 
 * Affiche la carte du talon
 * 
 */
public class BoardPanel extends JPanel {
	public BoardPanel() {
		this.setPreferredSize(new Dimension(CarteLabel.WIDTH, (int) (CarteLabel.RATIO * CarteLabel.WIDTH)));
	}
	/**
	 * Crée et affiche le talon avec la carte indiquée
	 * @param talon carte du talon
	 */
	public BoardPanel(Carte talon) {
		this.setPreferredSize(new Dimension(CarteLabel.WIDTH, (int) (CarteLabel.RATIO * CarteLabel.WIDTH)));
		CarteLabel c = new CarteLabel(talon, null);
		this.add(c);
	}

}
