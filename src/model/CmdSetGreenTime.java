package model;

import Command.Command;

public class CmdSetGreenTime implements Command{
	static double _time;
	CmdSetGreenTime(double time) {
		_time = time;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setGreenTime(_time);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
