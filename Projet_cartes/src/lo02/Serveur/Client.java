package lo02.Serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client  {

   private  int port = 8080;
   private Socket socket;
   private String host;
   private static Client instance = null;
   private Client(String host, int port ) {
     
      this.host=host;
      this.port=port;
   }
   
   private Client(String host ) {
	     
	   this.host=host;
   }
   private Client( ) {
	     
	   this.host= "127.0.0.1";
	   this.port = 8080;
   }

   public Client start(String pHost, int pPort) {
	   this.host=pHost;
	   this.port=pPort;
      try {
    	  socket = new Socket(InetAddress.getByName(host), port);
      } catch (IOException e) {
      }
      return this;
   }
   
   public Client start(String pHost) {
	   this.host=pHost;
	      try {
	    	  socket = new Socket(InetAddress.getByName(host), port);
	      } catch (IOException e) {
	      }
	      return this;
	   }

   private void close() {
     
      try {
         socket.close();
      } catch (IOException ex) {
      }
   }
   
   
   public Socket getSocket() {
	   return this.socket;
   }
   
   
   /**
  	 *  Instance du client 
  	 * @param pHost adresse du serveur
  	 * @return Client renvoie l'instance du Client
  	 */
   public static Client Instance()
   {   
       if (instance == null)
       {   
           synchronized(Client.class)
           {
               if (instance == null)
               {   instance = new Client();
               }
           }
       }
       return instance;
   }
}


