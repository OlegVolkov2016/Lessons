package sudoku;

public class Field {
	
	// Field of the game
	private int power;
	private int[][] field;
	private FieldPrinter printer;
	
	public Field() {
		// TODO Auto-generated constructor stub
		this(Constants.defaultPower.getValue());
	}
	
	public Field(int power) {
		this.power = power;
		this.field = new int[(int) Math.pow(getPower(),2)][(int) Math.pow(getPower(),2)];
	}
	
	//Getters and setters
	public int getPower() {
		return power;
	}
	
	@SuppressWarnings("unused")
	private void setPower(int power) {
		this.power = power;
	}
	
	public int[][] getField() {
		return field;
	}
	
	public void setField(int[][] field) {
		this.field = field;
	}
	
	public int getCell(int row, int column) {
		if ((row >= 0) && (row < this.field.length) && (column >= 0) && (column < this.field.length))
			return this.field[row][column];
		else return -1;
	}
	
	public void setCell(int row, int column, int value) {
		if ((row >= 0) && (row < this.field.length) && (column >= 0) && (column < this.field.length) && (value >= 0) && (value <= this.field.length))
			this.field[row][column] = value;
	}
	
	public FieldPrinter getPrinter() {
		return printer;
	}
	
	public void setPrinter(FieldPrinter printer) {
		this.printer = printer;
	}
	
}
