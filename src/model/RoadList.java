package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RoadList {
	
	/*
	 * RoadList is a container class for Roads. This class assists with gridbuilding and 
	 * nextRoad tracking.
	 */
	
	private final List<Road> roadList;
	
	
	RoadList() {
		roadList = new Stack<Road>();
	}
	
	public void addRoad(Road r) {
		roadList.add(r);
		for(Road road : roadList) {
			road.setNextRoad();
		}
	}
	
	public Road getRoad(int index) {
		return roadList.get(index);
	}
	
	public Iterable getIterable() {
		return roadList;
	}
	
	public int size() {
		return roadList.size();
	}
	
	public void removeRoad(int index) {
		roadList.remove(index);
	}
}
