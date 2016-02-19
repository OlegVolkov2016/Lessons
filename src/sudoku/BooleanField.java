package sudoku;

public class BooleanField {
	
	// Service field
	private int power;
	private boolean[][] field;
	private FieldPrinter printer;
	private FieldCalculator calculator;
	private boolean isCalculate;
	

	public BooleanField() {
		// TODO Auto-generated constructor stub
		this(Constants.defaultPower.getValue());
	}

	public BooleanField(int power) {
		this.power = power;
		this.field = new boolean[(int) Math.pow(getPower(),2)][(int) Math.pow(getPower(),2)];
	}
	
	// Getters and setters
	public int getPower() {
		return power;
	}
	
	@SuppressWarnings("unused")
	private void setPower(int power) {
		this.power = power;
	}
	
	public boolean[][] getField() {
		return field;
	}
	
	public void setField(boolean[][] field) {
		this.field = field;
	}
	
	public boolean getCell(int row, int column) {
		if ((row >= 0) && (row < this.field.length) && (column >= 0) && (column < this.field.length))
			return this.field[row][column];
		else return false;
	}
	
	public void setCell(int row, int column, boolean value) {
		if ((row >= 0) && (row < this.field.length) && (column >= 0) && (column < this.field.length))
			this.field[row][column] = value;
	}
	
	public FieldPrinter getPrinter() {
		return printer;
	}
	
	public void setPrinter(FieldPrinter printer) {
		this.printer = printer;
	}
	
	public FieldCalculator getCalculator() {
		return calculator;
	}
	
	public void setCalculator(FieldCalculator calculator) {
		this.calculator = calculator;
	}
	
	public boolean getCalculate() {
		return isCalculate;
	}
	
	public void setCalculate(boolean isCalculate) {
		this.isCalculate = isCalculate;
	}

}
