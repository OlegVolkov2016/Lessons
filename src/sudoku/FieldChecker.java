package sudoku;

import java.util.ArrayList;

public interface FieldChecker {
	
	// Check new turn
	public boolean checkTurn(SudokuField sudokuField, Turn turn);
	// Check field
	public boolean checkField(Field field);
	// Get possible values
	public void getValues(SudokuField sudokuField, Turn turn, ArrayList<Integer> values);

}
