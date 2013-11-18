package model;

import Command.Command;

public class CmdSetRoadLength implements Command{
	static double _length;

	CmdSetRoadLength(double length) {
		_length = length;
	}
	
	@Override
	public boolean run() {
		try {
			MP.setRoadLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}
}
