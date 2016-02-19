package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerator implements FieldGenerator {

	// Random field generator
	private static RandomGenerator instance = new RandomGenerator();

	private RandomGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static RandomGenerator getInstance() {
		return instance;
	}

	@Override
	public boolean generateField(SudokuField sudokuField) {
		// TODO Auto-generated method stub
		SudokuField tempField = new SudokuField(sudokuField);
		if (generateCell(tempField,0,0)) {
			sudokuField.setField(tempField.getField());
			return true;
		}
		else return false;
	}
	
	private boolean generateCell(SudokuField sudokuField, int row, int column) {
		int size = (int) Math.pow(sudokuField.getPower(),2);
		int value;
		if (sudokuField.getField().getCell(row,column) == 0) {
			Random rand = new Random();
			Turn turn;
			turn = new Turn(row,column,0);
			ArrayList<Integer> values = new ArrayList<Integer>();
			sudokuField.getChecker().getValues(sudokuField,turn,values);
			while (values.size() > 0) {
				value = values.get(rand.nextInt(values.size()));
				turn.setValue(value);
				sudokuField.makeTurn(turn);
				if (generateCell(sudokuField,row+(column+1)/size,(column+1)%size))
					return true;
				else {
					sudokuField.unmakeTurn(turn);
					values.remove(values.indexOf(value));
				}
			}
			return false;
		}
		else 
			if ((row+1)*(column+1) < Math.pow(size,2))
				return generateCell(sudokuField,row+(column+1)/size,(column+1)%size);
			else
				return sudokuField.getChecker().checkField(sudokuField.getField());
	}

}
