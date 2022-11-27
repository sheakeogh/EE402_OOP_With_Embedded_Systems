package ee402;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("serial")
public class PollutionReadings implements Serializable {
	private Vector<PollutionReading> vector = new Vector<PollutionReading>();
	
	public PollutionReadings(Vector<PollutionReading> v) {
		this.vector = v;
	}
	
	public void display() {
		for(int i = 0; i < vector.size(); i++) {
			vector.get(i).display();
		}
	}
	
	public float findAverage() {
		float total = 0;
		for(int i = 0; i < vector.size(); i++) {
			total += vector.get(i).getValue();
		}
		return (total/vector.size());
	}
	
	public void triggerAlarm(float max) {
		for(int i = 0; i < vector.size(); i++) {
			if(vector.get(i).getValue() >= max) {
				System.out.println("Alarm! Sensor at location " + vector.get(i).getLocation() + " exceeds level with reading " + vector.get(i).getValue() + "ug/m3");
			}
		}
	}
}
