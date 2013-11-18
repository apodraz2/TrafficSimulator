package util;

import model.Light;
import model.Road;

public class NullAnimatorBuilder implements AnimatorBuilder{

	public Animator getAnimator() { return new NullAnimator(); }

	@Override
	public void addLight(Light d, int i, int j) {
		
		
	}

	@Override
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		
		
	}

	@Override
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		
		
	}
	
}