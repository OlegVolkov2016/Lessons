package menu;

import scanner.SimpleScanner;

public class SimpleMenu implements ChoiseMenu {

	private static SimpleMenu instance = new SimpleMenu();
	
	private SimpleMenu() {
		// TODO Auto-generated constructor stub
	}

	public static SimpleMenu getInstance() {
		return instance;
	}
	
	@Override
	public int choiseMenu(String menu, int from, int to) {
		// TODO Auto-generated method stub
		int choise;
		do {
			System.out.print(menu);
			choise = SimpleScanner.getInstance().makeIntChoise(from,to);
		}
		while (choise < 0);
		return choise;
	}

}
