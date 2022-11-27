package ee402;

import java.io.Serializable;
import java.util.*;

@SuppressWarnings("serial")
public class myClass implements Serializable {
	private Vector<String> vector = new Vector<String>();
	
	public myClass(Vector<String> v) {
		this.vector = v;
	}
	
	public Vector<String> sortVector() {
		Collections.sort(vector);
		return (vector);
	}
	
	public void print() {
		System.out.println(vector.toString());
	}
}
