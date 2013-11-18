package main;


import ui.*;
import util.AnimatorBuilder;

public class Control {
	private MachineState state;
	private UI _ui;
	private AnimatorBuilder _ab;
	
	public static MachineState start;
	public static MachineState exit;
	public static MachineState input;
	
	
	Control(UI ui) {
		_ui = ui;
		start = new Start(_ab, this, _ui);
		input = new Input(_ab, this, _ui);
		exit = new Exited(_ab, this, _ui);
		
		state = start;
		
	}
	
	void run() {
		
		try {
			while(true){
				//System.out.println("Inside the loop");
				state.run();
			}
		
			} catch (UIError e) {
				_ui.displayError("UI Closed");
			}
	}
	
	public void setState(MachineState newState) {
		state = newState;
	}

}
