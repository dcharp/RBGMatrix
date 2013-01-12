package Dwhiz.model;

import java.awt.geom.Ellipse2D;

public class RgbLed {
	private int id;
	private int row;
	private int column;
	
	private boolean on;
	private int[] rgbVals;
	
	public final static int RED = 0;
	public final static int GREEN= 1;
	public final static int BLUE = 2;
	
	public Ellipse2D myView; 
	
	public RgbLed(int id){
		this.id = id;
		this.rgbVals = new int[3];
		this.on = false;
	}
	
	public void setRgbVals(int r, int g, int b){
		rgbVals[RED] = r;
		rgbVals[GREEN] = g;
		rgbVals[BLUE] = b;
	}
	public int [] getRgbVals(){
		return rgbVals;
	}
	public int getId(){
		return id;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getCol(){
		return column;
	}
	
}
