package main;

import java.util.Observable;

import ui.UI;
import util.Animator;
import util.AnimatorBuilder;
import util.SwingAnimatorBuilder;
import util.TextAnimatorBuilder;
import model.Agent;

import model.CarAcceptor;
import model.Light;

import model.Model1;
import model.Road;

/*
 * This is a Traffic Simulator
 */

public class Main {
	
	public static void main(String[] args){
		UI ui;
		ui = new ui.TextUI();
		Control control = new Control(ui);
		control.run();
	}
}
