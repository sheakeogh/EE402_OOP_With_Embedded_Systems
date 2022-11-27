package ee402;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PollutionReading implements Serializable {
	private String location;
	private float value;
	
	public PollutionReading(String loc, float val) {
		this.location = loc;
		this.value = val;
	}
	
	public void display() {
		System.out.println("Reading is " + value + "ug/m3 at location " + location);
	}
	
	public String getLocation() {
		return location;
	}
	
	public float getValue() {
		return value;
	}
}
