package model;

import Command.Command;

public class CmdSetCarEntryRate implements Command{
	static double _rate;
	CmdSetCarEntryRate(double rate){
		_rate = rate;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setCarEntryRate(_rate);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}
	
	
}
