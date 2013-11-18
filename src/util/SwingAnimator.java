package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class SwingAnimator implements Animator {
	
	private double _delay;
	private JFrame _frame;
	private ContentPane _content;
	private boolean _disposed = false;
	
	public SwingAnimator (final SwingAnimatorPainter painter, final String name, final int width, final int height, int delay) {
		_delay = delay;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_content = new ContentPane(painter, width, height);
				_frame = new JFrame();
				_frame.setTitle(name);
				_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				_frame.setContentPane(_content);
				_frame.pack();
				_frame.setVisible(true);
			}});
				
			}
	public void dispose() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_frame.dispose();
				_disposed = true;
			}});
	}
	@Override
	public void update(final Observable model, Object ignored) {
		if(_disposed)
			throw new IllegalStateException();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_content.repaint();
			
		}});
		
		try {
			Thread.currentThread().sleep((long) _delay);
		} catch(InterruptedException e) {}
	}
	
	private static class ContentPane extends JPanel {
		private static final long serialVersionUID = 2008L;
		private int _width;
		private int _height;
		private SwingAnimatorPainter _painter;
		
		ContentPane(SwingAnimatorPainter painter, int width, int height) {
			_painter = painter;
			_width = width;
			_height = height;
			setPreferredSize(new Dimension(width, height));
			setDoubleBuffered(true);
			setOpaque(true);
			setBackground(Color.WHITE);
		}
		
		void setPainter(SwingAnimatorPainter painter) {
			_painter = painter;
		}
		
		public void paint(Graphics g) {
			if(_painter != null) {
				g.clearRect(0, 0, _width, _height);
				_painter.paint(g);
			}
		}
	}
}
