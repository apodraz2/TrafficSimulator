package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.Agent;
import model.Car1;
import model.Light;
import model.Light.LightColor;
import model.Road;


public class TextAnimatorBuilder implements AnimatorBuilder {
	TextAnimator _animator;
	public TextAnimatorBuilder() {
		_animator = new TextAnimator();
	}
	
	
	@Override
	public Animator getAnimator() {
		if (_animator == null) { throw new IllegalStateException (); }
		Animator returnValue = _animator;
		//_animator = null;
		return returnValue;
	}

	@Override
	public void addLight(Light d, int i, int j) {
		_animator.addLight(d, i, j);
	}

	@Override
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		_animator.addRoad(l, i, j);
	}

	@Override
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		_animator.addRoad(l, i, j);
	}
	
	private static class TextAnimator implements Animator {
		
	private static class Element<T> {
		T x;
		int i;
		int j;
		
		Element(T x, int i, int j){
			this.x = x;
			this.i = i;
			this.j = j;
		}
	}

	private List<Element<Road>> _roadElements;
	private List<Element<Light>> _lightElements;
	
	TextAnimator() {
		_roadElements = new ArrayList<Element<Road>>();
		_lightElements = new ArrayList<Element<Light>>();
	}
	
	void addLight(Light x, int i, int j) {
		_lightElements.add(new Element<Light>(x,i,j));
	}
	
	void addRoad(Road x, int i, int j) {
		_roadElements.add(new Element<Road>(x,i,j));
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		for(Element<Light> e : _lightElements){
			System.out.print("Light at (" + e.i + "," + e.j + " ): ");
			if(e.x.getLightColor() == LightColor.GREEN) {
				System.out.println("Green");
			}else if (e.x.getLightColor() == LightColor.RED)
				System.out.println("Red");
			else 
				System.out.println("Yellow");
		}
		for(Element<Road> e : _roadElements) {
			for(Agent d : e.x.getCars()) {
				System.out.print("Road at (" + e.i + "," + e.j + "): ");
				System.out.println("Car at (" + ((Car1) d).getPosition() + "): ");
			}
		}
	}
	
	@Override
	public void dispose() {
		
	}
	}
}
