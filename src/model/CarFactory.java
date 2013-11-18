package model;

public class CarFactory {
	private CarFactory() {}
	
	
	public static Car1 newCar(TimeServer server, Road r) {
		return new Car1(server, r);
	}
}
