package lo02.Serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client  {

   private  int port = 8080;
   private Socket socket;
   private final String host;

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

}

