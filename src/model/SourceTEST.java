package model;

import util.NullAnimatorBuilder;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SourceTEST extends TestCase{
	TimeServer _server = new TimeServerLinked();
	Light l = new Light(_server, new NullAnimatorBuilder(), 0,0);
	RoadList rl = new RoadList();
	Road _r = new Road(_server, new NullAnimatorBuilder(), rl, 0, true, 0, 0, l);
	public SourceTEST(String name) {
		super(name);
	}
	
	public void testConstructor() {
		boolean exception = false;
		try{
			Source s = new Source(_server, _r);
			
		} catch (IllegalArgumentException e) { exception = true; }
		
		Assert.assertFalse(exception);
	}
	
	
	
	public void test1() {
		testConstructor();
	}
}
