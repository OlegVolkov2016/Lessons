package sudoku;

public class BoxCalculator implements FieldCalculator {
	
	// Box field calculator
	private static BoxCalculator instance = new BoxCalculator();
	
	private BoxCalculator() {
		// TODO Auto-generated constructor stub
	}
	
	public static BoxCalculator getInstance() {
		return instance;
	}

	// Calculate box values
	@Override
	public boolean calculateField(Field source, BooleanField result) {
		// TODO Auto-generated method stub
		int power = source.getPower();
		int size = (int) Math.pow(power,2);
		if (result.getField().length != size) return false;
		int value;
		BooleanField temp = new BooleanField(source.getPower());
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				value = source.getCell(((int) i / power) * power + j / power,(i * power) % size + j % power);
				if (value > 0)
					if (!temp.getCell(i,value-1))
						temp.setCell(i,value-1,true);
					else return false;
			}
		}
		result.setField(temp.getField());
		result.setCalculate(true);
		return true;
	}
	
}
