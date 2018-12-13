package adaptateur;

import java.io.File;

public class Adaptateur implements Runnable {
	String SID;
	long FrequenceActualisation;
	String Link;
	boolean SensorStarted = true;
	boolean presenceVehicle;
	
	
	public boolean isPresenceVehicle() {
		return presenceVehicle;
	}
	public void setPresenceVehicle(boolean presenceVehicle) {
		this.presenceVehicle = presenceVehicle;
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
			
			
			try {
				Thread.sleep(FrequenceActualisation);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	private void verifyState(String link) {
		File f= new File(link);
		//f.rea
	}

}
