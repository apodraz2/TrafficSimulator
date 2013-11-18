package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.Light.LightColor;

import util.AnimatorBuilder;

public class Road implements CarAcceptor {
		
	/* 
	 * Road class has several responsibilities. First, a Road must construct it's own light. A Road
	 * must also contain all the cars that are sourced into it. Finally, a Road must calculate the 
	 * distance between all the closest obstacles for each car. 
	 */
	
	static float endPosition = (float)MP.getRoadLength();
	private CarAcceptor nextRoad;
	private List<Agent> cars = new LinkedList<Agent>();
	static RoadList _roadList;
	private int _position;
	boolean _eastWest;
	Light _light;
	TimeServer _server;
	AnimatorBuilder _ab;
	int _i;
	int _j;
	
	Road(TimeServer server, AnimatorBuilder ab, RoadList rl, int position, boolean eastWest, int i, int j, Light l){
		_position = position;
		_roadList = rl;
		_eastWest = eastWest;
		_server = server;
		_light = l;
		_ab = ab;
		
		if (eastWest) {
			_light.setLightColor(LightColor.GREEN);
		}else
			_light.setLightColor(LightColor.RED);
		_ab.addLight(_light, i, j);
		_i = i;
		_j = j;
		_server.enqueue(_server.currentTime() + MP.getGreenTime(), _light);
	}
		
	public boolean accept(Agent c, float position){
		if(c == null) { throw new IllegalArgumentException(); }
		cars.remove(c);
		
		
		if(position > endPosition && nextRoad != null) {
			return(nextRoad.accept(c, position - endPosition));
		} 
		//Sink here
		else if (position > endPosition && nextRoad == null) {
			return false;
		} else {
			c.setCurrentRoad(this);
			c.setPosition(position);
			cars.add(c);
			return true;
		} 
			
	}
	
	public void setNextRoad() {
		List roads = (List) this._roadList.getIterable();
		if(roads.size() <= 1 || _position >= roads.size() - 1) {
			nextRoad = null;
			
		} else {
			nextRoad = _roadList.getRoad(_position + 1);
			
		}
	}
	
	public CarAcceptor getNextRoad() {
		return nextRoad;
	}
	
	public float getEndPosition() {
		return endPosition;
	}
	
	private double distanceToCarBack(double fromPosition) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		for (Agent c : cars) {
			if (((Car1) c).backPosition() >= fromPosition && ((Car1) c).backPosition() < carBackPosition){
				carBackPosition = ((Car1) c).backPosition();
			}
			
		}
		return carBackPosition;
	}
	
	public double distanceToObstacle(double fromPosition) {
		if (fromPosition < 0) { throw new IllegalArgumentException(); }
		double obstaclePosition = this.distanceToCarBack(fromPosition);
		if(nextRoad == null) {
			return 0;
		}
		if (obstaclePosition == Double.POSITIVE_INFINITY) {
			obstaclePosition = ((Road) nextRoad).distanceToObstacle(this.endPosition - fromPosition);
		}
		return obstaclePosition - fromPosition;
	}
	
	public double distanceToLightObstacle(double fromPosition) {
		//System.out.println(_light.getState());
		if (_light.getLightColor() == LightColor.RED)
			return this.endPosition - fromPosition;
		else
			return Double.POSITIVE_INFINITY;
	}
	
	public int getPosition() {
		return _position;
	}
	
	public List<Agent> getCars() {
		return cars;
	}

	
	
}
