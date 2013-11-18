package ui;

import util.AnimatorBuilder;
import util.SwingAnimatorBuilder;
import util.TextAnimatorBuilder;
import main.Control;
import model.MP;
import model.Model1;


public class Start implements MachineState {
	Control _control;
	UIFormTest _inputTest;
	UI _ui;
	Exited exit;
	Input input;
	AnimatorBuilder _ab;
	public Start(AnimatorBuilder ab, Control control, UI ui) {
		_control = control;
		_ui = ui;
		_ab = ab;
		_inputTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					int i = Integer.parseInt(input);
					return ( i >= 0 && i < 2);
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
	}

	@Override
	public void run() {
		//System.out.println("Start run method called");
		// TODO Auto-generated method stub
		UIMenuBuilder m = new UIMenuBuilder();
		m.add("Default", new UIMenuAction() {
			public void run() {
				System.out.println("No.");
			}
		});
		m.add("Run Simulation", new UIMenuAction(){
			public void run(){
				Model1 model = new Model1(MP.getAnimator(), MP.getRows(), MP.getColumns());
				model.run(MP.getDuration());
				model.dispose();
				_control.setState(_control.start);
			}
		});
		m.add("Change Simulation Parameters", new UIMenuAction() {
			public void run(){
				
				_control.setState(_control.input);
			}
		});
		m.add("Exit", new UIMenuAction(){
			public void run() {
				_control.setState(_control.exit);
			}
		});
		UIMenu menu = m.toUIMenu("Welcome");
		_ui.processMenu(menu);
	}


}
