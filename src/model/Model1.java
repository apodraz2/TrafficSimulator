package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import util.Animator;
import util.AnimatorBuilder;
import util.NullAnimatorBuilder;

public class Model1 extends Observable{
	
	private TimeServer _server;
	private Animator _animator;
	private boolean _disposed;
	private double _time;
	
	
	
	public Model1(AnimatorBuilder builder, int rows, int columns) {
		if(rows < 0 || columns < 0) 
			throw new IllegalArgumentException();
		
		if(builder == null)
			builder = new NullAnimatorBuilder();
		
		
		_server = new TimeServerLinked();
		_animator = builder.getAnimator();
		super.addObserver(_animator);
		setup(builder, rows, columns);
	}
	
	public void run(double duration) {
		if(_disposed) {
			throw new IllegalStateException();
		}
		
		
		super.setChanged();
		super.notifyObservers();
		for (double i = 0; i < duration; i+=MP.getTimeStep()) {
			_server.run(duration);
			_animator.update(this, duration);
		}
	}

	public void dispose() {
		_animator.dispose();
		_disposed = true;
	}
	
	public void setup(AnimatorBuilder builder, int rows, int columns) {
		
		if(MP.getGrid() == "Simple") {
			GridBuilder grid = new SimpleGrid(_server, rows, columns, builder);
			grid.getGrid();
		} else {
			GridBuilder grid = new AlternatingGrid(_server, rows, columns, builder);
			grid.getGrid();
		}
	}
}
