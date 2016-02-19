package sudoku;

public enum Constants {
	
	// Default power of field
	defaultPower(3),
	// Default power of shuffle
	shufflePower(2);
	
	private int value;
	
	private Constants(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
