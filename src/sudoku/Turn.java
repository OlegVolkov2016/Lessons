package sudoku;

public class Turn {
	
	// Turn - cell and value
	private int row, column, value;
	
	public Turn() {
		// TODO Auto-generated constructor stub
	}
	
	public Turn(int row, int column, int value) {
		setTurn(row,column,value);
	}
	
	public void setTurn(int row, int column, int value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}

	// Getters and setters
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
}
