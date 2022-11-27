interface Calculate {
	double calculate(double x, double y);
}

class calculateClass extends Object implements Calculate {
	public double calculate(double x, double y) {
		return x + y;
	}
}

public class Q4A {
	public static void display(Calculate c) {
		System.out.println(c.calculate(3, 3));
	}
	
	public static void main(String args[]) {
		Calculate c = new calculateClass();
		display(c);
	}
}
