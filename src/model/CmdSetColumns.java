package model;

import Command.Command;

public class CmdSetColumns implements Command{
	int _columns;
	
	CmdSetColumns(int columns) {
		_columns = columns;
	}
	@Override
	public boolean run() {
		// TODO Auto-generated method stub
		try {
			MP.setColumns(_columns);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

}
