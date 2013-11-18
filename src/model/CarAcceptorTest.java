package model;

import java.util.List;

import util.AnimatorBuilder;
import util.NullAnimatorBuilder;

import junit.framework.*;
public class CarAcceptorTest extends TestCase {
	boolean eastToWest;
	boolean northToSouth;
	
	TimeServer _server = new TimeServerLinked();
	
	public CarAcceptorTest(String name){
		super(name);
	}
	
	private void RoadAcceptorTEST() {
		boolean exception = false;
		AnimatorBuilder builder = new NullAnimatorBuilder();
		RoadList rl = new RoadList();
		Light l = new Light(_server, builder, 0, 0);
		Road road1 = new Road(_server, builder, rl, 0, eastToWest, 0, 0, l);
		Car1 car1 = new Car1(_server, road1);
		
		try {
			road1.accept(car1, (float) 0);
		} catch(IllegalArgumentException e){
			exception = true;
		}
		
		Assert.assertFalse(exception);
	}
	
	private void GetCarsTEST() {
		RoadList rl = new RoadList();
		AnimatorBuilder builder = new NullAnimatorBuilder();
		Light l = new Light(_server, builder, 0, 0);
		Road road2 = new Road(_server, builder, rl,0,eastToWest,0,0, l);
		Car1 car2 = new Car1(_server, road2);
		Car1 car3 = new Car1(_server, road2);
		
		road2.accept(car2, 0);
		road2.accept(car3, 0);
		List<Agent> cars = road2.getCars();
		
		Assert.assertSame(car2, cars.get(0));
		Assert.assertSame(car3, cars.get(1));
		
	}
	
	private void RoadListTEST() {
		RoadList rl = new RoadList();
		AnimatorBuilder builder = new NullAnimatorBuilder();
		Light l = new Light(_server, builder, 0, 0);
		Road road3 = new Road(_server, builder, rl, rl.size(), eastToWest, 0,0, l);
		rl.addRoad(road3);
		Assert.assertEquals(road3.getPosition(), rl.size()-1);
		Road road4 = new Road(_server, builder, rl, rl.size(), eastToWest, 0, 0, l);
		rl.addRoad(road4);
		Assert.assertEquals(road4.getPosition(), rl.size() - 1);
		//rl.setNextRoads();
		
		Assert.assertEquals(road3.getNextRoad(), road4);
		Road road5 = new Road(_server, builder, rl, rl.size(), eastToWest, 0,0, l);
		rl.addRoad(road5);
		//rl.setNextRoads();
		
		Assert.assertEquals(road4.getNextRoad(), road5);
	}
	
	public void test1() {
		RoadAcceptorTEST();
	}
	
	public void test2() {
		GetCarsTEST();
	}
	
	public void test3() {
		RoadListTEST();
	}
	
}
