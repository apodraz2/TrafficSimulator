package util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


import model.Car1;
import model.Light;
import model.Light.LightColor;
import model.MP;
import model.Road;


public class SwingAnimatorBuilder implements AnimatorBuilder {
	MyPainter _painter;
	private static double skipInit = VP.gap;
	private static double skipRoad = VP.gap + MP.getRoadLength();
	private static double skipCar = VP.gap + VP.elementWidth;
	private static double skipRoadCar = skipRoad + skipCar;
	
	public SwingAnimatorBuilder() {
		 _painter = new MyPainter();
	}
	public Animator getAnimator() {
		if(_painter == null) { throw new IllegalStateException(); }
		Animator returnValue = new SwingAnimator(_painter, "Traffic Simulator", VP.displayWidth, VP.displayHeight, VP.displayDelay);
		
		//_painter = null;
		return returnValue; 
	}
	
	public void addLight(Light d, int i, int j) {
		double x = skipInit + skipRoad + j*skipRoadCar;
		double y = skipInit + skipRoad + i*skipRoadCar;
		Translator t = new TranslatorWE(x, y, MP.getCarLength(), VP.elementWidth, VP.scaleFactor);
		_painter.addLight(d,t);
	}
	public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
		
		double x = skipInit + j*skipRoadCar;
		double y = skipInit + skipRoad + i*skipRoadCar;
		
		Translator t = eastToWest ? new TranslatorEW(x, y, MP.getRoadLength(), VP.elementWidth, VP.scaleFactor)
									: new TranslatorWE(x, y, MP.getRoadLength(), VP.elementWidth, VP.scaleFactor);
		_painter.addRoad(l,t);
	}
	public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
		double x = skipInit + skipRoad + j*skipRoadCar;
		double y = skipInit + i*skipRoadCar;
		Translator t = southToNorth ? new TranslatorSN(x, y, MP.getRoadLength(), VP.elementWidth, VP.scaleFactor)
									: new TranslatorSN(x, y, MP.getRoadLength(), VP.elementWidth, VP.scaleFactor);
		_painter.addRoad(l,t);
	}
	
	private static class MyPainter implements SwingAnimatorPainter {
		private static class Element<T> {
			T x;
			Translator t;
			Element(T x, Translator t) {
				this.x = x;
				this.t = t;
			}
		}
		
		private List<Element<Road>> _roadElements;
		private List<Element<Light>> _lightElements;
		MyPainter() {
			_roadElements = new ArrayList<Element<Road>>();
			_lightElements = new ArrayList<Element<Light>>();
		}
		void addLight(Light x, Translator t) {
			_lightElements.add(new Element<Light> (x,t));
		}
		void addRoad(Road x, Translator t) {
			_roadElements.add(new Element<Road>(x,t));
		}
		
		public void paint(Graphics g) {
			for (Element<Light> e : _lightElements) {
				if (e.x.getLightColor() == LightColor.GREEN) {
					g.setColor(Color.GREEN);
				} else if (e.x.getLightColor() == LightColor.RED) {
					g.setColor(Color.RED);
				} else
					g.setColor(Color.YELLOW);
				XGraphics.fillOval(g, e.t, 0, 0, MP.getCarLength(), VP.elementWidth);
			}
			g.setColor(Color.BLACK);
		
		for (Element<Road> e : _roadElements) {
			XGraphics.fillRect(g, e.t, 0, 0, MP.getRoadLength(), VP.elementWidth);
		}
		for (Element<Road> e : _roadElements) {
			for (Car1 d : e.x.getCars().toArray(new Car1[0])) {
				g.setColor(d.getColor());
				XGraphics.fillOval(g, e.t, d.getPosition(), 0, MP.getCarLength(), VP.elementWidth);
			}
		}
		}
	}
}

