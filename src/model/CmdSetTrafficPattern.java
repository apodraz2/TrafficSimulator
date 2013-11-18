package model;

import Command.Command;

public class CmdSetTrafficPattern implements Command{
	String _pattern;
	CmdSetTrafficPattern(String pattern) {
		_pattern = pattern;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setTrafficPattern(_pattern);
			return true;
		} catch (IllegalArgumentException e) { return false; }
	}
}
