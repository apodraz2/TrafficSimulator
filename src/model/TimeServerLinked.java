package model;
import java.util.*;

public final class TimeServerLinked extends Observable implements TimeServer {
	private static final class Node {
		public final double wakeTime;
		public final Agent agent;
		public Node next;
		
		public Node(double wakeTime, Agent agent, Node next){
			this.wakeTime = wakeTime;
			this.agent = agent;
			this.next = next;
		}
	}
	private double _currentTime;
	double _greenTime;
	private int _size;
	private Node _head;

	public TimeServerLinked() {
		_size = 0;
		_head = new Node(0, null, null);
		
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		Node node = _head.next;
		String sep = "";
		while (node != null){
			sb.append(sep).append("(").append(node.wakeTime).append(",").append(node.agent).append(")");
			node = node.next;
			sep = ";";
		}
		sb.append("]");
		return sb.toString();
	}
	
	public double currentTime() {
		return _currentTime;
	}
	
	public void enqueue(double wakeTime, Agent agent)
		throws IllegalArgumentException
		{
			if (wakeTime < _currentTime)
				throw new IllegalArgumentException();
			Node prevElement = _head;
			while ((prevElement.next != null) && (prevElement.next.wakeTime <= wakeTime)){
				prevElement = prevElement.next;
			}
			Node newElement = new Node(wakeTime, agent, prevElement.next);
			prevElement.next = newElement;
			_size++;
		}
	
	Agent dequeue() {
		if (_size < 1) 
			throw new NoSuchElementException();
		Agent rval = _head.next.agent;
		_head.next = _head.next.next;
		_size--;
		return rval;
	}
	
	int size(){
		return _size;
	}
	
	boolean isEmpty() {
		return _size == 0;
	}
	
	public void run(double duration) {
		double endtime = _currentTime + duration;
		while ((!isEmpty()) && (_head.next.wakeTime <= endtime)) {
			if ((_currentTime - _head.next.wakeTime) > 1e-09) {
				super.setChanged();
				super.notifyObservers();
			}
			_currentTime = _head.next.wakeTime;
			dequeue().run(duration);
		}
		_currentTime = endtime;
		_greenTime = endtime;
	}
	
	
}
