package model;

import Command.Command;

public class CmdSetYellowTime implements Command{
	static double _time;
	CmdSetYellowTime(double time) {
		_time = time;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setYellowTime(_time);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

}
