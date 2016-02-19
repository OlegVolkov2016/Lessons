package seabattle;

import java.util.Random;
import java.util.Scanner;

// Field for Sea Battle
public class SeaField {
	static Scanner sc = new  Scanner(System.in);
	private int size;
	private int[][] field;
	private int numShips = 10;
	// Size and maximum injures of ships 
	private int[] sizeShips = {4,3,3,2,2,2,1,1,1,1};
	private int[] injureShips = {4,3,3,2,2,2,1,1,1,1};
	// Count of shoots and injures
	private int shootcount = 0;
	private int hitcount = 0;

	SeaField() {
		// TODO Auto-generated constructor stub
		size = 0;
	}

	SeaField(int newsize) {
		// TODO Auto-generated constructor stub
		setSize(newsize);
	}
	
	@Override
	// To free scanner when finished
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		sc.close();
		super.finalize();
	}

	// Printing field
	public void printField() {
		for (int j = 0; j < getSize(); j++) {
			if (j < 9) System.out.print("  "+(j+1)+" ");
			else System.out.print("  "+(j+1)+"");
		}
		System.out.println(" ");
		for (int j = 0; j < getSize(); j++) {
			System.out.print("____");
		}
		System.out.println("_");
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				System.out.print("|_");
				// 0 is free
				if (getField()[i][j] == 0)
					System.out.print("__");
				// -1 is free but no ship 
				else if (getField()[i][j] == -1)
					System.out.print("-_");
				// -2 is miss shoot
				else if (getField()[i][j] == -2)
					System.out.print("0_");
				// -3... is hit shoot
				else if (getField()[i][j] < -2)
					System.out.print("X_");
				// 1... is ship
				else System.out.print("S_");
			}
			System.out.print("| ");
			if (i < 9) System.out.println(" "+(i+1));
			else System.out.println(""+(i+1));
		}
		/*for (int j = 0; j < getSize(); j++) {
			System.out.print("__");
		}
		System.out.println("_");*/
	}

	// Input coordinates of ship manually or random
	void fillField (boolean isRandom) {
		boolean isRepeat;
		int row, column, orientation;
		Random rand = new Random();
		for (int i = 0; i < getNumShips(); i++) {
			if (!isRandom) {
				System.out.println("Now you have this field:");
				printField();
				System.out.println("Set ship with size "+getSizeShips()[i]+":");
			}
			isRepeat = true;
			do {
				if (!isRandom) {
					System.out.print("Set start row: ");
					row = makeIntChoise(1,getSize())-1;
					System.out.print("Set start column: ");
					column = makeIntChoise(1,getSize())-1;
					if (getSizeShips()[i] > 1) {
						System.out.print("Set orientation (0-hor, 1-vert): ");
						orientation =  makeIntChoise(0,1);
					}
					else orientation = 0;
				}
				else {
					row = rand.nextInt(getSize());
					column = rand.nextInt(getSize());
					orientation = rand.nextInt(2);
				}
				if (setShip(i,row,column,orientation))
					isRepeat = false;
				else if (!isRandom)
					System.out.println("Wrong coordinates.");
			}
			while (isRepeat);
		}
		System.out.println("Result field:");
		printField();
	}

	// Checking of opportunity to set ship by coordinates by row or column
	boolean setShip (int index, int row, int column, int orientation) {
		boolean isSet = true;
		if (getField()[row][column] > 0)
			isSet = false;
		else if (orientation == 0)
			for (int i = 0; i < getSizeShips()[index]; i++)
				if ((column+i >= getSize()) || (isShipAround(row,column+i)))
					isSet = false;
				else;
		else if (orientation == 1)
			for (int i = 0; i < getSizeShips()[index]; i++)
				if ((row+i >= getSize()) || (isShipAround(row+i,column)))
					isSet = false;
				else;
		if (isSet)
			markShip(index,row,column,orientation);
		return isSet;
	}
	
	// Checking are other ships directly around of ship
	boolean isShipAround (int row, int column) {
		boolean isShip = false;
		if ((row > 0) && (getField()[row-1][column] > 0))
			isShip = true;
		else if ((row > 0) && (column < getSize()-1) && (getField()[row-1][column+1] > 0))
			isShip = true;
		else if ((column < getSize()-1) && (getField()[row][column+1] > 0))
			isShip = true;
		else  if ((row < getSize()-1) && (column < getSize()-1) && (getField()[row+1][column+1] > 0))
			isShip = true;
		else  if ((row < getSize()-1) && (getField()[row+1][column] > 0))
			isShip = true;
		else  if ((row < getSize()-1) && (column > 0) && (getField()[row+1][column-1] > 0))
			isShip = true;
		else  if ((column > 0) && (getField()[row][column-1] > 0))
			isShip = true;
		else  if ((row > 0) && (column > 0) && (getField()[row-1][column-1] > 0))
			isShip = true;
		return isShip;
	}
	
	// Setting the ship and mark field correctly
	void markShip (int index, int row, int column, int orientation) {
		if (orientation == 0) {
			for (int i = 0; i < getSizeShips()[index]; i++) 
				if (getField()[row][column+i] == 0)
					getField()[row][column+i] = index+1;
			if (row > 0)
				for (int i = -1; i < getSizeShips()[index]+1; i++)
					if ((column+i >= 0) && (column+i < getSize()))
						if (getField()[row-1][column+i] == 0)
							getField()[row-1][column+i] = -1;
			if (row < getSize()-1)
				for (int i = -1; i < getSizeShips()[index]+1; i++)
					if ((column+i >= 0) && (column+i < getSize()))
						if (getField()[row+1][column+i] == 0)
							getField()[row+1][column+i] = -1;
			if (column > 0)
				if (getField()[row][column-1] == 0)
					getField()[row][column-1] = -1;
			if (column+getSizeShips()[index] < getSize())
				if (getField()[row][column+getSizeShips()[index]] == 0)
					getField()[row][column+getSizeShips()[index]] = -1;
		}
		else if (orientation == 1) {
			for (int i = 0; i < getSizeShips()[index]; i++) 
				if (getField()[row+i][column] == 0)
					getField()[row+i][column] = index+1;
			if (column > 0)
				for (int i = -1; i < getSizeShips()[index]+1; i++)
					if ((row+i >= 0) && (row+i < getSize()))
						if (getField()[row+i][column-1] == 0)
							getField()[row+i][column-1] = -1;
			if (column < getSize()-1)
				for (int i = -1; i < getSizeShips()[index]+1; i++)
					if ((row+i >= 0) && (row+i < getSize()))
						if (getField()[row+i][column+1] == 0)
							getField()[row+i][column+1] = -1;
			if (row > 0)
				if (getField()[row-1][column] == 0)
					getField()[row-1][column] = -1;
			if (row+getSizeShips()[index] < getSize())
				if (getField()[row+getSizeShips()[index]][column] == 0)
					getField()[row+getSizeShips()[index]][column] = -1;
		}
	}
	
	// Return result of shoot
	int sendShoot (int row, int column) {
		int shoot = -1;
		setShootcount(getShootcount()+1);
		if (getField()[row][column] <= 0) {
			getField()[row][column] = -2;
			shoot = 0;
		}
		else if (getField()[row][column] > 0) {
			shoot = getField()[row][column];
			getField()[row][column]=-2-getField()[row][column];
			getInjureShips()[shoot-1]--;
			setHitcount(getHitcount()+1);
		}
		return shoot;
	}
	
	// Receive result of shoot
	void getShoot(int shoot, int row, int column) {
		boolean isMark;
		if (shoot == 0) {
			System.out.println("You're missed.");
			getField()[row][column] = -2;
		}
		else {
			setHitcount(getHitcount()+1);
			getField()[row][column] = -2-shoot;
			getInjureShips()[shoot-1]--;
			if (getInjureShips()[shoot-1] > 0)
				System.out.println("You're injure.");
			else {
				System.out.println("You're kill.");
				if (getSizeShips()[shoot-1] > 1) {
					// Definition of orientation and mark ship with sizze more than 1
					isMark = false;
					for (int i = 0; i < getSize(); i++) {
						for (int j = 0; j < getSize(); j++) {
							if (getField()[i][j] == -2-shoot)
								if ((j < getSize() -1) && (getField()[i][j+1] == -2-shoot)) {
									markShip(shoot-1,i,j,0);
									isMark = true;
									break;
								}
								else {
									markShip(shoot-1,i,j,1);
									isMark = true;
									break;
								}
						}
						if (isMark) break;
					}
				}
				// Mark ship with size 1
				else markShip(shoot-1,row,column,0);
			}
		}
		System.out.println("Result field:");
		printField();
	}
	
	// Input and making shoot
	boolean shootField (SeaField oppField, boolean isRandom) {
		boolean isRepeat, isMiss = true;
		int row, column, shoot;
		Random rand = new Random();
		isRepeat = true;
		do {
			if (!isRandom) {
				System.out.print("Set shoot row: ");
				row = makeIntChoise(1,getSize())-1;
				System.out.print("Set shoot column: ");
				column = makeIntChoise(1,getSize())-1;
			}
			else {
				row = rand.nextInt(getSize());
				column = rand.nextInt(getSize());
			}
			if (getField()[row][column] == 0) {
				setShootcount(getShootcount()+1);
				isRepeat = false;
				shoot = oppField.sendShoot(row,column);
				getShoot(shoot,row,column);
				if (shoot == 0) isMiss = true;
				else isMiss = false;
				}
			else if (!isRandom)
				System.out.println("Wrong coordinates.");
		}
		while (isRepeat);
		return isMiss;
	}
	
	// Simple methods of input confirmation and integer value in certain range
	boolean isRepeat(String question) {
		String answer;
		System.out.print(question);
		answer = sc.nextLine();
		if (answer.length() > 0)
			answer = answer.toLowerCase().substring(0, 1);
		else return false;
		if (answer.equals("y")) return true;
		else return false;
	}

	int makeIntChoise (int from, int to) {
		String enter;
		int choise = -1; 
		boolean isMake = false;
		do {
			enter = sc.nextLine();
			try {
				choise = Integer.parseInt(enter);
				if ((choise >= from) && (choise <= to))
					isMake = true;
				else System.out.println("Wrong value. Enter again.");
			}
			catch (NumberFormatException e) {
				System.out.println("Not a value. Enter again.");
			}
		}
		while (!isMake);
		return choise;
	}
	
	// Getters and setters
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
		this.field = new int[size][size];
	}
	
	public int[][] getField() {
		return field;
	}
	
	public void setField(int[][] field) {
		this.field = field;
	}
	
	public int getNumShips() {
		return numShips;
	}
	
	public void setNumShips(int numShips) {
		this.numShips = numShips;
	}
	
	public int[] getSizeShips() {
		return sizeShips;
	}
	
	public void setSizeShips(int[] sizeShips) {
		this.sizeShips = sizeShips;
	}
	
	public int[] getInjureShips() {
		return injureShips;
	}
	
	public void setInjureShips(int[] injureShips) {
		this.injureShips = injureShips;
	}
	
	public int getShootcount() {
		return shootcount;
	}
	
	public void setShootcount(int shootcount) {
		this.shootcount = shootcount;
	}
	
	public int getHitcount() {
		return hitcount;
	}
	
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}

	public static void main(String[] args) {
		boolean isRepeat = true, isRandom = false, isMiss = true;
		int size = 0;
		SeaField myField = new SeaField();
		SeaField oppField = new SeaField();
		// Input size of field and fill it
		do {
			System.out.print("Enter size of field: ");
			if (sc.hasNextInt())
				size = sc.nextInt();
			else {
				sc.nextLine();
				System.out.println("Wrong size");
				isRepeat = myField.isRepeat("Do you want to try again? (y - to repeat): ");
			}
			if (size > 0) {
				sc.nextLine();
				myField.setSize(size);
				//myField.printField();
				isRandom = myField.isRepeat("Do you want fill field random? (y - to random): ");
				myField.fillField(isRandom);
				oppField.setSize(size);
				// Battle with own field for testing...
				System.out.println("Start shooting...");
				do {
					isRandom = myField.isRepeat("Do you want shoot random? (y - to random): ");
					do {
						isMiss = oppField.shootField(myField,isRandom);
						if (!isMiss) {
							isRepeat = false;
							for (int i = 0; i < oppField.getNumShips(); i++)
								if (oppField.injureShips[i] > 0) {
									isRepeat = true;
									break;
								}
							if (!isRepeat) {
								System.out.println("Congratulations. You're win!");
								System.out.println("Shoots: "+oppField.getShootcount()+", hits: "+oppField.getHitcount()+".");
								isMiss = true;
							}
						}
					}
					while (!isMiss);
					if (isRepeat)
						isRepeat = myField.isRepeat("Do you want to try again? (y - to repeat): ");
				}
				while (isRepeat);
				isRepeat = false;
			}
		}
		while (isRepeat);
	}
}
