package model;

import Command.Command;

public class CmdSetCarLength implements Command{
	static double _length;
	
	CmdSetCarLength(double length){
		_length = length;
	}
	
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setCarLength(_length);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}
}
