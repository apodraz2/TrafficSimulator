package model;

import java.util.Observable;

import util.AnimatorBuilder;

public class Car1 implements Agent {
	private int _sleepSeconds;
	private double _frontPosition;
	private Road _currentRoad;
	
	private TimeServer _server;
	private float _position = 0;
	private float _velocity = (float) MP.getVelocity();
	private java.awt.Color _color = new java.awt.Color((int) Math.ceil(Math.random() * 255), 
			(int) Math.ceil(Math.random() * 255), (int) Math.ceil(Math.random() * 255));
	
	
	Car1(TimeServer server, Road r) {
		_server = server;
		_currentRoad = r;
		_server.enqueue(_server.currentTime(), this);
	}
	
	
	
	public java.awt.Color getColor() {
		return _color;
	}
	
	@Override
	public boolean run(double time) {
		// TODO Auto-generated method stub
		try {
			_velocity = (float) calculateVelocity();
			_position += _velocity;
		
			if(_currentRoad.accept(this, _position)){
			_server.enqueue(_server.currentTime() + time, this);
			}
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
		
	}
	
	public void setCurrentRoad(Road r) {
		_currentRoad = r;
	}
	
	public CarAcceptor getCurrentRoad() {
		return _currentRoad;
	}
	
	public void setPosition(float newPosition) {
		_position = newPosition;
	}
	
	public double getPosition() {
		return _position;
	}
	
	private double calculateVelocity() {
		//Bug here. This calculation works well with the default values and multiples of five...
		if(_currentRoad.distanceToObstacle(_position) - MP.getCarLength() == MP.getStopDistance()) {
			
			if (_velocity - MP.getBrakeDistance() >= 0) {
				_velocity -= MP.getBrakeDistance();
				return _velocity;
			}
			return 0;
		}
		else if(_position >= MP.getRoadLength() - MP.getIntersectionLength() && _currentRoad.distanceToLightObstacle(_position) <= MP.getIntersectionLength()){
			
			if (_velocity - MP.getBrakeDistance() >= 0) {
				_velocity -= MP.getBrakeDistance();
				return _velocity;
			}
			return 0;
		}
		
		return (float) MP.getVelocity();
	}
	
	public double getVelocity() {
		return _velocity;
	}

	double backPosition() {
		return _position - MP.getCarLength();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
