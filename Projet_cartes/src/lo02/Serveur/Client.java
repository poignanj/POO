package lo02.Serveur;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
	      this.port=8080;
	   }

   public void start() {
      try {
    	  socket = new Socket(InetAddress.getByName(host), port);
      } catch (IOException e) {
      }
   }

   private void close() {
     
      try {
         socket.close();
      } catch (IOException ex) {
      }
   }

}

