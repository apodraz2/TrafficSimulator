package ui;

import util.AnimatorBuilder;
import main.Control;
import model.CmdSetRows;
import model.CommandList;
import model.MP;
import model.Model1;

public class Input implements MachineState{
	Control _control;
	UI _ui;
	UIFormTest _inputTest;
	UIFormTest _durationTest;
	Start start;
	AnimatorBuilder _ab;
	
	
	public Input(AnimatorBuilder ab, Control control, UI ui){
		_control = control;
		_ui = ui;
		_ab = ab;
		_inputTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					int i = Integer.parseInt(input);
					return (i >= 0);
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
		_durationTest = new UIFormTest() {
			public boolean run(String input) {
				try {
					int i = Integer.parseInt(input);
					return (i >= 0);
				} catch (NumberFormatException e) {
					return false;
				}
			}
		};
		
	}
	
	String[] inputForm() {
		UIFormBuilder f = new UIFormBuilder();
		f.add("Please Enter New Value", _inputTest);
		String [] result = _ui.processForm(f.toUIForm(""));
		
		return result;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		UIMenuBuilder m = new UIMenuBuilder();
		
		m.add("Default", new UIMenuAction(){
			public void run() {
				System.out.println("No.");
			}
		});
		m.add("Show Current Values", new UIMenuAction(){
			public void run() {
				CommandList.newShowValuesCommand().run();
			}
		});
		m.add("Simulation Time Step", new UIMenuAction(){
			public void run() {
				String [] result = inputForm();
				CommandList.newSetTimeStepCommand(Integer.parseInt(result[0])).run();
			}
		});
		
		m.add("Simulation Run Time", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetDurationCommand(Integer.parseInt(result[0])).run();
			}
		});
		
		m.add("Grid Size", new UIMenuAction() {
			public void run() {
				UIFormBuilder f = new UIFormBuilder();
				f.add("Please Enter New Row Value", _inputTest);
				String [] result = _ui.processForm(f.toUIForm(""));
				CommandList.newSetRowCommand(Integer.parseInt(result[0])).run();
				
				UIFormBuilder f1 = new UIFormBuilder();
				f.add("Please Enter New Column Value", _inputTest);
				String [] result1 = _ui.processForm(f.toUIForm(""));
				CommandList.newSetColumnCommand(Integer.parseInt(result1[0])).run();
			}
		});
		
		m.add("Output Type", new UIMenuAction() {
			public void run() {
				UIMenuBuilder m = new UIMenuBuilder();
				m.add("default", new UIMenuAction() {
					public void run() {
						System.out.println("No.");
					}
				});
				m.add("Swing Animator", new UIMenuAction() {
					public void run() {
						CommandList.newSetOutputCommand("Swing").run();
					}
				});
				m.add("Text Animator", new UIMenuAction(){
					public void run() {
						CommandList.newSetOutputCommand("Text").run();
					}
				});
			}
		});
		
		m.add("Traffic Pattern", new UIMenuAction() {
			public void run() {
				UIMenuBuilder m = new UIMenuBuilder();
				m.add("default", new UIMenuAction(){
					public void run() {
						System.out.println("No.");
					}
				});
				m.add("Simple", new UIMenuAction(){
					public void run() {
						CommandList.newSetTrafficPatternCommand("Simple").run();
					}
				});
				m.add("Alternating", new UIMenuAction() {
					public void run() {
						CommandList.newSetTrafficPatternCommand("Alternating").run();
					}
				});
				_ui.processMenu(m.toUIMenu(""));
			}
		});
		m.add("Car Entry Rate", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetCarEntryRateCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Road Segment Length", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetRoadLengthCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Intersection Length", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetIntersectionLengthCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Car Length", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetCarLengthCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Car Maximum Velocity", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetMaxVelocityCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Car Stop Distance", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetStopDistanceCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Car Brake Distance", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetBrakeDistanceCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Traffic Light Green Time", new UIMenuAction(){
			public void run() {
				String [] result = inputForm();
				CommandList.newSetGreenTimeCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Traffic Light Yellow Time", new UIMenuAction() {
			public void run() {
				String [] result = inputForm();
				CommandList.newSetYellowTimeCommand(Integer.parseInt(result[0])).run();
			}
		});
		m.add("Reset Simulation and Return to Main Menu", new UIMenuAction(){
			public void run() {
				_control.setState(_control.start);
			}
		});
		UIMenu menu = m.toUIMenu("Please Choose Option");
		_ui.processMenu(menu);
	}

}
