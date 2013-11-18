package model;

import Command.Command;

public class CmdSetBrakeDistance implements Command{
	static double _distance;
	CmdSetBrakeDistance(double distance){
		_distance = distance;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setBrakeDistance(_distance);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}
	
}
