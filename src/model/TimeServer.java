package model;

public interface TimeServer {

	public double currentTime();
	public void enqueue(double wakeTime, Agent agent);
	public void run(double duration);
	public void addObserver(java.util.Observer o);
	public void deleteObserver(java.util.Observer o);
	
}
