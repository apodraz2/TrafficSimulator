package model;

import Command.Command;

public class CmdSetDuration implements Command{
	static double _duration;
	
	CmdSetDuration(double duration) {
		_duration = duration;
	}
	
	public boolean run() {
		try{
			MP.setDuration(_duration);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
}
