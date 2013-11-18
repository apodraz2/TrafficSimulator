package model;

import Command.Command;

public class CmdSetTimeStep implements Command{
	static double _timeStep;
	
	CmdSetTimeStep(double timeStep) {
		_timeStep = timeStep;
	}

	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setTimeStep(_timeStep);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	
}
