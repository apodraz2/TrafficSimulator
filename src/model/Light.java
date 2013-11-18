package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import util.AnimatorBuilder;

public class Light implements Agent{
	public enum LightColor{
		GREEN, YELLOW, RED
	}
	/*
	 * A light updates it's color according to the MP light time parameters and re-enqueues itself
	 * after updating. Colors are defined in the LightColor enum.
	 */
	
	static TimeServer _server;
	
	static AnimatorBuilder _ab;
	static int _i;
	static int _j;
	private boolean _state;
	private LightColor _color;
	double _timer = 0;
	
	Light(TimeServer server, AnimatorBuilder ab,  int i, int j) {
		_server = server;
		_i = i;
		_j = j;
		_ab = ab;
	}
	
	public boolean getState() {
		return _state;
	}
	
	void setState(boolean state) {
		_state = state;
	}
	
	public void setLightColor(LightColor color) {
		this._color = color;
	}
	
	public LightColor getLightColor() {
		return this._color;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	public void updateLights() {
		_ab.addLight(this, this._i, this._j);
	}
	@Override
	public boolean run(double time) {
		// TODO Auto-generated method stub
		try {
		if(this._color == LightColor.GREEN && ((TimeServerLinked) _server)._greenTime >= MP.getGreenTime()){
			this.setLightColor(LightColor.YELLOW);
			updateLights();
			
		}
		else if(this._color == LightColor.YELLOW && ((TimeServerLinked)_server)._greenTime >= MP.getLightTimer()){
			this.setLightColor(LightColor.RED);
			updateLights();
		}
		else if(this._color == LightColor.RED && ((TimeServerLinked)_server)._greenTime >= MP.getLightTimer()) {
			this.setLightColor(LightColor.GREEN);
			updateLights();
		}
			_server.enqueue(_server.currentTime() + time * MP.getLightTimer(), this);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
			
	}

	@Override
	public void setCurrentRoad(Road road) {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	public void setPosition(float position) {
		// TODO Auto-generated method stub
		throw new java.lang.UnsupportedOperationException();
	}
	
	
	
}
