package model;

import Command.Command;

public class CommandList {
	static public Command newSetRowCommand(int rows) {
		return new CmdSetRows(rows);
	}
	
	static public Command newSetColumnCommand(int columns) {
		return new CmdSetColumns(columns);
	}
	
	static public Command newSetDurationCommand(double duration) {
		return new CmdSetDuration(duration);
	}
	
	static public Command newShowValuesCommand() {
		return new CmdShowValues();
	}
	
	static public Command newSetTimeStepCommand(double timeStep) {
		return new CmdSetTimeStep(timeStep);
	}
	
	static public Command newSetTrafficPatternCommand(String pattern) {
		return new CmdSetTrafficPattern(pattern);
	}
	
	static public Command newSetCarEntryRateCommand(double rate) {
		return new CmdSetCarEntryRate(rate);
	}
	
	static public Command newSetRoadLengthCommand(double length) {
		return new CmdSetRoadLength(length);
	}
	
	static public Command newSetIntersectionLengthCommand(double length) {
		return new CmdSetIntersectionLength(length);
	}
	
	static public Command newSetCarLengthCommand(double length) {
		return new CmdSetCarLength(length);
	}
	
	static public Command newSetMaxVelocityCommand(double velocity) {
		return new CmdSetMaxVelocity(velocity);
	}
	
	static public Command newSetStopDistanceCommand(double distance) {
		return new CmdSetStopDistance(distance);
	}
	
	static public Command newSetBrakeDistanceCommand(double distance) {
		return new CmdSetBrakeDistance(distance);
	}
	
	static public Command newSetGreenTimeCommand(double time) {
		return new CmdSetGreenTime(time);
	}
	
	static public Command newSetYellowTimeCommand(double time) {
		return new CmdSetYellowTime(time);
	}
	
	static public Command newSetOutputCommand(String output) {
		return new CmdSetOutput(output);
	}
}
