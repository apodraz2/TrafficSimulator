package model;

import Command.Command;

public class CmdSetRows implements Command{
	int _rows;
	
	CmdSetRows(int rows) {
		_rows = rows;
	}
	@Override
	public boolean run() {
		try{
			MP.setRows(_rows);
			return true;
		} catch (IllegalArgumentException e){
			return false;
		}
	}

}
