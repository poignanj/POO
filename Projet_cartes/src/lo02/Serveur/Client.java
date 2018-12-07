package lo02.Serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client  {

   private  int port = 8080;
   private Socket socket;
   private final String host;
   private static Client instance = null;
   public Client(String host, int port ) {
     
      this.host=host;
      this.port=port;
   }
   
   public Client(String host ) {
	     
	   this.host=host;
	   }

   public Client start() {
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
   public static Client Instance(String pHost)
   {   
       if (instance == null)
       {   
           synchronized(Client.class)
           {
               if (instance == null)
               {   instance = new Client(pHost);
               }
           }
       }
       return instance;
   }
  /**
	 *  Instance du client 
	 * @param pHost adresse du serveur
	 * @param pPort port du serveur
	 * @return Client renvoie l'instance du Client
	 */
  public static Client Instance(String pHost, int pPort)
   {   
       if (instance == null)
       {   
           synchronized(Client.class)
           {
               if (instance == null)
               {   instance = new Client(pHost, pPort);
               }
           }
       }
       return instance;
   }

}

