package model;

import java.util.Observer;

public interface Agent extends Observer {

	public boolean run(double time);

	public void setCurrentRoad(Road road);

	public void setPosition(float position);
	
}
