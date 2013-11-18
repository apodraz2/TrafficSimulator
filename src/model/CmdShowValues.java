package model;

import Command.Command;

public class CmdShowValues implements Command{

	CmdShowValues() {}
	@Override
	public boolean run() {
		try {
			MP.output();
			return true;
		} catch (IllegalArgumentException e) { return false; }
	}
	

}
