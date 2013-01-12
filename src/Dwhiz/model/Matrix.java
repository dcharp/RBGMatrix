package Dwhiz.model;

import java.util.Random;

public class Matrix {
	private int numRows;
	private int numColumns;
	private int numLeds;
	public final static int RED = 0;
	public final static int GREEN= 1;
	public final static int BLUE = 2;
	
	private RgbLed[] leds;
	
	
	public Matrix(int rows , int columns){
		this.setNumColumns(columns);
		this.setNumRows(rows);
		this.numLeds = rows * columns;
		this.leds = new RgbLed[numLeds];
		initLeds();
	}
	
	public void debugMatrix(){
		System.out.println("The blue color is: "  );
	}
	
	public RgbLed[]  getLeds(){
		return leds;
	}
	public int getNumLeds(){
		return numLeds;
	}
	

	public int getNumColumns() {
		return numColumns;
	}

	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	public int getNumRows() {
		return numRows;
	}

	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
	
	private void initLeds(){
		Random gen = new Random();
		for (int i =0; i < leds.length; i++){
			leds[i] = new RgbLed(i);
			leds[i].setRgbVals(gen.nextInt(255), gen.nextInt(255), gen.nextInt(255));
		}
	}
}
