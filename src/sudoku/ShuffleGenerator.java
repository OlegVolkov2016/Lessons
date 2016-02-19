package sudoku;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class ShuffleGenerator implements FieldGenerator {
	
	// Shuffle field generator
	private static ShuffleGenerator instance = new ShuffleGenerator();
	private Method[] methods;

	private ShuffleGenerator() {
		// TODO Auto-generated constructor stub
		methods = new Method[5];
		try {
			methods[0] = this.getClass().getMethod("transpField",SudokuField.class);
			methods[1] = this.getClass().getMethod("swapRow",SudokuField.class);
			methods[2] = this.getClass().getMethod("swapColumn",SudokuField.class);
			methods[3] = this.getClass().getMethod("swapRows",SudokuField.class);
			methods[4] = this.getClass().getMethod("swapColumns",SudokuField.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ShuffleGenerator getInstance() {
		return instance;
	}

	@Override
	public boolean generateField(SudokuField sudokuField) {
		// TODO Auto-generated method stub
		SudokuField tempField = new SudokuField(sudokuField);
		initField(tempField);
		shuffleField(tempField);
		sudokuField.setField(tempField.getField());
		return true;
	}
	
	// First initialization
	private void initField(SudokuField sudokuField) {
		int power = sudokuField.getPower();
		int size = (int) Math.pow(sudokuField.getPower(),2);
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				sudokuField.getField().setCell(i,j,(i*power+i/power+j)%size+1);
	}
	
	private void shuffleField(SudokuField sudokuField) {
		int size = (int) Math.pow(sudokuField.getPower(),Constants.shufflePower.getValue());
		Random rand = new Random();
		try {
			for (int i = 0; i < size; i++) {
				this.getMethods()[rand.nextInt(this.methods.length)].invoke(this,sudokuField);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Transponent Field
	public void transpField(SudokuField sudokuField) {
		int size = (int) Math.pow(sudokuField.getPower(),2);
		int value;
		for (int i = 0; i < size-1; i++)
			for (int j = i+1; j < size; j++) {
				value = sudokuField.getField().getCell(i,j);
				sudokuField.getField().setCell(i,j,sudokuField.getField().getCell(j,i));
				sudokuField.getField().setCell(j,i,value);
			}
	}
	
	// Swap rows
	public void swapRow(SudokuField sudokuField) {
		int power = sudokuField.getPower();
		int size = (int) Math.pow(sudokuField.getPower(),2);
		int value;
		Random rand = new Random();
		int box, row1, row2;
		box = rand.nextInt(power);
		row1 = rand.nextInt(power);
		do {
			row2 = rand.nextInt(power);
		}
		while (row2 == row1);
		for (int j = 0; j < size; j++) {
			value = sudokuField.getField().getCell(box*power+row1,j);
			sudokuField.getField().setCell(box*power+row1,j,sudokuField.getField().getCell(box*power+row2,j));
			sudokuField.getField().setCell(box*power+row2,j,value);
		}
	}
	
	// Swap columns
	public void swapColumn(SudokuField sudokuField) {
		transpField(sudokuField);
		swapRow(sudokuField);
		transpField(sudokuField);
	}
	
	// Swap row areas
	public void swapRows(SudokuField sudokuField) {
		int power = sudokuField.getPower();
		int size = (int) Math.pow(sudokuField.getPower(),2);
		int value;
		Random rand = new Random();
		int box1, box2;
		box1 = rand.nextInt(power);
		do {
			box2 = rand.nextInt(power);
		}
		while (box2 == box1);
		for (int i = 0; i < power; i++) 
			for (int j = 0; j < size; j++) {
				value = sudokuField.getField().getCell(box1*power+i,j);
				sudokuField.getField().setCell(box1*power+i,j,sudokuField.getField().getCell(box2*power+i,j));
				sudokuField.getField().setCell(box2*power+i,j,value);
			}
	}
	
	// Swap column areas
	public void swapColumns(SudokuField sudokuField) {
		transpField(sudokuField);
		swapRows(sudokuField);
		transpField(sudokuField);
	}
	
	// Getters and setters
	private Method[] getMethods() {
		return methods;
	}
	
	@SuppressWarnings("unused")
	private void setMethods(Method[] methods) {
		this.methods = methods;
	}

}
