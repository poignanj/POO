package lo02.Vue;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import lo02.Modele.Carte;
import lo02.Modele.ChoixCouleurHumainIHM;
import lo02.Modele.DonneesClientReseau;
import lo02.Modele.Humain;
import lo02.Modele.Jeu;
import lo02.Modele.Joueur;
import lo02.Modele.Table;

/**
 * 
 * @author Jean-Jacques Poignant et Guillaume Paris
 *
 */
public class GameClientPanel extends JPanel implements Observer{
	private DonneesClientReseau dataSet;

	/**
	 * Paramètre et crée le plateau de jeu.
	 * 
	 * @param h 
	 * 			Humain
	 * 
	 */
	public GameClientPanel(Humain h) {
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
		dataSet = new DonneesClientReseau(h);
		dataSet.addObserver(this);
		((ChoixCouleurHumainIHM) dataSet.getHumain().getChoix()).addObserver(this);
		this.setVisible(true);
	}

	/**
	 * Met à jour les éléments le nécessitant quand ceux-ci s'update.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof DonneesClientReseau) {
			if(arg instanceof Carte) this.afficherPlateau(((DonneesClientReseau) o).getPremiereCarteTalon());
			else if (arg instanceof Float) {
				this.tourJoueur((int)(float)arg);
			}
			else if (arg instanceof Integer && ((int)arg %10 != 2)) {
				this.afficherJoueur((int) arg % 10, (int) arg / 10);
			}
			else if (((DonneesClientReseau) o).getNumJoueur() == 2) {
				this.afficherMain(((DonneesClientReseau) o).getHumain().getMain(), ((DonneesClientReseau) o).getPremiereCarteTalon());
			}
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
	 * renvoie le composant correspondant à la gestion des données
	 * 
	 * @return DonneesClientReseau composant correspondant à la gestion des données
	 */
	public DonneesClientReseau getDataSet() {
		return dataSet;
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
		HandPanel h = new HandPanel(main, this.dataSet.getHumain(), carte);
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
		this.getTablePanel().redrawIA((numJoueur+2)%4, nbCartes);
		this.revalidate();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
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
		if ((numJoueur+2) %4 != 0)
			this.getTablePanel().changerCouleurJoueur((numJoueur+2) %4);
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
