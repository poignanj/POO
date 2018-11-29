package lo02.Vue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lo02.Modele.*;

/**
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 *
 */
public class GamePanel extends JPanel implements Observer {
	private Humain h;

	/**
	 * Paramètre et crée le plateau de jeu.
	 * 
	 * @param h
	 *            Humain
	 */
	public GamePanel(Humain h) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.7;
		c.weightx = 1.0;
		c.gridy = 0;
		c.gridx = 0;
		// this.setLayout(new BorderLayout());
		this.add(new TablePanel(4), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.3;
		c.gridy = 1;
		c.gridx = 0;
		this.add(new HandPanel(), c);
		// this.getComponent(0).setPreferredSize(new Dimension((int)
		// (this.getWidth()*0.5), (int) (this.getHeight()*0.70)));
		this.h = h;
		this.setVisible(true);
	}

	/**
	 * Met à jour les éléments le nécessitant quand ceux-ci s'update.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Joueur) {
			if (((Joueur) o).getNumJoueur() == 0) {
				this.afficherMain(((Joueur) o).getMain(), (Carte) arg);
			} else {
				this.afficherJoueur(((Joueur) o).getNumJoueur(), ((Joueur) o).getMain().size());
			}
		}
		if (o instanceof Table) {
			this.afficherPlateau(((Table) arg).getPremiereCarteTalon());
		}
		if (o instanceof ChoixCouleurHumainIHM) {
			ArrayList<Carte> c = new ArrayList<Carte>();
			c.add(new Carte(13, 0));// "diamonds", "spades", "hearts", "clubs"
			c.add(new Carte(13, 1));
			c.add(new Carte(13, 2));
			c.add(new Carte(13, 3));
			this.afficherMain(c, null);
		}
		if (o instanceof Jeu) {
			this.tourJoueur((int) arg);
		}

	}

	/**
	 * renvoie le composant correspondant à la table (ce qui n'est pas la main du
	 * joueur
	 * 
	 * @return TablePanel composant correspondant à la table
	 */
	public TablePanel getTablePanel() {
		return (TablePanel) this.getComponent(0);
	}

	/**
	 * Affiche ou met à jour la main du joueur
	 * 
	 * @param main
	 *            main de l'humain
	 * @param carte
	 *            carte du talon
	 */
	public void afficherMain(ArrayList<Carte> main, Carte carte) {
		HandPanel h = new HandPanel(main, this.h, carte);
		h.setVisible(true);
		this.remove(1);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.3;
		c.weightx = 1.0;
		c.gridy = 1;
		c.gridx = 0;
		this.add(h, c);
		this.validate();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Affiche ou met à jour la carte du talon
	 * 
	 * @param talon
	 *            carte du talon
	 */
	public void afficherPlateau(Carte talon) {
		BoardPanel h = new BoardPanel(talon);
		h.setVisible(true);
		this.getTablePanel().remove(4);
		this.getTablePanel().add(h, 4);
		this.validate();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Affiche ou met à jour l'adversaire correspondant
	 * 
	 * @param numJoueur
	 *            emplacement du joueur
	 * @param nbCartes
	 *            nombre de cartes dans la main du joueur
	 */
	public void afficherJoueur(int numJoueur, int nbCartes) {
		this.getTablePanel().redrawIA(numJoueur, nbCartes);
		this.revalidate();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Change la couleur de fond en fonction du joueur en cours
	 * 
	 * @param numJoueur
	 *            emplacement du joueur
	 */
	public void tourJoueur(int numJoueur) {
		resetCouleurs();
		if (numJoueur != 0)
			this.getTablePanel().changerCouleurJoueur(numJoueur);
		else
			this.getComponent(1).setBackground(Color.LIGHT_GRAY);
		this.revalidate();
	}

	/**
	 * réinitialise toutes les couleurs de fond
	 */
	private void resetCouleurs() {
		this.getTablePanel().resetCouleurIA();
		this.getComponent(1).setBackground(Color.WHITE);
	}

}
