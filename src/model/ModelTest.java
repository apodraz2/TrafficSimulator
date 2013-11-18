package model;

import junit.framework.Assert;
import util.NullAnimatorBuilder;
import util.SwingAnimatorBuilder;

public class ModelTest {
	NullAnimatorBuilder a = null;
	SwingAnimatorBuilder b = new SwingAnimatorBuilder();
	
	private void NullTEST() {
		boolean exception = false;
		
		try {
			Model1 mod1 = new Model1(a, 1, 1);
		} catch (IllegalArgumentException e) { exception = true; }
		
		Assert.assertTrue(exception);
	}
	
	private void RowColTEST() {
		boolean exception = false;
		
		try { 
			Model1 mod2 =  new Model1(b, -1, -1);
		} catch (IllegalArgumentException e) { exception = true; }
		
		Assert.assertTrue(exception);
		
		exception = true;
	}
	
	public void test1() {
		NullTEST();
	}
	public void test2() {
		RowColTEST();
	}
}
