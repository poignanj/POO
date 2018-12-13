
public class Sensor implements Runnable {
	String SID;
	float FrequenceActualisation;
	public String getSID() {
		return SID;
	}
	public void setSID(String sID) {
		SID = sID;
	}
	public float getFrequenceActualisation() {
		return FrequenceActualisation;
	}
	public void setFrequenceActualisation(float frequenceActualisation) {
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
	public Sensor(String sid,String link, float frequence) {
		this.Link=link;
		this.SID=sid;
		this.FrequenceActualisation=frequence;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
