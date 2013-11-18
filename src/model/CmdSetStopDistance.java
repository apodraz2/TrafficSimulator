package model;

import Command.Command;

public class CmdSetStopDistance implements Command{
	static double _distance;
	CmdSetStopDistance(double distance) {
		_distance = distance;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setStopDistance(_distance);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
