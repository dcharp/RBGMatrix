package view;

import Dwhiz.controller.MatrixController;
import Dwhiz.model.Matrix;
import Dwhiz.model.RgbLed;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;

import javax.swing.*;
import java.util.Random;


public class MatrixView extends JFrame{
	private double width;
	private double height;
	private double ledRadius;
	private MatrixController mController;  
	private RgbLed [] leds;
	private MatrixComponent mc;
	private final int RED,GREEN,BLUE;
	
	public MatrixView(MatrixController mc, double w, double h,double r){
		this.mController = mc;
		this.width = w;
		this.height = h;
		this.ledRadius = r;
		this.leds = mc.getLeds();
		this.RED = RgbLed.RED;
		this.BLUE = RgbLed.BLUE;
		this.GREEN = RgbLed.GREEN;
	}
	
	public Runnable startMatrixView(){
		return new Runnable() {
			@Override
			public void run() {
				contructLeds();
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
			}
		};
	}
	
	public void updateLed(RgbLed led, int [] rgbVals){
		led.setRgbVals(rgbVals[RED], rgbVals[GREEN], rgbVals[BLUE]);
		mc.repaint();
	}
	
	
	
	private void contructLeds(){
		int numLeds = mController.numberOfLeds();
		int rows = mController.numberOfRows();
		int cols = mController.numberOfColumns();
		double xCoord ,yCoord;
		Ellipse2D e;
		int tmp = 0;
		xCoord = yCoord = ledRadius ; 
		for(int i = 0; i < rows; i++ ){
			for(int j = 0; j < cols; j++){
				xCoord = xCoord + ledRadius;
				e = new Ellipse2D.Double();
				e.setFrame(xCoord, yCoord, ledRadius,ledRadius);
				leds[tmp++].myView = e;	
			}
			yCoord = yCoord + ledRadius;
			xCoord = ledRadius;
		}
		mc = new MatrixComponent();
		add(mc);
		pack();
	}
	

	@SuppressWarnings("serial")
	private class MatrixComponent extends JComponent{
		RgbLed [] Rgbleds; 
		public MatrixComponent(){
			addMouseListener(mController.getMouseHandler());
			this.Rgbleds = mController.getLeds();
		}
		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D) g;
			int i = 0;
			int [] vals;
			for (RgbLed led : leds){
				vals = Rgbleds[i++].getRgbVals();
				g2.setPaint(new Color(vals[RED],vals[GREEN], vals[BLUE]));
				g2.fill(led.myView);
			}
		}
		
		public Dimension getPreferredSize(){return new Dimension(1000,1000);};
		
	}// end of class MatrixComponent
	

	
}// end of class MatrixView



