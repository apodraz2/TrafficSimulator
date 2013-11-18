package ui;

import util.AnimatorBuilder;
import main.Control;



public class Exited implements MachineState{
	Control _control;
	UIFormTest _inputTest;
	UI _ui;
	Start start;
	AnimatorBuilder _ab;
	
	public Exited(AnimatorBuilder ab, Control control, UI ui) {
		_control = control;
		_ui = ui;
		_ab = ab;
		_inputTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					return (input == "Y" || input == "N");
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		UIMenuBuilder m = new UIMenuBuilder();
		m.add("Default", new UIMenuAction(){
			public void run(){
				System.out.println("Please reenter.");
			}
		});
		m.add("Yes, I want to quit.", new UIMenuAction(){
			public void run() {
				System.exit(0);
			}
		});
		m.add("No, I don't want to quit.", new UIMenuAction(){
			public void run() {
				_control.setState(_control.start);
			}
		});
		
		UIMenu menu = m.toUIMenu("Are you sure?");
		_ui.processMenu(menu);
	}
	
	
	
}
