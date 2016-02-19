package sudoku;

import java.util.ArrayList;

public class SimpleChecker implements FieldChecker {
	
	// Simple field generator
	private static SimpleChecker instance = new SimpleChecker();
	
	private SimpleChecker() {
		// TODO Auto-generated constructor stub
	}
	
	public static SimpleChecker getInstance() {
		return instance;
	}

	@Override
	public boolean checkTurn(SudokuField sudokuField, Turn turn) {
		// TODO Auto-generated method stub
		int power = sudokuField.getPower();
		if (sudokuField.getRowValues().getField()[turn.getRow()][turn.getValue()-1]) return false;
		if (sudokuField.getColumnValues().getField()[turn.getColumn()][turn.getValue()-1]) return false;
		if (sudokuField.getBoxValues().getField()[(turn.getRow() / power * power) + (turn.getColumn() / power)][turn.getValue()-1]) return false;
		return true;
	}

	@Override
	public boolean checkField(Field field) {
		// TODO Auto-generated method stub
		int power = field.getPower();
		SudokuField tempField = new SudokuField(power);
		tempField.setField(field);
		if (!tempField.getRowValues().getCalculator().calculateField(tempField.getField(),tempField.getRowValues()))
			return false;
		if (!tempField.getColumnValues().getCalculator().calculateField(tempField.getField(),tempField.getColumnValues()))
			return false;
		if (!tempField.getBoxValues().getCalculator().calculateField(tempField.getField(),tempField.getBoxValues()))
			return false;
		return true;
	}

	@Override
	public void getValues(SudokuField sudokuField, Turn turn, ArrayList<Integer> values) {
		// TODO Auto-generated method stub
		int size = (int) Math.pow(sudokuField.getPower(),2);
		for (int i = 0; i < size; i++) {
			turn.setValue(i+1);
			if (sudokuField.getChecker().checkTurn(sudokuField,turn))
				values.add(i+1);
		}	
	}
	
}
