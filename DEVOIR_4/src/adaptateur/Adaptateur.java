package adaptateur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Adaptateur implements Runnable {
	String SID;
	long FrequenceActualisation;
	String Link;
	boolean SensorStarted = true;
	boolean presenceVehicle=false;
	
	
	public boolean isPresenceVehicle() {
		return presenceVehicle;
	}
	
	
	public String getSID() {
		return SID;
	}


	public Adaptateur(String sid,String link, long frequence) {
		this.Link=link;
		this.SID=sid;
		this.FrequenceActualisation=frequence;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.SensorStarted=true;
		while(SensorStarted) {
			verifyState(Link);
			
			try {
				Thread.sleep(FrequenceActualisation);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void verifyState(String link) {
		
		try {
			File f= new File(link);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String s = br.readLine();
			presenceVehicle=s.contains("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
