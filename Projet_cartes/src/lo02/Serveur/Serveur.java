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
	
	  public Serveur(){

	      try {
	         server = new ServerSocket(port, 100, InetAddress.getByName(host));
	      } catch (UnknownHostException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

	   }

	   

	   public Serveur(String pHost, int pPort){
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

	   

	   

	   //On lance notre serveur

	   public void open(){
		  
	      Thread t = new Thread(new Runnable(){

	         public void run(){
	            while(isRunning == true){
	               try {
	            	  System.out.println("test");
	                  Socket client = server.accept();
	                  System.out.println("Connexion cliente reçue.");                  
	                  Thread t = new Thread(/* to do add la gestion du joueur*/);
	                  t.start();    

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

	   public void close(){

	      isRunning = false;

	   } 
	   
	   public static void main(String[] args) {
		   	  Jeu j = new Jeu();
		      String host = "127.0.0.1";
		      int port = 2345;
		      
		      Serveur ts = new Serveur(host, port);
		      ts.open();
		      
		      System.out.println("Serveur initialisé.");
		      j.jouer();
		  
		   }
}
