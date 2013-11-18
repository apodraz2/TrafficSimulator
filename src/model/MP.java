package model;

import util.AnimatorBuilder;
import util.SwingAnimatorBuilder;
import util.TextAnimatorBuilder;

public class MP {
	MP() {}
	private static double _carLength = 10;
	private static double _roadLength = 200;
	private static double _maxVelocity = 6;
	public static double getMaxVelocity() {
		return _maxVelocity;
	}
	private static double _velocity = (int) Math.ceil(Math.random() * _maxVelocity);
	
	private static double _duration = 1000;
	private static double _timeStep = .1;
	private static int _numRows = 2;
	private static int _numColumns = 3; 
	private static AnimatorBuilder _ab = new SwingAnimatorBuilder();
	private static double _intersectionLength = 15;
	private static String _trafficPattern = "Simple";
	private static double _carEntryRate = 50;
	private static double _stopDistance = 10;
	private static double _brakeDistance = 10;
	private static double _greenTime = 40;
	private static double _yellowTime = 10;
	private static double _lightController = _greenTime + _yellowTime;
	
	static String getGrid() {
		return _trafficPattern;	
	}
	
	static void setYellowTime(double time) {
		_yellowTime = time;
	}
	
	static double getYellowTime() {
		return _yellowTime;
	}
	
	static void setGreenTime(double time) {
		_greenTime = time;
	}
	
	static double getGreenTime() {
		return _greenTime;
	}
	
	static void setBrakeDistance(double distance) {
		_brakeDistance = distance;
	}
	
	public static double getBrakeDistance() {
		return _brakeDistance;
	}
	
	static void setStopDistance(double distance) {
		_stopDistance = distance;
	}
	
	public static double getStopDistance() {
		return _stopDistance;
	}
	
	static void setTrafficPattern(String string) {
		_trafficPattern = string;
	}
	
	static void setCarEntryRate(double rate) {
		_carEntryRate = rate;
	}
	
	public static double getCarEntryRate() {
		return _carEntryRate;
	}
	
	//Car Length getters and setters
	static void setCarLength(double carLength) {
		_carLength = carLength;
	}

	public static float getCarLength() {
		// TODO Auto-generated method stub
		return (float) _carLength;
	}
	
	//Road Length getters and setters
	static void setRoadLength(double roadLength){
		_roadLength = roadLength;
	}
	
	public static double getRoadLength() {
		return _roadLength;
	}
	
	//Velocity getters and setters
	static void setMaxVelocity(double velocity) {
		_maxVelocity = velocity;
	}
	
	public static double getVelocity() {
		return _velocity;
	}
	
	
	//Light timer getters and setters
	static void setLightTimer(double time) {
		_lightController = time;
	}
	
	public static double getLightTimer() {
		return _lightController;
	}
	
	//Duration getters and setters
	static void setDuration (double duration) {
		_duration = duration;
	}
	
	public static double getDuration () {
		return _duration;
	}
	
	//TimeStep getters and setters
	public static void setTimeStep(double timeStep) {
		_timeStep = timeStep;
	}
	
	public static double getTimeStep() {
		return _timeStep;
	}
	
	//Rows getters and setters
	static void setRows(int rows) {
		_numRows = rows;
	}
	
	public static int getRows() {
		return _numRows;
	}
	
	//Columns getters and setters
	static void setColumns(int columns) {
		_numColumns = columns;
	}
	
	public static int getColumns() {
		return _numColumns;
	}
	
	//Animator getters and setters
	public static void setAnimator(String s) {
		
		if (s == "Text") {
			_ab = new TextAnimatorBuilder();
		}
		else 
			_ab = new SwingAnimatorBuilder();
	}
	
	public static AnimatorBuilder getAnimator() {
		return _ab;
	}
	
	//Intersection length getters and setters
	public static void setIntersectionLength(double length) {
		_intersectionLength = length;
	}
	
	public static double getIntersectionLength() {
		return _intersectionLength;
	}
	
	static void output() {
		System.out.println("Simulation Time Step: [" + _timeStep + "]" );
		System.out.println("Simulation Run Time: [" + _duration + "]");
		System.out.println("Grid Size: [" + _numRows + "]" + "[" + _numColumns + "]");
		System.out.println("Traffic Pattern: [" + _trafficPattern + "]");
		System.out.println("Output Type: [" + _ab.getClass() + "]");
		System.out.println("Car Entry Rate: [" + _carEntryRate + "]");
		System.out.println("Intersection Length: [" + _intersectionLength + "]");
		System.out.println("Car Length: [" + _carLength + "]");
		System.out.println("Car Maximum Velocity: [" + _maxVelocity + "]");
		System.out.println("Car Stop Distance: [" + _stopDistance + "]");
		System.out.println("Car Brake Distance: [" + _brakeDistance + "]");
		System.out.println("Traffic Green Time: [" + _greenTime + "]");
		System.out.println("Traffic Yellow Time: [" + _yellowTime + "]");
		
	}
	
}
