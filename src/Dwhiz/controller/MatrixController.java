package Dwhiz.controller;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import javax.swing.plaf.SliderUI;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import Dwhiz.model.Matrix;
import Dwhiz.model.RgbLed;
import view.MatrixView;

public class MatrixController {
	private MatrixView view;
	private Matrix m;
	private MouseHandler mouseHandler;
	
	public MatrixController(){
		m = new Matrix(9, 12);
		view = new MatrixView(this, 1000, 1000, 30);
		mouseHandler = new MouseHandler();
	}
	
	public int numberOfRows(){
		return m.getNumRows();
	}
	
	public int numberOfColumns(){
		return m.getNumColumns();
	}
	
	public int numberOfLeds(){
		return m.getNumLeds();
	}
	
	public RgbLed[] getLeds(){
		return m.getLeds();
	}
	
	public Matrix getMatrixReference(){
		return m;
	}
	
	public RgbLed findClickedLed(Point2D p){
		for (RgbLed led : m.getLeds()){
			if(led.myView.contains(p))
				return led;
		}
		return null;
	}
	
	public MouseHandler getMouseHandler(){
		return mouseHandler;
	}
	
	public int[] generateRandomRgb(){
		Random r = new Random();
		int [] vals = {r.nextInt(256),r.nextInt(256),r.nextInt(256)};
		return vals;
	}
	public Runnable startView(){
		return view.startMatrixView();
	}
	
	public void debugMatrixDisplay() throws InterruptedException{
		while(true){
			for(RgbLed led : m.getLeds()){
				view.updateLed(led,generateRandomRgb());
				Thread.sleep(500);
			}
		}
	}
	
	public static void main(String args[]) throws InvocationTargetException, InterruptedException{
		MatrixController mc = new MatrixController();
		EventQueue.invokeAndWait(mc.view.startMatrixView());
		mc.debugMatrixDisplay();
	}
	
	private class MouseHandler extends MouseAdapter{
		public void mouseClicked(MouseEvent event){
			RgbLed led = findClickedLed(event.getPoint());
			if(led != null){
				view.updateLed(led,generateRandomRgb());
			}
		}
	}// end of class MouseHandler
}
