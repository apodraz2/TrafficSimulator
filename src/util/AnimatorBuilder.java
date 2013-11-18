package util;
import java.util.Observable;
import model.Road;
import model.Light;
import util.Animator;
public interface AnimatorBuilder {
	
	public Animator getAnimator();
	
	public void addLight(Light d, int i, int j);
	
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest);
	
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth);
	
	
}

class NullAnimator implements Animator{

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}

