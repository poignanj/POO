import java.io.File;

public class Adaptateur implements Runnable {
	String SID;
	long FrequenceActualisation;
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public float getFrequenceActualisation() {
		return FrequenceActualisation;
	}
	public void setFrequenceActualisation(long frequenceActualisation) {
		FrequenceActualisation = frequenceActualisation;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public boolean isSensorStarted() {
		return SensorStarted;
	}
	public void setSensorStarted(boolean sensorStarted) {
		SensorStarted = sensorStarted;
	}
	String Link;
	boolean SensorStarted = false;
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
