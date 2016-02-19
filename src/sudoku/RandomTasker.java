package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class RandomTasker implements FieldTasker {

	// Random field generator
	private static RandomTasker instance = new RandomTasker();

	private RandomTasker() {
		// TODO Auto-generated constructor stub
	}

	public static RandomTasker getInstance() {
		return instance;
	}

	@Override
	public int generateTask(SudokuField sudokuField, int difficult) {
		// TODO Auto-generated method stub
		int currentDifficult;
		SudokuField tempField = new SudokuField(sudokuField);
		currentDifficult = generateNext(tempField,difficult);
		sudokuField.setField(tempField.getField());
		return currentDifficult;
	}

	private int generateNext(SudokuField sudokuField, int difficult) {
		int size = (int) Math.pow(sudokuField.getPower(),2);
		int currentDifficult;
		ArrayList<Integer> cells = new ArrayList<Integer>();
		currentDifficult = getCells(sudokuField,cells);
		if (currentDifficult == difficult) {
			return currentDifficult;
		}
		Random rand = new Random();
		Turn turn = new Turn();
		boolean isUnique;
		int cell, row, column, value;
		ArrayList<Integer> values;
		while (cells.size() > 0) {
			cell = rand.nextInt(cells.size());
			row = cells.get(cell) / size;
			column = cells.get(cell) % size;
			turn.setRow(row);
			turn.setColumn(column);
			value = sudokuField.getField().getCell(row,column);
			turn.setValue(value);
			sudokuField.unmakeTurn(turn);
			values = new ArrayList<Integer>();
			sudokuField.getChecker().getValues(sudokuField,turn,values);
			if (values.size() > 1) {
				isUnique = false;
				for (int j = 0; j < values.size(); j++) {
					SudokuField solveField = new SudokuField(sudokuField);
					turn.setValue(values.get(j));
					solveField.makeTurn(turn);
					if (solveField.getSolver().solveField(solveField))
						if (!isUnique) {
							isUnique = true;
						}
						else {
							isUnique = false;
							j = values.size();
						}
				}
			}
			else
				isUnique = true;
			if (isUnique) {
				return generateNext(sudokuField,difficult);
			}
			else {
				turn.setValue(value);
				sudokuField.makeTurn(turn);
				cells.remove(cell);
			}		
		}
		return currentDifficult;
	}
	
	private int getCells(SudokuField sudokuField, ArrayList<Integer> cells) {
		int size = (int) Math.pow(sudokuField.getPower(),2);
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (sudokuField.getField().getCell(i,j) > 0)
					cells.add(i*size+j);
		return cells.size();
	}
	
	// Getters and setters

}
