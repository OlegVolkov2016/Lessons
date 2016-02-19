package sudoku;

import java.util.Scanner;

public class SimpleScanner implements FieldScanner {
	
	// Sudoku Gamer
	private static SimpleScanner instance = new SimpleScanner();
	private Scanner scanner;
	
	private SimpleScanner() {
		// TODO Auto-generated constructor stub
		this.scanner = new Scanner(System.in);
	}

	public static SimpleScanner getInstance() {
		return instance;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		scanner.close();
		super.finalize();
	}

	@Override
	public boolean isRepeat(String question) {
		// TODO Auto-generated method stub
		String answer;
		System.out.print(question);
		answer = this.getScanner().nextLine();
		answer = answer.toLowerCase().substring(0, 1);
		if (answer.equals("y")) return true;
		else return false;
	}

	@Override
	public int makeIntChoise(int from, int to) {
		// TODO Auto-generated method stub
		String enter;
		int choise = -1; 
		boolean isMake = false;
		do {
			enter = this.getScanner().nextLine();
			if (enter.equals("")) {
				choise = -1;
				isMake = true;
			}
			else {
				try {
					choise = Integer.parseInt(enter);
					if ((choise >= from) && (choise <= to))
						isMake = true;
					else System.out.print("Wrong value. Enter again: ");
				}
				catch (NumberFormatException e) {
					System.out.print("Not a value. Enter again: ");
				}
			}
		}
		while (!isMake);
		return choise;
	}

	@Override
	public String makeNameChoise() {
		// TODO Auto-generated method stub
		String enter;
		boolean isMake = false;
		do {
			enter = this.getScanner().nextLine();
			if (enter.equals("")) isMake = true;
			else if (enter.length() > 0) {
				isMake = true;
				for (int i = 0; i < enter.length(); i++) {
					if (((enter.toUpperCase().charAt(i) < 'A') || (enter.toUpperCase().charAt(i) > 'Z')) &&
							(enter.toUpperCase().charAt(i) != '.') && (enter.toUpperCase().charAt(i) != ' ')) {
						System.out.print("Wrong value. Enter again: ");
						isMake = false;
					}
				if (!isMake) break;
				}
			}
		}
		while (!isMake);
		return enter;
	}
	
	@Override
	public String makeStringChoise() {
		// TODO Auto-generated method stub
		String enter;
		boolean isMake = false;
		do {
			enter = this.getScanner().nextLine();
			isMake = true;
		}
		while (!isMake);
		return enter;
	}

	// Getters and setters
	
	private Scanner getScanner() {
		return scanner;
	}
	
	@SuppressWarnings("unused")
	private void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
