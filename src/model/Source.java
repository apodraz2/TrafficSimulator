package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

public class Source implements Agent, CarAcceptor {
	
	private double _sleepSeconds;
	TimeServer _server;
	Road _r;
	private List<Car1> cars = new LinkedList<Car1>();
	
	
	Source(TimeServer server, Road r) {
		_sleepSeconds = MP.getCarEntryRate();
		_server = server;
		_r = r;
	}
	
	public boolean run(double time) {
		try{
			Car1 c = CarFactory.newCar(_server, _r);
			_r.accept(c, 0);
			_server.enqueue(_server.currentTime() + time * _sleepSeconds, this);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean accept(Agent c, float f) {
		// TODO Auto-generated method stub
		try {
			cars.add((Car1) c);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public Iterable<Car1> getCars() {
		return cars;
	}

	@Override
	public void setCurrentRoad(Road road) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition(float position) {
		// TODO Auto-generated method stub
		
	}
}
