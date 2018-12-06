package Outils;

import java.io.BufferedInputStream;
import java.io.IOException;

public class Outil {
	public static String read(BufferedInputStream reader) throws IOException{
	    String response = "";
	    int stream;
	    byte[] b = new byte[4096];
	    stream = reader.read(b);
	    response = new String(b, 0, stream);
	    return response;
	 }
}
