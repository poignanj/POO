package lo02.Vue;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import lo02.Modele.Carte;

/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 */
public class CarteLabel extends JLabel {
	public final static double RATIO = 1.452;
	public final static int WIDTH = 120;
	private int indexMain;
	private boolean crossed = false;

	/**
	 * Créée une carte et l'affiche, barrée si non joubale sur carte2
	 * 
	 * @param carte
	 *            carte actuelle
	 * @param carte2
	 *            carte à confronter
	 */
	public CarteLabel(Carte carte, Carte carte2) {
		if (carte2 != null) {
			crossed = carte.estJouableSur(carte2);
		}
		String imagePath = "res/img/" + carte.toString() + ".png";
		this.setIcon(new ImageIcon(getScaledImage(new ImageIcon(imagePath).getImage(), WIDTH, (int) (WIDTH * RATIO))));

		// new ImageIcon(imagePath)
	}

	/**
	 * Redimenssionne l'image srcImg en fonction de la hauteur h et largeur w
	 * 
	 * @param srcImg
	 *            image à redimensionner
	 * @param w
	 *            largeur
	 * @param h
	 *            hauteur
	 * @return BufferedImage image redimensionnée
	 */
	private Image getScaledImage(Image srcImg, int w, int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}

	// @override
	/**
	 * Surdéfinition de la fonction paint de JPanel
	 */
	public void paintComponent(Graphics g) {
		ImageIcon temp = new ImageIcon("res/crossed.png");
		if (crossed)
			g.drawImage(getScaledImage(temp.getImage(), WIDTH, (int) (WIDTH * RATIO)), 0, 0, null);
		super.paintComponent(g);
	}

	/**
	 * renvoie la position dans la main (ordre de création)
	 * 
	 * @return indexMain emplacement de la carte dans la main
	 */
	public int getIndexMain() {
		return indexMain;
	}

	/**
	 * Définie la position dans la main (ordre de création)
	 * 
	 * @param indexMain
	 *            emplacement de la carte dans la main
	 */
	public void setIndexMain(int indexMain) {
		this.indexMain = indexMain;
	}
}