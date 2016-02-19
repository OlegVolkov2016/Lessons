package sudoku;

import java.util.HashMap;
import java.util.LinkedList;

public class SudokuGamer implements FieldGamer {
	
	// Sudoku Gamer
	private static SudokuGamer instance = new SudokuGamer();
	// Input scanner
	private FieldScanner scanner;
	// Current username
	private String username;
	// User results
	private HashMap<String, Integer> results;
	// Current field
	private SudokuField sudokuField;
	// Current difficult
	private int difficult;
	// User turns
	private LinkedList<Turn> turns;
	
	private SudokuGamer() {
		// TODO Auto-generated constructor stub
		scanner = SimpleScanner.getInstance();
		username = "";
		results = new HashMap<String, Integer>();
		sudokuField = new SudokuField();
		sudokuField.generate();
		difficult = -1;
		turns = new LinkedList<Turn>();
		firstInit();
	}

	public static SudokuGamer getInstance() {
		return instance;
	}

	private void firstInit() {
		getResults().put("Oleg",10);
		getResults().put("Alex",5);
	}
	
	// Main menu
	@Override
	public boolean menu(String type) {
		// TODO Auto-generated method stub
		int choise;
//		setUsername("Oleg");
		if (getUsername().equals("")) {
			System.out.print("Choose section (1 - Login, 0 - Exit): ");
			choise = getScanner().makeIntChoise(0,1);
			if (choise == 0) return false;
			if (choise == 1) inputUser(false,false);
		}
		else {
			System.out.print(getUsername()+", choose section (1 - User Menu, 2 - Generate field, 3 - Generate task, 4 - Solve task, 0 - Exit): ");
			choise = getScanner().makeIntChoise(0,4);
			if (choise == 0) return !(getScanner().isRepeat("Are you sure to exit of the game? (y - to exit): "));
			if (choise == 1) while (userMenu());
			if (choise == 2) while (generatorMenu());
			if (choise == 3) while (taskerMenu());
			if (choise == 4) while (solverMenu());
		}
		return true;
	}
	
	// Sudoku user menu
	private boolean userMenu() {
		int choise;
		System.out.print(getUsername()+", choose section (1 - List of users, 2 - Change user, 3 - New user, 4 - Delete user, 0 - Exit): ");
		choise = getScanner().makeIntChoise(0,4);
		if (choise == 0) return false; 
		if (choise == 1) printResults();
		if (choise == 2) inputUser(false,false);
		if (choise == 3) inputUser(true,false);
		if (choise == 4) inputUser(false,true);
		return true;
	}
	
	// Input, change or delete user
	private void inputUser(boolean isNew, boolean isDelete) {
		boolean isMake = false;
		String username;
		do {
			System.out.print("Input user name: ");
			username = getScanner().makeNameChoise();
			if (username.equals(""))
				if (!isNew)
					isMake = true;
				else
					System.out.println("Please input any name...");
			else {
				isMake = true;
				if ((isNew) && (getResults().containsKey(username)))
					System.out.println("Name is already exist...");
				else {
					if (isDelete)
						if (getResults().containsKey(username)) {
							getResults().remove(username);
							if (getUsername().equals(username))
								setUsername("");
						}
						else System.out.println("No user found...");
					else {
						setUsername(username);
						if (!getResults().containsKey(username))
							getResults().put(username,0);
						System.out.println("Welcome "+getUsername()+", your result is "+getResults().get(getUsername()));
					}
				}
			}
		}
		while (!isMake);
	}
	
	// Print list of users
	private void printResults() {
		if (getResults() != null)
			getResults().forEach((username,value) -> System.out.println("User name: "+username+", result is "+value));
	}
	
	// Sudoku generator menu
	private boolean generatorMenu() {
		int choise;
		System.out.print(getUsername()+", choose type (1 - Random, 2 - Shuffle, 3 - Print field, 0 - Exit): ");
		choise = getScanner().makeIntChoise(0,3);
		if (choise == 0) return false; 
		if (choise == 3) printField();
		if (choise == 1) generateField(true);
		if (choise == 2) generateField(false);
		return true;
	}
	
	// Print current field
	private void printField() {
		if (getSudokuField() != null)
			getSudokuField().print();
		else
			System.out.println("Field is not generated.");		
	}
	
	// Generate sudoku field 
	private void generateField(boolean isRandom) {
		int power;
		System.out.print("Input power of field (from 2 to 5, 3 - default): ");
		power = getScanner().makeIntChoise(2,5);
		if (power == -1)
			power = 3;
		this.sudokuField = new SudokuField(power);
		if (isRandom)
			getSudokuField().setGenerator(RandomGenerator.getInstance());
		getSudokuField().generate();
		System.out.println("Field is generated.");		
	}
	
	// Sudoku tasker menu
	private boolean taskerMenu() {
		int choise;
		System.out.print(getUsername()+", choose type (1 - New task, 2 - From generated field, 3 - Print task, 0 - Exit): ");
		choise = getScanner().makeIntChoise(0,3);
		if (choise == 0) return false; 
		if (choise == 3) printTask();
		if (choise == 1) generateField(false);
		if ((choise == 1) || (choise == 2)) generateTask();
		return true;
	}
	
	// Print current task
	private void printTask() {
		if ((getSudokuField() != null) && (getDifficult() >= 0)) {
			getSudokuField().print();
			System.out.println("Difficult is "+getDifficult()+" cells.");
		}
		else
			System.out.println("Task is not generated.");		
	}
	
