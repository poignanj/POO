package lo02.Serveur;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import lo02.Modele.ChoixCouleurHumainIHM;
import lo02.Modele.Humain;
import lo02.Modele.Jeu;
import lo02.Vue.GameFrame;

/**
 * 
 * @author Vivien Piris
 * 
 * Affiche la carte du talon
 * 
 */


public class Serveur {
	private int port = 8080;

	private String host = "127.0.0.1";

	private ServerSocket server = null;

	private boolean isRunning = true;
	private Socket client;
	
	
	private static Serveur instance = null;
	/**
	 *  Constructeur du serveur
	 * 
	 */
	  private Serveur(){

	      try {
	         server = new ServerSocket(port, 100, InetAddress.getByName(host));
	      } catch (UnknownHostException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

	   }

	   
	  /**
		 *  Constructeur du serveur 
		 * @param pHost adresse du serveur
		 * @param pPort port du serveur
		 */
	   private Serveur(String pHost, int pPort){
	      host = pHost;
	      port = pPort;
	      try {
	         server = new ServerSocket(port, 100, InetAddress.getByName(host));
	      } catch (UnknownHostException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();

	      }

	   }

	   

	   

	   /**
		 *  Lance le serveur 
		 * 
		 */
	   public void open(){
		  
	      Thread t = new Thread(new Runnable(){

	         public void run(){
	            while(isRunning == true){
	               try {
	            	  System.out.println("test");
	                  client = server.accept();
	                  System.out.println("Connexion cliente reçue.");                  
	                    

	               } catch (IOException e) {

	                  e.printStackTrace();
	               }
	            }
	            try {
	               server.close();
	            } catch (IOException e) {
	               e.printStackTrace();
	               server = null;
	            }
	        }
	      });
	      t.start();

	   }

	   /**
		 *  Ferme le serveur 
		 * 
		 */
	   public void close(){

	      isRunning = false;
	      try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
            server = null;
		}
	   } 
	   
	   /**
		 *  Instance du serveur 
		 * 
		 * @return Serveur renvoie l'instance du serveur
		 */
	   public static Serveur Instance()
	    {   
	        if (instance == null)
	        {   
	            synchronized(Serveur.class)
	            {
	                if (instance == null)
	                {   instance = new Serveur();
	                }
	            }
	        }
	        return instance;
	    }
	   /**
		 *  Instance du serveur 
		 * @param pHost adresse du serveur
		 * @param pPort port du serveur
		 * @return Serveur renvoie l'instance du serveur
		 */
	   public static Serveur Instance(String pHost, int pPort)
	    {   
	        if (instance == null)
	        {   
	            synchronized(Serveur.class)
	            {
	                if (instance == null)
	                {   instance = new Serveur(pHost, pPort);
	                }
	            }
	        }
	        return instance;
	    }
	   
}
