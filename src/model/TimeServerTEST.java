package model;
import java.util.Observable;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TimeServerTEST extends TestCase {
	TimeServerLinked L = new TimeServerLinked();
	
	public TimeServerTEST(String name){
		super(name);
	}
	
	public void testEmptySize() {
		Assert.assertEquals(L.size(), 0);
	}
	
	public void testDequeueOnEmpty() {
		boolean exceptionOccurred = false;
		
		try {
			Agent o = L.dequeue();
		} catch (java.util.NoSuchElementException e){
			exceptionOccurred = true;
		}
		
		Assert.assertTrue(exceptionOccurred);
	}
	
	public void testEnqueueFollowedByDequeue() {
		class Test implements Agent {
			public boolean run(double time){
				return true;
			}

			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setCurrentRoad(Road road) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setPosition(float position) {
				// TODO Auto-generated method stub
				
			}
		}
		
		Agent x1 = new Test();
		L.enqueue(0, x1);
		
		Assert.assertSame(x1, L.dequeue());
		Assert.assertEquals(0, L.size());
	}
	
	public void testElementOrder() {
		class Test implements Agent {
			public boolean run(double time) {return true;}

			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setCurrentRoad(Road road) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setPosition(float position) {
				// TODO Auto-generated method stub
				
			}
		}
		
		Agent x1 = new Test();
		Agent x2 = new Test();
		
		L.enqueue(0, x2);
		L.enqueue(1, x1);
		
		Assert.assertSame(x2, L.dequeue());
		Assert.assertSame(x1, L.dequeue());
		
		L.enqueue(1, x1);
		L.enqueue(0, x2);
		
		Assert.assertSame(x2, L.dequeue());
		Assert.assertSame(x1, L.dequeue());
		
		L.enqueue(0, x1);
		L.enqueue(0, x2);
		
		Assert.assertSame(x1, L.dequeue());
		Assert.assertSame(x2, L.dequeue());
		
		L.enqueue(0, x2);
		L.enqueue(0, x1);
		
		Assert.assertSame(x2, L.dequeue());
		Assert.assertSame(x1, L.dequeue());
	}
	
	public void testToString() {
		class TestString implements Agent {
			public boolean run(double time) {return true;}
			public String toString() { return "x"; }
			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setCurrentRoad(Road road) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setPosition(float position) {
				// TODO Auto-generated method stub
				
			}
		}
		
		L.enqueue(0, new TestString());
		L.enqueue(1, new TestString());
		Assert.assertEquals("[(0.0,x);(1.0,x)]", L.toString());
	}
	
	public void TestCurrentTime() {
		class TestTime implements Agent {
			public boolean run(double time) { return true; }

			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setCurrentRoad(Road road) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setPosition(float position) {
				// TODO Auto-generated method stub
				
			}
		}
		
		double expected = 1230;
		L.enqueue(expected, new TestTime());
		
		Assert.assertEquals(0.0, L.currentTime());
		L.run(expected);
		
		Assert.assertEquals(expected, L.currentTime());
	}
	
	private double _scratch;
	public void testDoActionsAtOrBefore() {
		class TestActions implements Agent {
			private double _myScratch;
			TestActions(double myScratch) {
				_myScratch = myScratch;
			}
			public boolean run(double time) {
				_scratch = _myScratch;
				return true;
			}
			@Override
			public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setCurrentRoad(Road road) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void setPosition(float position) {
				// TODO Auto-generated method stub
				
			}
		}
		
		double time1 = 12;
		double time2 = 23;
		double value1 = 42;
		double value2 = 27;
		
		L.enqueue(time1, new TestActions(value1));
		
		_scratch = 0;
		L.run(time1 - 1);
		Assert.assertEquals(0.0, _scratch);
		
		_scratch = 0;
		L.run(1);
		Assert.assertEquals(value1, _scratch);
		
		L.enqueue(time2, new TestActions(value2));
		
		_scratch = 0;
		L.run(time2);
		Assert.assertEquals(value2, _scratch);
		
		
	}
}

