package lo02.Controleur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import lo02.Modele.*;
import lo02.Vue.CarteLabel;

/**
 * La classe ControleurCarte permet aux boutons d'avoir un effet
 * 
 * @author Jean-Jacques Poignant et Elise Poignant
 */
public class ControleurCarte {
	/**
	 * 
	 * @param carte CarteLabel qui deviendra cliquable
	 * @param h Humain à qui le clic sera envoyé
	 * permet de renvoie à h la position de la carte dans sa main.
	 */
	public ControleurCarte(CarteLabel carte, Humain h) {

		carte.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				((ChoixCouleurHumainIHM) h.getChoix()).setNumClique(carte.getIndexMain());
				((DonHumainIHM) h.getDon()).setNumClique(carte.getIndexMain());
				((PoseHumainIHM) h.getPose()).setNumClique(carte.getIndexMain());
			}

		});
	}
}
