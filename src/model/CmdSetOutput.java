package model;

import Command.Command;

public class CmdSetOutput implements Command{
	String _output;
	
	CmdSetOutput(String output) {
		_output = output;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setAnimator(_output);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	

}
