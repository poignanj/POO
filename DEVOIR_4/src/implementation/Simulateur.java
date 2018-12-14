package implementation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;

import adaptateur.Adaptateur;

public class Simulateur implements Runnable {
	private String cheminDossier;
	private long tempsRaffraichissement;
	private TreeMap<Path, Adaptateur> senseurs;
	
	public Simulateur(String cheminDossier, long tempsRaffraichissement)
	{
		this.cheminDossier = cheminDossier;
		this.tempsRaffraichissement = tempsRaffraichissement;
		this.senseurs = new TreeMap<>();
	}

	@Override
	public void run() {
		while(true) {
			Path dir = Paths.get(cheminDossier);
			
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*")) {
			    for (Path file : stream) {
			    	if(!senseurs.containsKey(file))
			    	{
				    	try{
					    	InputStream flux = new FileInputStream(file.toString()); 
					    	InputStreamReader lecture=new InputStreamReader(flux);
					    	BufferedReader buff=new BufferedReader(lecture);
					    	String ligne;
					    	String sid = "";
					    	String lnk = "";
					    	long frq = -1;
					    	while ((ligne=buff.readLine())!=null){
					    		String[] ligneDecoupee = ligne.split(" ");
					    		switch(ligneDecoupee[0])
					    		{
						    		case "SID:":
						    		{
						    			sid = ligneDecoupee[1];
						    			break;
						    		}
						    		case "FRQ:":
						    		{
						    			frq = (long)(1000.0 / Float.parseFloat(ligneDecoupee[1]));
						    			break;
						    		}
						    		case "LNK:":
						    		{
						    			lnk = dir.resolve(ligneDecoupee[1]).toString();
						    			break;
						    		}
					    		}
					    	}
					    	if(frq > 0 && sid != "" && lnk != "")
					    	{
					    		senseurs.put(file, new Adaptateur(sid, lnk, frq));
					    	}
					    	buff.close(); 
				    	} catch (Exception e){
				    		System.out.println(e.toString());
				    	}
			    	}
			    }
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				Thread.sleep(tempsRaffraichissement);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public TreeMap<String, String> GetStates()
	{
		TreeMap<String, String> res = new TreeMap<>();
		senseurs.forEach((file, adaptateur) -> {
			res.put(adaptateur.getSID(), adaptateur.isPresenceVehicle() ? "1" : "0");
		});
		return res;
	}
}