	// Generate sudoku task
	private void generateTask() {
		int difficult;
		if (getSudokuField() != null) {
			while (getTurns().size() > 0) {
				getSudokuField().unmakeTurn(getTurns().pollFirst());
				setDifficult(getDifficult()-1);
			}
			System.out.print("Input difficult (0 is maximum, up to "+((int) Math.pow(getSudokuField().getPower(),4)-1)+"): ");
			difficult = getScanner().makeIntChoise(0,(int) Math.pow(getSudokuField().getPower(),4)-1);
			if (difficult == -1)
				difficult = 0;
			setDifficult(getSudokuField().task(difficult));
			printTask();
		}
		else
			System.out.println("Field is not generated.");
	}
	
	// Sudoku solver menu
	private boolean solverMenu() {
		if ((getSudokuField() != null) && (getDifficult() >= 0)) {
			int choise;
			System.out.print(getUsername()+", choose type (1 - Authomatic, 2 - Next turn, 3 - Undo turn, 4 - Print field, 0 - Exit): ");
			choise = getScanner().makeIntChoise(0,4);
			if (choise == 0) return false; 
			if (choise == 4) printTask();
			if (choise == 1) solveTask();
			if (choise == 2) makeTurn();
			if (choise == 3) undoTurn();
		}
		else {
			System.out.println("Task is not generated.");
			return false;
		}
		return true;
	}
	
	// Solve sudoku task
	private void solveTask() {
		if (getSudokuField().solve()) {
			printField();
			System.out.println("Task is solved.");
			setDifficult((int) Math.pow(getSudokuField().getPower(),4)); 
		}
		else
			System.out.println("No solution.");
	}
	
	// Make next turn
	private void makeTurn() {
		int size = (int) Math.pow(getSudokuField().getPower(),2);
		if (getDifficult() == (int) Math.pow(size,2)) {
			System.out.println("Task is already solved.");
		}
		else {
			System.out.println("Make turn no. "+(getTurns().size()+1)+": ");
			Turn turn = inputTurn();
			if (turn != null) {
				if (!checkTurn(turn))
					System.out.println("Turn is not available.");
				else {
					SudokuField solveField = new SudokuField(sudokuField);
					if (!solveField.getSolver().solveField(solveField))
						System.out.println("Warning! No solution!");
				}
			}
			else
				System.out.println("Turn is not maked.");
		}
	}
	
	// Input turn values
	private Turn inputTurn() {
		int size = (int) Math.pow(getSudokuField().getPower(),2);
		int row, column, value;
		System.out.print("Input row (from 1 to "+size+"): ");
		row = getScanner().makeIntChoise(1,size);
		if (row > 0) {
			System.out.print("Input column (from 1 to "+size+"): ");
			column = getScanner().makeIntChoise(1,size);
			if (column > 0) {
				System.out.print("Input value (from 1 to "+size+"): ");
				value = getScanner().makeIntChoise(1,size);
				if (value > 0) {
					return new Turn(row-1,column-1,value);
				}
			}
		}
		return null;
	}
	
	// Check current turn
	private boolean checkTurn(Turn turn) {
		int size = (int) Math.pow(getSudokuField().getPower(),2);
		if ((getSudokuField().getField().getCell(turn.getRow(),turn.getColumn()) == 0) && (getSudokuField().getChecker().checkTurn(getSudokuField(),turn))) {
			getSudokuField().makeTurn(turn);
			getTurns().addFirst(turn);
			getSudokuField().print();
			setDifficult(getDifficult()+1);
			if (getDifficult() == (int) Math.pow(size,2)) {
				System.out.println("Congratulations. Task is solved!");
				getResults().put(getUsername(),getResults().get(getUsername())+1);
			}
			return true;
		}
		else
			return false;
	}
	
	// Undo last turn
	private void undoTurn() {
		Turn turn;
		if (getTurns().size() > 0) {
			turn = getTurns().pollFirst();
			System.out.println("Undo turn no. "+(getTurns().size()+1)+": ("+(turn.getRow()+1)+", "+(turn.getColumn()+1)+") -> "+ turn.getValue());
			getSudokuField().unmakeTurn(turn);
			setDifficult(getDifficult()-1);
			printField();
		}
		else
			System.out.println("No turns to undo.");
	}
	
	// Getters and setters
	private FieldScanner getScanner() {
		return scanner;
	}
	
	@SuppressWarnings("unused")
	private void setScanner(FieldScanner scanner) {
		this.scanner = scanner;
	}
	
	private String getUsername() {
		return username;
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	private HashMap<String, Integer> getResults() {
		return results;
	}
	
	@SuppressWarnings("unused")
	private void setResults(HashMap<String, Integer> results) {
		this.results = results;
	}
	
	private SudokuField getSudokuField() {
		return sudokuField;
	}
	
	@SuppressWarnings("unused")
	private void setSudokuField(SudokuField sudokuField) {
		this.sudokuField = sudokuField;
	}
	
	private int getDifficult() {
		return difficult;
	}
	
	private void setDifficult(int difficult) {
		this.difficult = difficult;
	}
	
	private LinkedList<Turn> getTurns() {
		return turns;
	}
	
	@SuppressWarnings("unused")
	private void setTurns(LinkedList<Turn> turns) {
		this.turns = turns;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to sudoku game!");
		while (SudokuGamer.getInstance().menu("sudoku"));
	}
	
}
