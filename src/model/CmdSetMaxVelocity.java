package model;

import Command.Command;

public class CmdSetMaxVelocity implements Command{
	static double _velocity;
	CmdSetMaxVelocity(double velocity) {
		_velocity = velocity;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setMaxVelocity(_velocity);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	
}
