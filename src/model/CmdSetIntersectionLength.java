package model;

import Command.Command;

public class CmdSetIntersectionLength implements Command{
	static double _length;
	CmdSetIntersectionLength(double length) {
		_length = length;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setIntersectionLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

}
